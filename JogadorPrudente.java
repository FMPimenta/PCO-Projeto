import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Representa jogadores que ponderam bem a forma como tomam as decisões. Um
 * jogador prudente tem recursos limitados que pode usar para o cálculo da
 * direção das jogadas e auxilia-se de um Direcionador para esse cálculo. Um
 * jogador prudente guarda um histórico das suas jogadas (os recursos gastos e 
 * o número de pontos ganho com cada jogada) e só considera que vale a pena 
 * jogar (atuar) se as jogadas que já fez foram compensadoras.
 * @author Grupo:03, 
 * Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971
 */
public class JogadorPrudente extends Jogador {

    private Direcionador dir;
    private int recursos;
    private ArrayList<Par<Integer,Integer>> historico = new ArrayList<Par<Integer, Integer>>();
    private int gasto;

    /**
     * Inicializa um novo objeto com um ambiente que terá nLinhas linhas, nCols
     * colunas e obstáculos nas posições dadas pelos pares contidos na lista
     * obstaculos (pode estar vazia); o Direcionador que usará sempre que é 
     * preciso calcular a direção para a simulação é dado pelo parâmetro dir; 
     * os recursos que terá para gastar são dados pelo parâmetro recursos;
     */
    public JogadorPrudente(int nLinhas, int nCols, 
    List<Par<Integer, Integer>> obstáculos, int recursos, Direcionador dir) {

        super(nLinhas, nCols, obstáculos);
        this.dir = dir;
        this.recursos = recursos;

    }

    /**
     * retorna o valor dos recursos que este jogador prudente ainda tem
     */
    public int recursos() {
        return this.recursos;
    }

    /**
     * Retorna a representação textual deste jogador prudente, que inclui a sua
     * pontuação e o seu ambiente, o tipo do seu direcionador, os recursos que 
     * ainda tem, o total de gastos que já fez e a descrição dos gastos e 
     * pontos obtidos em cada jogada já feita
     */
    public String toString() {
       
        int gastos = 0;

        for (Par<Integer,Integer> jogada : historico) {
            gastos += jogada.primeiro();
        }

        StringBuilder result = new StringBuilder();

        result.append(super.toString());
        result.append("Direcionador: " + this.dir.getClass().getName()+ "\n");
        result.append("Recursos: " + this.recursos() + " Gastos: " + gastos + "\n");
        result.append("Jogadas: \n");

        for (Par<Integer, Integer> jogada : historico) {
            result.append("Gasto: " + jogada.primeiro() + " Pontos " + "obtidos: " + jogada.segundo() + "\n");
        } 

        return result.toString();
    }

    /**
     * Pergunta ao direcionador deste jogador prudente qual o preço que leva 
     * por uma consulta para saber a direção e regista esse gasto; de seguida 
     * pede a direção ao direcionador e devolve essa direção; os métodos 
     * invocados sobre o direcionador terão como parâmetro o alvo de simulação 
     * deste jogador
     */
    public String direcao() {

        this.gasto = this.dir.precoConsulta(this.alvoSimulacao());
        String direcao = this.dir.direcao(this.alvoSimulacao());

        return direcao;

    }

    /**
     * Retorna o máximo entre o número de linhas e o número de colunas do 
     * ambiente deste jogador
     */
    public int forca() {

        Par<Integer, Integer> dimensoes = super.ambiente.dimensoes();
        int maximo = Math.max(dimensoes.primeiro(), dimensoes.segundo());

        return maximo;
    }

    @Override
    /**
     * Retorna true se todas as condições seguintes se verificarem: o ambiente
     * deste jogador prudente pode atuar, os recursos que ainda tem são
     * suficientes para pagar a consulta ao seu direcionador e o total de
     * recursos gastos nas jogadas já feitas não é superior ao total de pontos 
     * que já ganhou multiplicado pelo número de linhas e pelo número de 
     * colunas do seu ambiente.
     */
    public boolean podeAtuar() {

        //ambiente pode atuar
        boolean ambientePodeAtuar = super.podeAtuar();

        // tem recursos suficientes para pagar a consulta ao direcionador 
        boolean podePagar;

        //recursos gastos nas jogadas ja feitas nao e superior ao total 
        //de pontos que ja ganhou miltiplicado pelo numero de linhas e 
        //pelo numero de colunas do seu ambiente
        boolean recursosGastos;

        
        int preco = this.dir.precoConsulta(super.alvoSimulacao());
        int gastos = 0;
        int pontos = 0;
        Par<Integer, Integer>  dimensoes = super.ambiente.dimensoes();
        int linhas = dimensoes.primeiro();
        int colunas = dimensoes.segundo();
        
        for (Par<Integer, Integer> jogada : historico) {
            gastos += jogada.primeiro();
            pontos += jogada.segundo();
        }

        if (this.recursos >= preco) {
            podePagar = true;
        } else {
            podePagar = false;
        }

        if (gastos <= pontos * linhas * colunas) {
            recursosGastos = true;
        } else {
            recursosGastos = false;
        }

        if (ambientePodeAtuar && podePagar && recursosGastos) {
            return true;
        } else {
            return false;
        }
    
    }

    @Override
    /**
     * Chama a versão deste método na classe Jogador e
     * também guarda o registo desta nova jogada (recursos gastos e pontos
     * ganhos)
     */
    public void registaJogadaComPontuacao (List<Par<Integer, Integer>> afetados, 
    int pontos) {

        super.registaJogadaComPontuacao(afetados, pontos);
        int gasto = this.gasto;

        this.recursos = this.recursos() - gasto;
    
        Par<Integer, Integer> jogada = new Par<Integer, Integer>(gasto, pontos);
        this.historico.add(jogada);
        
    }
}   
