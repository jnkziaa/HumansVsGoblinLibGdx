package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class Play implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;



    @Override
    public void show() {
        map = new TmxMapLoader().load("maps/1147tmx.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        Player player;
        /*Gdx.input.setInputProcessor(player = new Player(new Sprite(new Texture("player/player.png")),
                (TiledMapTileLayer) map.getLayers().get(0)));
        player.setPosition(20 * player.get)*/
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
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
