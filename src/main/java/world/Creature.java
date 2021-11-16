/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package world;

import screen.Screen;

import java.awt.*;

/**
 *
 * @author Aeranythe Echosong
 */
public class Creature {

    private int Hp;
    protected World world;

    private int credits;
    private int x;

    public int hp(){return Hp;}
    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    private int y;

    private int attackValue;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    private char glyph;

    public char glyph() {
        return this.glyph;
    }

    private Color color;

    public Color color() {
        return this.color;
    }

    private CreatureAI ai;

    public void setAI(CreatureAI ai) {
        this.ai = ai;
    }

    public void moveBy(int mx, int my) {
        Creature other = world.creature(x + mx, y + my);
        if (other == null) {
            ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        } else {
            attack(other);
        }
    }

    public int getCredits(){
        return credits;
    }

    public void earnCredits(Creature c){
        this.credits += c.getCredits();
    }

    public void attack(Creature other) {
        System.out.println(1);
        this.Hp -= other.attackValue;
        other.Hp -= this.attackValue;
        System.out.println(this.hp() + " " + other.hp());
        if(this.Hp <= 0){
            other.earnCredits(this);
        }
        else if(other.Hp <= 0){
            this.earnCredits(other);
        }
        world.updateAll();
    }


    public void update() {
        this.ai.onUpdate();
    }

    public boolean isDead(){return Hp <= 0;}

    public Creature(int Hp,int attackValue,char glyph, Color color,int credits) {
        this.Hp = Hp;
        this.glyph = glyph;
        this.color = color;
        this.credits = credits;
        this.attackValue = attackValue;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void route(){
        ai.route();
    }

}
