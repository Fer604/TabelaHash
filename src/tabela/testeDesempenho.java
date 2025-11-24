package tabela;

public class testeDesempenho {
    //testa uma tabela de cada vez com as diferentes permutações de inserções e tamanhos da tabela
    public static void executar(hashTable tabela,int tamanhoTabela, int[] tamanhosDados, String nomeMetodo,String nomeHash) {
        long seed = 123;
        geradorDados gerador = new geradorDados(seed);
        for (int tamanhoDados : tamanhosDados) {
            registro[] dados = gerador.gerar(tamanhoDados);
            long tempo = gerador.inserirTodos(tabela, dados);
            long inicioBusca = System.currentTimeMillis();
            for (registro r : dados) tabela.buscar(r);//teste que busca todos os registros que foram adicionados
            long tempoBusca = System.currentTimeMillis() - inicioBusca;
            System.out.printf(
                    "Tabela %s de tamanho %d (%d dados), utilizando %s -> Tempo: %d ms | Colisões: %d | Fator: %.3f%n | Tempo de Busca: %d ms",
                    nomeMetodo, tamanhoTabela, tamanhoDados,nomeHash, tempo, tabela.getColisoes(), tabela.getFatorCarga(), tempoBusca
            );
            if (tabela instanceof estrategiaEncadeamento enc) {
                int[] maiores = enc.maioresListas();
                System.out.printf("  Maiores listas: %d, %d, %d%n", maiores[0], maiores[1], maiores[2]);
                double[] gaps = enc.calcularGaps();
                System.out.printf("  Gaps -> Menor: %.0f | Maior: %.0f | Média: %.2f%n",
                        gaps[0], gaps[1], gaps[2]);
            }

            if (tabela instanceof estrategiaLinear linear) {
                double[] gaps = linear.calcularGaps();
                System.out.printf("  Gaps -> Menor: %.0f | Maior: %.0f | Média: %.2f%n",
                        gaps[0], gaps[1], gaps[2]);
            }
            //gaps duplo
            if (tabela instanceof estrategiaDuplo duplo) {
                double[] gaps = duplo.calcularGaps();
                System.out.printf("  Gaps -> Menor: %.0f | Maior: %.0f | Média: %.2f%n",
                        gaps[0], gaps[1], gaps[2]);
            }
            tabela.limpar(); // limpa antes do próximo teste
        }
    }
    static double[] calculaGapsDeTabelaOcupada(byte[] state, int tamanhoTabela){
        int occupied=0;
        for (int i=0;i<tamanhoTabela;i++) {
            if (state[i] == 1) occupied++;
        }

        int minGap,maxGap;
        double avgGap;

        if (occupied<=1){ // casos triviais
            minGap=0;
            maxGap=tamanhoTabela-1;
            if(occupied==0){
                avgGap = tamanhoTabela;
            }
            else{
                avgGap =(tamanhoTabela-1);
            }
            return new double[]{minGap,maxGap,avgGap};
        }

        int[] occ = new int[occupied];
        int indice=0;
        for (int i=0;i<tamanhoTabela;i++) {
            if (state[i] == 1) occ[indice++] = i;
        }

        minGap = Integer.MAX_VALUE;
        maxGap = Integer.MIN_VALUE;
        long sum=0L;

        for (int j=0;j<occupied;j++){
            int a = occ[j];
            int b = occ[(j+1)%occupied]; // circular
            int gap;

            if (b>a){
                gap=(b-a-1);
            }
            else{
                gap=(tamanhoTabela-(a-b)-1);
            }

            if (gap<minGap) minGap=gap;
            if (gap>maxGap) maxGap=gap;
            sum += gap;
        }
        avgGap = (double)sum / occupied;
        return new double[]{minGap,maxGap,avgGap};
    }
}
