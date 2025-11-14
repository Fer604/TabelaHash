package tabela;

public class hashDuplo implements hashTable {
    private registro[] tabela;
    private int colisoes;
    private int elementos;
    private int tamanhoTabela;

    public hashDuplo(int tamanho) {
        tabela = new registro[tamanho];
        colisoes = 0;
        elementos = 0;
        tamanhoTabela = tamanho;
    }

    // Primeira função hash (resto da divisão)
    private int hash1(int chave) {
        return moduloValor(chave) % tamanhoTabela;
    }

    // Segunda função hash (deve ser coprima com o tamanho)
    private int hash2(int chave) {
        int h = 1 + (moduloValor(chave) % (tamanhoTabela - 1));
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

        while (tentativa < tamanhoTabela) {
            indice = floorMod(hash1(chave) + tentativa * hash2(chave), tamanhoTabela);//utiliza função hash da mesma forma em inserir e buscar

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

        while (tentativa < tamanhoTabela) {
            indice = floorMod(hash1(chave) + tentativa * hash2(chave), tamanhoTabela);//utiliza função hash da mesma forma em inserir e buscar


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
        return tamanhoTabela;
    }

    @Override
    public int getElementos() {
        return elementos;
    }

    @Override
    public double getFatorCarga() {
        return (double) elementos / tamanhoTabela;
    }

    @Override
    public void limpar() {
        for (int i = 0; i < tamanhoTabela; i++) {
            tabela[i] = null;
        }
        colisoes = 0;
        elementos = 0;
    }
    public double[] calcularGaps() {
        int anterior = -1;
        int menor = 2147483647;
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
    private int moduloValor(int valor){
        if (valor <0){
            valor = valor * -1;
        }
        return valor;
    }
    public static int floorMod(int a, int b) {//o nome é igual da ultima função q foi usada por conveniencia
        return (a % b + b) % b;
    }

}
