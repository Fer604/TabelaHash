package tabela;
import java.util.Random;
public class geradorDados {
    public static registro[] gerar(long seed,int tamanho){
        Random r = new Random(seed);
        registro[] dados = new registro[tamanho];
        for (int i=0;i<tamanho;i++){
            int num =  r.nextInt(999999999);
            dados[i] = new registro(Integer.toString(num));
        }
        return dados;
    }
}
