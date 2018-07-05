package com.missionbit.game.particleeffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.missionbit.game.ExampleDrawable;

import java.util.Locale;

public class ParticleEffectDrawable extends ExampleDrawable {
    private SpriteBatch batch;
    private BitmapFont font;

    private ParticleEffectPool effectPool;                  // Pool of particle effects
    private Array<PooledEffect> effects; // Array of active particle effects
    private ParticleEffect explosion;                       // The actual particle effect

    public ParticleEffectDrawable(Camera gameCamera) {
        super(gameCamera);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();

        explosion = new ParticleEffect();

        // You have to load the particle effect, and the image used for the effect
        explosion.load(Gdx.files.internal("effects/explosion.p"),Gdx.files.internal("effects/"));
        explosion.scaleEffect(0.5f); // It's kinda big at 480x800, so I had to scale it down

        effectPool = new ParticleEffectPool(explosion, 0, 200);

        effects = new Array<PooledEffect>();
    }

    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isTouched()) {
            // Transforms screen coordinates to game world coordinates
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            // Grabs particle effect from pool (or creates a new one if one isn't free)
            PooledEffect expl = effectPool.obtain();
            expl.setPosition(touchPos.x, touchPos.y);
            expl.start(); // Must start particle effect, otherwise it just adds it to the pool only
            effects.add(expl);
        }

        // Updates particle effects, and removes it from the active array when finished
        for (PooledEffect p : effects) {
            p.update(deltaTime);
            if (p.isComplete()) {
                p.free();
                effects.removeValue(p, false);
            }
        }
    }

    @Override
    public void draw() {
        batch.begin();
        font.draw(batch, String.format(Locale.US, "Effects: %d | Free: %d/%d | Peak: %d", effects.size, effectPool.getFree(), effectPool.max, effectPool.peak), 0, font.getLineHeight());
        for(PooledEffect p : effects) { p.draw(batch); }
        batch.end();
    }
}
