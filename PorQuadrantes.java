import java.util.ArrayList;

/**
 * @author Grupo:003, Francisco Pimenta 54973, Miguel Duarte 54941, Pedro Quintao 54971 
 * 
 */

public class PorQuadrantes implements Direcionador {

    /**
     * Este metodo devolve uma direcao sugerida para um passo de simulacao sobre o alvo alvo
     * @param alvo O alvo da simulacao
     * @return um dos elementos ("N","S","E","O") dependendo do quadrante 
     * afetado e do quadrante afetador.
     */ 
        public String direcao(EstadoSimulacao[][] alvo) {
            
        int linhas = alvo.length;
        int colunas = alvo[0].length;
        
        /* Aqui vai se calcular cada quadrante dependendo do numero de linhas 
        e colunas que o alvo tiver */
        ArrayList<EstadoSimulacao> quadrante1;
        ArrayList<EstadoSimulacao> quadrante2;
        ArrayList<EstadoSimulacao> quadrante3;
        ArrayList<EstadoSimulacao> quadrante4;


        /* Caso em que o numero de colunas e linhas eh par */
        if (colunas % 2 == 0 && linhas % 2 == 0) {
            /* 1º QUADRANTE */
            quadrante1 = calculaQuadrante(alvo, (linhas/2), colunas, 0, (colunas/2));
            /* 2º QUADRANTE */
            quadrante2 = calculaQuadrante(alvo, (linhas/2), (colunas/2), 0, 0);
            /* 3º QUADRANTE */            
            quadrante3 = calculaQuadrante(alvo, linhas , (colunas/2), (linhas/2), 0);
            /* 4º QUADRANTE */
            quadrante4 = calculaQuadrante(alvo, linhas, colunas, linhas/2, colunas/2);

        } else if (colunas % 2 == 0 && linhas % 2 != 0) { /* Caso em que o numero de 
            colunas eh par e linhas impar */
            
            quadrante1 = calculaQuadrante(alvo, (linhas/2) + 1, colunas, 0, (colunas/2));
            
            quadrante2 = calculaQuadrante(alvo, (linhas/2) + 1, (colunas/2), 0, 0);

            quadrante3 = calculaQuadrante(alvo, linhas , (colunas/2), (linhas/2) + 1, 0);

            quadrante4 = calculaQuadrante(alvo, linhas, colunas, (linhas/2) + 1, (colunas/2));  

        } else if (colunas % 2 != 0 && linhas % 2 == 0) { /* Caso em que o numero de 
            colunas eh impar e linhas eh par */
            
            quadrante1 = calculaQuadrante(alvo, (linhas/2), colunas, 0, (colunas/2));
            
            quadrante2 = calculaQuadrante(alvo, (linhas/2), (colunas/2), 0, 0);
            
            quadrante3 = calculaQuadrante(alvo, linhas, (colunas/2), (linhas/2), 0);
            
            quadrante4 = calculaQuadrante(alvo, linhas, colunas, linhas/2, (colunas/2));

        } else { /* Caso em que o numero de colunas e linhas sao impar */
            
            quadrante1 = calculaQuadrante(alvo, (linhas/2) + 1, colunas, 0, (colunas/2) + 1);
            
            quadrante2 = calculaQuadrante(alvo, (linhas/2) + 1, (colunas/2), 0, 0);
            
            quadrante3 = calculaQuadrante(alvo, linhas, (colunas/2), (linhas/2) + 1, 0);
            
            quadrante4 = calculaQuadrante(alvo, linhas, colunas, (linhas/2) + 1, (colunas/2));
        }
        
        /* Aqui adiciona-se cada quadrante do alvo a um ArrayList */
        ArrayList<ArrayList<EstadoSimulacao>> todosQuadrantes = new ArrayList<ArrayList<EstadoSimulacao>>();
        todosQuadrantes.add(quadrante1);
        todosQuadrantes.add(quadrante2);
        todosQuadrantes.add(quadrante3);
        todosQuadrantes.add(quadrante4);
       
        int indiceQuadrante = 0;
        int minimoAfetados = 0;
        int indiceQuadranteMenosAfetado = 0;
        
        /* Os ciclos seguintes servem para calcular qual é o quadrante que tem 
        menos afetados (quadrante afetado). Indentifica-se o quadrante através do seu indice no 
        Arraylist todosQuadrantes (0 - 1º quadrante, 1 - 2º quadrante, 
        2 - 3º quadrante e 3 - 4º quadrante) */

        for (ArrayList<EstadoSimulacao> quadrante : todosQuadrantes) {
            int contadorAfetados = 0;
            for (EstadoSimulacao elemento : quadrante) {
                if ( elemento == EstadoSimulacao.AFETADO) {
                    contadorAfetados++;
                }
            }
            if (indiceQuadrante == 0) {
                minimoAfetados = contadorAfetados;
                indiceQuadranteMenosAfetado = indiceQuadrante;
            } else if (contadorAfetados < minimoAfetados) {
                minimoAfetados = contadorAfetados;
                indiceQuadranteMenosAfetado = indiceQuadrante;
            }
            indiceQuadrante++;
        }

        /* Depois de calculado o quadrante com menos afetados (quadrante afetado), 
        adicionamos ao array de ints "possiveisQuadrantesAAfetar" os quadrantes que 
        se encontram imediatamente ao lado desse quadrante, ou seja, os quadrantes que 
        têm a possibilidade de afetar o quadrante afetado */

        int[] possiveisQuadrantesAAfetar = {0, 0};
        if (indiceQuadranteMenosAfetado == 1 || indiceQuadranteMenosAfetado == 2) {
            possiveisQuadrantesAAfetar[0] = indiceQuadranteMenosAfetado - 1;
            possiveisQuadrantesAAfetar[1] = indiceQuadranteMenosAfetado + 1;
        } else if (indiceQuadranteMenosAfetado == 0) {
            possiveisQuadrantesAAfetar[0] = indiceQuadranteMenosAfetado + 1;
            possiveisQuadrantesAAfetar[1] = indiceQuadranteMenosAfetado + 3;
        } else {
            possiveisQuadrantesAAfetar[0] = indiceQuadranteMenosAfetado - 3;
            possiveisQuadrantesAAfetar[1] = indiceQuadranteMenosAfetado - 1;
        }

        int contadorMaisAfetado = 0;
        int maximoMaisAfetado = 0;
        int indiceQuadranteMaisAfetado = 0;
       
        /* Os ciclos seguintes servem para calcular qual dos quadrantes que têm 
        possibilidade de afetar (com os indices no array de ints 
        "possiveisQuadrantesAAfetar") têm o maior numero de afetados (quadrante 
        afetador) */

        for (Integer indice : possiveisQuadrantesAAfetar) {
        
            contadorMaisAfetado = 0;

            for (EstadoSimulacao elemento : todosQuadrantes.get(indice)) {
                if ( elemento == EstadoSimulacao.AFETADO ) {
                    contadorMaisAfetado++; 
                }
            }
            if (contadorMaisAfetado > maximoMaisAfetado) {
                maximoMaisAfetado = contadorMaisAfetado;
                indiceQuadranteMaisAfetado = indice;
            }
        }

        /* Para o caso em que nenhum dos quadrantes com possibilidade de afetar 
        tenha mais que 0 afetados, o quadrante selecionado é o quem vem primeiro 
        no array de ints "possiveisQuadrantesAAfetar", ou seja, o quadrante menor */

        if (maximoMaisAfetado == 0) {
            indiceQuadranteMaisAfetado = possiveisQuadrantesAAfetar[0];
        }


        /* As condicoes If seguintes irão calcular a direcao sugerida dependendo 
        dos 2 quadrantes que foram selecionados anteriormente (o quadrante afetado 
        e o quadrante afetador) */

        if (indiceQuadranteMenosAfetado == 0 ) {
            if (indiceQuadranteMaisAfetado == 1) {
                return "O";
            } else {
                return "S";
            }
        } else if (indiceQuadranteMenosAfetado == 1) {
            if (indiceQuadranteMaisAfetado == 0) {
                return "E";
            } else {
                return "S";
            }
        } else if (indiceQuadranteMenosAfetado == 2) {
            if (indiceQuadranteMaisAfetado == 1) {
                return "N";
            } else {
                return "E";
            }
        } else {
            if (indiceQuadranteMaisAfetado == 0) {
                return "N";
            } else {
                return "O";
            }
        }

    }

