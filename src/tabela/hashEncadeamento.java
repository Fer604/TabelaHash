package tabela;

import lista.listaEncadeada;//

public class hashEncadeamento implements hashTable{
    private listaEncadeada[] tabela;
    private int colisoes;

    public hashEncadeamento(int tamanho){
        tabela = new listaEncadeada[tamanho];
        colisoes = 0;
        for(int i = 0; i < tabela.length; i++){tabela[i] = new listaEncadeada();}
    }
    public int hash(int chave){
        return (chave) % tabela.length;
    }
    
    @Override
    public void inserir(registro registro){
        int indice = hash(registro.getCodigoNumerico());
        tabela[indice].inserir(registro);//esse inserir() é da lista encadeada
    }
    @Override
    public boolean buscar(registro r) {
        //a implementar
        return false;
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
