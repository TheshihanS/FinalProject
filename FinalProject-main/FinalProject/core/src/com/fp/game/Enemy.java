/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author thesh
 */
public class Enemy extends GameObject{
    protected int hp;
    protected Texture image;
    public Enemy(float xSpeed, float ySpeed, float xPos, float yPos, int sideLength, int hp, Texture i) {
        super(xSpeed, ySpeed, xPos, yPos, sideLength);
        this.hp = hp;
        image = i;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    
    
    
    public void enemyPathing(Player player1) {
        int randomness = (int) (Math.random() * 4) + 1;
        int randomDir = (int) (Math.random() * 40) + 1;
        if (randomDir == 1) {
            this.xSpeed = 1;
        }
        
        if (player1.getxPos() > this.xPos && randomness == 1) {
            this.xSpeed = 1;
        } else if (player1.getxPos() < this.xPos & randomness == 2) {
            this.xSpeed = -1;
        }  if (player1.getyPos() > this.yPos && randomness == 3) {
            this.ySpeed = 1;
        } else if (player1.getyPos() < this.yPos && randomness == 4) {
            this.ySpeed = -1;
        }
    }
    
    public void update() {
        this.xPos = this.xPos + this.xSpeed;
        this.yPos = this.yPos + this.ySpeed;
    }
    
    public void setImage(SpriteBatch batch) {
        batch.draw(image, xPos, yPos, 50, 50);
    }
}