    /**
     * Este método devolve o número de linhas de alvo multiplicado pelo número de colunas de alvo
     * @param alvo O alvo de simulacao
     * @return linhas * colunas
     */
    public int precoConsulta(EstadoSimulacao[][] alvo) {
        int colunas = alvo.length;
        int linhas = alvo[0].length;

        return (linhas * colunas);
    }

    /**
     * Este metodo devolve um quadrante pertencente ao alvo alvo
     * @param alvo O alvo de simulacao
     * @param linhas numero de linhas do alvo
     * @param colunas numero decolunas do alvo
     * @param inicioLinhas numero da linha pelo qual deve ser comecado a calcular o quadrante 
     * @param inicioColunas numero da coluna pelo qual deve ser comecado a calcular o quadrante 
     * @return um quadrante pertencente ao alvo
     */
    private ArrayList<EstadoSimulacao> calculaQuadrante(EstadoSimulacao[][] alvo, int linhas, int colunas, 
                                               int inicioLinhas, int inicioColunas) {

        ArrayList<EstadoSimulacao> quadrante = new ArrayList<EstadoSimulacao>(); 
        for (int i = inicioLinhas; i < linhas; ++i) {
            for(int j = inicioColunas; j < colunas; ++j) {
              quadrante.add(alvo[i][j]);
            }
        }

        return quadrante;
    }
}


            