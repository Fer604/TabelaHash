package tabela;

public class hashLinear implements hashTable {

    private registro[] tabela;
    private int colisoes;
    private int elementos;
    private int tamanhoTabela;

    public hashLinear(int tamanho){
        tabela = new registro[tamanho];
        colisoes = 0;
        elementos = 0;
        tamanhoTabela = tamanho;
    }

    private int hash(int chave, int tentativa){
        return (chave+tentativa) % tamanhoTabela;
    }
    @Override
    public void inserir(registro r) {
        int tentativa = 0;
        int indice;
        while(tentativa < tamanhoTabela){
            indice = hash(r.getCodigoNumerico(), tentativa);
            if(tabela[indice] == null){
                tabela[indice] = r;
                elementos++;
                return;
            }
            else{
                tentativa++;
                colisoes++;
            }
        }
    }

    @Override
    public boolean buscar(registro r) {
        int tentativa = 0;
        int indice;

        while (tentativa < tamanhoTabela) {
            indice = hash(r.getCodigoNumerico(), tentativa);

            if (tabela[indice] == null)
                return false; // parou em espaço vazio
            if (tabela[indice].getCodigoNumerico() == r.getCodigoNumerico())
                return true;

            tentativa++;
        }
        return false;
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
        for (int i = 0; i < tamanhoTabela; i++){tabela[i] = null;}
        colisoes = 0;
        elementos = 0;
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

