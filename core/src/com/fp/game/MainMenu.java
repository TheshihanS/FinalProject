/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Admin
 */
public class MainMenu {
    //play button properties
    private static final int PLAYBUTTONX = 480;
    private static final int PLAYBUTTONY = 400;
    //exit button properties
    private static final int EXITBUTTONX = 480;
    private static final int EXITBUTTONY = 90;
    private static final int EXITBUTTONX2 = 870;
    private static final int EXITBUTTONY2 = 70;
    //tutorial button properties
    private static final int TUTORIALBUTTONX = 1200;
    private static final int TUTORIALBUTTONY = 700;
    //title properties
    private static final int TITLEX = 0;
    private static final int TITLEY = 750;
    //save button properties
    private static final int SAVEBUTTONX = 560;
    private static final int SAVEBUTTONY = 400;
    //return button properties
    private static final int RETURNBUTTONX = 0;
    private static final int RETURNBUTTONY = 0;
    //end title properties
    private static final int ENDTITLEX = 90;
    private static final int ENDTITLEY = 620;
    //play and exit button size
    private static final int BUTTON_LENGTH = (int)(524 * 0.7);
    private static final int BUTTON_HEIGHT = (int)(234 * 0.7);
    //tutorial button size
    private static final int TUTORIAL_LENGTH = 200;
    private static final int TUTORIAL_HEIGHT = 200;
    private final Texture background;
    private final Texture playButtonActive;
    private final Texture playButtonInactive;
    private final Texture exitButtonActive;
    private final Texture exitButtonInactive;
    private final Texture tutorialButton;
    private final Texture title;
    private final Texture tutorial;
    private final Texture endTitle;
    private final Texture returnButtonInactive;
    private final Texture returnButtonActive;
    private final Texture saveButtonInactive;
    private final Texture saveButtonActive;
    
    public MainMenu() {
        background = new Texture("mainmenuart.JPG");
        playButtonActive = new Texture("playButtonActive.PNG");
        playButtonInactive = new Texture("playButtonInactive.PNG");
        exitButtonActive = new Texture("exitButtonActive.PNG");
        exitButtonInactive = new Texture("exitButtonInactive.PNG");
        tutorialButton = new Texture("Question_Mark_Icon.PNG");
        title = new Texture("TITLE.PNG");
        tutorial = new Texture("tutorial.PNG");
        endTitle = new Texture("Game_Over.PNG");
        returnButtonInactive = new Texture("returnButtonInactive.PNG");
        returnButtonActive = new Texture("returnButtonActive.PNG");
        saveButtonActive = new Texture("saveButtonActive.PNG");
        saveButtonInactive = new Texture("saveButtonInactive.PNG");
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
        }else if (stage == 2){
            batch.draw(endTitle, ENDTITLEX, ENDTITLEY);
            batch.draw(returnButtonInactive, RETURNBUTTONX, RETURNBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            batch.draw(saveButtonInactive, SAVEBUTTONX, SAVEBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
        }
    }

    public boolean playButtonState(SpriteBatch batch, int x, int y) {
        if (x >= PLAYBUTTONX && x <= PLAYBUTTONX + BUTTON_LENGTH && y >= PLAYBUTTONY && y <= PLAYBUTTONY + BUTTON_HEIGHT) {
            batch.draw(playButtonActive, PLAYBUTTONX, PLAYBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            return true;
        }
        return false;
    }

    public boolean exitButtonState(SpriteBatch batch, int x, int y) {
        if (x >= EXITBUTTONX && x <= EXITBUTTONX + BUTTON_LENGTH && y >= EXITBUTTONY && y <= EXITBUTTONY + BUTTON_HEIGHT) {
            batch.draw(exitButtonActive, EXITBUTTONX, EXITBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            return true;
        }
        return false;
    }
    
    public boolean exitButton2State(SpriteBatch batch, int x, int y) {
        if (x >= EXITBUTTONX2 && x <= EXITBUTTONX2 + BUTTON_LENGTH && y >= EXITBUTTONY2 && y <= EXITBUTTONY2 + BUTTON_HEIGHT) {
            batch.draw(exitButtonActive, EXITBUTTONX2, EXITBUTTONY2, BUTTON_LENGTH, BUTTON_HEIGHT);
            return true;
        }
        return false;
    }
    
    public boolean saveButtonState(SpriteBatch batch, int x, int y) {
        if (x >= SAVEBUTTONX && x <= SAVEBUTTONX + BUTTON_LENGTH && y >= SAVEBUTTONY && y <= SAVEBUTTONY + BUTTON_HEIGHT) {
            batch.draw(saveButtonActive, SAVEBUTTONX, SAVEBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
            return true;
        }
        return false;
    }
    
    public boolean returnButtonState(SpriteBatch batch, int x, int y) {
        if (x >= RETURNBUTTONX && x <= RETURNBUTTONX + BUTTON_LENGTH && y >= RETURNBUTTONY && y <= RETURNBUTTONY + BUTTON_HEIGHT) {
            batch.draw(returnButtonActive, RETURNBUTTONX, RETURNBUTTONY, BUTTON_LENGTH, BUTTON_HEIGHT);
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

    public static int getPLAYBUTTONX() {
        return PLAYBUTTONX;
    }

    public static int getPLAYBUTTONY() {
        return PLAYBUTTONY;
    }

    public static int getEXITBUTTONX() {
        return EXITBUTTONX;
    }

    public static int getEXITBUTTONY() {
        return EXITBUTTONY;
    }

    public static int getEXITBUTTONX2() {
        return EXITBUTTONX2;
    }

    public static int getEXITBUTTONY2() {
        return EXITBUTTONY2;
    }

    public static int getTUTORIALBUTTONX() {
        return TUTORIALBUTTONX;
    }

    public static int getTUTORIALBUTTONY() {
        return TUTORIALBUTTONY;
    }

    public static int getTITLEX() {
        return TITLEX;
    }

    public static int getTITLEY() {
        return TITLEY;
    }

    public static int getBUTTON_LENGTH() {
        return BUTTON_LENGTH;
    }

    public static int getBUTTON_HEIGHT() {
        return BUTTON_HEIGHT;
    }

    public static int getTUTORIAL_LENGTH() {
        return TUTORIAL_LENGTH;
    }

    public static int getTUTORIAL_HEIGHT() {
        return TUTORIAL_HEIGHT;
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getPlayButtonActive() {
        return playButtonActive;
    }

    public Texture getPlayButtonInactive() {
        return playButtonInactive;
    }

    public Texture getExitButtonActive() {
        return exitButtonActive;
    }

    public Texture getExitButtonInactive() {
        return exitButtonInactive;
    }

    public Texture getTutorialButton() {
        return tutorialButton;
    }

    public Texture getTitle() {
        return title;
    }

    public Texture getTutorial() {
        return tutorial;
    }

}
