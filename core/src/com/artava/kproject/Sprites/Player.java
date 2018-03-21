package com.artava.kproject.Sprites;

import com.artava.kproject.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Владимир on 11.02.2018.
 */

public class Player extends Sprite {
    public World world;
    public Body body;

    public Player(World world){
        this.world = world;
        definePlayer();
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100/MyGame.PPM,200/MyGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(40/MyGame.PPM,40/MyGame.PPM);

        //fdef.density = 1f; //Вес
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
