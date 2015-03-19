package io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class LunarLanderDemo extends Game {

    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        world = new World(new Vector2(0, -10f), true);

        debugRenderer = new Box2DDebugRenderer();

        world.setContactListener(new ContactListenerImpl());

        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        Terrain terrain = new Terrain(world);
        Lander lander = new Lander(world);
        stage.addActor(terrain);
        stage.addActor(lander);
        stage.setKeyboardFocus(lander);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        debugRenderer.render(world, stage.getCamera().combined);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
