package tabela;

public class hashDuplo implements hashTable{
    private registro[] tabela;
    private int colisoes;
    private int elementos;

    public hashDuplo(int tamanho){
        tabela = new registro[tamanho];
    }
    private int hash2(int chave) {
        // Segunda função hash — deve ser coprima de tamanho
        return 1 + (Math.abs(chave) % (tabela.length - 1));
    }


    protected int hash(int chave, int tentativa) {
        return (Math.abs(chave) + tentativa * hash2(chave)) % tabela.length;
    }

    @Override
    public void inserir(registro r) {
        int tentativa = 0;
        int indice;

        while(tentativa < tabela.length){
            indice = hash(hash2(tentativa), tentativa);
            if(tabela[indice] == null){
                tabela[indice] = r;
                elementos++;
                return;
            }
            else{
                colisoes++;
                tentativa++;
            }
        }
    }

    @Override
    public boolean buscar(registro r) {
        int tentativa = 0;
        int indice;

        while(tentativa < tabela.length){
            indice = hash(hash2(r.getCodigoNumerico()), tentativa);
            if(tabela[indice].getCodigoNumerico() == r.getCodigoNumerico()){
                return true;
            }
            if(tabela[indice] == null){
                return false;
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
        elementos = 0;
        colisoes = 0;
    }


}
