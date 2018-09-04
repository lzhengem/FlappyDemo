package com.lzhengem.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lzhengem.game.FlappyDemo;

public class MenuState extends State{
    //set up the background and play button using the png from assess
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //draw the background on the sprite
        sb.draw(background,0,0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        //set the play button in the middle of the screen
        sb.draw(playBtn,(FlappyDemo.WIDTH/2) - (playBtn.getWidth() / 2), FlappyDemo.HEIGHT/2);
        sb.end();

    }
}
