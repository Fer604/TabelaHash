package tabela;

import lista.listaEncadeada;

public class hashLinear implements hashTable {

    private listaEncadeada[] tabela;
    private int colisoes;
    private int elementos;


    public hashLinear(int tamanho){
        tabela = new listaEncadeada[tamanho];
        for (int i = 0; i < tabela.length; i++){tabela[i] = new listaEncadeada();}
        colisoes = 0;
        elementos = 0;
    }

    private int hash(int chave, int tamanho){
        return chave % tamanho;
    }
    @Override
    public void inserir(registro r) {
        int indice = hash(r.getCodigoNumerico(), tabela.length);
        if (!tabela[indice].vazia()) colisoes++;
        tabela[indice].inserir(r);
        elementos++;
    }

    @Override
    public boolean buscar(registro r) {
        int indice = hash(r.getCodigoNumerico(), tabela.length);
        return tabela[indice].contem(r);
    }

    @Override
    public int getColisoes() {
        return colisoes;
    }
    @Override
    public  int getTamanho() {
        return tabela.length;
    }

    @Override
    public  int getElementos() {
        return elementos;
    }

    @Override
    public double getFatorCarga(){
        return (double) elementos /tabela.length;
    }

    @Override
    public void limpar(){
        for (int i = 0; i < tabela.length; i++){tabela[i] = new listaEncadeada();}
        colisoes = 0;
        elementos = 0;
    }

}

