package tabela;

public class testeDesempenho {
    public static void executar(hashTable tabela,int tamanhoTabela, int[] tamanhosDados, String nomeMetodo) {
        long seed = 123;
        geradorDados gerador = new geradorDados(seed);

        for (int tamanhoDados : tamanhosDados) {
            registro[] dados = gerador.gerar(tamanhoDados);
            long tempo = gerador.inserirTodos(tabela, dados);
            System.out.printf(
                    "Tabela %s de tamanho %d (%d dados) -> Tempo: %d ms | Colisões: %d | Fator: %.3f%n",
                    nomeMetodo,tamanhoTabela, tamanhoDados, tempo, tabela.getColisoes(), tabela.getFatorCarga()
            );
            tabela.limpar(); // limpa antes do próximo teste
        }
    }
}
