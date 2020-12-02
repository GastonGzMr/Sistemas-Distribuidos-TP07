import java.net.*;

/**
 *
 * @author Gastón
 */

//Esta clase funciona como un registro mediante el cual comunicarse con una
//máquina activa.
public class RegistroMaquina {
    
    public InetAddress iDireccion;
    public int iPuerto;
    public DatagramSocket iSocket;
    
    //Constructor de la clase.
    public RegistroMaquina(InetAddress pDireccion, int pPuerto) throws Exception{
        iDireccion = pDireccion;
        iPuerto = pPuerto;
        iSocket = new DatagramSocket();
    }
    
    //Este método se utiliza para obtener el tiempo actual medido por la máquina
    //especificada.
    public int ObtenerTiempoActual() throws Exception {
        //Elementos del proceso.
        byte[] buffer = new byte[1024];
        String mensajeRecibido;
        String mensajeAEnviar = "REPORTAR";
        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
        DatagramPacket paqueteAEnviar = new DatagramPacket(mensajeAEnviar.getBytes(), mensajeAEnviar.length(), iDireccion, iPuerto);
        
        //Envía la solicitud del tiempo a la máquina y espera la respuesta.
        iSocket.send(paqueteAEnviar);
        iSocket.receive(paqueteRecibido);
        
        //Obtiene el tiempo de la máquina a partir del paquete recibido.
        mensajeRecibido = new String(paqueteRecibido.getData()).trim();
        return Integer.parseInt(mensajeRecibido);
    }
    
    public void ActualizarTiempo(int pNuevoTiempo) throws Exception {
       //Elementos del proceso.
       String mensajeAEnviar = Integer.toString(pNuevoTiempo);
       DatagramPacket paqueteAEnviar = new DatagramPacket(mensajeAEnviar.getBytes(), mensajeAEnviar.length(), iDireccion, iPuerto);
       
       //Envía el nuevo tiempo a la máquina.
       iSocket.send(paqueteAEnviar);
    }
}
