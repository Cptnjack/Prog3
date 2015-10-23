/**
 * Name:    Lewis Confair and Michael Whitley
 * Course:  4143 Contemporary Programming Languages
 * Date:    10/23/2015
 * Program: Program 3 - Monsters
 * Purpose: Develop a library of actors to demonstrate interfaces, inheritance, 
 *          and polymorphism. Create a 2D grid and display the actors on the 
 *          grid having them move and interact with each other and their 
 *          environment
 * Borrowed Code:   Horstmann's gridworld code was utilized to implement the 
 *                  grid and GUI.
 * Modified Code:   The following files from Horstmann's code were modified for 
 *                  this assignment:
 *                      -Actor.java
 *                      -ActorWorld.java
 *                      -Being.java
 *                      -Location.java
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
        //Display instructions in a panel for user
        String info = "You can choose to have the application randomly "+
                "put actors in the grid or if you wish to enter the yourself."+
                "\nTo add actors to the grid, click on a spot in the grid and"+
                " select the actor you wish to be added.\n" +
                "To change the size of the grid, select the 'World'"+
                " item on the menu strip, then select 'Set Grid' from"+
                " the dropdown menu.\n";
                JOptionPane.showMessageDialog(null, info, "Instructions", 
                JOptionPane.PLAIN_MESSAGE);
        
        //Display option for user to enter actors or have the placement be 
        //random
        String rand = "Would you like to use random placement of actors?";
         int option = JOptionPane.showConfirmDialog(null, rand, "Random", 
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        
         //for random actor placement
        ActorWorld world = new ActorWorld();
        if(option == JOptionPane.YES_OPTION)
        {         
            world.add(new Food());
            world.add(new Food());
            world.add(new Vampire());
            world.add(new Zombie());
            world.add(new Rock());
            world.add(new Rock()); 
            world.add(new Entrance());
            world.add(new ExitPortal());
            world.show();
        }
        //populate the GUI with needed classes to facilitate their placement on
        //the grid
        else
        {
            world.addOccupantClass("info.gridworld.actor.Rock");
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
