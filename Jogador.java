import java.util.List;
import java.util.Arrays;

/**
 * Classe abstrata 
 * @author Grupo:03, Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao
 *         54971
 */

public abstract class Jogador implements Acionavel  {
    
    private int pontos;
    public Ambiente ambiente;

    /**
     * Inicializa um novo objeto com um ambiente que terá nLinhas linhas, nCols
     * colunas e obstáculos nas posições dadas pelos pares contidos na lista
     * obstaculos (pode estar vazia)
     */
    public Jogador(int nLinhas, int nCols, List<Par<Integer, Integer>> obstaculos){

        this.ambiente = new Ambiente(nLinhas, nCols, obstaculos);
    }


    /**
     * Devolve a pontuação que este jogador tem
     */
    public int pontuacao() {

        return this.pontos;

    }

    /**
     * Devolve um par cujo primeiro elemento é o número de linhas do ambiente deste
     * jogador e segundo elemento é o número de colunas
     */
    public Par<Integer,Integer> dimensoesAmbiente() {

        return this.ambiente.dimensoes();
        
    }

    /**
     * Assumindo que a lista afetados contém posições válidas relativamente ao
     * ambiente deste jogador, regista essas posições como afetadas no 
     * ambiente e adiciona pontos à pontuação deste jogador
     */
    public void registaJogadaComPontuacao(List<Par<Integer, Integer>> afetados,
    int pontos){
        
        this.pontos += pontos;
        this.ambiente.registaAfetados(afetados);

    }

    /**
     * Retorna a representação textual destas eleições, que inclui a pontuação 
     * e o ambiente deste jogador
     */
    public String toString(){
        
        StringBuilder result = new StringBuilder();
        result.append("Pontuacao: " + this.pontuacao() + "\n");
        result.append(this.ambiente.toString() + "\n");

        return result.toString();
    }

    @Override
    /**
     * Retorna a matriz que representa o ambiente deste jogador para efeitos de
     * simulação
     */
    public EstadoSimulacao[][] alvoSimulacao(){

        return this.ambiente.alvoSimulacao();
    }

    @Override
    /**
     * Retorna true se o ambiente deste jogador pode atuar
     */
    public boolean podeAtuar(){

        return this.ambiente.podeAtuar();

    }

    /**
     * Retorna a direção que este jogador quer usar na próxima jogada (simulação)
     */
    public abstract String direcao();


    /**
     * Retorna a força que este jogador quer usar na próxima jogada (simulação)
     */
    public abstract int forca();

}
