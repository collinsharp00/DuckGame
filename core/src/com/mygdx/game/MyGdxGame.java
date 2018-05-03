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

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ducko;
	private OrthographicCamera camera;
	private Rectangle duck;
	private Music duckSong;
	private double xSpeed;
	private double ySpeed;

	
	@Override
	public void create () {

		batch = new SpriteBatch();
		ducko = new Texture("ducky.png");
		duck = new Rectangle();
		duck.x = 0;
		duck.y = 0;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		duck.x = 800/2 - 64/2;
		duck.y = 20;
		duck.width = 64;
		duck.height = 64;

		//music
		duckSong = Gdx.audio.newMusic(Gdx.files.internal("ducksong.mp3"));
		duckSong.setLooping(true);
		duckSong.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(ducko, duck.x, duck.y);
		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) duck.x -= 600 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) duck.x += 600 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) duck.y += 600 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) duck.y -= 600 * Gdx.graphics.getDeltaTime();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		ducko.dispose();
	}

}
