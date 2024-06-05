package com.spiktimeland.game.Actors;

import static com.spiktimeland.game.Main.Enemy1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spiktimeland.game.GraphicsObj.GraphicsObj;
import com.spiktimeland.game.Main;
import com.spiktimeland.game.Tools.Circle;
import com.spiktimeland.game.Tools.Point2D;

public class Enemy extends Actors{

    private int Health, Score, Rank;
    private Texture texture;
    GraphicsObj gameObject1 = new GraphicsObj(Enemy1);


    public Enemy(Texture texture, Point2D position, int  Rank){
        super(texture, position);
        this.texture = texture;
        this.Rank = Rank;

        if(Rank == 1){
            this.texture = gameObject1.setTexture(Main.Enemy1);
        }
        if(Rank == 2){this.texture = gameObject1.setTexture(Main.Enemy2);}
        if(Rank == 3){this.texture = gameObject1.setTexture(Main.Enemy3);}
        if(Rank == 20){this.texture = gameObject1.setTexture(Main.EnemyBOS);}
        if(Rank == 5){this.texture = gameObject1.setTexture(Main.Enemy4);}
        if(Rank == 6){this.texture = gameObject1.setTexture(Main.Enemy5);}



        switch (Rank){
            case 1:
                R = Main.WIDTH/25; Speed = 4; Score = Health = 10; break;
            case 2:
                R = Main.WIDTH/20; Speed = 7; Score = Health = 20; break;
            case 3:
                R = Main.WIDTH/15; Speed = 10;Score = Health = 20; break;
            case 20:
                R = Main.WIDTH/10; Speed = 12;Score = Health = 200; break;
            case 5:
                R = Main.WIDTH/13; Speed = 9;Score = Health = 50; break;
            case 6:
                R = Main.WIDTH/11; Speed = 11;Score = Health = 75; break;

        }
        bounds = new Circle(position, R);
        direction.setX((float) Math.sin(Math.toRadians(Math.random() * 360)));
        direction.setY((float) Math.cos(Math.toRadians(Math.random()*360)));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture,position.getX() - R, position.getY() - R,R * 2, R*2);
    }

    @Override
    public void update() {
        if(position.getX() + R > Main.WIDTH)direction.setX(-direction.getX());
        if(position.getX() - R < 0)direction.setX(-direction.getX());
        if(position.getY() + R > Main.HEIGHT)direction.setY(-direction.getY());
        if(position.getY() - R < 0)direction.setY(-direction.getY());

        position.add(direction.getX() * Speed, direction.getY() * Speed);
        bounds.pos.setPoint(position);
    }

    public void hit(){
        Health--;
    }

    public int getHealth(){return Health;}
}
