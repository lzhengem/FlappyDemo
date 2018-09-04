package com.lzhengem.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lzhengem.game.FlappyDemo;
import com.lzhengem.game.sprites.Bird;

public class PlayState extends State {
    private Bird bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        //view smaller portion of screen
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);


    }

    @Override
    public void render(SpriteBatch sb) {
        //zoom in the screen, so the bird looks bigger
        //and part of the bg is not showing (ie. the pipes)
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
