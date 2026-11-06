package com.github.CMax00.EvenOut;

import java.util.Random;

public class DrawPile {
    boolean[][] cardsSetup = new boolean[63][6];
    int currentCard = -1;

    public DrawPile() {
        Random r = new Random();
        for (int i = 0; i < 63; i++) {
            for (int u = 0; u < 6; u++) {
                cardsSetup[i][u] = r.nextBoolean();
            }
            for (int l = 0; l < i; l++) {
                if (cardsSetup[l] == cardsSetup[i]) {
                    i--;
                    break;
                }
            }
        }
    }

    public boolean[] drawCard() {
        currentCard++;
        return cardsSetup[currentCard];
    }
}
