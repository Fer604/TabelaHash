package tabela;

import lista.listaEncadeada;//

public class hashEncadeamento implements hashTable{
    private listaEncadeada[] tabela;
    private int colisoes;
    private int elementos;

    public hashEncadeamento(int tamanho){
        tabela = new listaEncadeada[tamanho];
        colisoes = 0;
        for(int i = 0; i < tabela.length; i++){tabela[i] = new listaEncadeada();}
    }
    public int hash(int chave){
        return Math.abs(chave) % tabela.length;
    }


    @Override
    public void inserir(registro registro){
        int indice = hash(registro.getCodigoNumerico());
        if (!tabela[indice].vazia()) colisoes++;
        tabela[indice].inserir(registro);
        elementos++;
    }
    @Override
    public boolean buscar(registro r) {
        int indice = hash(r.getCodigoNumerico());
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
    public int[] maioresListas() {
        int[] maiores = new int[3];
        for (var lista : tabela) {
            if (lista != null) {
                int tam = lista.tamanho();
                if (tam > maiores[0]) {
                    maiores[2] = maiores[1];
                    maiores[1] = maiores[0];
                    maiores[0] = tam;
                } else if (tam > maiores[1]) {
                    maiores[2] = maiores[1];
                    maiores[1] = tam;
                } else if (tam > maiores[2]) {
                    maiores[2] = tam;
                }
            }
        }
        return maiores;
    }

}
