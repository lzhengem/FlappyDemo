package com.lzhengem.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lzhengem.game.FlappyDemo;

public class PlayState extends State {
    private Texture bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        //zoom in the screen, so the bird looks bigger
        //and part of the bg is not showing (ie. the pipes)
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird, 50,50);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
