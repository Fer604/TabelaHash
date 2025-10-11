package tabela;

import lista.listaEncadeada;

public class hashLinear implements hashTable {

    private listaEncadeada[] tabela;
    private int colisoes;



    @Override
    public void inserir(registro r) {

    }

    @Override
    public boolean buscar(registro r) {
        return false;
    }

    @Override
    public int getColisoes() {
        return 0;
    }
    @Override
    public  int getTamanho() {
        return 0;
    }

    @Override
    public  int getElementos() {
        return 0;
    }

    @Override
    public double getFatorCarga(){
        return 0;
    }

    @Override
    public void limpar(){

    }

}

