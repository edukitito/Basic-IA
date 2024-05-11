package com.fatec;

public class Perceptron {
    public static float ativacao(int x1, int w1, int x2, int w2, int b, int wb) {
        return x1 * w1 + x2 * w2 + b * wb;
    }
    public static int transferencia(float v){
        if(v > 0){
            return 1;
        }else {
            return 0;
        }
    }

    public static int teste(int esperado, int obtido){
        return esperado - obtido;
    }

    public static int novo(int w, int n, int e, int x){
        return w + n * e * x;
    }

}
