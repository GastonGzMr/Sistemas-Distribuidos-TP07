import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Gastón
 */

//Esta clase se utiliza como intermediaria entre el usuario y la clase Máquina,
//a fin de realizar pruebas
public class MainMaquina {
    
    public static void main(String[] args) throws Exception{
        //Elementos del proceso.
        Scanner scanner = new Scanner(System.in);
        int segundosActuales = 0;
        
        //Pide la dirección y el puerto al usuario para crear una instancia de
        //la máquina.
        System.out.print("La máquina funciona en la dirección: ");
        InetAddress direccionMaquina = InetAddress.getByName(scanner.nextLine());
        System.out.print("En el puerto: ");
        int puertoMaquina = Integer.parseInt(scanner.nextLine());
        Maquina maquinaActual = new Maquina(direccionMaquina, puertoMaquina, segundosActuales);
        System.out.println("La máquina está funcionando en el puerto "+puertoMaquina+" de la dirección "+direccionMaquina);
        
        //Hace avanzar el tiempo durante la ejecución del main, mostrando por
        //pantalla el tiempo actual de la máquina.
        while(true){
            segundosActuales = maquinaActual.AvanzarTiempo();
            System.out.println("Tiempo actual de la máquina: "+segundosActuales+" segundos");
        }
    }
}
