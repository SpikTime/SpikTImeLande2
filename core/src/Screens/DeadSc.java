package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.spiktimeland.game.Main;

public class DeadSc implements Screen {
    Main main;
    String score;
    BitmapFont font;
    GlyphLayout gl, gl2, gl3;




    public DeadSc(Main main, String score){
        this.main = main;

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("ofontruBubblezGraffiti.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = Main.WIDTH/20;
        p.color = new Color(10, 7, 0,1);
        font = gen.generateFont(p);

        gl = new GlyphLayout();
        gl.setText(font, "Score " + score);

        gl2 = new GlyphLayout();
        String info;



        if(Integer.parseInt(score) > Main.Record) {
            info = "NEW RECORD !!!";
            Main.Write(score);
            Main.Record = Integer.parseInt(score);
        }

        else info = "RECORD :" + Main.Record;

        gl2.setText(font, info);

        gl3 =new GlyphLayout();
        gl3.setText(font, "Tap to begin!");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        main.batch.begin();
        main.batch.draw(main.backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // рисуем текстуру фона на весь экран
        font.draw(main.batch, gl, Main.WIDTH/2 - gl.width/2, Main.HEIGHT/2 - gl.height/2);
        font.draw(main.batch, gl2, Main.WIDTH/2 - gl2.width/2, (Main.HEIGHT/2 - gl.height/2) - gl2.height * 2);
        font.draw(main.batch, gl3, Main.WIDTH/2 - gl3.width/2, (Main.HEIGHT/2 - gl.height/2) + gl2.height * 2);
        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void update(){
        if(Gdx.input.justTouched())main.setScreen(new GameSc(main));
    }
}
