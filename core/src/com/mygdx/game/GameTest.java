package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class GameTest extends ApplicationAdapter {
    // instance variables
    SpriteBatch batch;                                  // ask john about spritebatch
    Texture ducko;
    private OrthographicCamera camera;                  // ask john how camera works
    private Rectangle duck;
    private Music duckSong;
    private int xSpeed;
    private int ySpeed;
    private int speed;
    private double acceleration;
    private String direction;
    private int dashDistance;
    private String lastDirection;


    @Override
    public void create () {
        batch = new SpriteBatch();
        ducko = new Texture("ducky.png");
        duck = new Rectangle();
        camera = new OrthographicCamera();

        // initialize variables
        xSpeed = 0;
        ySpeed = 0;
        speed = 600;
        acceleration = .8;
        dashDistance = 2000;
        direction = null;

        // sets up duck
        camera.setToOrtho(false, 1920 * 4, 1080 * 4);
        duck.x = 800/2 - 64/2;
        duck.y = 20;
        duck.width = 64;
        duck.height = 64;

        // music
        duckSong = Gdx.audio.newMusic(Gdx.files.internal("ducksong.mp3"));
        duckSong.setLooping(true);
        duckSong.play();
    }

    @Override
    public void render () {
        // whatever this means i think it renders stuff
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(ducko, duck.x, duck.y);
        batch.end();

        lastDirection = null;

        // movement
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            xSpeed -= speed;
            //duck.x -= 600 * Gdx.graphics.getDeltaTime();
            lastDirection += 'L';
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            xSpeed += speed;
            //duck.x += 600 * Gdx.graphics.getDeltaTime();
            lastDirection += 'R';
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            ySpeed += speed;
            //duck.y += 600 * Gdx.graphics.getDeltaTime();
            lastDirection += 'U';
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            ySpeed -= speed;
            //duck.y -= 600 * Gdx.graphics.getDeltaTime();
            lastDirection += 'D';
        }
        if(duck.y + ySpeed * Gdx.graphics.getDeltaTime() < (1080 - 64) * 4 && duck.y + ySpeed * Gdx.graphics.getDeltaTime() > 0) {
            duck.y += ySpeed * Gdx.graphics.getDeltaTime();
        }
        if(duck.x + xSpeed * Gdx.graphics.getDeltaTime() < (1920 - 64) * 4 && duck.x + xSpeed * Gdx.graphics.getDeltaTime() > 0) {
            duck.x += xSpeed * Gdx.graphics.getDeltaTime();
        }
        ySpeed *= acceleration;
        xSpeed *= acceleration;

        direction = checkMovement(xSpeed, ySpeed);

        // dash
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(direction != null) {
                if(direction == "UR") {
                    duck.y += dashDistance * Gdx.graphics.getDeltaTime();
                    duck.x += dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "DR") {
                    duck.y -= dashDistance * Gdx.graphics.getDeltaTime();
                    duck.x += dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "UL") {
                    duck.y += dashDistance * Gdx.graphics.getDeltaTime();
                    duck.x -= dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "DL") {
                    duck.y -= dashDistance * Gdx.graphics.getDeltaTime();
                    duck.x -= dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "U") {
                    duck.y += dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "D") {
                    duck.y -= dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "R") {
                    duck.x += dashDistance * Gdx.graphics.getDeltaTime();
                }
                else if(direction == "L") {
                    duck.x -= dashDistance * Gdx.graphics.getDeltaTime();
                }
            }
        }

    }

    @Override
    public void dispose () {
        // gets rid of stuff?
        batch.dispose();
        ducko.dispose();
    }

    public String checkMovement(double xSpd, double ySpd) {
        String myDirection;
        if(ySpeed > 0 && (xSpeed > -250 && xSpeed < 250 )){
            myDirection = "U";
        }
        else if(ySpeed < 0 && (xSpeed > -250 && xSpeed < 250 )) {
            myDirection = "D";
        }
        else if((ySpeed > -250 && ySpeed < 250 ) && xSpeed > 0) {
            myDirection = "R";
        }
        else if((ySpeed > -250 && ySpeed < 250 ) && xSpeed < 0) {
            myDirection = "L";
        }
        else if(ySpeed > 0 && xSpeed > 0){
            myDirection = "UR";
        }
        else if(ySpeed < 0 && xSpeed > 0) {
            myDirection = "DR";
        }
        else if(ySpeed > 0 && xSpeed < 0) {
            myDirection = "UL";
        }
        else if(ySpeed < 0 && xSpeed < 0) {
            myDirection = "DL";
        }
        else {
            myDirection = null;
        }

        return myDirection;
    }

    public String checkDirection(lastDirection) {
        String inputDirection = lastDirection;
        String finalDirection = "";

        
    }
}


