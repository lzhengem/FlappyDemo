package com.lzhengem.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    //moving forwards
    private static final int MOVEMENT = 100;
    //need position and texture and velocity(where its going)
    //vector3 uses 3 coordinates x,y,z
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;
    private Animation birdAnimation;

    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        texture  = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth()/3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        //everytime bird is updated and it is above the bottom of the screen, add gravity to it
        if (position.y >0)
            velocity.add(0,GRAVITY,0);
        //multiplies evrything by a deltatime
        velocity.scl(dt);
        //as time goes on, move forward and drops down
        position.add(MOVEMENT * dt,velocity.y,0);
        //devide it back to original velocity
        velocity.scl(1/dt);
        //if the bird hits the bottom of the screen, keep it there
        if(position.y < 0){
            position.y = 0;
        }

        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){

        velocity.y = 250;
        flap.play(0.5f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){

        texture.dispose();
        flap.dispose();
    }
}
