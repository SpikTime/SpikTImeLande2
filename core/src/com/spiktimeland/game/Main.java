package com.spiktimeland.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spiktimeland.game.Tools.Point2D;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import java.util.logging.FileHandler;

import Screens.GameSc;
import Screens.Menu;
import Screens.Shop;


public class Main extends Game {
	public SpriteBatch batch;
	public Texture img;
	public static int WIDTH,HEIGHT;
	public static Texture circle, actor,Enemy1, Enemy2, Enemy3, Enemy4,Enemy5,EnemyBOS,backgroundSprite, bullet, circle2,actor2, pauseButtonTexture,resumeButtonTexture,table,menuButtonTexture,exitButtonTexture,playButtonTexture,MenubackgroundTexture;
	public static int Record;
	public Texture backgroundTexture;





	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}

	@Override
	public void create () {

		if(!Gdx.files.local("rec.txt").exists())Write("0");

		Record = Read();


		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("circle2.png");
		circle2 = new Texture("circle3.png");
		actor = new Texture("player2.png");
		actor2 = new Texture("playerhit.png");
		Enemy1 = new Texture("Enemy1.png");
		Enemy2 = new Texture("Enemy2.png");
		Enemy3 = new Texture("Enemy3.png");
		Enemy4 = new Texture("Enemy4.png");
		Enemy5 = new Texture("Enemy5.png");
		EnemyBOS = new Texture("EnemyBOS.png");
		backgroundTexture = new Texture("background1.png");
		pauseButtonTexture = new Texture("paus.png");
		resumeButtonTexture = new Texture("back.png");
		menuButtonTexture = new Texture("Menu.png");
		playButtonTexture = new Texture("Game.png");
		exitButtonTexture = new Texture("Exit.png");
		MenubackgroundTexture = new Texture("menubacground.png");
		table = new Texture("table.png");
		bullet = new Texture("bullets.png");



		setScreen(new Menu(this));


	}

	@Override
	public void pause() {
		super.pause();
	}


	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		backgroundTexture.dispose();

	}

	public static  void  Write(String str){
		FileHandle file = Gdx.files.local("rec.txt");
		file.writeString(str, false);
	}

	public static int Read(){
		FileHandle file = Gdx.files.local("rec.txt");
		return Integer.parseInt(file.readString());
	}
}
