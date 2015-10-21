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
        ActorWorld world = new ActorWorld();
        int option = JOptionPane.showConfirmDialog(null, rand, "asdf", 
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        
        if(option == JOptionPane.YES_OPTION)
        {
            world.addOccupantClass("info.gridworld.actor.Rock");
            world.addOccupantClass("info.gridworld.actor.Bug");
            world.addOccupantClass("info.gridworld.actor.Food");
            world.show();
            
            world.add(new Food());
            world.add(new Vampire());
            world.add(new Rock());
            world.add(new Rock());
            world.add(new Human());            
        }
        else
        {
            world.addOccupantClass("info.gridworld.actor.Rock");
            world.addOccupantClass("info.gridworld.actor.Bug");
            world.addOccupantClass("info.gridworld.actor.Food");
            world.addOccupantClass("info.gridworld.actor.Human");
            world.addOccupantClass("info.gridworld.actor.Zombie");
            world.addOccupantClass("info.gridworld.actor.Vampire");
            world.show();
        }
		
    }
    
}
