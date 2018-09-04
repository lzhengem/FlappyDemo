package com.lzhengem.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    //need position and texture and velocity(where its going)
    //vector3 uses 3 coordinates x,y,z
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
    }

    public void update(float dt){
        //everytime bird is updated and it is above the bottom of the screen, add gravity to it
        if (position.y >0)
            velocity.add(0,GRAVITY,0);
        //multiplies evrything by a deltatime
        velocity.scl(dt);
        position.add(0,velocity.y,0);
        //devide it back to original velocity
        velocity.scl(1/dt);
        //if the bird hits the bottom of the screen, keep it there
        if(position.y < 0){
            position.y = 0;
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public void jump(){
        velocity.y = 250;
    }
}
