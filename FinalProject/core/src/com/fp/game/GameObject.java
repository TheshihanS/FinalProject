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
    protected double xSpeed;
    protected double ySpeed;
    protected int xPos;
    protected int yPos;
    protected int sideLength;
    
    /**
     * Base GameObject Constructor
     * @param xPos
     * @param yPos 
     */
    public GameObject(int xPos, int yPos) {
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
    public GameObject(int xSpeed, int ySpeed, int xPos, int yPos) {
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
    
    public double getxSpeed() {
        return xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }


    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    
}
