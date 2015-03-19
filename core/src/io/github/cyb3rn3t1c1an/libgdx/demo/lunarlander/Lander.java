package io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Lander extends Actor {

    public static final int MAX_THRUST = 20;

    private Texture landerTexture = new Texture(Gdx.files.internal("lander.png"));
    private TextureRegion textureRegion = new TextureRegion(landerTexture);
    private Texture landerThrustUpTexture = new Texture(Gdx.files.internal("lander_thrust_up.png"));
    private Texture landerCrashedTexture = new Texture(Gdx.files.internal("lander_crashed.png"));
    private Body body;

    private boolean launched = false;
    private boolean isCrashed = false;

    public Lander(World world) {
        super();

        int positionX = Gdx.graphics.getWidth() / 2;
        int positionY = Gdx.graphics.getHeight() / 2;

        setPosition(positionX, positionY);
        setWidth(landerTexture.getWidth());
        setHeight(landerTexture.getHeight());
        setBounds(positionX, positionY, getWidth(), getHeight());

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());

        body = world.createBody(bodyDef);

        body.setUserData(this);

        PolygonShape polygonShape = new PolygonShape();

        polygonShape.setAsBox(landerTexture.getWidth() / 2, landerTexture.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;

        body.createFixture(fixtureDef);

        polygonShape.dispose();

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!isCrashed) {
                    System.out.println("Key down");
                    if (keycode == Input.Keys.UP && body.getLinearVelocity().y <= MAX_THRUST) {
                        launched = true;
                        System.out.println("UP");
                        Vector2 vector2 = new Vector2(0, 25000f);
                        body.applyForceToCenter(vector2, true);
                    } else if (keycode == Input.Keys.DOWN && body.getLinearVelocity().y > 0) {
                        System.out.println("DOWN");
                        Vector2 vector2 = new Vector2(0, 25000f);
                        body.applyForceToCenter(vector2, true);
                    } else if (keycode == Input.Keys.LEFT) {
                        System.out.println("LEFT");
                        Vector2 vector2 = new Vector2(-100000f, 0);
                        body.applyForceToCenter(vector2, true);
                    } else if (keycode == Input.Keys.RIGHT) {
                        System.out.println("RIGHT");
                        Vector2 vector2 = new Vector2(100000f, 0);
                        body.applyForceToCenter(vector2, true);
                    }
                    System.out.println(
                            "linear velocity x: " + body.getLinearVelocity().x + " y: " + body.getLinearVelocity().y);
                }
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (!isCrashed) {
                    System.out.println("Key up");

                    if (keycode == Input.Keys.UP) {
                        launched = false;
                    }
                }
                return super.keyUp(event, keycode);
            }
        });

    }

    public void setCrashed(boolean isCrashed) {
        this.isCrashed = isCrashed;
    }

    private void applyForce() {
        if (launched && body.getLinearVelocity().y <= MAX_THRUST) {
            Vector2 vector2 = new Vector2(0, 10000f);
            body.applyForceToCenter(vector2, true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        applyForce();

        updatePosition();

        Texture landerTextureToDraw;

        if (launched) {
            landerTextureToDraw = landerThrustUpTexture;
        } else if (isCrashed) {
            landerTextureToDraw = landerCrashedTexture;
        } else {
            landerTextureToDraw = landerTexture;
        }

        batch.draw(landerTextureToDraw, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation(), textureRegion.getRegionX(), textureRegion.getRegionY(),
                textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), false, false);
    }

    public void updatePosition() {
        setPosition(body.getPosition().x - 16, body.getPosition().y - 16);
        setRotation((float) Math.toDegrees(body.getAngle()));
    }
}
