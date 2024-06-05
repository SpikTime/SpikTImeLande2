package com.spiktimeland.game.Tools;

public class Circle {

    public Circle(Point2D pos,float R){
        this.pos = new Point2D(pos);
        this.R = R;
    }


    float R;

    public Point2D pos;

    public boolean isContains(Point2D point){
        float dx = pos.getX() - point.getX();
        float dy = pos.getY() - point.getY();
        return dx * dx + dy* dy <= R * R;
    }

    public boolean OverLaps(Circle c) {
        float dx = pos.getX() - c.pos.getX();
        float dy = pos.getY() - c.pos.getY();
            float dist = dx * dx + dy * dy;
            float sumR = c.R+R;
            return dist < sumR * sumR;
    }
}
