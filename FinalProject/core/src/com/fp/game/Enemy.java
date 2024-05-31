/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

/**
 *
 * @author thesh
 */
public class Enemy extends GameObject{
    protected int hp;
    public Enemy(float xSpeed, float ySpeed, float xPos, float yPos, int hp) {
        super(xSpeed, ySpeed, xPos, yPos);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    
    
    
    public void enemyPathing(Player player1) {
        if (player1.getxPos() > this.xPos) {
            this.xSpeed = 1;
        } else if (player1.getxPos() < this.xPos) {
            this.xSpeed = -1;
        } if (player1.getyPos() > this.yPos) {
            this.ySpeed = 1;
        } else if (player1.getyPos() < this.yPos) {
            this.ySpeed = -1;
        }
    }
    
    public void update() {
        this.xPos = this.xPos + this.xSpeed;
        this.yPos = this.yPos + this.ySpeed;
    }
    
}
