package io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Terrain extends Actor {

    public static final int MAX_THRUST = 5;

    private Texture texture = new Texture(Gdx.files.internal("terrain.png"));
    private TextureRegion textureRegion = new TextureRegion(texture);
    private Body body;

    public Terrain(World world) {
        super();

        setPosition(0, 0);
        setWidth(Gdx.graphics.getWidth());
        setHeight(texture.getHeight());
        setBounds(0, 0, getWidth(), getHeight());

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX(), getY());

        body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();

        polygonShape.setAsBox(getWidth(), getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 100f;

        body.createFixture(fixtureDef);

        polygonShape.dispose();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), textureRegion.getRegionX(), textureRegion.getRegionY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), false, false);
    }

}
