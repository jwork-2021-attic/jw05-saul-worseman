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

import asciiPanel.AsciiPanel;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Aeranythe Echosong
 */
public class Player extends Creature {

    private static Player player = new Player();

    private Player(){
        super(100,2,(char)2, AsciiPanel.brightWhite,0);
        setX(1);
        setY(1);
    }
    private boolean ready = false;

    public static Player getPlayer(){
        return player;
    }

    public int hp(){
        return super.hp();
    }

    public int getCredits(){
        return super.getCredits();
    }

    public boolean readyForNextLevel(){
        return ready;
    }

    @Override
    public void run(){
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                if(world.tile(this.x(), this.y()) == Tile.DOOR)
                    ready = true;
                else
                    ready = false;
                lock.lock();
                if (world.tile(this.x(), this.y()) == Tile.LAVA)
                    this.Hp -= 10;
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void revive(){
        player = new Player();
    }
}
