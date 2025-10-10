public class registro {
    private final String codigo;

    public registro(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
    public int getCodigoNumerico() {
        return Integer.parseInt(codigo);
    }

    @Override
    public String toString() {
        return codigo;
    }
}
