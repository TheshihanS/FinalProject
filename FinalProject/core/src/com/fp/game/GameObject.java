/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fp.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Admin
 */
public class GameObject {
    protected float xSpeed;
    protected float ySpeed;
    protected float xPos;
    protected float yPos;
    protected int sideLength = 50;
    
    /**
     * Base GameObject Constructor
     * @param xPos
     * @param yPos 
     */
    public GameObject(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    /**
     * GameObject Constructor that includes speed
     * @param xSpeed
     * @param ySpeed
     * @param xPos
     * @param yPos 
     */
    public GameObject(float xSpeed, float ySpeed, float xPos, float yPos, int sideLength) {
        this(xPos, yPos);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.sideLength = sideLength;
    }

    /**
     * Checking if a collision has occured
     * @param o
     * @return 
     */
    public boolean isCollision(GameObject o) {
        Rectangle rect1 = new Rectangle(xPos,yPos,sideLength,sideLength);
        Rectangle rect2 = new Rectangle(o.getxPos(), o.getyPos(), o.sideLength, o.sideLength);
        return rect1.overlaps(rect2);
    }
    
    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }


    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    
}
