package com.missionbit.game.com.missionbit.game.simplecollision;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.missionbit.game.FlyingCreature;


public class CollidableCreature extends FlyingCreature {

    public CollidableCreature(SpriteBatch batch){
        super(batch);
    }

    public boolean collide(CollidableCreature other){
        //Get the other creature's rectangle
        Rectangle otherRect = other.getImage().getBoundingRectangle();

        //See if it overlaps our rectangle
        boolean flag = image.getBoundingRectangle().overlaps(otherRect);
        if(flag){
           // System.out.println("boom " + this + " " + other);
        }
        return flag;
    }

}
