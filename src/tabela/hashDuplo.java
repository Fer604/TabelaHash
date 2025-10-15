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
        // hash duplo: usa duas funções hash diferentes
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
                return;
            }
            else{
                colisoes++;
                tentativa++;
            }
        }
        elementos++;
    }

    @Override
    public boolean buscar(registro r) {
        int tentativa = 0;
        int indice;

        while(tentativa < tabela.length){
            indice = hash(hash2(tentativa), tentativa);
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
        //a implementar
        return 0;
    }

    @Override
    public void limpar() {
        //a implementar
    }

}
