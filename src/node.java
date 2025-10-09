public class node {
    private Integer informacao;
    private node proximo;
    public node()
    {
        this.informacao = null;
        this.proximo = null;
    }
    public Integer getInformacao() {
        return informacao;
    }
    public void setInformacao(Integer informacao) {
        this.informacao = informacao;
    }
    public node getProximo() {
        return proximo;
    }
    public void setProximo(node proximo) {
        this.proximo = proximo;
    }
    public void getUltimo(){

    }
}
