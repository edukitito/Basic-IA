package com.fatec;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int w1 = 0, w2 = 0, wb = 0;
        int b = 1, n = 1;

        Map<String, Integer> map = new HashMap<>();
        map.put("Kepler", 0b11);
        map.put("Einstein", 0b10);
        map.put("Bach", 0b00);
        map.put("Beethoven", 0b01);
        System.out.println(map);

        Map<Integer, Integer> esperado = new HashMap<>();
        esperado.put(3,1);
        esperado.put(2,1);
        esperado.put(1,0);
        esperado.put(0,0);

        Map<Integer, String> checagem = new HashMap<>();
        checagem.put(1, "Cientista");
        checagem.put(0, "Artista");

        int tentativa = 1;
        int voltasTotais=0;
        while(tentativa > 0) {
            tentativa = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                // Obtém o valor inteiro associado à chave
                int valor = entry.getValue();
                // Pega o primeiro bit do valor usando operações bitwise
                int primeiroBit = (valor & 0b10) >> 1;
                int segundoBit = (valor & 0b01);
                int erro = 1;
                int loops = 0;
                while (erro != 0) {
                    float v = Perceptron.ativacao(primeiroBit, w1, segundoBit, w2, b, wb);
                    int f = Perceptron.transferencia(v);
                    System.out.println("valor esperado: " + esperado.get(valor));
                    System.out.println("Valor obtido: " + f);
                    erro = Perceptron.teste(esperado.get(valor), f);
                    System.out.println("O valor de erro: " + erro);
                    if (erro == 0) {
                        loops++;
                        System.out.println("Loops necessários: " + loops);
                        System.out.println("Os valores: \nw1: " + w1 + "\nw2: " + w2 + "\nwb: " + wb);
                        break;
                    }
                    loops++;
                    w1 = Perceptron.novo(w1, n, erro, primeiroBit);
                    w2 = Perceptron.novo(w2, n, erro, segundoBit);
                    wb = Perceptron.novo(wb, n, erro, b);
                    System.out.println("Os valores: \nw1: " + w1 + "\nw2: " + w2 + "\nwb: " + wb);
                    tentativa++;
                    voltasTotais++;
                }
            }
        }
        System.out.println("Voltas totais: "+voltasTotais);
        System.out.println("Valores finais: \nw1: "+w1+"\nw2: "+w2+"\nwb: "+wb);
        int continuar;
        do {
            System.out.println(map);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite um valor: ");
            int valor = scanner.nextInt();
            int primeiroBit = (valor & 0b10) >> 1;
            int segundoBit = (valor & 0b01);

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == (byte) valor) {
                    System.out.println("Nome associado ao valor: " + entry.getKey());
                    break;
                }
            }

            float v = Perceptron.ativacao(primeiroBit, w1, segundoBit, w2, b, wb);
            int f = Perceptron.transferencia(v);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == valor) {
                    System.out.println(entry.getKey() + " é : " + checagem.get(f));
                    break;
                }
            }
            System.out.println("deseja continuar? 1 sim 2 não: ");
            continuar = scanner.nextInt();
        }while(continuar == 1);
    }
}