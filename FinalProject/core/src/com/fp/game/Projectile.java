/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

/**
 *
 * @author thesh
 */
public class Projectile extends GameObject{
    protected boolean bulletAlive;
    public Projectile(float xSpeed, float ySpeed, float xPos, float yPos, boolean bulletAlive) {
        super(xSpeed, ySpeed, xPos, yPos);
        this.bulletAlive = bulletAlive;
    }

    public boolean isBulletAlive() {
        return bulletAlive;
    }

    public void setBulletAlive(boolean bulletAlive) {
        this.bulletAlive = bulletAlive;
    }
    
    
}
