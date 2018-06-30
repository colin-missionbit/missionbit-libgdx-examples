package com.missionbit.game.com.missionbit.game.simplecollision;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.missionbit.game.ExampleDrawable;

import java.util.ArrayList;

public class SimpleCollisionDrawable extends ExampleDrawable {

    protected SpriteBatch drawBatch;

    protected ArrayList<CollidableCreature> creatures = new ArrayList<CollidableCreature>();

    private ShapeRenderer debugRenderer = new ShapeRenderer();
    private boolean showDebug = true;

    public SimpleCollisionDrawable(Camera gameCamera){
        super(gameCamera);
        drawBatch = new SpriteBatch();

        for(int i = 0; i < 5; i++){
            CollidableCreature c = new CollidableCreature(drawBatch);
            creatures.add(c);
        }
    }

    @Override
    public void draw(){
        drawBatch.setProjectionMatrix(camera.combined);

        drawBatch.begin();
        for(CollidableCreature c : creatures){
            c.draw();
        }
        drawBatch.end();

        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 1, 0, 1);
            for(CollidableCreature c : creatures){
                Rectangle debugRect = c.getImage().getBoundingRectangle();
                debugRenderer.rect(c.getImage().getX(), c.getImage().getY(), c.getImage().getWidth(), c.getImage().getHeight());

            }
            debugRenderer.end();
        }
    }

    @Override
    public void update(){
        for(CollidableCreature c : creatures){
            c.update();
            boolean anyCollisions = false;
            for(CollidableCreature other : creatures){
                if(c != other && c.collide(other)){
                    c.setColor(1, 0, 0, 1);
                    //other.setColor(1, 0, 0, 1);
                    anyCollisions = true;
                    //other.setColor(MathUtils.random(), 0, 0, 1);
                }
            }
            if(!anyCollisions){
                c.setColor(1, 1, 1, 1);
            }
        }
    }
}
