package com.fp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img, img2;
    Player player1;

    ArrayList<Projectile> projectiles;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        img2 = new Texture("bullet.jpg");
        player1 = new Player(0, 0, 0, 0, false, 3);
        projectiles = new ArrayList();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, player1.getxPos(), player1.getyPos(), 50, 50);
        for (int i = 0; i < projectiles.size(); i++) {
            batch.draw(img2, projectiles.get(i).getxPos(), projectiles.get(i).getyPos());
        }

        batch.end();

        //////////////
        ///MOVEMENT///
        //////////////
        int normalSpeed = 2;

        if (Gdx.input.isKeyPressed(Keys.A)) {
            player1.setxSpeed(-normalSpeed);
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            player1.setxSpeed(normalSpeed);
        } else {
            player1.setxSpeed(0);
        }

        if (Gdx.input.isKeyPressed(Keys.W)) {
            player1.setySpeed(normalSpeed);
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            player1.setySpeed(-normalSpeed);
        } else {
            player1.setySpeed(0);
        }
        
        float dx;
        float dy;
        
        double magnitude = (float) (Math.sqrt(Math.pow(player1.getxSpeed(), 2) + Math.pow(player1.getySpeed(), 2)));
        if (magnitude > normalSpeed) {
            dx = ((float) player1.getxSpeed() / (float) magnitude) * normalSpeed;
            dy = ((float) player1.getySpeed() / (float) magnitude) * normalSpeed;
            player1.setxSpeed(dx);
            player1.setySpeed(dy);
        }

        player1.setxPos(player1.getxPos() + player1.getxSpeed());
        player1.setyPos(player1.getyPos() + player1.getySpeed());

        if (Gdx.input.isKeyPressed(Keys.C)) {
            
            Projectile p = new Projectile(player1.getxSpeed() * 3, player1.getySpeed() * 3, player1.xPos + 50, player1.yPos + 25, true);
                
            projectiles.add(p);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).getBulletAlive() == true) {
                
                    projectiles.get(i).setxPos(projectiles.get(i).getxPos() + projectiles.get(i).getxSpeed());
                    projectiles.get(i).setyPos(projectiles.get(i).getyPos() + projectiles.get(i).getySpeed());
                
            } else {
                projectiles.remove(i);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        img2.dispose();
    }
}
