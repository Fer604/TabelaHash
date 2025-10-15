import tabela.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        // teste
        hashLinear tabelaLinear = new hashLinear(20);

        registro a = new registro("10");
        registro b = new registro("30");
        registro c = new registro("50");

        tabelaLinear.inserir(a);
        tabelaLinear.inserir(b);
        tabelaLinear.inserir(c);

        System.out.println(tabelaLinear.buscar(a));  // deve ser true
        System.out.println(tabelaLinear.buscar(new registro("99")));  // deve ser false     


    }
}