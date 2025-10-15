class Node {
    int codigo;
    Node prox;

    Node(int codigo) {
        this.codigo = codigo;
        this.prox = null;
    }
}

public class HashEncadeamento {
    private Node[] tabela;
    private int tamanho;
    private long colisoes;

    public HashEncadeamento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
        this.colisoes = 0;
    }

    private int funcaoHash(int codigo) {
        if (codigo < 0) codigo = -codigo;
        return codigo % tamanho;
    }

 
    public void inserir(int codigo) {
        int indice = funcaoHash(codigo);
        Node novo = new Node(codigo);

        if (tabela[indice] == null) {
            tabela[indice] = novo;
        } else {
            colisoes++;
            novo.prox = tabela[indice];
            tabela[indice] = novo;
        }
    }


    public boolean buscar(int codigo) {
        int indice = funcaoHash(codigo);
        Node atual = tabela[indice];

        while (atual != null) {
            if (atual.codigo == codigo) return true;
            atual = atual.prox;
        }
        return false;
    }


    public boolean remover(int codigo) {
        int indice = funcaoHash(codigo);
        Node atual = tabela[indice];
        Node anterior = null;

        while (atual != null) {
            if (atual.codigo == codigo) {
                if (anterior == null) tabela[indice] = atual.prox;
                else anterior.prox = atual.prox;
                return true;
            }
            anterior = atual;
            atual = atual.prox;
        }
        return false;
    }

    public long getColisoes() {
        return colisoes; 
    }
    
    public int getTamanho() {
        return tamanho;
    }
}
