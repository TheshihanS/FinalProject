package com.fp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {

    SpriteBatch batch;
    Texture playerIMG, knifeUp, knifeRight, knifeLeft, knifeDown, heart, backgroundSheet, spinach, squash, mushroom, eggplant, carrot, cabbage;
    Texture panBL, panBR, panTR, panTL;
    TextureRegion background1, background2, background3;
    int lastKey, randomSide, randomSpawn, menuStage;
    MainMenu mainMenu;
    Player player1;
    Enemy enemy1;

    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> baseEnemies;

    Texture carrotWalkSheet;
    Animation<TextureRegion> carrotWalkAni;

    float stateTime;

    int carrotCol = 8;
    int carrotRow = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerIMG = new Texture("badlogic.jpg");
        knifeRight = new Texture("knifeRight.png");
        knifeLeft = new Texture("knifeLeft.png");
        knifeUp = new Texture("knifeUp.png");
        knifeDown = new Texture("knifeDown.png");
        /*
        0 = main menu
        1 = game menu
        2 = end menu
         */
        menuStage = 0;
        spinach = new Texture("spinach.png");
        cabbage = new Texture("cabbage.png");
        carrot = new Texture("carrot.png");
        eggplant = new Texture("eggplant.png");
        mushroom = new Texture("mushroom.png");
        squash = new Texture("squash.png");

        panBL = new Texture("fryingPanBL.png");
        panBR = new Texture("fryingPanBR.png");
        panTL = new Texture("fryingPanTL.png");
        panTR = new Texture("fryingPanTR.png");
        
        heart = new Texture("apple_red.png");
        
        player1 = new Player(0, 0, 0, 0, 50, false, 3, false);
        mainMenu = new MainMenu();
        backgroundSheet = new Texture("tileset.jpg");
        background1 = new TextureRegion(backgroundSheet, 0, 30, 48, 32);
        background2 = new TextureRegion(backgroundSheet, 0, 62, 48, 32);
        background3 = new TextureRegion(backgroundSheet, 0, 94, 48, 32);

        projectiles = new ArrayList();
        baseEnemies = new ArrayList();

        carrotWalkSheet = new Texture("carrot.png");

        TextureRegion[][] tmp = TextureRegion.split(carrotWalkSheet, carrotWalkSheet.getWidth() / carrotCol, carrotWalkSheet.getHeight() / carrotRow);

        TextureRegion[] carrotWalkFrames = new TextureRegion[carrotCol * carrotRow];
        int index = 0;
        for (int i = 0; i < carrotRow; i++) {
            for (int j = 0; j < carrotCol; j++) {
                carrotWalkFrames[index++] = tmp[i][j];
            }
        }

        carrotWalkAni = new Animation<TextureRegion>(0.1f, carrotWalkFrames);

        stateTime = 0f;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();

        batch.draw(heart, 100, 815, 50, 50);
        batch.draw(heart, 175, 815, 50, 50);
        batch.draw(heart, 250, 815, 50, 50);
        
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = carrotWalkAni.getKeyFrame(stateTime, true);

        for (int l = 100; l < 1250; l += 150) {
            for (int k = 100; k < 800; k += 100) {
                batch.draw(background1, l, k, 150, 100);
            }
        }

        batch.draw(playerIMG, player1.getxPos(), player1.getyPos(), 50, 50);

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).getOrientation() == 2) {
                projectiles.get(i).setImageY(batch);
            } else if (projectiles.get(i).getOrientation() == 1){
                projectiles.get(i).setImageX(batch);
            } else if (projectiles.get(i).getOrientation() == 3) {
                projectiles.get(i).setImageD(batch);
            }
            
        }

        //////////////////
        //MOUSE TRACKING//
        //////////////////
        int mouseXPos, mouseYPos;
        mouseXPos = Gdx.input.getX();
        mouseYPos = Gdx.graphics.getHeight() - Gdx.input.getY();
        boolean isClicked = Gdx.input.isTouched();

        /////////////
        //MAIN MENU//
        /////////////
        if (menuStage == 0) {
            boolean mouseOnPlay, mouseOnExit;
            mainMenu.render(batch);
            mouseOnPlay = mainMenu.playButtonState(batch, mouseXPos, mouseYPos);
            mouseOnExit = mainMenu.exitButtonState(batch, mouseXPos, mouseYPos);
            if (mouseOnPlay && isClicked) {
                menuStage = 1;
            } else if (mouseOnExit && isClicked) {
                System.exit(0);
            }
        }

         for (int j = 0; j < baseEnemies.size(); j++) {
            
            baseEnemies.get(j).setImage(batch);
            
        }
        
        batch.end();    
        //game menu
        if (menuStage == 1) {

            //////////////
            ///MOVEMENT///
            //////////////
            int normalSpeed = 2;

            if (Gdx.input.isKeyPressed(Keys.A)) {
                player1.setxSpeed(-normalSpeed);
                lastKey = Keys.A;
                player1.setMoving(true);
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                player1.setxSpeed(normalSpeed);
                lastKey = Keys.D;
                player1.setMoving(true);
            } else {
                player1.setxSpeed(0);
                player1.setMoving(false);
            }

            if (Gdx.input.isKeyPressed(Keys.W)) {
                player1.setySpeed(normalSpeed);
                lastKey = Keys.W;
                player1.setMoving(true);
            } else if (Gdx.input.isKeyPressed(Keys.S)) {
                player1.setySpeed(-normalSpeed);
                lastKey = Keys.S;
                player1.setMoving(true);
            } else {
                player1.setySpeed(0);
                player1.setMoving(false);
            }

            ////////////////
            ///PROJECTILE///
            ////////////////
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

            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                
                    if (player1.getxSpeed() > 0 && player1.getySpeed() > 0) {
                        Projectile p = new Projectile(player1.getxSpeed() * 3, player1.getySpeed() * 3, player1.xPos + 50, player1.yPos + 25, 40, true, 40, panTR, 3);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() > 0 && player1.getySpeed() < 0) {
                        Projectile p = new Projectile(player1.getxSpeed() * 3, player1.getySpeed() * 3, player1.xPos + 50, player1.yPos + 25, 65, true, 8, panBR, 3);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() < 0 && player1.getySpeed() > 0) {
                        Projectile p = new Projectile(player1.getxSpeed() * 3, player1.getySpeed() * 3, player1.xPos + 50, player1.yPos + 25, 8, true, 65, panTL, 3);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() < 0 && player1.getySpeed() < 0) {
                        Projectile p = new Projectile(player1.getxSpeed() * 3, player1.getySpeed() * 3, player1.xPos + 50, player1.yPos + 25, 8, true, 65, panBL, 3);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() == 0 && player1.getySpeed() > 0) {
                        Projectile p = new Projectile(0, normalSpeed * 3, player1.xPos + 50, player1.yPos + 25, 8, true, 65, knifeUp, 2);
                      projectiles.add(p);
                    } else if (player1.getxSpeed() == 0 && player1.getySpeed() < 0) {
                        Projectile p = new Projectile(0, -(normalSpeed * 3), player1.xPos + 50, player1.yPos + 25, 8, true, 65, knifeDown, 2);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() > 0 && player1.getySpeed() == 0) {
                        Projectile p = new Projectile(normalSpeed * 3, 0, player1.xPos + 50, player1.yPos + 25, 65, true, 8, knifeRight, 1);
                        projectiles.add(p);
                    } else if (player1.getxSpeed() < 0 && player1.getySpeed() == 0) {
                        Projectile p = new Projectile(-(normalSpeed * 3), 0, player1.xPos + 50, player1.yPos + 25, 65, true, 8, knifeLeft, 1);
                        projectiles.add(p);
                    }
                
            }

            for (int i = 0; i < projectiles.size(); i++) {

                if ((projectiles.get(i).getxPos() < -50 || projectiles.get(i).getxPos() > 1200 || projectiles.get(i).getyPos() < 0 || projectiles.get(i).getyPos() > 900)) {
                    projectiles.get(i).setBulletAlive(false);
                }

                if (projectiles.get(i).getBulletAlive() == true) {

                    projectiles.get(i).setxPos(projectiles.get(i).getxPos() + projectiles.get(i).getxSpeed());
                    projectiles.get(i).setyPos(projectiles.get(i).getyPos() + projectiles.get(i).getySpeed());

                } else {
                    projectiles.remove(i);
                }
            }

            /////////////
            ///ENEMIES///
            /////////////
            randomSide = (int) (Math.random() * 4) + 1;
            randomSpawn = (int) (Math.random() * 50) + 1;

            if (randomSpawn == 1) {

                int ran = (int) (Math.random() * 5) + 1;

                Texture enemySprite = new Texture("cabbage.png");

                if (ran == 1) {
                    enemySprite = new Texture("cabbage.png");
                } else if (ran == 2) {
                    enemySprite = new Texture("carrot.png");
                } else if (ran == 3) {
                    enemySprite = new Texture("eggplant.png");
                } else if (ran == 4) {
                    enemySprite = new Texture("mushroom.png");
                } else if (ran == 5) {
                    enemySprite = new Texture("spinach.png");
                } else if (ran == 6) {
                    enemySprite = new Texture("squash.png");
                }

                if (randomSide == 1) {
                    Enemy e = new Enemy(0, 0, 0, 450, 50, 1, enemySprite);
                    baseEnemies.add(e);
                } else if (randomSide == 2) {
                    Enemy e = new Enemy(0, 0, 1200, 450, 50, 1, enemySprite);
                    baseEnemies.add(e);
                } else if (randomSide == 3) {
                    Enemy e = new Enemy(0, 0, 600, 0, 50, 1, enemySprite);
                    baseEnemies.add(e);
                } else {
                    Enemy e = new Enemy(0, 0, 600, 900, 50, 1, enemySprite);
                    baseEnemies.add(e);
                }

            }

            for (int i = 0; i < baseEnemies.size(); i++) {
                for (int j = 0; j < projectiles.size(); j++) {
                    if (baseEnemies.get(i).isCollision((GameObject) projectiles.get(j)) == true) {
                        projectiles.get(j).setBulletAlive(false);
                        baseEnemies.get(i).setHp(baseEnemies.get(i).getHp() - 1);
                    }
                }
                if (baseEnemies.get(i).isCollision((GameObject) player1) == true) {
                    baseEnemies.get(i).setHp(baseEnemies.get(i).getHp() - 1);
                    player1.setHp(player1.getHp() - 1);
                }
                if (baseEnemies.get(i).getHp() >= 1) {
                    baseEnemies.get(i).enemyPathing(player1);
                    baseEnemies.get(i).update();
                } else {
                    baseEnemies.remove(i);
                }
            }
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        playerIMG.dispose();
        spinach.dispose();
        carrotWalkSheet.dispose();
    }
}
