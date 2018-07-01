package com.missionbit.game.com.missionbit.game.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.missionbit.game.ExampleDrawable;

public class TextUIDrawable extends ExampleDrawable {

    protected BitmapFont bodyFont;
    protected BitmapFont headlineFont;
    protected SpriteBatch batch;
    protected TextArticle text;

    protected int page = 0;
    protected int pageSize = 400;
    protected int maxPage;

    public TextUIDrawable(Camera gameCamera){
        super(gameCamera);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/RobotoSlab-Regular.ttf"));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 18;

        bodyFont = generator.generateFont(parameter); // font size 12 pixels

        generator.dispose();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/RobotoSlab-Bold.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 18;
        headlineFont = generator.generateFont(parameter);

        generator.dispose();

        batch = new SpriteBatch();

        text = TextArticle.loadJSON("text/textExample.json");
        maxPage = text.bodyText.length() / 400;

    }

    @Override
    public void draw(){

        String textPage = text.bodyText.substring(page * pageSize,
                Math.min((page + 1) * pageSize, text.bodyText.length()));

        batch.begin();
        headlineFont.draw(batch, text.headline, 100, Gdx.graphics.getHeight() - 60, 400, Align.left, false);
        headlineFont.draw(batch,(page + 1) + " of " + (maxPage + 1), 100, Gdx.graphics.getHeight() - 120, 400, Align.left, false);
        bodyFont.draw(batch, textPage, 100, Gdx.graphics.getHeight() - 180, 400, Align.left, true);
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            if(page < maxPage) {
                page++;
            }
        }

    }

    @Override
    public void update(){

    }
}
