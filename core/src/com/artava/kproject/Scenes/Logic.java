package com.artava.kproject.Scenes;

import com.artava.kproject.MyGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.String;

import java.awt.Font;
import java.sql.Time;

/**
 * Created by Владимир on 10.02.2018.
 */

public class Logic {
    public Stage stage;
    public Viewport viewport;

    private Integer worldTimer;
    private Integer score;

    Label screLabel;
    Label timeLabel;
    Label lvlLabel;
    Label scorel;
    Label Timel;
    Label Worldl;

    public Logic(SpriteBatch sb){
        worldTimer = 0;
        score = 0;

        viewport = new FillViewport(MyGame.WIDTH,MyGame.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label(java.lang.String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        screLabel = new Label(java.lang.String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        lvlLabel = new Label("level - 1",new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        Timel = new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        scorel = new Label("Score",new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        Worldl = new Label("Level",new Label.LabelStyle(new BitmapFont(), Color.GOLD));

        table.add(Timel).expandX().pad(10);
        table.add(scorel).expandX().pad(10);
        table.add(Worldl).expandX().pad(10);
        table.row();
        table.add(timeLabel).expandX();
        table.add(screLabel).expandX();
        table.add(lvlLabel).expandX();

        stage.addActor(table);
    }
}
