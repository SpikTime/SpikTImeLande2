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

public class Shop implements Screen {
    Main main;
    BitmapFont font;
    GlyphLayout  gl3;




    public Shop(Main main, String score){
        this.main = main;

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("ofontruBubblezGraffiti.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = Main.WIDTH/20;
        p.color = new Color(10, 7, 0,1);
        font = gen.generateFont(p);

        gl3 =new GlyphLayout();
        gl3.setText(font, "Sorry, shop lock :)");
    }

    public Shop(Main main) {
        this.main = main;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        main.batch.begin();
        font.draw(main.batch, gl3, Main.WIDTH/2 - gl3.width/2, (Main.HEIGHT/2 - gl3.height/2) + gl3.height * 2);
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
        if(Gdx.input.justTouched())main.setScreen(new Menu(main));
    }
}
