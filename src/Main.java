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
        System.out.println("3 - Hash Multiplicação");
        System.out.print("Opção: ");
        int opcao = input.nextInt();

        String nomeMetodo = "";
        hashTable tabela = null;

        for (int tamanhoTabela : tamanhosTabela) {
            for (int tamanhoDados : tamanhosDados) {
                System.out.println("\n=== Testando tabela de tamanho " + tamanhoTabela + " com " + tamanhoDados + " registros ===");

                // Cria a tabela conforme a escolha do usuário
                switch (opcao) {
                    case 1:
                        tabela = new hashLinear(tamanhoTabela);
                        nomeMetodo = "Hash Linear";
                        break;
                    case 2:
                        tabela = new hashDuplo(tamanhoTabela);
                        nomeMetodo = "Hash Duplo";
                        break;
                    case 3:
                        tabela = new hashEncadeamento(tamanhoTabela);
                        nomeMetodo = "Hash Encadeamento";
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        input.close();
                        return;
                }

                testarTabela(tabela, tamanhoDados, nomeMetodo);
            }
        }
    }

    private static void testarTabela(hashTable tabela, int tamanhoDados, String nomeMetodo) {
        long seed = 123;
        geradorDados gerador = new geradorDados(seed);
        long inicio = System.currentTimeMillis();

        registro[] dados  = gerador.gerar(tamanhoDados);
        gerador.inserirTodos(tabela,dados);


        long fim = System.currentTimeMillis();
        System.out.println(nomeMetodo + " - Tempo: " + (fim - inicio) + " ms");
    }
}
