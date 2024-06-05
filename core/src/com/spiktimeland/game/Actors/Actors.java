package com.spiktimeland.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.spiktimeland.game.GraphicsObj.GraphicsObj;
import com.spiktimeland.game.Tools.Circle;
import com.spiktimeland.game.Tools.Point2D;

public abstract class Actors extends GraphicsObj {

    public Point2D direction;
    public Point2D position;
    public float Speed,R;
    public Circle bounds;


    public Actors(Texture texture,Point2D position, float Speed, float R){
        super(texture);
        this.position = new Point2D(position);
        this.Speed = Speed;
        this.R = R;
        bounds = new Circle(position, R);
        direction = new Point2D(0, 0);

    }

    public Actors(Texture img,Point2D position){
        super(img);
        this.position = new Point2D(position);
        direction = new Point2D(0, 0);

    }

    public  void setDirection(Point2D dir) {
        direction = dir;
    }
}
