package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.missionbit.game.com.missionbit.game.simplecollision.SimpleCollisionDrawable;
import com.missionbit.game.com.missionbit.game.text.TextUIDrawable;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class LibGDXExamples extends ApplicationAdapter {

    private OrthographicCamera camera;
    private ExampleDrawable drawable;

    @Override
    public void create() {

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Collision demo
        //drawable = new SimpleCollisionDrawable(camera);

        // Text drawing demo
        drawable = new TextUIDrawable(camera);

    }

    @Override
    public void render() {

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();

        //Update and draw our shapes
        drawable.update();
        drawable.draw();


    }

    @Override
    public void dispose() {

    }
}