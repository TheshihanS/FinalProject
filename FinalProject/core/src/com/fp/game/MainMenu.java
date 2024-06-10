/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author Admin
 */
public class MainMenu {

    private static int PLAYBUTTONX = 400;
    private static int PLAYBUTTONY = 400;
    private static int EXITBUTTONX = 400;
    private static int EXITBUTTONY = 90;
    private static int EXITBUTTONX2 = 900;
    private static int EXITBUTTONY2 = 70;
    private static int TUTORIALBUTTONX = 1200;
    private static int TUTORIALBUTTONY = 700;
    private static int TITLEX = 0;
    private static int TITLEY = 750;
    private static final int BUTTON_LENGTH = 524;
    private static final int BUTTON_HEIGHT = 234;
    private static final int TUTORIAL_LENGTH = 200;
    private static final int TUTORIAL_HEIGHT = 200;
    private Texture background;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture tutorialButton;
    private Texture title;
    private Texture tutorial;

    public MainMenu() {
        background = new Texture("mainmenuart.JPG");
        playButtonActive = new Texture("playButtonActive.PNG");
        playButtonInactive = new Texture("playButtonInactive.PNG");
        exitButtonActive = new Texture("exitButtonActive.PNG");
        exitButtonInactive = new Texture("exitButtonInactive.PNG");
        tutorialButton = new Texture("Question_Mark_Icon.PNG");
        title = new Texture("TITLE.PNG");
        tutorial = new Texture("tutorial.PNG");
    }

    public void render(SpriteBatch batch, int stage) {
        batch.draw(background, 0, 0);
        if (stage == 0) {
            batch.draw(playButtonInactive, PLAYBUTTONX, PLAYBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            batch.draw(exitButtonInactive, EXITBUTTONX, EXITBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            batch.draw(tutorialButton, TUTORIALBUTTONX, TUTORIALBUTTONY, TUTORIAL_LENGTH, TUTORIAL_HEIGHT);
            batch.draw(title, TITLEX, TITLEY);
        }else if (stage == 3){
            batch.draw(exitButtonInactive, EXITBUTTONX2, EXITBUTTONY2, BUTTON_LENGTH, BUTTON_HEIGHT);
            batch.draw(tutorial, 0, 0, 650, 850);
        }
    }

    public boolean playButtonState(SpriteBatch batch, int x, int y) {
        if (x >= PLAYBUTTONX && x <= PLAYBUTTONX + BUTTON_LENGTH && y >= PLAYBUTTONY && y <= PLAYBUTTONY + BUTTON_HEIGHT) {
            batch.draw(playButtonActive, PLAYBUTTONX, PLAYBUTTONY);
            return true;
        }
        return false;
    }

    public boolean exitButtonState(SpriteBatch batch, int x, int y) {
        if (x >= EXITBUTTONX && x <= EXITBUTTONX + BUTTON_LENGTH && y >= EXITBUTTONY && y <= EXITBUTTONY + BUTTON_HEIGHT) {
            batch.draw(exitButtonActive, EXITBUTTONX, EXITBUTTONY);
            return true;
        }
        return false;
    }
    
    public boolean exitButton2State(SpriteBatch batch, int x, int y) {
        if (x >= EXITBUTTONX2 && x <= EXITBUTTONX2 + BUTTON_LENGTH && y >= EXITBUTTONY2 && y <= EXITBUTTONY2 + BUTTON_HEIGHT) {
            batch.draw(exitButtonActive, EXITBUTTONX2, EXITBUTTONY2);
            return true;
        }
        return false;
    }

    public boolean tutorialButtonState(SpriteBatch batch, int x, int y) {
        if (x >= TUTORIALBUTTONX && x <= TUTORIALBUTTONX + TUTORIAL_LENGTH && y >= TUTORIALBUTTONY && y <= TUTORIALBUTTONY + TUTORIAL_HEIGHT) {
            return true;
        }
        return false;
    }

}
