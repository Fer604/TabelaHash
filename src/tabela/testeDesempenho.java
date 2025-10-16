package tabela;

public class testeDesempenho {
    public static void executar(hashTable tabela,int tamanhoTabela, int[] tamanhosDados, String nomeMetodo) {
        long seed = 123;
        geradorDados gerador = new geradorDados(seed);

        for (int tamanhoDados : tamanhosDados) {
            registro[] dados = gerador.gerar(tamanhoDados);
            long tempo = gerador.inserirTodos(tabela, dados);
            long inicioBusca = System.currentTimeMillis();
            for (registro r : dados) tabela.buscar(r);
            long tempoBusca = System.currentTimeMillis() - inicioBusca;
            System.out.printf(
                    "Tabela %s de tamanho %d (%d dados) -> Tempo: %d ms | Colisões: %d | Fator: %.3f%n | Tempo de Busca: %d ms" ,
                    nomeMetodo,tamanhoTabela, tamanhoDados, tempo, tabela.getColisoes(), tabela.getFatorCarga(), tempoBusca
            );
            if (tabela instanceof hashEncadeamento enc) {
                int[] maiores = enc.maioresListas();
                System.out.printf("  Maiores listas: %d, %d, %d%n", maiores[0], maiores[1], maiores[2]);
            }

            if (tabela instanceof hashLinear linear) {
                double[] gaps = linear.calcularGaps();
                System.out.printf("  Gaps -> Menor: %.0f | Maior: %.0f | Média: %.2f%n",
                        gaps[0], gaps[1], gaps[2]);
            }
            //gaps duplo
            if(tabela instanceof hashDuplo duplo) {
                double[] gaps = duplo.calcularGaps();
                System.out.printf("  Gaps -> Menor: %.0f | Maior: %.0f | Média: %.2f%n",
                        gaps[0], gaps[1], gaps[2]);
            }
            tabela.limpar(); // limpa antes do próximo teste
        }
    }
}
