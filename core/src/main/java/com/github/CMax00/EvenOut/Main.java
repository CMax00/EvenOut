package com.github.CMax00.EvenOut;

import com.badlogic.gdx.Game;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
