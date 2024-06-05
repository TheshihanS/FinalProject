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
    private static int PLAYBUTTONY = 600;
    private static int EXITBUTTONX = 400;
    private static int EXITBUTTONY = 200;
    private static final int BUTTON_LENGTH = 524;
    private static final int BUTTON_HEIGHT = 234;
    private Texture background;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    
    public MainMenu(){
        background = new Texture("mainmenuart.JPG");
        playButtonActive = new Texture("playButtonActive.PNG");
        playButtonInactive = new Texture("playButtonInactive.PNG");
        exitButtonActive = new Texture("exitButtonActive.PNG");
        exitButtonInactive = new Texture("exitButtonInactive.PNG");
    }
    
    public void render(SpriteBatch batch){
        batch.draw(background, 0, 0);
        batch.draw(playButtonInactive, PLAYBUTTONX, PLAYBUTTONY);
        batch.draw(exitButtonInactive, EXITBUTTONX, EXITBUTTONY);
    }
    
    public void buttonState(SpriteBatch batch, int x, int y){
        if (x >= PLAYBUTTONX && x <= PLAYBUTTONX + BUTTON_LENGTH && y >= PLAYBUTTONY && y <= PLAYBUTTONY + BUTTON_HEIGHT){
            batch.draw(playButtonActive, PLAYBUTTONX, PLAYBUTTONY);
        }
        if (x >= EXITBUTTONX && x <= EXITBUTTONX + BUTTON_LENGTH && y >= EXITBUTTONY && y <= EXITBUTTONY + BUTTON_HEIGHT){
            batch.draw(exitButtonActive, EXITBUTTONX, EXITBUTTONY);
        }
    }
}
