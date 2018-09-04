package com.lzhengem.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lzhengem.game.FlappyDemo;
import com.lzhengem.game.sprites.Bird;
import com.lzhengem.game.sprites.Tube;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private Tube tube;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        //view smaller portion of screen
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        bg = new Texture("bg.png");
        tube = new Tube(100);
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
        //draw the bg wherever the camera is positioned
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        sb.draw(tube.getTobTube(), tube.getPosTopTube().x,tube.getPosTopTube().y);
        sb.draw(tube.getBottomTube(),tube.getPosBotTube().x, tube.getPosBotTube().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
