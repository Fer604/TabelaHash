package tabela;

public class estrategiaLinear implements hashTable {

    private registro[] tabela;
    private int colisoes;
    private int elementos;
    private int tamanhoTabela;
    private byte[] estado;
    private int mask;
    private int hashBase = 0;

    public estrategiaLinear(int tamanho){ //REHASH LINEAR
        tamanhoTabela = tamanho;
        tabela = new registro[tamanhoTabela];
        estado = new byte[tamanhoTabela];

        int pow2 = hashes.nextPow2(tamanhoTabela);
        if (pow2 == tamanhoTabela){
            mask = tamanhoTabela-1;
        }
        else{
            mask =-1;
        }


        colisoes = 0;
        elementos = 0;
    }

    private int hash(int chave){
        int h;

        switch (hashBase) {
            case 1:
                h = hashes.hMul(chave);
                break;
            case 2:
                h = hashes.hMisto(chave);
                break;
            default://divisao
                h = chave;
        }

        // Se a tabela for potência de 2, uso máscara
        if (mask != -1)
            return (h & 0x7fffffff) & mask;

        // Caso contrário, hash por divisão tradicional
        return hashes.hDiv(h, tamanhoTabela);
    }
    @Override
    public void inserir(registro r) {
        int chave = r.getCodigoNumerico();
        int indice = hash(chave);

        while (estado[indice] == 1) {
            if (tabela[indice].getCodigoNumerico() == chave)
                return;  // já existe

            // avançar linearmente
            indice++;
            if (indice == tamanhoTabela) indice = 0;

            colisoes++;
        }

        tabela[indice] = r;
        estado[indice] = 1;
        elementos++;
    }


    @Override
    public boolean buscar(registro r) {
        int chave = r.getCodigoNumerico();
        int indice = hash(chave);

        while (estado[indice] != 0) {
            if (estado[indice] == 1 &&
                    tabela[indice].getCodigoNumerico() == chave)
                return true;

            indice++;
            if (indice == tamanhoTabela) indice = 0;
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

    @Override
    public void setHashBase(int b) {
        hashBase = b;
    }

    @Override
    public double[] calcularGaps() {
        double[] valores = testeDesempenho.calculaGapsDeTabelaOcupada(estado, tamanhoTabela);
        return valores;
    }


}

