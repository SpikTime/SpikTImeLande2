package com.spiktimeland.game.GraphicsObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicsObj {


    public  GraphicsObj(Texture img){
        this.img = img;
    }
    public Texture img;

    public void draw(SpriteBatch batch) {

    }

    public void update() {

    }

    public Texture setTexture(Texture img) {
        this.img = img;
        return img;
    }
}
