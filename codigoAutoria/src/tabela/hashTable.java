package tabela;

public interface hashTable {

    void inserir(registro r);

    boolean buscar(registro r);

    int getColisoes();

    int getTamanho();

    int getElementos();

    double getFatorCarga();

    void limpar();

}
