import java.util.*;
import java.net.*;

/**
 *
 * @author Gastón
 */

//Esta clase se utiliza para hacer uso de los distintos algoritmos de
//sincronización de computadoras.
public class MainSincronizador {
    
    public static void main(String[] args) throws Exception{
        //Elementos del proceso.
        Scanner scanner = new Scanner(System.in);
        ArrayList<RegistroMaquina> listaDeMaquinas = new ArrayList<RegistroMaquina>();
        RegistroMaquina[] arrayDeMaquinas;
        String opcion = "";
        InetAddress direccionMaquina;
        int puertoMaquina;
        
        //Menu
        while(!opcion.equals("0")){
            System.out.println("1 - Agregar máquina");
            System.out.println("2 - Sincronizar con algoritmo de Lamport");
            System.out.println("3 - Sincronizar con algoritmo de Berkeley");
            System.out.println("4 - Sincronizar con algoritmo de Cristian");
            System.out.println("0 - Salir");        
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextLine();
            
            switch(opcion) {
                
                //Agrega un nuevo registro de máquina a la lista
                case "1":
                    //Pide dirección y puerto al usuario.
                    System.out.print("Dirección de la máquina: ");
                    direccionMaquina = InetAddress.getByName(scanner.nextLine());
                    System.out.print("Puerto de la máquina: ");
                    puertoMaquina = Integer.parseInt(scanner.nextLine());
                    
                    //Agrega el registro a la lista y avisa al usuario.
                    listaDeMaquinas.add(new RegistroMaquina(direccionMaquina, puertoMaquina));
                    System.out.println("La máquina ha sido agregada exitosamente.");
                    break;
                
                //Sincroniza las máquinas con el algoritmo de Lamport.
                case "2":
                    //El tiempo de envío del mensaje se carga manualmente.
                    System.out.print("Tiempo de envío: ");
                    int tiempoDeEnvio = Integer.parseInt(scanner.nextLine());
                    
                    //Transforma el ArrayList en un array para recorrerlo más
                    //fácilmente dentro del proceso.
                    arrayDeMaquinas = new RegistroMaquina[listaDeMaquinas.size()];
                    listaDeMaquinas.toArray(arrayDeMaquinas);
                    
                    //Sincroniza las máquinas y avisa al usuario que se logró
                    //correctamente.
                    AlgoritmoDeLamport.SincronizarMaquinas(arrayDeMaquinas, tiempoDeEnvio);
                    System.out.println("Las máquinas han sido sincronizadas exitosamente.");
                    break;
                
                //Sincroniza las máquinas con el algoritmo de Berkeley.
                case "3":
                    //Transforma el ArrayList en un array para recorrerlo más
                    //fácilmente dentro del proceso.
                    arrayDeMaquinas = new RegistroMaquina[listaDeMaquinas.size()];
                    listaDeMaquinas.toArray(arrayDeMaquinas);
                    
                    //Sincroniza las máquinas y avisa al usuario que se logró
                    //correctamente.
                    AlgoritmoDeBerkeley.SincronizarMaquinas(arrayDeMaquinas);
                    System.out.println("Las máquinas han sido sincronizadas exitosamente.");
                    break;
                    
                case "4":
                    //Pide dirección y del servidor de tiempo puerto al usuario.
                    System.out.print("Dirección de la máquina: ");
                    direccionMaquina = InetAddress.getByName(scanner.nextLine());
                    System.out.print("Puerto de la máquina: ");
                    puertoMaquina = Integer.parseInt(scanner.nextLine());
                    
                    //Transforma el ArrayList en un array para recorrerlo más
                    //fácilmente dentro del proceso.
                    arrayDeMaquinas = new RegistroMaquina[listaDeMaquinas.size()];
                    listaDeMaquinas.toArray(arrayDeMaquinas);
                    
                    //Sincroniza las máquinas y avisa al usuario que se logró
                    //correctamente.
                    AlgoritmoDeCristian.SincronizarMaquinas(arrayDeMaquinas, direccionMaquina, puertoMaquina);
                    System.out.println("Las máquinas han sido sincronizadas exitosamente.");
                    break;
                    
                case "0":
                    System.out.println("Hasta luego.");
                    break;
                    
                default:
                    System.out.println("La opción ingresada no existe. Intente nuevamente.");
                    break;
            }
        }
    }
}
