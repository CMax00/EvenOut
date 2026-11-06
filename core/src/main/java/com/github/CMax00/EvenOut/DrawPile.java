package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;
import java.util.ArrayList;

public class DrawPile {
    ArrayList<Texture> cardTextures;
    boolean[][] cardsSetup = new boolean[63][6];

    public DrawPile(ArrayList<Texture> cardTextures) {
        this.cardTextures = cardTextures;

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
}
