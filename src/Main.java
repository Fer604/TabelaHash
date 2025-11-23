import java.util.Scanner;

import tabela.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] tamanhosTabela = {100_000, 1_000_000, 10_000_000};
        int[] tamanhosDados = {100_000, 1_000_000, 10_000_000};

        System.out.println("=== Teste de Tabelas Hash ===");
        System.out.println("Escolha a função hash para testar:");
        System.out.println("1 - Hash Linear");
        System.out.println("2 - Hash Duplo");
        System.out.println("3 - Hash Encadeado");
        System.out.print("Opção: ");
        int opcao = input.nextInt();

        int[][] combinacoes = {
                {0, 0}, // tabela 100k x dados 100k  -> fator ≈ 1
                {1, 0}, // tabela 1M x dados 100k   -> fator ≈ 0.1
                {1, 1}, // tabela 1M x dados 1M     -> fator ≈ 1
                {2, 1}, // tabela 10M x dados 1M    -> fator ≈ 0.1
                {2, 2}  // tabela 10M x dados 10M   -> fator ≈ 1
        };

        for (int[] combo : combinacoes) {
            int tamanhoTabela = tamanhosTabela[combo[0]];
            int tamanhoDados = tamanhosDados[combo[1]];

            hashTable tabela = null;
            String nomeMetodo = "";

            switch (opcao) {
                case 1 -> {
                    tabela = new estrategiaLinear(tamanhoTabela);
                    nomeMetodo = "Hash Linear";
                }
                case 2 -> {
                    tabela = new estrategiaDuplo(tamanhoTabela);
                    nomeMetodo = "Hash Duplo";
                }
                case 3 -> {
                    tabela = new estrategiaEncadeamento(tamanhoTabela);
                    nomeMetodo = "Hash Encadeamento";
                }
                default -> {
                    System.out.println("Opção inválida!");
                    input.close();
                    return;
                }
            }

            System.out.println("\n=== Testando " + nomeMetodo + " ===");
            testeDesempenho.executar(tabela, tabela.getTamanho(), new int[]{tamanhoDados}, nomeMetodo);
        }

        input.close();
    }
}
