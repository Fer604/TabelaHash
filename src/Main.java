import tabela.*;
import java.util.Random;

public class Main {
    // multiplicative hash (Knuth) for integer keys
//    public static int multiplicativeHash(int key, int tableSize){
//        // use unsigned multiplication behavior via long
//        final long A = 2654435769L; // Knuth's multiplicative constant (32-bit)
//        long unsignedKey = Integer.toUnsignedLong(key);
//        long hashed = (unsignedKey * A) & 0xFFFFFFFFL;
//        return (int)((hashed >>> (32 - Integer.numberOfTrailingZeros(tableSize))) % tableSize);
//    }

    public static void main(String[] args){
        long seed = 12345L;
        int n1 = 100000; // number of registros to generate
        int n2 = 1000000;
        int n3 = 10000000;
        tabela.geradorDados gen = new tabela.geradorDados(seed);
        registro[] dadosP = gen.gerar(n1);
        registro[] dadosM = gen.gerar(n2);
        registro[] dadosG = gen.gerar(n3);

        testeLinear(dadosP);
        testeLinear(dadosM);
        testeLinear(dadosG);


        // print a few sample hashes for visibility
//        System.out.println("Sample multiplicative hashes:");
//        for (int i = 0; i < 5; i++){
//            int codigo = dados[i].getCodigoNumerico();
//            int h = multiplicativeHash(codigo, 4096);
//            System.out.println(dados[i].getCodigo() + " -> " + h);
//        }

        // create tables
//        hashLinear linear = new hashLinear(n);
//        hashDuplo duplo = new hashDuplo(n);
//        hashEncadeamento enc = new hashEncadeamento(n);
//
//        long t1 = gen.inserirTodos(linear, dados);
//        System.out.println("Linear: time=" + t1 + "ms, colisoes=" + linear.getColisoes() + ", elementos=" + linear.getElementos());
//
//        long t2 = gen.inserirTodos(duplo, dados);
//        System.out.println("Duplo: time=" + t2 + "ms, colisoes=" + duplo.getColisoes() + ", elementos=" + duplo.getElementos());
//
//        long t3 = gen.inserirTodos(enc, dados);
//        System.out.println("Encadeamento: time=" + t3 + "ms, colisoes=" + enc.getColisoes() + ", elementos=" + enc.getElementos());
    }
    public static void testeLinear(registro[] dados){
        hashTable linearP = new hashLinear(1000);
        hashTable linearM = new hashLinear(10000);
        hashTable linearG = new hashLinear(100000);

        for  (int i = 0; i < dados.length; i++){
            linearP.inserir(dados[i]);
            linearM.inserir(dados[i]);
            linearG.inserir(dados[i]);

        }

    }
    public void testeEncadeamento(registro[] dados){
        hashTable encadeadaP = new hashEncadeamento(1000);
        hashTable encadeadaM = new hashEncadeamento(10000);
        hashTable encadeadaG = new hashEncadeamento(100000);

        for  (int i = 0; i < dados.length; i++){
            encadeadaP.inserir(dados[i]);
            encadeadaM.inserir(dados[i]);
            encadeadaG.inserir(dados[i]);
        }

    }
    public void testeDuplo(registro[] dados){
        hashTable duplaP = new hashDuplo(1000);
        hashTable duplaM = new hashDuplo(10000);
        hashTable duplaG = new hashDuplo(100000);

        for  (int i = 0; i < dados.length; i++){
            duplaP.inserir(dados[i]);
            duplaM.inserir(dados[i]);
            duplaG.inserir(dados[i]);
        }

    }
}
