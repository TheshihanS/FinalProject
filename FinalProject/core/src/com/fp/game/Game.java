package com.fp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player1;
        
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                player1 = new Player(0,0,0,0,false,3);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, player1.getxPos(), player1.getyPos(), 50,50);
		batch.end();
                
                if (Gdx.input.isKeyPressed(Keys.A)) {
                    player1.setxSpeed(-1);
                }  else if (Gdx.input.isKeyPressed(Keys.D)) {
                    player1.setxSpeed(1);
                } else {
                    player1.setxSpeed(0);
                }  
                
                if (Gdx.input.isKeyPressed(Keys.W)) {
                    player1.setySpeed(1);
                }  else if (Gdx.input.isKeyPressed(Keys.S)) {
                    player1.setySpeed(-1);
                }  else {
                    player1.setySpeed(0);
                }
                double magnitude = Math.sqrt(Math.pow(player1.getxSpeed(), 2)) + Math.pow(player1.getySpeed(), 2);
                if (magnitude > 1) {
                    player1.setxSpeed(player1.getxSpeed()/magnitude);
                    player1.setySpeed(player1.getySpeed()/magnitude);
                }
                
                player1.setxPos(player1.getxPos() + (int)player1.getxSpeed());
                player1.setyPos(player1.getyPos() + (int)player1.getySpeed());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
