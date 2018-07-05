package com.missionbit.game.particleeffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.missionbit.game.ExampleDrawable;

public class ParticleEffectDrawable extends ExampleDrawable {
    private SpriteBatch batch;

    private ParticleEffectPool effectPool;                  // Pool of particle effects
    private Array<ParticleEffectPool.PooledEffect> effects; // Array of active particle effects
    private ParticleEffect explosion;                       // The actual particle effect

    public ParticleEffectDrawable(Camera gameCamera) {
        super(gameCamera);
        batch = new SpriteBatch();

        effects = new Array<ParticleEffectPool.PooledEffect>();
        explosion = new ParticleEffect();

        // You have to load the particle effect, and the image used for the effect
        explosion.load(Gdx.files.internal("effects/explosion.p"),Gdx.files.internal("effects/"));
        explosion.scaleEffect(0.5f); // It's kinda big at 480x800, so I had to scale it down

        // Initializes the pool with one particle
        effectPool = new ParticleEffectPool(explosion, 1, 100);
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
            ParticleEffectPool.PooledEffect expl = effectPool.obtain();
            expl.setPosition(touchPos.x, touchPos.y);
            effects.add(expl);
        }

        // Updates particle effects, and removes it from the active array when finished
        for (ParticleEffectPool.PooledEffect p : effects) {
            p.update(deltaTime);
            if (p.isComplete()) {
                p.free();
                effects.removeValue(p, true);
            }
        }
    }

    @Override
    public void draw() {
        batch.begin();
        for(ParticleEffectPool.PooledEffect p : effects) { p.draw(batch); }
        batch.end();
    }
}
