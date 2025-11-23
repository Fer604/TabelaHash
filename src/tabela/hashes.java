package tabela;

public class hashes {
    // Hash por divisão
    public static int hDiv(int x, int M) {
        x ^= (x >>> 16);//XOR com a própria versão deslocada 16 espalha bits altos para os 16 bits baixos
        x = x * 0x7feb352d;// constante "knuth-like"
        x ^= (x >>> 15);// misturar ainda mais, agora com deslocamento de 15 bits
        return (x & 0x7fffffff) % M;//zera o bit de sinal e retorna no intervalo de 0 à M-1,produzindo o índice real na tabela hash
    }
    // Hash por multiplicação (usa constante de Knuth + mix)
    public static int hMul(int x) {
        long z = ((long)x * 0x9E3779B1L) & 0xFFFF_FFFFL; // Knuth
        z ^= (z >>> 16);//XOR com a própria versão deslocada 16 espalha bits altos para os 16 bits baixos
        z = (z * 0x85EBCA6BL) & 0xFFFF_FFFFL;//multiplicacção pela constante de knuth(0x85...) mantém apenas 32 bits com o "&"
        z ^= (z >>> 13);//Último passo de mistura.Remexe bits médios e baixos.
        return (int)z;//Retorna os 32 bits finais como um inteiro Java
    }
    // Um "misto" de shifts (xorshift) — serve como terceira variação.
    public static int hMisto(int x) {
        x ^= (x << 13);//desloca bits para a esquerda (joga bits altos fora).XOR mistura esses novos padrões com o valor original.
        x ^= (x >>> 17);//Usa versão deslocada para a direita.Mistura bits altos nos baixos.
        x ^= (x << 5);//Mistura final de bits.
        return x;//hash rápido, mas menos robusto
    }
    // Acha o próximo primo >= n (duplo hashing e encadeamento).
    public static int nextPrimeAtLeast(int n){
        if (n <= 2) return 2;
        int p;
        if (n%2 == 0){
             p =n+1;
        }
        else{
            p = n;
        }
        while (!isPrime(p)) p += 2;
        return p;
    }
    // Teste se é primo simples
    public static boolean isPrime(int x){
        if (x < 2) return false;
        if (x % 2 == 0) return x == 2;
        for (int i=3; i*(long)i <= x; i+=2) if (x % i == 0) return false;
        return true;
    }
    // Próxima potência de 2 >= v (útil para linear probing com máscara).
    public static int nextPow2(int v){
        int p=1;
        while (p < v && p>0) p<<=1;
        if (p>0) return p;
        return v;
    }
    // Divisão inteira arredondando para cima (ceil).
    public static int ceilDiv(int a, int b){
        return (a + b - 1) / b;
    }
}

