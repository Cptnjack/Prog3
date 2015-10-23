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
import java.io.IOException;
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
    public static void main(String[] args) throws IOException
    {
//<<<<<<< HEAD
//        ActorWorld world = new ActorWorld();
//
//        world.addOccupantClass("info.gridworld.actor.Rock");
//        world.addOccupantClass("info.gridworld.actor.Food");
//        world.add(new Location(7, 8), new Rock());
//        world.add(new Location(3, 3), new Rock());
//        world.add(new Location(0, 0), new Entrance());
//        world.add(new Location(9, 9), new ExitPortal());
//        world.add(new Location(5, 8), new Zombie());
//        world.show();
//=======
        String info = "You can choose to have the application randomly "+
                "put actors in the grid or if you wish to enter the yourself."+
                "\nTo add actors to the grid, click on a spot in the grid and"+
                " select the actor you wish to be added.\n" +
                "To change the size of the grid, select the 'World'"+
                " item on the menu strip, then select 'Set Grid' from"+
                " the dropdown menu.\n";
        
        JOptionPane.showMessageDialog(null, info, "Instructions", 
                JOptionPane.PLAIN_MESSAGE);
        
        //Creates a String to be  displayed in the JOptionPane
        String rand = "Would you like to use random placement of actors?";
        
        int option = JOptionPane.showConfirmDialog(null, rand, "asdf", 
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        
        ActorWorld world = new ActorWorld();
        if(option == JOptionPane.YES_OPTION)
        {
            world.addOccupantClass("info.gridworld.actor.Rock");
            world.addOccupantClass("info.gridworld.actor.Bug");
            world.addOccupantClass("info.gridworld.actor.Food");           
            world.add(new Food());
            world.add(new Vampire());
            world.add(new Zombie());
            world.add(new Rock());
            world.add(new Rock()); 
            world.add(new Entrance());
            world.add(new ExitPortal());
            world.show();
        }
        else
        {
            world.addOccupantClass("info.gridworld.actor.Rock");
            world.addOccupantClass("info.gridworld.actor.Bug");
            world.addOccupantClass("info.gridworld.actor.Food");
            world.addOccupantClass("info.gridworld.actor.Human");
            world.addOccupantClass("info.gridworld.actor.Zombie");
            world.addOccupantClass("info.gridworld.actor.Vampire");
            world.addOccupantClass("info.gridworld.actor.Entrance");
            world.addOccupantClass("info.gridworld.actor.ExitPortal");
            world.show();
        }
		
    }
    
}
