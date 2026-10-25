package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class Box extends Actor {
    private Texture texture;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private float paddingX;
    private float paddingSideX;
    private float paddingY;

    public Box(Texture texture, float x, float y, float width, float height, float paddingX, float paddingY, float paddingSideX) {
        this.texture = texture;
        setPosition(x, y);
        setSize(width, height);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        this.paddingSideX = paddingSideX;
    }

    public void addCard(Card card) {
        float newCardX = getX() + getWidth() - paddingX - (cards.size() * (paddingX + card.getWidth())) - card.getWidth() - paddingSideX;
        float newCardY = getY() + paddingY;
        card.setPosition(newCardX, newCardY);
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.removeIf(c -> c.equals(card));
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            float newCardX = getX() + getWidth() - paddingX - (i * (paddingX + c.getWidth())) - c.getWidth() - paddingSideX;
            float newCardY = getY() + paddingY;
            c.setPosition(newCardX, newCardY);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public boolean isBoxFull() {
        if (cards.size() >= 7) {
            return true;
        } else {
            return false;
        }
    }
}
