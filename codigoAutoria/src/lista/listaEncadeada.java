package lista;
import tabela.registro;

public class listaEncadeada {

    private node Lista;
    public listaEncadeada(){
        this.Lista = null;
    }

    public void inserir (registro informacao){
        node no = new node();
        no.setInformacao(informacao);
        if(Lista == null){
            Lista=no;
        }
        else{
            node atual =Lista;
            while(atual.getProximo()!=null){
                atual = atual.getProximo();
            }
            atual.setProximo(no);
//          System.out.println("Nó atual no endereço" + atual);
        }
    }
    public void imprime(){
        node atual = Lista;
        while (atual != null) {
            System.out.print(atual.getInformacao()+ " -> ");
            atual= atual.getProximo();
        }
        System.out.println("Null");
    }

    public boolean vazia(){
        return Lista == null;
    }


    public void inserePrimeiro(registro registro){
        node novoPrimeiro = new node();

        novoPrimeiro.setInformacao(registro);

        novoPrimeiro.setProximo(Lista);

        Lista=novoPrimeiro;
    }
    public void insereDepois(node node, registro registro){
        node novoDepois = new node();
        novoDepois.setInformacao(registro);
        novoDepois.setProximo(node.getProximo());
        node.setProximo(novoDepois);


    }

    public void insereUltimo(registro registro){
        node novoUltimo = new node();
        node atual=Lista;
        novoUltimo.setInformacao(registro);
        while (atual.getProximo() != null) {
            atual= atual.getProximo();
        }

        atual.setProximo(novoUltimo);

    }

    public void insereOrdenado(registro informacao){
        node noOrdenado = new node();
        noOrdenado.setInformacao(informacao);
        int maior = -99999;
        node atual = Lista;
        int indiceDoMaior= 0;
        int ind = 0;
        while (atual.getProximo() != null) {
            if (atual.getInformacao().getCodigoNumerico() > maior) {
                maior = atual.getInformacao().getCodigoNumerico();
                indiceDoMaior = ind;
            }
            atual= atual.getProximo();
            ind++;
        }
        node velhoMaior = getNoFromPosi(indiceDoMaior);
        node proximoDoVelhoMaior = velhoMaior.getProximo();
        velhoMaior.setProximo(noOrdenado);
        noOrdenado.setProximo(proximoDoVelhoMaior);
    }

    public node removePrimeiro(){
        node removido =  Lista;
        Lista = Lista.getProximo();
        return removido;
    }

    public node removeUltimo(){
        node penultimo = Lista;
        while ((penultimo.getProximo()).getProximo() != null) {
            penultimo= penultimo.getProximo();
        }
        node removido = penultimo.getProximo();
        penultimo.setProximo(null);
        return removido;
    }



    public node remove(node no) {
        if (Lista == no) {
            return removePrimeiro();
        }

        node atual = Lista;
        node anterior = null;

        while (atual != null && atual != no) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual == null) {
            return null;
        }

        anterior.setProximo(atual.getProximo());

        return atual;
    }


    public node getNoFromPosi(int posicaoDesejada){
        node atual =Lista;
        int counter = 0;
        while(counter != posicaoDesejada){
            atual = atual.getProximo();
            counter++;
        }
        return atual;

    }
    public boolean contem(registro registro){
        int valor = registro.getCodigoNumerico();
        node atual = Lista;
        if (atual == null){
            return false;
        }
        while (atual.getProximo() != null) {
            if (atual.getInformacao().getCodigoNumerico() == valor) {
                return true;
            }
            atual= atual.getProximo();
        }
        return false;
    }
    public int tamanho(){
        node atual=Lista;
        int tamanho=0;

        if (atual ==null){
            return tamanho;
        }
        while (atual.getProximo() != null) {
            tamanho++;
            atual= atual.getProximo();
        }
        return tamanho;
    }
}
