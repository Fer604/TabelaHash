package tabela;

public interface hashTable {
    //interface base(os os metodos que uma tabela PRECISA ter)

    void inserir(registro r);

    boolean buscar(registro r); // como pela especificação do trabalho "implementar e analisar o desempenho de diferentes tabelas hash em Java." só nos interessa se o tabela.registro existe ou não para a analise de desempenho logo não retorna o tabela.registro e sim boolean

    int getColisoes();

    int getTamanho();

    int getElementos(); //qtd total de elementos aramazenados(acaboou não sendo utilizado)

    double getFatorCarga();//retorna o fator de carga (load factor = elementos / tamanho da tabela).

    void limpar();//sai sujeira

    void setHashBase(int b);

}
