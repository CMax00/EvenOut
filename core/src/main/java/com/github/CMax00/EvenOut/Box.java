package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class Box extends Actor {
    private Texture texture;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Box(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        setPosition(x, y);
        setSize(width, height);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
