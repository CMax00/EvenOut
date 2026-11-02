package com.github.CMax00.EvenOut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.ArrayList;

public class Card extends Actor {

    private final ArrayList<Texture> textures;
    private final Box box;
    private boolean inBox = false;
    private boolean active;
    private ArrayList<Boolean> items;

    public Card(ArrayList<Texture> textures, float x, float y, float width, float height, Box box, boolean active) {
        this.textures = textures;
        setPosition(x, y);
        setSize(width, height);
        this.box = box;
        this.active = active;
        if (!active) {
            return;
        }
        this.addListener(
            new DragListener() {
                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);

                    toFront();

                    if (inBox) {
                        takeOutofBox();
                    }
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    moveBy(x - getTouchDownX(), y - getTouchDownY());

                    Vector2 touchCords = new Vector2(event.getStageX(), event.getStageY());
                    box.stageToLocalCoordinates(touchCords);
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);

                    // converts Card cords to global then to Box cords
                    Vector2 touchCords = new Vector2(event.getStageX(), event.getStageY());
                    box.stageToLocalCoordinates(touchCords);

                    // checks if card is dropped in box
                    if (box.hit(touchCords.x, touchCords.y, true) != null) {
                        placeInBox();
                    }

                }
            }
        );
    }

    public Card(ArrayList<Texture> textures, float x, float y, float width, float height, Box box, boolean active, ArrayList<Boolean> items) {
        this(textures,x,y,width,height,box,active);
        this.items = items;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (items == null) {
            batch.draw(textures.get(0), getX(), getY(), getWidth(), getHeight());
        } else {
            for (int i = 0; i < 6; i++) {
                if (items.get(i)) {
                    batch.draw(textures.get(i + 1), getX(), getY(), getWidth(), getHeight());
                }
            }
        }
    }

    private void placeInBox() {
        if (box.isBoxFull()) {
            return;
        }
        // the transformation happens in addCard
        box.addCard(this);
        inBox = true;
    }

    private void takeOutofBox() {
        inBox = false;
        setWidth(getWidth() / (2f / 3f));
        setHeight(getHeight() / (2f / 3f));
        box.removeCard(this);
    }
}
