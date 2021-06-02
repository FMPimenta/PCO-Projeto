import java.util.Arrays;
/**
 * @author Grupo:03, 
 * Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971
 */
public class VerticalHorizontal implements Direcionador {
    
    /**
     * Devolve uma direção sugerida para um passo de simulação sobre o alvo alvo; A
     * direção escolhida é da linha (primeira/ultima) com mais afetados para a linha
     * (ultima/primeira) com menos afetados ("N"/"S") ou da coluna
     * (esquerda/direita) com mais afetados para a coluna (direita/esquerda) com
     * menos afetados ("O"/"E"); destas duas hipóteses escolhe-se aquela em que a
     * diferença e' maior (se as diferenças forem iguais, escolhe-se uma direção
     * horizontal ("O"/"E")
     * @param alvo O alvo de simulacao
     * @return direcao A string direcao
     */
    public String direcao(EstadoSimulacao[][] alvo) {
        
        

        int altura = alvo.length;
        int largura = alvo[0].length;
        int primeiraCol = 0;
        int ultimaCol = 0;
        int primeiraLinha = 0;
        int ultimaLinha = 0;

        //iterando na primeira linha, à procura de coordenadas afetadas
        for(int pl = 0; pl < largura; pl++) {
            if(alvo[0][pl] == EstadoSimulacao.AFETADO){
                primeiraLinha = primeiraLinha + 1;
            }
        }
        //iterando na primeira coluna, à procura de coordenadas afetadas
        for(int pc = 0; pc < altura; pc++){ 
            if(alvo[pc][0] == EstadoSimulacao.AFETADO){
                primeiraCol = primeiraCol + 1;
            }
        }
        //iterando na última linha, à procura de coordenadas afetadas
        for(int ul = 0; ul < largura; ul++){
            if(alvo[altura - 1][ul] == EstadoSimulacao.AFETADO){
                ultimaLinha = ultimaLinha + 1;
            }
        }
        //iterando na última coluna, à procura de coordenadas afetadas
        for(int uc = 0; uc < altura; uc++){
            if(alvo[uc][largura - 1] == EstadoSimulacao.AFETADO){
                ultimaCol = ultimaCol + 1;
            }
        }


        
        //declaração de variáveis para decidir a direção dada ao jogador
        String direcao;
        int diferencaCol = Math.abs(primeiraCol - ultimaCol);
        int diferencaLinha = Math.abs(primeiraLinha - ultimaLinha);
        //verificação das condições para a decisão da direção do jogador prudente
        if (diferencaCol == diferencaLinha) {
            if (ultimaCol < primeiraCol) {
                direcao = "O";
            } else {
                direcao = "E";
            }
        } else if (diferencaCol > diferencaLinha) {
            if (ultimaCol > primeiraCol) {
                direcao = "E";
            } else {
                direcao = "O";
            }
        } else {
            if (ultimaLinha > primeiraLinha) {
                direcao = "S";
            } else {
                direcao = "N";
            }
        }

        return direcao;
    }

    /**
     * Devolve o dobro do número de linhas de alvo adicionado ao dobro do 
     * número de colunas de alvo
     * @param alvo O alvo de simulacao
     * @return precoConsulta O int precoConsulta
     */
    public int precoConsulta(EstadoSimulacao[][] alvo) {
        //decalração do número de linhas e colunas
        int altura = alvo.length;
        int largura = alvo[0].length;
        //multiplicação da largura e altura por dois e de seguida a soma para o custo da consulta por parte do jogador
        int toAdd = largura * 2;
        int toAdd1 = altura * 2;
        int precoConsulta = toAdd + toAdd1;


        return precoConsulta;

        
    }
}
