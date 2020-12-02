import java.net.*;

/**
 *
 * @author Gastón
 */

//Esta clase se utiliza para sincronizar máquinas mediante el algoritmo de Cristian.
public class AlgoritmoDeCristian {
    
    //Este método sincroniza los tiempos de un conjunto de máquinas mediante el
    //algoritmo de Cristian.
    public static void SincronizarMaquinas(RegistroMaquina[] pListaDeMaquinas, InetAddress pDireccionServidor, int pPuertoServidor) throws Exception {
        //Elementos del proceso
        int indiceActual = 0;
        int tiempoDeEspera;
        int tiempoDelServidor;
        byte[] buffer = new byte [1024];
        String mensajeAEnviar = "REPORTAR";
        DatagramPacket paqueteAEnviar = new DatagramPacket(mensajeAEnviar.getBytes(), mensajeAEnviar.length(), pDireccionServidor, pPuertoServidor);
        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
        String mensajeRecibido;
        DatagramSocket socket = new DatagramSocket();
        
        //Recorre la lista de máquinas, actualizando en cada caso el tiempo
        //según el servidor de tiempo.
        while(indiceActual < pListaDeMaquinas.length){
            RegistroMaquina maquinaActual = pListaDeMaquinas[indiceActual];
            
            //Consulta el tiempo actual al servidor
            socket.send(paqueteAEnviar);
            
            //Espera a recibir un paquete del servidor, actualizando el tiempo
            //de espera.
            tiempoDeEspera = 0;
            tiempoDelServidor = 0;
            while(tiempoDelServidor == 0){
                socket.setSoTimeout(1000);
                try{
                    socket.receive(paqueteRecibido);
                    socket.setSoTimeout(0);
                    mensajeRecibido = new String(paqueteRecibido.getData()).trim();
                    tiempoDelServidor = Integer.parseInt(mensajeRecibido);
                }
                catch (SocketTimeoutException e){
                    socket.setSoTimeout(0);
                    tiempoDeEspera++;
                }
            }
            
            //Actualiza el tiempo de la máquina a partir del tiempo obtenido del
            //servidor y el de espera.
            maquinaActual.ActualizarTiempo(Math.round(tiempoDelServidor+(tiempoDeEspera/2)));
            indiceActual++;
        }
    }
}
