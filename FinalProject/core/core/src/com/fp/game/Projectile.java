/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author thesh
 */
public class Projectile extends GameObject{
    protected boolean bulletAlive;
    protected int ySideLength;
    protected int sideLength;
    protected int orientation;
    protected Texture sprite;
    public Projectile(float xSpeed, float ySpeed, float xPos, float yPos, int sideLength, boolean bulletAlive, int ySide, Texture sp, int o) {
        super(xSpeed, ySpeed, xPos, yPos, sideLength);
        this.bulletAlive = bulletAlive;
        ySideLength = ySide;
        sprite = sp;
        orientation = o;
    }

    public boolean isCollision(GameObject o) {
        Rectangle rect1 = new Rectangle(xPos,yPos,sideLength,ySideLength);
        Rectangle rect2 = new Rectangle(o.getxPos(), o.getyPos(), o.sideLength, o.sideLength);
        return rect1.overlaps(rect2);
    }
    
    public boolean getBulletAlive() {
        return bulletAlive;
    }

    public void setBulletAlive(boolean bulletAlive) {
        this.bulletAlive = bulletAlive;
    }
    
    public void setImageX(SpriteBatch batch) {
        batch.draw(sprite, xPos, yPos, 70, 10);
    }
    
    public void setImageY(SpriteBatch batch) {
        batch.draw(sprite, xPos, yPos, 10, 70);
    }
    
    public void setImageD(SpriteBatch batch) {
        batch.draw(sprite, xPos, yPos, 40, 40);
    }

    public boolean isBulletAlive() {
        return bulletAlive;
    }

    public int getySideLength() {
        return ySideLength;
    }

    public int getSideLength() {
        return sideLength;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setySideLength(int ySideLength) {
        this.ySideLength = ySideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }
    
    public int getOrientation() {
        return orientation;
    }
}
