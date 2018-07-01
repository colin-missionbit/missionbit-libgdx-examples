package com.missionbit.game.com.missionbit.game.simplecollision;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

        for(int i = 0; i < 2; i++){
            //Add a UFO with a square bounding box
            creatures.add(new CollidableCreature(drawBatch));

            //Add a doge with a bounding circle
            creatures.add(new CircleCollidableCreature(drawBatch));

        }
    }

    @Override
    public void draw(){
        drawBatch.setProjectionMatrix(camera.combined);

        //Draw creatures
        drawBatch.begin();
        for(CollidableCreature c : creatures){
            c.draw();
        }
        drawBatch.end();

        //Draw bounding shapes
        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 1, 0, 1);
            for(CollidableCreature c : creatures){
                c.drawDebug(debugRenderer);
            }
            debugRenderer.end();
        }
    }

    @Override
    public void update(){
        for(CollidableCreature c : creatures){
            c.update();
            boolean anyCollisions = false;

            //Note: We're comparing every creature to every other
            // creature in this inner loop.
            // If we did this for a lot of creatures it would be slow!
            for(CollidableCreature other : creatures){

                //Set color based on if we collide
                if(c != other && c.collide(other)){
                    c.setColor(1, 0, 0, 1);
                    anyCollisions = true;
                }
            }
            if(!anyCollisions){
                c.setColor(1, 1, 1, 1);
            }
        }
    }
}
