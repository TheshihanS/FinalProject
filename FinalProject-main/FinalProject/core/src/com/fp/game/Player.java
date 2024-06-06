/*
 * 
 * 
 */
package com.fp.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Admin
 */
public class Player extends GameObject{
    protected boolean shooting;
    protected int hp;
    protected int coins;
    protected boolean moving;
    public Player(float xSpeed, float ySpeed, float xPos, float yPos, int sideLength, boolean shooting, int hp, boolean moving) {
        super(xSpeed,ySpeed,xPos,yPos, sideLength);
        this.shooting = shooting;
        this.hp = hp;
        this.moving = moving;
    }

    public int getHp() {
        return hp;
    }

    public int getCoins() {
        return coins;
    }

    public boolean getMoving() {
        return moving;
    }
    
    
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    
}
