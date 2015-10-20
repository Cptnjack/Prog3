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
//        Random rn = new Random();
//        int randomInt = rn.nextInt(10)+1;
//        for(int i =0; i < randomInt; i++)
//            world.add(new Rock());
        world.addOccupantClass("info.gridworld.actor.Rock");
        world.addOccupantClass("info.gridworld.actor.Vampire");
        world.addOccupantClass("info.gridworld.actor.Zombie");
        //world.add(new Location(3, 3), new Rock());
        //world.add(new Location(2, 8), new Rock(Color.BLUE));
        //world.add(new Location(5, 5), new Rock(Color.PINK));
        //world.add(new Location(1, 5), new Rock(Color.RED));
        //world.add(new Location(7, 2), new Rock(Color.YELLOW));
        
        //world.add(new Location(4, 4), new ChameleonCritter());
        //world.add(new Location(5, 8), new ChameleonCritter());
//        world.add(new Location(1, 1), new Zombie());
//        world.add(new Location(1, 2), new Vampire());
        world.show();
        
    }
    
}
