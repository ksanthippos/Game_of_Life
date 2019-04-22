package gameoflife;

import java.util.Random;

public class GameOfLife {

    private int[][] taulukko;

    public GameOfLife(int leveys, int korkeus) {
        this.taulukko = new int[leveys][korkeus];
    }

    public void alustaSatunnaisesti() {
        Random satunnaistaja = new Random();

        int x = 0;
        while (x < taulukko.length) {

            int y = 0;
            while (y < taulukko[x].length) {
                if (satunnaistaja.nextDouble() < 0.2) {
                    taulukko[x][y] = 1;
                }

                y++;
            }
            x++;
        }
    }

    public void kehity() {
        // säännöt kehittymiselle:

        // 1. jokainen elossa oleva alkio, jolla on alle 2 elossa olevaa naapuria kuolee
        // 2. jokainen elossa oleva alkio, jolla on 2 tai 3 elossa olevaa naapuria pysyy hengissä
        // 3. jokainen elossa oleva alkio, jolla on 4 tai enemmän naapureita kuolee
        // 4. jokainen kuollut alkio, jolla on tasan 3 naapuria muuttuu eläväksi
        // taulukossa arvo 1 kuvaa elävää alkiota, arvo 0 kuollutta alkiota
        int[][] kopio = new int[this.taulukko.length][this.taulukko[0].length];

        // kopioidaan arvot
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[i].length; j++) {
                kopio[i][j] = taulukko[i][j];
            }
        }

        // sääntöjen tarkistus
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[i].length; j++) {

                if (taulukko[i][j] == 1 && (elossaOleviaNaapureita(taulukko, i, j) < 2 || elossaOleviaNaapureita(taulukko, i, j) >= 4))
                    kopio[i][j] = 0;

                else if (taulukko[i][j] == 1 && (elossaOleviaNaapureita(taulukko, i, j) == 2 || elossaOleviaNaapureita(taulukko, i, j) == 3))
                    continue;

                else if (taulukko[i][j] == 0 && elossaOleviaNaapureita(taulukko, i, j) == 3)
                    kopio[i][j] = 1;

            }
        }

        // arvojen päivitys
        taulukko = kopio;

    }

    public int[][] getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(int[][] taulukko) {
        this.taulukko = taulukko;
    }

    public int elossaOleviaNaapureita(int[][] taulukko, int x, int y) {


        int maara = 0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                try {
                    if (i == x && j == y)
                        continue;

                    else if (taulukko[i][j] == 1)
                        maara++;
                }
                catch (ArrayIndexOutOfBoundsException e) {  // estetään taulukon ulkopuolelle meno poikkeuskäsittelyllä
                    continue;
                }

            }
        }

        return maara;

  /*
        // Alkuperäinen, mutta hieman tehottomampi tapa. Toisaalta ilman poikkeuskäsittelyä..

        int maara = 0;
        int X = x + 1;
        int Y = y + 1;


        *//*
        luodaan uusi taulukko annetun ympärille, jolloin voidaan tarkistaa pienen taulukon ympärillä olevat
        ruudut helposti ilman rajojen ylittämistä
        *//*

        int taulukkoReunoilla[][] = new int[taulukko.length + 2][taulukko.length + 2];

        // täytetään isompi taulukko ensin nollilla
        for (int i = 0; i < taulukkoReunoilla.length; i++) {
            for (int j = 0; j < taulukkoReunoilla[i].length; j++) {
                taulukkoReunoilla[i][j] = 0;
            }
        }
        // pienemmän arvot isomman sisään, reunoille jää nollia
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[i].length; j++) {
                taulukkoReunoilla[i + 1][j + 1] = taulukko[i][j];
            }
        }


        for (int i = X - 1; i < X + 2; i++) {
            for (int j = Y - 1; j < Y + 2; j++) {
                if (i == X && j == Y)
                    continue;
                else if (taulukkoReunoilla[i][j] == 1)
                    maara++;
            }
        }

        return maara;*/
    }
}
