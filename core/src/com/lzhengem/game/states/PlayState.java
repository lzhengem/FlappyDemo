package com.lzhengem.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lzhengem.game.FlappyDemo;
import com.lzhengem.game.sprites.Bird;
import com.lzhengem.game.sprites.Tube;

public class PlayState extends State {
    //the spaces between each tubes
    private static final int TUBE_SPACING = 125;
    //how many total tubes we will have
    private static final int TUBE_COUNT = 4;
    //ground was too tall, so offset it further down the screen
    private static final int GROUND_Y_OFFSET = -50;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        //view smaller portion of screen
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2((cam.position.x-cam.viewportWidth/2),GROUND_Y_OFFSET);
        groundPos2 = new Vector2(((cam.position.x-cam.viewportWidth/2) + ground.getWidth()),GROUND_Y_OFFSET);

        //create all your tubes up to the max
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i *(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
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
        updateGround();
        bird.update(dt);
        //set the camera to follow the bird. move it 80 pixels to the right
        cam.position.x = bird.getPosition().x + 80;


        //reposition tubes
//        for(Tube tube: tubes){
        for(int i = 0; i< tubes.size; i++){
            Tube tube = tubes.get(i);
            //if the tube is to the left of the screen, move it
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) *TUBE_COUNT));
            }
            //loops through to check if each tube hits the bird
            if (tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }

        //if the bird hits the ground, reset it
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        cam.update(); //tells libgdx that the camera has been updated


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
        //draw the tubes
        for (Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube: tubes){
            tube.dispose();
        }
        ground.dispose();
        System.out.println("Play state disposed");


    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth() ){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth() ){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
