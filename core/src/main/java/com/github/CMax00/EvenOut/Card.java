package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Card extends Actor {

    private Texture texture;

    public Card(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        setPosition(x, y);
        setSize(width, height);

        this.addListener(
            new DragListener() {
                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    moveBy(x - getTouchDownX(), y - getTouchDownY());
                }
            }
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
