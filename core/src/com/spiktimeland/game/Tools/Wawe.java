package com.spiktimeland.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.spiktimeland.game.Actors.Enemy;
import com.spiktimeland.game.Main;

import Screens.GameSc;

public class Wawe {
    private int Delay, WaweNumber, minEnemy; //время между волнами(время задержки) , какой номер волны, инимальное колличество врагов которые будут в этой волне.
    private long SrartTimer; // Точка начала отсчёта()

    private String str = "Wawe = ";

    BitmapFont font;



    public Wawe(int Delay, int WaweNumber, int minEnemy) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("ofontruBubblezGraffiti.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = Main.WIDTH/20;
        p.color = new Color(10, 7, 0,1);

        font = gen.generateFont(p);


        this.Delay = Delay;
        this.WaweNumber = WaweNumber;
        this.minEnemy = minEnemy;

    }

    public void draw(SpriteBatch batch){
        GlyphLayout gl = new GlyphLayout();
        gl.setText(font, str + WaweNumber);
        font.draw(batch, gl, Main.WIDTH/2 - gl.width/2, Main.HEIGHT/2);
    }

    public boolean isDraw(){
        return SrartTimer > 0;
    }

    public void update(){
        if(GameSc.Enemies.size == 0 && SrartTimer == 0)SrartTimer = System.currentTimeMillis();
        int Seconds = 0;
        if(SrartTimer > 0)Seconds = (int) (System.currentTimeMillis() - SrartTimer)/1000;
        if(Seconds >= Delay) {setWawe(); WaweNumber++; SrartTimer = 0;}
    }

    public void setWawe(){
        int Enemies = minEnemy + WaweNumber * 2;

        int MaxRang = 1;
        if(WaweNumber > 1)MaxRang = 2;
        if(WaweNumber > 2)MaxRang = 3;
        if(WaweNumber == 4)MaxRang = 20;
        if(WaweNumber > 4)MaxRang = 5;
        if(WaweNumber > 5)MaxRang = 6;
        if(MaxRang != 20) {
            for(int i = 0; i < Enemies; i++) {
                GameSc.Enemies.add(new Enemy(Main.actor, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 4), (int) ((Math.random() * MaxRang) + 1)));
            }
        }


        else {
            GameSc.Enemies.add(new Enemy(Main.actor, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 4), 20));
        }
    }
}
