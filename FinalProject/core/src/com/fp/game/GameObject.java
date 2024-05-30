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
    protected int sideLength;
    
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
    public GameObject(float xSpeed, float ySpeed, float xPos, float yPos) {
        this(xPos, yPos);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Checking if a collision has occured
     * @param r
     * @return 
     */
    public boolean isCollision(Rectangle r) {
        Rectangle gameO = new Rectangle(xPos,yPos,sideLength,sideLength);
        return gameO.overlaps(r);
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

    
}
