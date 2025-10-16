package tabela;

public class hashLinear implements hashTable {

    private registro[] tabela;
    private int colisoes;
    private int elementos;


    public hashLinear(int tamanho){
        tabela = new registro[tamanho];
        colisoes = 0;
        elementos = 0;
    }

    private int hash(int chave, int tentativa){
        return (chave+tentativa) % tabela.length;
    }
    @Override
    public void inserir(registro r) {
        int tentativa = 0;
        int indice;
        while(tentativa < tabela.length){
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

        while (tentativa < tabela.length) {
            indice = hash(r.getCodigoNumerico(), tentativa);

            if (tabela[indice] == null)
                return false;
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
        for (int i = 0; i < tabela.length; i++){tabela[i] = null;}
        colisoes = 0;
        elementos = 0;
    }
    public double[] calcularGaps() {
        int anterior = -1;
        int menor = Integer.MAX_VALUE;
        int maior = 0;
        int soma = 0;
        int qtd = 0;

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                if (anterior != -1) {
                    int gap = i - anterior;
                    soma += gap;
                    menor = Math.min(menor, gap);
                    maior = Math.max(maior, gap);
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

