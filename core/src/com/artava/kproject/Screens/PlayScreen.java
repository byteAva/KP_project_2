package com.artava.kproject.Screens;

import com.artava.kproject.MyGame;
import com.artava.kproject.Scenes.Logic;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Владимир on 10.02.2018.
 */

public class PlayScreen implements Screen {

    private MyGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Logic logic;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    public PlayScreen(MyGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGame.WIDTH,MyGame.HEIGHT,gamecam);
        logic = new Logic(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("t4.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched())
            gamecam.position.x += 70*dt;
    }

    public void update(float dt){
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        game.batch.setProjectionMatrix(logic.stage.getCamera().combined);
        logic.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
