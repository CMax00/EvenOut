package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Card extends Actor {

    private Texture texture;

    public Card(Texture texture, float x, float y, float width, float height, Box box) {
        this.texture = texture;
        setPosition(x, y);
        setSize(width, height);

        this.addListener(
            new DragListener() {
                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    moveBy(x - getTouchDownX(), y - getTouchDownY());

                    Vector2 touchCords = new Vector2(event.getStageX(), event.getStageY());
                    box.stageToLocalCoordinates(touchCords);
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);

                    Vector2 touchCords = new Vector2(event.getStageX(), event.getStageY());
                    box.stageToLocalCoordinates(touchCords);

                    if (box.hit(touchCords.x, touchCords.y, true) != null) {
                        placeInBox();
                    }
                }
            }
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    private void placeInBox() {

    }
}
