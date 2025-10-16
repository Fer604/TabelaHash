import java.util.Random;
public class antigo_main {
    public static void main(String[] args) {
        hashLinear tabelaLinear = new hashLinear(20);

        registro a = new registro("10");
        registro b = new registro("30");
        registro c = new registro("50");

        tabelaLinear.inserir(a);
        tabelaLinear.inserir(b);
        tabelaLinear.inserir(c);

        System.out.println(tabelaLinear.buscar(a));
        System.out.println(tabelaLinear.buscar(new registro("99")));


    }
}