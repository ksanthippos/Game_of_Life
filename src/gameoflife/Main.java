package gameoflife;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        GameOfLife gol = new GameOfLife(3, 3);

        int[][] taulukko = new int[3][3];
        taulukko[0][0] = 1;
        taulukko[0][1] = 1;
        taulukko[1][1] = 1;
        taulukko[2][2] = 1;


        System.out.println(gol.elossaOleviaNaapureita(taulukko, 0, 0));
        System.out.println(gol.elossaOleviaNaapureita(taulukko, 1, 0));
        System.out.println(gol.elossaOleviaNaapureita(taulukko, 1, 1));
        System.out.println(gol.elossaOleviaNaapureita(taulukko, 2, 2));

    }
}
