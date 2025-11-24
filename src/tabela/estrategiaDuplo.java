package tabela;

public class estrategiaDuplo implements hashTable { //ESTRATÉGIA REHASHING DUPLO
    private registro[] tabela;
    private int colisoes;
    private int elementos;
    private int tamanhoTabela;
    private int funcaoHashBase;
    private byte[] estado;

    public estrategiaDuplo(int tamanho) {
        tamanhoTabela = hashes.nextPrimeAtLeast(tamanho);
        tabela = new registro[tamanhoTabela];
        colisoes = 0;
        elementos = 0;
        //funciona melhor com tamanho da tabela primo
        estado = new byte[tamanhoTabela];
    }

    // Primeira função hash (vareia)
    private int hash1(int chave) {
        switch (funcaoHashBase) {
            case 0:return (hashes.hDiv(chave,tamanhoTabela));
            case 1:return (hashes.hMul(chave));
            default: return (hashes.hMisto(chave));
        }
    }

    // Segunda função hash auxiliar, conhecida mais intimamente como step(deve ser coprima com o tamanho)
    private int hash2(int chave) {//1..tamanhoTabela-1 garantindo que seja coprimo com tamanhoTabela primo
        int s=((chave ^ 0x5bd1e995) & 0x7fffffff) % (tamanhoTabela-2);//Este valor 0x5bd1e995 é uma constante,e zera o bit de sinal com &0x7fff...
        //%tamanhoTabela-2 produz um valor entre 0 e tamanhoTabela−3
        return 1 + s;
        /*Então o passo (step) fica entre 1 e tamanhoTabela−2.
        Como tamanhoTabela é primo, isso garante que:
        step é sempre não zero
        step é coprimo com tamanhoTabela
        A sonda cobre toda a tabela → evita ciclos menores do que M.
         */
    }
    @Override
    public void setHashBase(int b){//meio inutil(podia ser chamada no construtor) mas ajuda a deixar mais claro
        funcaoHashBase = b;
    }

    @Override
    public void inserir(registro r) {
        int chave = r.getCodigoNumerico();
        int indice = hash1(chave);
        int step =hash2(chave);
        while (estado[indice]==1){//enquanto a posição que está tentando inserir estiver ocupada
            if (tabela[indice].getCodigoNumerico()==chave) {// numero/chave/registro/valor repetido
                return;
            }
            indice+=step; if (indice>=tamanhoTabela) indice-=tamanhoTabela; // avança em saltos (tem melhor dispersão que linear)
            colisoes++;
        }
        tabela[indice]=r; estado[indice]=1; elementos++;
    }

    @Override
    public boolean buscar(registro r) {
        int chave = r.getCodigoNumerico();
        int indice=hash1(chave), step=hash2(chave);
        while (estado[indice]!=0){
            if (estado[indice]==1 && tabela[indice].getCodigoNumerico()==chave) return true;
            indice+=step; if (indice>=tamanhoTabela) indice-=tamanhoTabela;
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
            estado[i] = 0;
        }
        colisoes = 0;
        elementos = 0;
    }
    public double[] calcularGaps() {
        double[] valores =  testeDesempenho.calculaGapsDeTabelaOcupada(estado,tamanhoTabela);
        return valores;
    }
}
