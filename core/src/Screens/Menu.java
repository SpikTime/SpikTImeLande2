package Screens;

import static com.spiktimeland.game.Main.backgroundSprite;
import static com.spiktimeland.game.Main.pauseButtonTexture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spiktimeland.game.Actors.Bullet;
import com.spiktimeland.game.Actors.Enemy;
import com.spiktimeland.game.Actors.Player;
import com.spiktimeland.game.Main;
import com.spiktimeland.game.Tools.BulletGeneration;
import com.spiktimeland.game.Tools.GameHud;
import com.spiktimeland.game.Tools.Point2D;
import com.spiktimeland.game.Tools.Wawe;
import com.spiktimeland.game.Tools.joystick;

public class Menu implements Screen {

    Main main;

    public Menu(Main main){
        this.main = main;
    }

    @Override
    public void show() {
        LoadActors();
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int)screenY, true, pointer);

                return false;

            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int)screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int)screenY, true, pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });


    }

    @Override
    public void render(float delta) {
        GameUpdate();
        main.batch.begin();
        main.batch.draw(Main.MenubackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // рисуем текстуру фона на весь экран
        GameRender(main.batch);
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


    public void GameUpdate(){
    }




    public void GameRender(SpriteBatch batch) {

            batch.draw(Main.playButtonTexture, Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 , Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2,(float) Gdx.graphics.getWidth() / 5, (float) Gdx.graphics.getHeight() /5 );
            batch.draw(Main.exitButtonTexture, Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 , Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - 300,(float) Gdx.graphics.getWidth() / 5 , (float) Gdx.graphics.getHeight() /5 );
    }

    public void  LoadActors() {
    }

    public void multitouch(float x, float y, boolean isDowmToch, int pointer) {
        if (isDowmToch && x >= Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 - (float) (Gdx.graphics.getWidth() / 6) && x <= Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 + (float) (Gdx.graphics.getWidth() / 6)  && y >= Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() / 2 - (float) (Main.playButtonTexture.getHeight() / 6) / 2) {
            main.setScreen(new GameSc(main));
        }

        if (isDowmToch && x >= Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 - (float) (Gdx.graphics.getWidth() / 6) && x <= Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 + (float) (Gdx.graphics.getWidth() / 6) && y <= (Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - 300) + (float) (Gdx.graphics.getHeight() / 24) && y >= (Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - 320) - (float) (Gdx.graphics.getHeight() / 24)) {
                Gdx.app.exit();
            }
        }
    }


