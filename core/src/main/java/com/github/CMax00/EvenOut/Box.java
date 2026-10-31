package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

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
        float newCardWidth = card.getWidth() * 2 / 3;
        float newCardHeight = card.getHeight() * 2 / 3;
        float newCardX = getX() + getWidth() - paddingX - (cards.size() * (paddingX + newCardWidth)) - newCardWidth - paddingSideX;
        float newCardY = getY() + paddingY;

        card.addAction(Actions.parallel(
            Actions.moveTo(newCardX, newCardY, 0.25f),
            Actions.sizeTo(newCardWidth,newCardHeight, 0.25f)
        ));

        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.removeIf(c -> c.equals(card));
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
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
        if (cards.size() >= 7) {
            return true;
        } else {
            return false;
        }
    }
}
