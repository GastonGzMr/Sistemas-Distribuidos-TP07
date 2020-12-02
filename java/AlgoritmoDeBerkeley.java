/**
 *
 * @author Gastón
 */

//Esta clase se utiliza para sincronizar máquinas mediante el algoritmo de Berkeley.
public class AlgoritmoDeBerkeley {
    
    //Este método sincroniza los tiempos de un conjunto de máquinas mediante el
    //algoritmo de Berkeley.
    public static void SincronizarMaquinas(RegistroMaquina[] pListaDeMaquinas) throws Exception {
        //Elementos del proceso
        int indiceActual = 0;
        int tiempoPromedio = 0;
        
        //Recorre la lista de máquinas por primera vez para obtener el tiempo
        //promedio.
        while(indiceActual < pListaDeMaquinas.length){
            RegistroMaquina maquinaActual = pListaDeMaquinas[indiceActual];
            
            //Acumula el tiempo de cada máquina.
            tiempoPromedio += maquinaActual.ObtenerTiempoActual();
            indiceActual++;
        }
        
        //Obtiene el tiempo promedio entre las máquinas
        tiempoPromedio = Math.round(tiempoPromedio / pListaDeMaquinas.length);
        
        //Recorre la lista de máquinas por segunda vez para asignar a cada una
        //el nuevo tiempo.
        indiceActual = 0;
        while(indiceActual < pListaDeMaquinas.length){
            RegistroMaquina maquinaActual = pListaDeMaquinas[indiceActual];
            maquinaActual.ActualizarTiempo(tiempoPromedio);
            indiceActual++;
        }
    }
}
