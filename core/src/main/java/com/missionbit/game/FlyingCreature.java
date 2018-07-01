package com.missionbit.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class FlyingCreature {

    protected Sprite image;
    protected SpriteBatch batch;
    protected Vector2 velocity;
    protected boolean alive = true;

    public FlyingCreature(SpriteBatch batch, String spritePath, Vector2 startPosition, Vector2 startVelocity){
        this.batch = batch;
        image = new Sprite( new Texture(Gdx.files.internal(spritePath)));

        velocity = startVelocity;
        image.setPosition(startPosition.x, startPosition.y);

    }

    public FlyingCreature(SpriteBatch batch, String spritePath){
        this(batch,
                spritePath,
                new Vector2(MathUtils.random(400), MathUtils.random(400)),
                new Vector2(MathUtils.random() * 300, MathUtils.random() * 300)
        );

    }
    public FlyingCreature(SpriteBatch batch){
        this(batch, "images/ufo.png");
    }

    public void update(){
        float xPos = image.getX() + velocity.x * Gdx.graphics.getDeltaTime();
        float yPos = image.getY() + velocity.y * Gdx.graphics.getDeltaTime();

        image.setX(xPos);
        image.setY(yPos);

        if(image.getX() < 0){
            image.setX(0);
            velocity.x *= -1;
        }
        if(image.getX() + image.getWidth() > Gdx.graphics.getWidth()){
            image.setX(Gdx.graphics.getWidth() - image.getWidth());
            velocity.x *= -1;
        }

        if(image.getY() < 0){
            image.setY(0);
            velocity.y *= -1;
        }
        if(image.getY() + image.getHeight() > Gdx.graphics.getHeight()){
            image.setY(Gdx.graphics.getHeight() - image.getHeight());
            velocity.y *= -1;
        }
    }

    public void draw(){
        if(alive) {
            image.draw(batch);
        }

    }

    public boolean handleClick(Vector3 touchPos){
        boolean hit = image.getBoundingRectangle().contains(touchPos.x, touchPos.y);

        return hit;
    }

    public boolean isActive(){
        return alive;
    }

    public Sprite getImage() {
        return image;
    }

    public void setImage(Sprite image) {
        this.image = image;
    }

    public void setColor(float r, float g, float b, float a){
        image.setColor(r, g, b, a);
    }
}
