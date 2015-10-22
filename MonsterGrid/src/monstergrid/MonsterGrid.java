/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monstergrid;
import info.gridworld.actor.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author lconfair
 */
public class MonsterGrid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ActorWorld world = new ActorWorld();

        world.addOccupantClass("info.gridworld.actor.Rock");
        world.addOccupantClass("info.gridworld.actor.Food");
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(0, 0), new Entrance());
        world.add(new Location(9, 9), new ExitPortal());
        world.add(new Location(5, 8), new Zombie());
        world.show();
        
    }
    
}
