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
    public Player(float xSpeed, float ySpeed, float xPos, float yPos, boolean shooting, int hp) {
        super(xSpeed,ySpeed,xPos,yPos);
        this.shooting = shooting;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getCoins() {
        return coins;
    }
    
    
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    
}
