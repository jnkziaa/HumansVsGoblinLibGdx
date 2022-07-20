package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
    //** the movement velocity */
    private Animation still, left, right, up, down;
    private Vector2 velocity = new Vector2();
    private float speed = 100 * 2, gravity = 60 * 1.8f;
    private TiledMapTileLayer collisionLayer;
    private boolean canJump = true;
    public Player(Animation still, Animation left, Animation right, Animation up, Animation down,TiledMapTileLayer collisionLayer) {
        super((Texture) still.getKeyFrame(0));

    }



    @Override
    public void draw(Batch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }





    public void update(float delta){


        //apply gravity
        velocity.y -= gravity * delta;

        //clamp velocity
        if(velocity.y > speed){
            velocity.y = speed;
        }
        else if (velocity.y < speed){
            velocity.y = -speed;
        }


        //save old pos
        float oldX = getX(), oldY = getY(), tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();//collision detection
        boolean collisionX = false, collisionY = false;

        //move on X
        setX(getX() + velocity.x * delta);

        /*
        if(velocity.x < 0){
            //if smaller than 0 go left
            //top left
            collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight())/tileHeight
                    )).getTile().getProperties().containsKey("collision");

            //middle left
            if(!collisionX)
                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()/2)/tileHeight
                    )).getTile().getProperties().containsKey("collision");


            //bottom left
            if(!collisionX)
                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY()/tileHeight)
            ).getTile().getProperties().containsKey("collision");



        }else if(velocity.x > 0) {
            //top right
            collisionX = collisionLayer.getCell((int)(getX() + getWidth() / tileWidth), (int)((getY() + getHeight())/tileHeight
                )).getTile().getProperties().containsKey("collision");

            //middle right
            if(!collisionX)
                collisionX = collisionLayer.getCell((int)((getX() +getWidth())/ tileWidth), (int)((getY() + getHeight()/2)/tileHeight
                )).getTile().getProperties().containsKey("collision");


            //bottom right
            if(!collisionX)
                collisionX = collisionLayer.getCell((int)((getX() + getWidth())/ tileWidth), (int)(getY()/tileHeight)
                ).getTile().getProperties().containsKey("collision");
        }

        //react to X collision
        if(collisionX){
            setX(oldX);
            velocity.x = 0;
        }

        //move on Y
        setY(getY() + velocity.y * delta);

        if(velocity.y < 0){
            //bottom left
            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY()/tileHeight
            )).getTile().getProperties().containsKey("collision");

            //bottom middle
            if(!collisionY)
                collisionY= collisionLayer.getCell( (int) ((getX() + getWidth()/2) / tileWidth), (int)(getY() / tileHeight
                )).getTile().getProperties().containsKey("collision");


            //bottom right
            if(!collisionY)
                collisionY= collisionLayer.getCell( (int) (getX() + getWidth() / tileWidth), (int)(getY() / tileHeight
                )).getTile().getProperties().containsKey("collision");

        }else if(velocity.y > 0) {
            //top left
            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() + getHeight()/tileHeight
            )).getTile().getProperties().containsKey("collision");

            //top middle
            if(!collisionY)
                collisionY= collisionLayer.getCell( (int) ((getX() + getWidth()/2) / tileWidth), (int)(getY()  + getHeight()/ tileHeight
                )).getTile().getProperties().containsKey("collision");

            //top right
            if(!collisionY)
                collisionY= collisionLayer.getCell( (int) (getX() + getWidth()/ tileWidth), (int)(getY()  + getHeight()/ tileHeight
                )).getTile().getProperties().containsKey("collision");
        }

        //react to Y collision
        if(collisionY){
            setX(oldY);
            velocity.y = 0;
        }*/
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:

                if(canJump){
                    velocity.y = speed;
                    canJump = false;
                }
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                break;
            case Input.Keys.S:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.D:
                velocity.x = 0;


        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
