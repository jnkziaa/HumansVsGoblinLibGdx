package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import javax.swing.*;

public class Play implements Screen {
    private static final float FRAME_TIME = 1/15f;
    private Animation<TextureRegion> run;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera camera;
    private Player player;



    @Override
    public void show() {
        /*TextureAtlas charset = new TextureAtlas(Gdx.files.internal("run.atlas"));
        run = new Animation<TextureRegion>(FRAME_TIME, charset.findRegion("run"));
        run.setFrameDuration(FRAME_TIME);*/
        map = new TmxMapLoader().load("maps/1147tmx.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        player = new Player(new Sprite(new Texture("player/player.png")), (TiledMapTileLayer) map.getLayers().get(0));
        player.setPosition(30, 350);

        Gdx.input.setInputProcessor(player);
        System.out.println("this got called show()");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.position.set(700, 350, 0);
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
