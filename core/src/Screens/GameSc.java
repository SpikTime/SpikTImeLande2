package Screens;

import static com.spiktimeland.game.Main.actor;
import static com.spiktimeland.game.Main.backgroundSprite;
import static com.spiktimeland.game.Main.pauseButtonTexture;
import static com.spiktimeland.game.Main.resumeButtonTexture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spiktimeland.game.Actors.Bullet;
import com.spiktimeland.game.Actors.Enemy;
import com.spiktimeland.game.Actors.Player;
import com.spiktimeland.game.Main;
import com.spiktimeland.game.Tools.BulletGeneration;
import com.spiktimeland.game.Tools.GameHud;
import com.spiktimeland.game.Tools.Point2D;
import com.spiktimeland.game.Tools.Wawe;
import com.spiktimeland.game.Tools.joystick;


public class GameSc implements Screen {

    joystick joy, joy2;
    public static Player player;

    public static Array<Bullet> bullets;
    public static Wawe wawe;
    public static GameHud hud;
    Main main;
     BulletGeneration bullgen;
    public static Array<Enemy>Enemies;
    public boolean isPaused = false;


    public GameSc(Main main){
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
        if (!isPaused) {GameUpdate();}
        main.batch.begin();
        main.batch.draw(main.backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());// рисуем текстуру фона на весь экран
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
        player.setDirection(joy.getDir());
        player.update();
        bullgen.update(joy2);
        for(int i =0; i < bullets.size;i++) {
            bullets.get(i).update();
            if(bullets.get(i).isOut) bullets.removeIndex(i--);
        }
        for(int i =0; i < Enemies.size;i++){
            Enemies.get(i).update();
            if (Enemies.get(i).getHealth() < 1){
                Enemies.removeIndex(i--);
            }
        }

        collision();
        wawe.update();

        if(player.getHealth() <= 0) main.setScreen(new DeadSc(main, player.getScore() + ""));

    }




    public void GameRender(SpriteBatch batch) {
        player.draw(batch);
        joy.draw(batch);
        joy2.draw(batch);
        for(int i =0; i < bullets.size;i++)bullets.get(i).draw(batch);
        for(int i =0; i < Enemies.size;i++)Enemies.get(i).draw(batch);
        if(wawe.isDraw())wawe.draw(batch);
        hud.draw(batch);
        if (isPaused) {
            main.batch.draw(Main.table, (float) (Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 - (float) Gdx.graphics.getWidth() / 3.8), (float) (Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - Gdx.graphics.getHeight() / 3), (float) ((float) Gdx.graphics.getWidth() / 1.8), (float) ((float) Gdx.graphics.getHeight() / 1.8));
            // Отображение кнопок на паузе
            batch.draw(Main.resumeButtonTexture, (float) (Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 - (float) Gdx.graphics.getWidth() / 9.5), Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2,(float) Gdx.graphics.getWidth() / 5, (float) Gdx.graphics.getHeight() /6 );
            batch.draw(Main.menuButtonTexture, (float) (Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2 - (float) Gdx.graphics.getWidth() / 9.5), Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - 300,(float) Gdx.graphics.getWidth() / 5 , (float) Gdx.graphics.getHeight() /6 );
        } else {
            // Отображение кнопки в углу экрана
            main.batch.draw(Main.pauseButtonTexture, (float) (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/12), Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() / 6, (float) Gdx.graphics.getWidth() / 12 , (float) Gdx.graphics.getHeight() /6 );
        }



    }

    public void  LoadActors() {
        joy2 = new joystick(Main.circle, Main.circle2, new Point2D(Main.WIDTH - ((float) (Main.HEIGHT / 3) /2 + (float) (Main.HEIGHT / 3) /4), (float) (Main.HEIGHT / 3) /2 + (float) (Main.HEIGHT / 3) /4), (float) Main.HEIGHT /3);
        joy = new joystick(Main.circle, Main.circle2, new Point2D((float) (Main.HEIGHT/3)/4 + (float) Main.HEIGHT /5, (float) (Main.HEIGHT / 3) /2 + (float) (Main.HEIGHT / 3) /4), (float) Main.HEIGHT /3);
        bullgen = new BulletGeneration();
        bullets = new Array<Bullet>();
        Enemies = new Array<Enemy>();
        player = new Player(Main.actor, new Point2D((float) Main.WIDTH / 2, (float) Main.HEIGHT / 2), 7, (float) Main.HEIGHT / 20, 3);

        wawe = new Wawe(5,1,5);
        hud = new GameHud();


    }

    public void multitouch(float x, float y, boolean isDowmToch, int pointer) {
        if (isPaused) {
            // Обработка кнопок при паузе
            if (isDowmToch && x >= Gdx.graphics.getWidth() - (float) Gdx.graphics.getWidth() /2  - (float) (Main.resumeButtonTexture.getWidth() / 7) /2 && y >=  Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() /2 - (float) (Main.resumeButtonTexture.getHeight() /6 ) /2) {
                // Продолжить игру (снять с паузы)
                isPaused = false;
            } else if (isDowmToch && x >= (Gdx.graphics.getWidth() - (float) pauseButtonTexture.getWidth() /2 ) - (float) (Main.menuButtonTexture.getWidth() / 7) / 2 && y >= (Gdx.graphics.getHeight() - pauseButtonTexture.getHeight() * 2 - 300) - (float) (Main.menuButtonTexture.getHeight() / 6) / 2) {
                // Переход на другой экран (например, MainMenuScreen)
                main.setScreen(new Menu(main));
            }
        }
        else {
            // Обработка обычного нажатия в игре
            for(int i = 0; i < 5; i++){
                joy.update(x, y , isDowmToch, pointer);
                joy2.update(x,y, isDowmToch,pointer);
            }
        }
        if (isDowmToch && x >=  (float) (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/12) && y >= Gdx.graphics.getHeight() - (float) Gdx.graphics.getHeight() / 6){isPaused = true;}
    }

    public void collision() {
        for(int i = 0; i < bullets.size; i++)
            for(int j  = 0; j < Enemies.size; j++) {
                if (bullets.get(i).bounds.OverLaps(Enemies.get(j).bounds)) {
                    Enemies.get(j).hit();
                    bullets.removeIndex(i);
                    player.setScore();
                    break;
                }
            }
        for(int j = 0; j < Enemies.size;j++){
            if(player.bounds.OverLaps(Enemies.get(j).bounds)) {
                player.hit();
                if(!player.isGhost()) {
                    Enemies.get(j).hit();
                }
            }
        }

    }
}
