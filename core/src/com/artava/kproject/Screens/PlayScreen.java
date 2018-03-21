package com.artava.kproject.Screens;

import com.artava.kproject.MyGame;
import com.artava.kproject.Scenes.Logic;
import com.artava.kproject.Sprites.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
    private OrthogonalTiledMapRenderer renderer;
    //Box 2d переменные
    private World world;
    private Box2DDebugRenderer b2dr;
    //Игрок
    private Player player;

    public PlayScreen(MyGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGame.WIDTH/MyGame.PPM,MyGame.HEIGHT/MyGame.PPM,gamecam);
        logic = new Logic(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("t5.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/MyGame.PPM);
        gamecam.position.set((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/2),0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //И создал бог землю
        for (MapObject object: map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type =  BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2)/MyGame.PPM,(rect.getY()+(rect.getHeight()/2))/MyGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2/MyGame.PPM,rect.getHeight()/2/MyGame.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }
        //Создам и ещё кучу всего
    }

    @Override
    public void show() {

    }

    public void handleInput(){
        if (Gdx.input.isTouched()){
           // gamecam.position.x += 70*dt;

            player.body.applyLinearImpulse(new Vector2(0,1f),player.body.getWorldCenter(),true);
        }
    }

    public void update(float dt){
        player.body.applyLinearImpulse(new Vector2(0.02f,0),player.body.getWorldCenter(),true);

        handleInput();
        world.step(1/60f,6,2);

        gamecam.position.x = player.body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        b2dr.render(world,gamecam.combined);

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
