package com.lzhengem.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    //Vector is xyz system
    protected Vector3 mouse;
    //to create a pause state on top of the game state
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    //dt = detatime the difference between one frame rendered and next frame rendered
    public abstract void update(float dt);
    //spritebatch container for things we need to render to screen
    public abstract void render(SpriteBatch sb);


}
