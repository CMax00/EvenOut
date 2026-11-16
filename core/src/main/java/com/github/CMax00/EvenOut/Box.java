package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.Arrays;

public class Box extends Actor {
    private final Texture texture;
    private final ArrayList<Card> cardsInBox = new ArrayList<Card>();
    private final float paddingX;
    private final float paddingSideX;
    private final float paddingY;

    public Box(Texture texture, float x, float y, float width, float height, float paddingX, float paddingY, float paddingSideX) {
        this.texture = texture;
        setPosition(x, y);
        setSize(width, height);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        this.paddingSideX = paddingSideX;
    }

    public void addCard(Card card) {
        float newCardWidth = card.getWidth() * 2 / 3;
        float newCardHeight = card.getHeight() * 2 / 3;
        float newCardX = getX() + getWidth() - paddingX - (cardsInBox.size() * (paddingX + newCardWidth)) - newCardWidth - paddingSideX;
        float newCardY = getY() + paddingY;

        card.addAction(Actions.parallel(
            Actions.moveTo(newCardX, newCardY, 0.25f),
            Actions.sizeTo(newCardWidth, newCardHeight, 0.25f)
        ));

        cardsInBox.add(card);
        checkAllEven();
    }

    public void removeCard(Card card) {
        cardsInBox.removeIf(c -> c.equals(card));
        //fill place between the remaining cards
        for (int i = 0; i < cardsInBox.size(); i++) {
            Card c = cardsInBox.get(i);
            float newCardX = getX() + getWidth() - paddingX - (i * (paddingX + c.getWidth())) - c.getWidth() - paddingSideX;
            float newCardY = getY() + paddingY;
            c.addAction(Actions.moveTo(newCardX, newCardY, 0.25f));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public boolean isBoxFull() {
        return cardsInBox.size() >= 7;
    }

    public void checkAllEven() {
        // count each item
        int[] items = new int[6];
        for (Card card : cardsInBox) {
            for (int i = 0; i < 6; i++) {
                if (card.getItems()[i]) {
                    items[i] += 1;
                }
            }
        }
        // check if all are even
        for (int item : items) {
            if (item % 2 != 0) {
                return;
            }
        }
    }

}
