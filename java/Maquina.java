import java.net.*;

/**
 *
 * @author Gastón
 */

//Esta clase representa a una computadora de un sistema distribuido cuyo reloj
//se ve afectado por algoritmos de sincronización.
public class Maquina {
    
    private int iSegundosActuales;
    public DatagramSocket iSocket;
    
    //Constructor de la clase.
    public Maquina(InetAddress pDireccion, int pPuerto, int pSegundosIniciales) throws Exception{
        iSocket = new DatagramSocket(pPuerto, pDireccion);
        iSegundosActuales = pSegundosIniciales;
    }
    
    //Este método se utiliza para notificar los segundos actuales medidos por el
    //reloj de la máquina.
    public void NotificarTiempoActual(InetAddress pDireccion, int pPuerto) throws Exception{
        //Genera el paquete con el tiempo de la máquina.
        String datosAEnviar = Integer.toString(iSegundosActuales);
        DatagramPacket paqueteAEnviar = new DatagramPacket(datosAEnviar.getBytes(), datosAEnviar.length(), pDireccion, pPuerto);
        iSocket.send(paqueteAEnviar);
    }
    
    //Este método se utiliza para avanzar el tiempo medido por la máquina y
    //actualizarlo en caso de recibir la notificación. Luego, devuelve los
    //segundos actuales.
    public int AvanzarTiempo() throws Exception{
        //Elementos del proceso
        int MAXIMOS_MILISEGUNDOS = 1500;
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
        String mensajeRecibido;
        
        //Genera un número aleatorio de milisegundos hasta aumentar el contador
        //de segundos actuales. Esto se hace para simular las distorsiones en el
        //reloj de cada máquina. Esta cantidad varía entre 0 y MAXIMOS_MILISEGUNDOS.
        int milisegundosActuales = (int) Math.round(MAXIMOS_MILISEGUNDOS * Math.random());
        
        //Espera milisegundosActuales para recibir una actualización del tiempo
        try{
            iSocket.setSoTimeout(milisegundosActuales);
            iSocket.receive(paqueteRecibido);
            mensajeRecibido = new String(paqueteRecibido.getData()).trim();
            
            //Si recibe un mensaje solicitando que reporte su tiempo actual,
            //notifica los segundos contados.
            if (mensajeRecibido.equals("REPORTAR")) {
                NotificarTiempoActual(paqueteRecibido.getAddress(), paqueteRecibido.getPort());
            }
            
            //En caso contrario, asume que el mensaje se trata del nuevo valor
            //que debe tomar el reloj.
            else{
                iSegundosActuales = Integer.parseInt(mensajeRecibido);
            }
        }
        
        //Si pasa el tiempo y no recibe paquetes, incrementa en 1 los segundos
        //actuales.
        catch (SocketTimeoutException e){
            iSocket.setSoTimeout(0);
            iSegundosActuales++;
        }
        return iSegundosActuales;
    }
}
