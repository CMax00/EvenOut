package com.github.CMax00.EvenOut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Viewport viewport;

    private Texture cardTexture;
    private Texture boxTexture;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Box box;
    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        // Textures
        cardTexture = new Texture(Gdx.files.internal("Card_BG.png"));
        boxTexture = new Texture(Gdx.files.internal("Box.png"));

        // Cards
        float cardWidth = cardTexture.getWidth() * 4f;
        float cardHeight = cardTexture.getHeight() * 4f;

        //Box
        // Calculates the size of the Box in proportion to the cards
        float targetCardWidth = (2f / 3f) * cardWidth;
        float targetCardHeight = (2f / 3f) * cardHeight;
        float paddingX = targetCardWidth * 0.05f;
        float paddingY = targetCardHeight * 0.13f;
        float paddingSideX = 13;
        float boxWidth = (7 * targetCardWidth) + (8 * paddingX) + 2 * paddingSideX;
        float boxHeight = targetCardHeight + (2 * paddingY);
        float boxX = stage.getWidth() - boxWidth - 8;
        float boxY = stage.getHeight() - boxHeight - 8;
        box = new Box(boxTexture, boxX, boxY, boxWidth, boxHeight, paddingX, paddingY, paddingSideX);

        //DrawPileTexture Cards
        //This are just th displayed cards not the actual pile
        float drawPileHeight = boxHeight;
        float drawPileWidth = boxHeight * 40/56;
        float drawPileY = boxY;
        float drawPileX = boxX + 2 * (-drawPileWidth - 10);
            //It works
        for (int i = 0; i < 4; i++){
            cards.add(new Card(cardTexture,
                drawPileX - i * drawPileWidth * 0.015f,
                drawPileY + i * drawPileHeight * 0.035f,
                (drawPileHeight - 4 * drawPileHeight * 0.035f) * 40 / 56,
                drawPileHeight - 4 * drawPileHeight * 0.035f,
                box, false));
        }

        // Add Actors
        stage.addActor(box);

        for (Card card : cards) {
            stage.addActor(card);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
