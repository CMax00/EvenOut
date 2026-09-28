package com.github.CMax00.EvenOut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private ArrayList<Card> cards = new ArrayList<Card>();

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        cardTexture = new Texture(Gdx.files.internal("Card.png"));

        for (int i = 0; i < 7; i++){
            cards.add(new Card(cardTexture, i * 50, 0, cardTexture.getWidth() * 0.7f, cardTexture.getHeight() * 0.7f));
        }

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
