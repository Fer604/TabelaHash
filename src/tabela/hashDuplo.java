package tabela;

public class hashDuplo implements hashTable {
    private registro[] tabela;
    private int colisoes;
    private int elementos;

    public hashDuplo(int tamanho) {
        tabela = new registro[tamanho];
        colisoes = 0;
        elementos = 0;
    }

    // Primeira função hash (resto da divisão)
    private int hash1(int chave) {
        return Math.abs(chave) % tabela.length;
    }

    // Segunda função hash (deve ser coprima com o tamanho)
    private int hash2(int chave) {
        int h = 1 + (Math.abs(chave) % (tabela.length - 1));
        if(h==0){
            return 1;
        }
        return h; // garante que nunca será 0
    }

    @Override
    public void inserir(registro r) {
        int chave = r.getCodigoNumerico();
        int tentativa = 0;
        int indice;

        while (tentativa < tabela.length) {
            indice = Math.floorMod(hash1(chave) + tentativa * hash2(chave), tabela.length);//utiliza função hash da mesma forma em inserir e buscar

            if (tabela[indice] == null) {
                tabela[indice] = r;
                elementos++;
                return;
            } else {
                colisoes++;
                tentativa++;
            }
        }
    }

    @Override
    public boolean buscar(registro r) {
        int chave = r.getCodigoNumerico();
        int tentativa = 0;
        int indice;

        while (tentativa < tabela.length) {
            indice = Math.floorMod(hash1(chave) + tentativa * hash2(chave), tabela.length);//utiliza função hash da mesma forma em inserir e buscar


            if (tabela[indice] == null) {
                return false;
            }
            if (tabela[indice].getCodigoNumerico() == chave) {
                return true;
            }

            tentativa++;
        }
        return false;
    }

    @Override
    public int getColisoes() {
        return colisoes;
    }

    @Override
    public int getTamanho() {
        return tabela.length;
    }

    @Override
    public int getElementos() {
        return elementos;
    }

    @Override
    public double getFatorCarga() {
        return (double) elementos / tabela.length;
    }

    @Override
    public void limpar() {
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = null;
        }
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
