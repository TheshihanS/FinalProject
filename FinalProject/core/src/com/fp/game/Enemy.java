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
    public Enemy(float xSpeed, float ySpeed, float xPos, float yPos) {
        super(xSpeed, ySpeed, xPos, yPos);
    }
}
