package tabela;

import lista.listaEncadeada;//

public class estrategiaEncadeamento implements hashTable{
    private listaEncadeada[] tabela;
    private int colisoes;
    private int elementos;
    private int tamanhoTabela;
    private int[] len; //comprimento por "bucket"/slot/lista na tabela hash
    private int funcaoHashBase;


    public estrategiaEncadeamento(int tamanho){
        tamanhoTabela = tamanho;
        tabela = new listaEncadeada[tamanhoTabela];
        len = new int[tamanhoTabela];
        for(int i = 0; i < tamanhoTabela; i++) {
            tabela[i] = new listaEncadeada();
            len[i] = 0;
        }
        colisoes = 0;
        elementos = 0;
    }
    public int hash(int chave){
        switch (funcaoHashBase) {
            case 0:return (hashes.hDiv(chave,tamanhoTabela));
            case 1:return (hashes.hMul(chave));
            default: return (hashes.hMisto(chave));
        }
    }

    @Override
    public void setHashBase(int b) {
        funcaoHashBase = b;
    }


    @Override
    public void inserir(registro registro){
        int indice = hash(registro.getCodigoNumerico());
        if (!tabela[indice].vazia()) colisoes++;//tinha que conta as colisões
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
        return tamanhoTabela;
    }

    @Override
    public  int getElementos() {
        return elementos;
    }

    @Override
    public double getFatorCarga(){
        return (double) elementos /tamanhoTabela;
    }

    @Override
    public void limpar(){
        for (int i = 0; i < tamanhoTabela; i++){tabela[i] = new listaEncadeada();}
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
    public int moduloValor(int valor){
        if (valor <0){
            valor = valor* -1;
        }
        return valor;
    }
    public double[] calcularGaps() {
        int anterior = -1;
        int menor = 2147483647; //int limite
        int maior = 0;
        int soma = 0;
        int qtd = 0;

        for (int i = 0; i < tamanhoTabela; i++) {
            if (tabela[i] != null) {
                if (anterior != -1) {
                    int gap = i - anterior;
                    soma += gap;
                    if (gap < menor) {
                        menor = gap;
                    }
                    if (gap > maior) {
                        maior = gap;
                    }
                    qtd++;
                }
                anterior = i;
            }
        }

        double media;
        if (qtd > 0) {
            media = (double) soma / qtd;
        } else {
            media = 0;
        }

        return new double[]{menor, maior, media};
    }
}
