package com.missionbit.game.com.missionbit.game.simplecollision;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.missionbit.game.FlyingCreature;


public class CollidableCreature extends FlyingCreature {

    public CollidableCreature(SpriteBatch batch){
        super(batch);
    }

    public CollidableCreature(SpriteBatch batch, String spritePath){
        super(batch, spritePath);
    }

    /*public boolean collide(CollidableCreature other){
        //Get the other creature's rectangle
        Rectangle otherRect = other.getImage().getBoundingRectangle();

        //See if it overlaps our rectangle
        boolean flag = image.getBoundingRectangle().overlaps(otherRect);

        return flag;
    }*/


    /*
        Check if we collide with another creature.  Consider the case
        where the other collision shape is a circle
     */
    public boolean collide(CollidableCreature other){
        Shape2D ourShape = getCollisionShape();
        Shape2D otherShape = other.getCollisionShape();

        if(ourShape instanceof Circle){
            if(otherShape instanceof Circle){
                return Intersector.overlaps((Circle)ourShape, (Circle)otherShape);
            }
            else{
                return Intersector.overlaps((Circle)ourShape, (Rectangle) otherShape);
            }
        }
        else{
            if(otherShape instanceof Circle){
                return Intersector.overlaps((Circle)otherShape, (Rectangle) ourShape);
            }
            else{
                return Intersector.overlaps((Rectangle) ourShape, (Rectangle) otherShape);
            }
        }
    }

    public void drawDebug(ShapeRenderer renderer){
        renderer.rect(image.getX(), image.getY(), image.getWidth(), image.getHeight());
    }

    public Shape2D getCollisionShape(){
        return image.getBoundingRectangle();
    }

}
