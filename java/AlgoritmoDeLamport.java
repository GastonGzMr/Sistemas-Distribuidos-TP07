/**
 *
 * @author Gastón
 */

//Esta clase se utiliza para sincronizar máquinas mediante el algoritmo de Lamport.
public class AlgoritmoDeLamport {
    
    //Este método sincroniza los tiempos de un conjunto de máquinas mediante el
    //algoritmo de Lamport, a partir de un tiempo inicial pTiempoDeEnvio.
    static void SincronizarMaquinas(RegistroMaquina[] pListaDeMaquinas, int pTiempoDeEnvio) throws Exception {
        int indiceActual = 0;
        
        //Recorre la lista de máquinas.
        while(indiceActual < pListaDeMaquinas.length){
            RegistroMaquina maquinaActual = pListaDeMaquinas[indiceActual];
            
            //Si el tiempo actual medido por esta máquina es anterior al de envío,
            //actualiza su reloj para evitar inconsistencias.
            if (maquinaActual.ObtenerTiempoActual() < pTiempoDeEnvio) {
                maquinaActual.ActualizarTiempo(pTiempoDeEnvio + 1);
            }
            pTiempoDeEnvio++;
            indiceActual++;
        }
    }
}
