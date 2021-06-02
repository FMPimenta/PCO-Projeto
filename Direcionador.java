/**
 * @author Grupo:03, 
 * Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971
 */
public interface Direcionador {
 
    /**
     * Devolve uma direção sugerida para um passo de simulação sobre o alvo
     */
    String direcao(EstadoSimulacao[][] alvo);	

    /**
     * Devolve o preço de uma consulta sobre a direção a usar num passo de
     * simulação sobre o alvo
     */
    int precoConsulta(EstadoSimulacao[][] alvo);	
    
}
