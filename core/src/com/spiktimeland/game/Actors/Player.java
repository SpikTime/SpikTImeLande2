package com.spiktimeland.game.Actors;

import static com.spiktimeland.game.Main.Enemy1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spiktimeland.game.GraphicsObj.GraphicsObj;
import com.spiktimeland.game.Main;
import com.spiktimeland.game.Tools.Point2D;

public class Player extends Actors{

    public int Score = 0;
    public float health;
    private boolean ghost;
    private long starttimer = 0;
    GraphicsObj gameObject2 = new GraphicsObj(Main.actor);





    public Player(Texture img, Point2D position, float Speed, float R, float health) {
        super(img, position, Speed, R);
        this.health = health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(1,1,1,1);
        batch.draw(img, position.getX() - R, position.getY() - R);
    }


    @Override
    public void update() {
        super.update();
        if(position.getX() + R > Main.WIDTH)position.setX(Main.WIDTH - R);
        if(position.getX() - R < 0)position.setX(R);
        if(position.getY() + R > Main.HEIGHT)position.setY(Main.HEIGHT - R);
        if(position.getY() - R < 0)position.setY(R);

        if(ghost){
            this.img = gameObject2.setTexture(Main.actor2);
        }

        if(!ghost){
            this.img = gameObject2.setTexture(Main.actor);
        }


        if(starttimer == 0 && ghost)starttimer = System.currentTimeMillis();
        int second = 0;
        if(starttimer > 0)second = (int)(System.currentTimeMillis() - starttimer) /1000;

        if(second > 3) {
            ghost = false;
            starttimer = 0;
        }

        position.add(direction.getX() * Speed, direction.getY() *Speed);
        bounds.pos.setPoint(position);
    }
    public void hit() {
        if(!ghost){
            ghost = true;
            health--;
        }

    }
    public boolean isGhost(){
        return ghost;
    }

    public float getHealth() {
        return (float) health;
    }

    public void setScore(){Score++;}
    public int getScore(){return Score;}
}
