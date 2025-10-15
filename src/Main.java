import tabela.*;
import java.util.Random;

public class Main {
    // multiplicative hash (Knuth) for integer keys
    public static int multiplicativeHash(int key, int tableSize){
        // use unsigned multiplication behavior via long
        final long A = 2654435769L; // Knuth's multiplicative constant (32-bit)
        long unsignedKey = Integer.toUnsignedLong(key);
        long hashed = (unsignedKey * A) & 0xFFFFFFFFL;
        return (int)((hashed >>> (32 - Integer.numberOfTrailingZeros(tableSize))) % tableSize);
    }

    public static void main(String[] args){
        long seed = 12345L;
        int n = 20000; // number of registros to generate

        tabela.geradorDados gen = new tabela.geradorDados(seed);
        registro[] dados = gen.gerar(n);

        // print a few sample hashes for visibility
        System.out.println("Sample multiplicative hashes:");
        for (int i = 0; i < 5; i++){
            int codigo = dados[i].getCodigoNumerico();
            int h = multiplicativeHash(codigo, 4096);
            System.out.println(dados[i].getCodigo() + " -> " + h);
        }

        // create tables
        hashLinear linear = new hashLinear(n * 2);
        hashDuplo duplo = new hashDuplo(n * 2);
        hashEncadeamento enc = new hashEncadeamento(n * 2);

        long t1 = gen.inserirTodos(linear, dados);
        System.out.println("Linear: time=" + t1 + "ms, colisoes=" + linear.getColisoes() + ", elementos=" + linear.getElementos());

        long t2 = gen.inserirTodos(duplo, dados);
        System.out.println("Duplo: time=" + t2 + "ms, colisoes=" + duplo.getColisoes() + ", elementos=" + duplo.getElementos());

        long t3 = gen.inserirTodos(enc, dados);
        System.out.println("Encadeamento: time=" + t3 + "ms, colisoes=" + enc.getColisoes() + ", elementos=" + enc.getElementos());
    }
}
