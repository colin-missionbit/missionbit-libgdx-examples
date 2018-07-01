package com.missionbit.game.com.missionbit.game.simplecollision;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import com.badlogic.gdx.math.Shape2D;

public class CircleCollidableCreature extends CollidableCreature {

    protected Circle collisionCircle = new Circle();

    public CircleCollidableCreature(SpriteBatch batch, String spritePath){
        super(batch, spritePath);
    }

    public CircleCollidableCreature(SpriteBatch batch){
        this(batch, "images/doge.png");
        collisionCircle = new Circle(image.getX() + image.getWidth() / 2.0f,
                image.getY() + image.getHeight() / 2.0f,
                Math.min(image.getWidth(), image.getHeight()) / 2.0f);
    }

    public void update(){
        super.update();
        collisionCircle.x = image.getX() + image.getWidth() / 2.0f;
        collisionCircle.y = image.getY() + image.getHeight() / 2.0f;
    }

    public void drawDebug(ShapeRenderer renderer){
        renderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    }

    public Shape2D getCollisionShape(){
        return collisionCircle;
    }

}
