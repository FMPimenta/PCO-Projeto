/**
 * @author Grupo:03, 
 * Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971
 */
public interface Acionavel {

    /**
     * Devolve a matriz de elementos deste acionável sobre a qual poderá ser
     * feita uma simulação
     */
    EstadoSimulacao[][] alvoSimulacao();

    /**
     * Devolve true se este acionável pode ser usado numa simulação
     */
    boolean podeAtuar();
    
}
