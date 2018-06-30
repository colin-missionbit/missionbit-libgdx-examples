package com.missionbit.game;

import com.badlogic.gdx.graphics.Camera;

public abstract class ExampleDrawable {
    protected Camera camera;
    public abstract void update();
    public abstract void draw();


    public ExampleDrawable(Camera gameCamera){
        camera = gameCamera;
    }
}
