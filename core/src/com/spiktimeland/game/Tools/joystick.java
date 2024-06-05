package com.spiktimeland.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class joystick {
    Texture CircleImg, StickImg;
    Circle CircleBounds, StickBounds;
    float Rcircle, Rstick;
    private  int pointer = -1;
    Point2D direction;



    public joystick(Texture cimg, Texture simg, Point2D point, float Size){
        CircleImg = cimg;
        StickImg = simg;
        Rcircle = Size/2;
        Rstick = Rcircle/3;
        CircleBounds = new Circle(point, Rcircle);
        StickBounds = new Circle(point, Rstick);
        direction = new Point2D(0,0);

    }

    public void draw(SpriteBatch batch) {
        batch.draw(CircleImg, CircleBounds.pos.getX() - Rcircle, CircleBounds.pos.getY() - Rcircle,Rcircle *2,Rcircle *2);
        batch.draw(StickImg, StickBounds.pos.getX() - Rstick, StickBounds.pos.getY() - Rstick, Rstick *2, Rstick *2);
    }

    public void update(float x, float y, boolean isDowmToch, int pointer){
        Point2D touch = new Point2D(x, y);
        if(CircleBounds.isContains(touch) && isDowmToch && this.pointer == -1)this.pointer = pointer;
        if(CircleBounds.OverLaps(StickBounds) && isDowmToch && pointer == this.pointer)atControl(new Point2D(x, y));
        if((!isDowmToch && pointer == this.pointer) || (isDowmToch && pointer == this.pointer &&!CircleBounds.isContains(touch)))returnStick();
    }
    public void atControl(Point2D point){
            StickBounds.pos.setPoint(point);
            float dx = CircleBounds.pos.getX() - StickBounds.pos.getX();
            float dy = CircleBounds.pos.getY() - StickBounds.pos.getY();
        float dist = (float)Math.sqrt(dx * dx +  dy * dy);
        direction.setPoint(-(dx/dist), -(dy/dist));

    }
    public void returnStick() {
        StickBounds.pos.setPoint(StickBounds.pos);
        direction.setPoint(0,0);
        pointer =-1;
    }

    public Point2D getDir() {
        return direction;
    }
}
