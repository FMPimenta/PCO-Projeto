import java.util.List;
import java.util.Random;

/** 
 * Representam jogadores que confiam na sorte, ou seja, usam um
 * gerador de aleatórios para calcular os valores que lhes são pedidos
 * @author Grupo:03, 
 * Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971
 */
public class JogadorConfiante extends Jogador {
    
    private Random gerador; 
    
    /**
     * Inicializa um novo objeto com um ambiente que terá nLinhas linhas, nCols
     * colunas e obstáculos nas posições dadas pelos pares contidos na lista
     * obstaculos (pode estar vazia). Deve ainda inicializar um atributo do 
     * tipo Random que usará sempre que é preciso calcular a direção e a força 
     * para a simulação.
     */
    public JogadorConfiante(int nLinhas, int nCols, 
    List<Par<Integer, Integer>> obstaculos){
    
        super(nLinhas, nCols, obstaculos);

        this.gerador = new Random(1);
            

    }
    
    /**
     * Retorna uma direção aleatória (um elemento pertencente a 
     * {"N","S","E","O"})
     * @return um dos elementos de {"N","S","E","O"}
     */
    public String direcao(){
    
        String [] direcoes = {"N","S","E","O"};

        int intAlt = this.gerador.nextInt(4);

        return direcoes[intAlt];
    
    }
    
    /**
     * Retorna um valor aleatório entre zero e o número de linhas do seu 
     * ambiente, exclusive
     */
    public int forca(){
    
        Par<Integer,Integer> dimensoes = super.dimensoesAmbiente();

        int linhas = dimensoes.primeiro();

        int intAlt = this.gerador.nextInt(linhas);
    
        return intAlt;
    }

}