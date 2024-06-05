package com.spiktimeland.game.Tools;

import com.spiktimeland.game.Actors.Bullet;
import com.spiktimeland.game.Main;

import Screens.GameSc;

public class BulletGeneration {
    boolean isFire;

    public void update(joystick joy) {
        isFire = (joy.getDir().getX() == 0 && joy.getDir().getY() == 0)?false:true;

        if(isFire) GameSc.bullets.add(new Bullet(Main.bullet, GameSc.player.position, 15,GameSc.player.R/2, joy.getDir()));
    }
}
