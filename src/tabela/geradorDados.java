package tabela;

import java.util.Random;
import java.util.function.Consumer;

/** Seeded data generator for registro objects. */
public class geradorDados {
    private final Random rnd;

    public geradorDados(long seed) {
        this.rnd = new Random(seed);
    }

    /** Generate an array of registros */
    public registro[] gerar(int tamanho) {
        registro[] dados = new registro[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int num = rnd.nextInt(999999999);
            dados[i] = new registro(Integer.toString(num));
        }
        return dados;
    }

    /** Insert all registros into a hashTable and return elapsed ms */
    public long inserirTodos(hashTable tabela, registro[] dados) {
        long inicio = System.currentTimeMillis();
        for (registro r : dados) tabela.inserir(r);
        long fim = System.currentTimeMillis();
        return fim - inicio;
    }

    /** Allow custom processing for each registro */
    public void forEachRegistro(registro[] dados, Consumer<registro> consumer) {
        for (registro r : dados) consumer.accept(r);
    }
}
