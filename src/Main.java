import java.util.Random;
import java.util.Scanner;

import tabela.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] tamanhosTabela = {1000, 10000, 100000};
        int[] tamanhosDados = {100000, 1000000, 10000000};

        System.out.println("=== Teste de Tabelas Hash ===");
        System.out.println("Escolha a função hash para testar:");
        System.out.println("1 - Hash Linear");
        System.out.println("2 - Hash Duplo");
        System.out.println("3 - Hash Encadeado");
        System.out.print("Opção: ");
        int opcao = input.nextInt();

        int[][] combinacoes = {
                {0, 0}, // tabela 1000 x dados 100_000
                {1, 1}, // tabela 10000 x dados 1_000_000
                {2, 2}  // tabela 100000 x dados 10_000_000
        };

        for (int[] combo : combinacoes) {
            int tamanhoTabela = tamanhosTabela[combo[0]];
            int tamanhoDados = tamanhosDados[combo[1]];

            hashTable tabela = null;
            String nomeMetodo = "";

            switch (opcao) {
                case 1 -> {
                    tabela = new hashLinear(tamanhoTabela);
                    nomeMetodo = "Hash Linear";
                }
                case 2 -> {
                    tabela = new hashDuplo(tamanhoTabela);
                    nomeMetodo = "Hash Duplo";
                }
                case 3 -> {
                    tabela = new hashEncadeamento(tamanhoTabela);
                    nomeMetodo = "Hash Encadeamento";
                }
                default -> {
                    System.out.println("Opção inválida!");
                    input.close();
                    return;
                }
            }

            System.out.println("\n=== Testando " + nomeMetodo + " ===");
            testeDesempenho.executar(tabela, tamanhoTabela, new int[]{tamanhoDados}, nomeMetodo);
        }

        input.close();
    }
}
