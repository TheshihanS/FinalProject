package com.fp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Game extends ApplicationAdapter {

    SpriteBatch batch;
    Texture playerIMG, knifeUp, knifeRight, knifeLeft, knifeDown, heart, backgroundSheet, spinach, squash, mushroom, eggplant, carrot, cabbage;
    Texture panBL, panBR, panTR, panTL;
    Texture one, two, three, four, five, six, seven, eight, nine, zero, killCountImg;
    TextureRegion background1, background2, background3;
    int lastKey, randomSide, randomSpawn, menuStage;
    MainMenu mainMenu;
    Player player1;
    Enemy enemy1;

    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> baseEnemies;
    ArrayList<Integer> killCountDigits;

    Texture walkUpSheet, walkDownSheet, walkRightSheet, walkLeftSheet;
    Animation<TextureRegion> walkUpAni, walkDownAni, walkRightAni, walkLeftAni, currentAni;
    FileWriter writer;
    float stateTime;

    String userName;

    int col = 3;
    int row = 1;

    public ArrayList<String> fileReaderName(File f, ArrayList<String> al) {
        try {
            
            Scanner s = new Scanner(f);
            
            while (s.hasNextLine()) {
                al.add(s.nextLine());
                s.nextLine();
            }
            
            return al;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR " + e);
        }
        return null;
    }
    
    public ArrayList<Integer> fileReaderScore(File f, ArrayList<Integer> al) {
        try {
            
            Scanner s = new Scanner(f);
            
            while (s.hasNextLine()) {
                s.nextLine();
                al.add(Integer.parseInt(s.nextLine()));
            }
            
            return al;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR " + e);
        }
        return null;
    }
    
    public void sort(List<String> score, List<String> name) {
        boolean swapped = true;
        // the number of passes
        int passes = 1;
        
        while (swapped == true) {
            // assumes no swaps will happen
            swapped = false;
            // checks through the entire array minus the number of passes because on each pass the last number will always be in the right order
            for (int i  = 0; i < (score.size() - passes); i++) {
                // checks i and the value after it, if i is bigger
                if (Integer.parseInt(score.get(i)) < Integer.parseInt(score.get(i+1))) {
                    // swaps them
                    String temp1 = (score.get(i));
                    score.set(i, score.get(i+1));
                    score.set(i+1, temp1);

                    String temp2 = name.get(i);
                    name.set(i, name.get(i+1));
                    name.set(i+1, temp2);
                    
                    // and sets the swapped variable to true
                    swapped = true;
                } 
            }
            // then adds one to the passes
            passes += 1;
        }
    }


    
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
        3 = tutorial menu
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

        zero = new Texture("zero.png");
        one = new Texture("one.png");
        two = new Texture("two.png");
        three = new Texture("three.png");
        four = new Texture("four.png");
        five = new Texture("five.png");
        six = new Texture("six.png");
        seven = new Texture("seven.png");
        eight = new Texture("eight.png");
        nine = new Texture("nine.png");
        killCountImg = new Texture("killCountImg.png");

        player1 = new Player(0, 0, 650, 450, 50, false, 3, false);

        mainMenu = new MainMenu();
        backgroundSheet = new Texture("tileset.jpg");
        background1 = new TextureRegion(backgroundSheet, 0, 30, 48, 32);
        background2 = new TextureRegion(backgroundSheet, 0, 62, 48, 32);
        background3 = new TextureRegion(backgroundSheet, 0, 94, 48, 32);

        projectiles = new ArrayList();
        baseEnemies = new ArrayList();
        killCountDigits = new ArrayList();

        walkUpSheet = new Texture("heroUp.png");
        walkDownSheet = new Texture("heroDown.png");
        walkLeftSheet = new Texture("heroLeft.png");
        walkRightSheet = new Texture("heroRight.png");

        TextureRegion[][] tmp = TextureRegion.split(walkUpSheet, walkUpSheet.getWidth() / col, walkUpSheet.getHeight() / row);

        TextureRegion[] walkUpFrames = new TextureRegion[col * row];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkUpFrames[index++] = tmp[i][j];
            }
        }

        walkUpAni = new Animation<TextureRegion>(0.5f, walkUpFrames);

        tmp = TextureRegion.split(walkDownSheet, walkDownSheet.getWidth() / col, walkDownSheet.getHeight() / row);

        TextureRegion[] walkDownFrames = new TextureRegion[col * row];
        index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkDownFrames[index++] = tmp[i][j];
            }
        }

        walkDownAni = new Animation<TextureRegion>(0.5f, walkDownFrames);

        tmp = TextureRegion.split(walkRightSheet, walkRightSheet.getWidth() / col, walkRightSheet.getHeight() / row);

        TextureRegion[] walkRightFrames = new TextureRegion[col * row];
        index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkRightFrames[index++] = tmp[i][j];
            }
        }

        walkRightAni = new Animation<TextureRegion>(0.5f, walkRightFrames);

        tmp = TextureRegion.split(walkLeftSheet, walkLeftSheet.getWidth() / col, walkLeftSheet.getHeight() / row);

        TextureRegion[] walkLeftFrames = new TextureRegion[col * row];
        index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkLeftFrames[index++] = tmp[i][j];
            }
        }

        walkLeftAni = new Animation<TextureRegion>(0.5f, walkLeftFrames);

        stateTime = 0f;

        currentAni = walkUpAni;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        //////////////////
        //MOUSE TRACKING//
        //////////////////
        int mouseXPos, mouseYPos;
        mouseXPos = Gdx.input.getX();
        mouseYPos = Gdx.graphics.getHeight() - Gdx.input.getY();
        boolean isClicked = Gdx.input.isTouched();

        /////////////
        //MENUS//
        /////////////
        boolean mouseOnPlay, mouseOnExit, mouseOnTutorial, mouseOnReturn, mouseOnSave, mouseOnLeaderBoard;

        if (menuStage == 0) {

            mainMenu.render(batch, menuStage);
            mouseOnPlay = mainMenu.playButtonState(batch, mouseXPos, mouseYPos);
            mouseOnExit = mainMenu.exitButtonState(batch, mouseXPos, mouseYPos);
            mouseOnTutorial = mainMenu.tutorialButtonState(batch, mouseXPos, mouseYPos);

            if (mouseOnPlay && isClicked) {
                menuStage = 1;
            } else if (mouseOnExit && isClicked) {
                System.exit(0);
            } else if (mouseOnTutorial && isClicked) {
                menuStage = 3;
            }
        } else if (menuStage == 3) {
            mainMenu.render(batch, menuStage);
            mouseOnExit = mainMenu.exitButton2State(batch, mouseXPos, mouseYPos);
            if (mouseOnExit && isClicked) {
                menuStage = 0;
            }
        } else if (menuStage == 2) {
            mouseOnReturn = mainMenu.returnButtonState(batch, mouseXPos, mouseYPos);
            mouseOnSave = mainMenu.saveButtonState(batch, mouseXPos, mouseYPos);
            mouseOnLeaderBoard = mainMenu.leaderBoardButtonState(batch, mouseXPos, mouseYPos);
            mainMenu.render(batch, menuStage);

            if (mouseOnReturn && isClicked) {
                menuStage = 0;
                player1.setHp(3);
                player1.setxPos(650);
                player1.setyPos(450);
                player1.setKillCount(0);
                baseEnemies.clear();
            } else if (mouseOnSave && isClicked) {
                userName = JOptionPane.showInputDialog(null, "ENTER YOUR NAME!");
                try {
                    Files.write(Paths.get("nameScores.txt"), (userName + "\n").getBytes() , StandardOpenOption.APPEND);
                    Files.write(Paths.get("saveScores.txt"), ("" + player1.getKillCount() + "\n").getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            } else if (mouseOnLeaderBoard && isClicked){
                List<String> scores = new ArrayList();
                List<String> name = new ArrayList();
                
                File fName = new File("FinalProject\\assets\\nameScores.txt");
                File fScore = new File("FinalProject\\assets\\saveScores.txt");
                
                try {
                    name = Files.readAllLines(Paths.get("nameScores.txt"));
                    scores = Files.readAllLines(Paths.get("saveScores.txt"));
                    System.out.println(name.get(0));
                } catch (IOException e) {
                    
                }
                //fileReaderScore(fScore, scores);
                
                sort(scores, name);
                
                String message = "";
                
                int scoresDisplayed = 0;
                
                if (name.size() > 3) {
                    scoresDisplayed = 3;
                } else {
                    scoresDisplayed = name.size();
                }
                
                if (name.size() > 0) {
                    for (int i = 0; i < scoresDisplayed; i++) {
                        message += name.get(i) + ": " + scores.get(i) + "\n";
                    }
                }

                JOptionPane.showMessageDialog(null, message);
                
            }
        } else if (menuStage == 1) {

            //displaying hearts
            int heartx = 100;
            for (int i = 0; i < player1.hp; i++) {
                batch.draw(heart, heartx, 25, 50, 50);
                heartx += 100;
            }

            //displaying killcount
            numberSep(player1.getKillCount());

            int killCountX = 1200;

            //running for each number
            for (int i = 0; i < killCountDigits.size(); i++) {

                //displaying specific number
                if (killCountDigits.get(i) == 0) {
                    batch.draw(zero, killCountX, 50);
                } else if (killCountDigits.get(i) == 1) {
                    batch.draw(one, killCountX, 50);
                } else if (killCountDigits.get(i) == 2) {
                    batch.draw(two, killCountX, 50);
                } else if (killCountDigits.get(i) == 3) {
                    batch.draw(three, killCountX, 50);
                } else if (killCountDigits.get(i) == 4) {
                    batch.draw(four, killCountX, 50);
                } else if (killCountDigits.get(i) == 5) {
                    batch.draw(five, killCountX, 50);
                } else if (killCountDigits.get(i) == 6) {
                    batch.draw(six, killCountX, 50);
                } else if (killCountDigits.get(i) == 7) {
                    batch.draw(seven, killCountX, 50);
                } else if (killCountDigits.get(i) == 8) {
                    batch.draw(eight, killCountX, 50);
                } else if (killCountDigits.get(i) == 9) {
                    batch.draw(nine, killCountX, 50);
                }
                killCountX -= 50;
            }

            killCountDigits.clear();
            batch.draw(killCountImg, 1100, 0);

            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentFrame = currentAni.getKeyFrame(stateTime, true);

            for (int l = 100; l < 1250; l += 150) {
                for (int k = 100; k < 800; k += 100) {
                    batch.draw(background1, l, k, 150, 100);
                }
            }

            batch.draw(currentFrame, player1.getxPos(), player1.getyPos(), 100, 100);

            for (int i = 0; i < projectiles.size(); i++) {
                if (projectiles.get(i).getOrientation() == 2) {
                    projectiles.get(i).setImageY(batch);
                } else if (projectiles.get(i).getOrientation() == 1) {
                    projectiles.get(i).setImageX(batch);
                } else if (projectiles.get(i).getOrientation() == 3) {
                    projectiles.get(i).setImageD(batch);
                }

            }
            for (int j = 0; j < baseEnemies.size(); j++) {

                baseEnemies.get(j).setImage(batch);

            }

        }

        

        batch.end();

        //game menu
        if (menuStage == 1) {

            //player health
            if (player1.getHp() <= 0) {
                menuStage = 2;
            }

            //////////////
            ///PLAYER/////
            //////////////
            int normalSpeed = 2;

            if (Gdx.input.isKeyPressed(Keys.A)) {
                player1.setxSpeed(-normalSpeed);
                lastKey = Keys.A;
                currentAni = walkLeftAni;
                player1.setMoving(true);
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                player1.setxSpeed(normalSpeed);
                lastKey = Keys.D;
                currentAni = walkRightAni;
                player1.setMoving(true);
            } else {
                player1.setxSpeed(0);
                player1.setMoving(false);
            }

            if (Gdx.input.isKeyPressed(Keys.W)) {
                player1.setySpeed(normalSpeed);
                lastKey = Keys.W;
                currentAni = walkUpAni;
                player1.setMoving(true);
            } else if (Gdx.input.isKeyPressed(Keys.S)) {
                player1.setySpeed(-normalSpeed);
                currentAni = walkDownAni;
                lastKey = Keys.S;
                player1.setMoving(true);
            } else {
                player1.setySpeed(0);
                player1.setMoving(false);
            }

            if (player1.getxPos() < 50) {
                player1.setxSpeed(0);
                player1.setxPos(50);
            } else if (player1.getxPos() > 1250) {
                player1.setxSpeed(0);
                player1.setxPos(1250);
            }
            if (player1.getyPos() < 75) {
                player1.setySpeed(0);
                player1.setyPos(75);
            } else if (player1.getyPos() > 750) {
                player1.setySpeed(0);
                player1.setyPos(750);
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
                } else if (player1.getxSpeed() == 0 && player1.getySpeed() == 0) {
                    if (lastKey == Keys.S) {
                        Projectile p = new Projectile(0, -(normalSpeed * 3), player1.xPos + 50, player1.yPos + 25, 8, true, 65, knifeDown, 2);
                        projectiles.add(p);
                    } else if (lastKey == Keys.D) {
                        Projectile p = new Projectile(normalSpeed * 3, 0, player1.xPos + 50, player1.yPos + 25, 65, true, 8, knifeRight, 1);
                        projectiles.add(p);
                    } else if (lastKey == Keys.W) {
                        Projectile p = new Projectile(0, normalSpeed * 3, player1.xPos + 50, player1.yPos + 25, 8, true, 65, knifeUp, 2);
                        projectiles.add(p);
                    } else if (lastKey == Keys.A) {
                        Projectile p = new Projectile(-(normalSpeed * 3), 0, player1.xPos + 50, player1.yPos + 25, 65, true, 8, knifeLeft, 1);
                        projectiles.add(p);
                    }
                }

            }

            for (int i = 0; i < projectiles.size(); i++) {

                if ((projectiles.get(i).getxPos() < 100 || projectiles.get(i).getxPos() > 1275 || projectiles.get(i).getyPos() < 100 || projectiles.get(i).getyPos() > 775)) {
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
                        player1.addKillCount(1);

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
        walkUpSheet.dispose();
    }

    public int numberSep(int num) {
        if (num < 10) {
            killCountDigits.add(num);
            return num;
        } else {
            int right = num % 10;
            int left = num / 10;
            killCountDigits.add(right);
            return numberSep(left);
        }
    }

}
