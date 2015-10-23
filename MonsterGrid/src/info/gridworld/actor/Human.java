/*
 * Name:    Lewis Confair and Michael Whitley
 * File:    Human.java
 * Purpose: Provides a class for a Human actor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Location;
<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
=======
import java.awt.Color;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.sound.sampled.*;
>>>>>>> Lewis

/**
 * A Human is an actor that spawns from an entrance, moves towards the exit 
 * while dodging monsters and eating any food it finds. 
 */
public class Human extends Being
{   
    /**
     * Method:  Human
     * Purpose: Default constructor for a Human.
     * @throws java.io.IOException
     */
    public Human() throws IOException
    {
        super();        
        setColor(null); 
        PlaySound();
    }

    /**
     * Method:  Human
     * Purpose: Parameterized constructor for a Human of a given color.
     * @param initialColor: the initial color of this human
     * @throws java.io.IOException
     */
    public Human(Color initialColor) throws IOException
    {
        setColor(initialColor);
        PlaySound();
    }

    /**
     * Method:  act
     * Purpose: A human acts by getting a list of its neighbors, processing 
     *          them, getting locations to move to, selecting one of them, and 
     *          moving to the selected location.
     * @return String: returns a string to be output in the panel 
     */
    public String act()
    {
        //if this human is non on the grid do nothing and return
        if (getGrid() == null)
            return "";
        //get current location for use int string output
        Location current = this.getLocation();
        
        //get the adjacent actors then process them for action
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        
        //get valid move options, select the location, then make the move
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs, findDirection());
        makeMove(loc);
        
        //create string and return it for output to the panel
        Location next = this.getLocation();
        String s = "\nHuman moved from "+current.toString()+" to "+
                next.toString();
        return new String(s);
    }

    /**
     * Method:  getActors
     * Purpose: Gets the actors that are in the same grid for processing. 
     *          Implemented to return the actors that occupy neighboring grid 
     *          locations. 
     * @return: a list of actors that are neighbors of this human
     */
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    /**
     * Method:  processActors
     * Purpose: Processes the actors. If there is food nearby will consume one
     *          of the food. 
     * Precondition:    All objects in actors are contained in the same grid as 
     *                  this human.
     * @param actors:   the actors to be processed
     */
    public String processActors(ArrayList<Actor> actors)
    {
        //create a new list of actors and add any food that is nearby
        ArrayList<Actor> enemy = new ArrayList<>();
        for (Actor a : actors)
        {             
            if (a instanceof Food)
            {
                enemy.add(a); 
            }
        }   
        
        //if the list of enemies is empty return
        int n = enemy.size();
        if (n == 0)
            return "";
        
        //get a random number 0 - enemy.size and grab the actor from that index
        //remove that food(actor) from the grid
        int r = (int) (Math.random() * n);
        Actor other = enemy.get(r);
        other.removeSelfFromGrid();
        
        //build the string for output and return
        String s = "";
        return new String(s);        
    }
    
    /**
     * Method:  getMoveLocations
     * Purpose: Gets the possible locations for the next move. Locations are all
     *          adjacent to the current Human 
     * Postcondition:   The locations must be valid in the grid of this Human.
     * @return: a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }

    /**
     * Method:  selectMoveLocation
     * Purpose: Selects the location for the next move. 
     * Precondition:    All locations in <code>locs</code> are valid in the grid
     *                  of this Human
     * @param locs:         the possible locations for the next move
     * @param direction:    the direction of the closest monster or exit
     * @return Location:    the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs, int direction)
    {
        Location towards = null;
        int newD = (direction + 180) % 360;
        int n = locs.size();
        
        //if the list of possible locations is empty return current location
        if (n == 0)
            return getLocation();
        //if there is only one possible location return it
        else if (n == 1)
            return locs.get(0);
        //find the direction from an actor of interest and find the location 
        //that is closest to that direction
        else
        {
            //findDirection did not find any actors of interest, choose a random
            //empty location to move to
            if (direction == 999)
            {
                int r = (int) (Math.random() * n);
                towards = locs.get(r);
            }
            //find the location that is the closest to the direction from 
            //findDirection
            else
            {
                int tempDirect = 360;
                int d;
                for (Location L : locs)
                {   
                    d = this.getLocation().getDirectionToward(L);
                    //if the current location is in the direction from 
                    //find direction return this location
                    if (d == newD)
                        return L;
                    //compare the direction of L to a temp direction and replace 
                    //the temp if the direction to L is less
                    else 
                    {
                        if (Math.abs(newD - d) < tempDirect)
                        {
                            tempDirect = d;
                            towards = L;
                        }
                    }                
                }
            }
        }
        return towards;
    }

    /**
     * Method:  makeMove
     * Purpose: Change the human to face the new location and moves to the 
     *          given location. 
     * Precondition: loc is valid in the grid of this critter
     * @param loc:   the location to move to (must be valid)
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        moveTo(loc);
    }
    
    /**
     * Method:  PlaySound
     * Purpose: Plays a sound whenever this Human is placed on the grid. 
     * @throws java.io.IOException
     */
    public void PlaySound() throws IOException
    {
        try {           
            File audioFile = new File("src\\info\\gridworld\\actor\\Human.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.start();
            

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            audioClip.close();
            audioStream.close();
            
            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method:  findDirection
     * Purpose: finds a preferred direction of movement for this human. Looks
     *          for monsters or the exit and will move toward or away from the
     *          closest of them. Returns 999 if no monster or exit are found in 
     *          the default distance
     * @return int: the direction of the nearest monster or exit
     */
    public int findDirection()
    {   
        //get all occupied locations, add monsters and exit to a new list 
        //players
        ArrayList<Location> L = getGrid().getOccupiedLocations();
        ArrayList<Actor> players = new ArrayList<>();
        for (Location i : L)
        {
            players.add(getGrid().get(i));
        }
        
        double dist = 10;
        Actor target = null;
        int direction = 999;
        
        //loop through players checking the distance from this Human to each of 
        //them. Compare that distance to a temp double dist and replace dist if 
        //the distance is less. Then replace target with P
        for (Actor P : players)
        {
            if ((P instanceof Zombie) 
                    || (P instanceof Vampire) 
                    || (P instanceof ExitPortal))
            {
                if (P.getLocation().calcDistanceTo(this.getLocation()) < dist)
                {
                    target = P;
                    dist = P.getLocation().calcDistanceTo(this.getLocation());
                }
            }             
        }
        
        //find the direction to the location updated in target
        if (target != null)
        {
            direction = this.getLocation().getDirectionToward(target.getLocation());
        }
        
        //if the target is an ExitPortal reverse distance so human moves towards
        //instead of away
        if (target instanceof ExitPortal)
            direction += 180;
               

        return direction;
    }
}
