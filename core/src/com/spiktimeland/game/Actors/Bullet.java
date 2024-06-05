package com.spiktimeland.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.spiktimeland.game.Main;
import com.spiktimeland.game.Tools.Point2D;

public class Bullet extends Actors {

    public  boolean isOut;
    public Bullet(Texture img, Point2D position, float Speed, float R, Point2D direction) {
        super(img, position, Speed, R);
        this.direction = new Point2D(direction);

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX(), position.getY(), R*2,R*2);
    }

    @Override
    public void update() {
        isOut = ((position.getX() - R > Main.WIDTH) || (position.getX() + R < 0) || (position.getY() - R > Main.HEIGHT) ||  (position.getY() + R < 0))?
        true:false;
        position.add(direction.getX() * Speed, direction.getY()*Speed);
        bounds.pos.setPoint(position);
    }
}
