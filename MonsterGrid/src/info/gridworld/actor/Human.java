/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
<<<<<<< HEAD
import java.awt.Color;
import java.io.File;
import java.io.IOException;
=======

import java.io.File;
import java.io.IOException;

import java.awt.Color;


>>>>>>> origin/Lewis
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

/**
 *
 * @author lconfair
 */
public class Human extends Being
<<<<<<< HEAD
=======

>>>>>>> origin/Lewis
{   
    /**
     * Constructs a Human.
     */
    public Human() throws IOException
    {
<<<<<<< HEAD
        super();        
        setColor(null); 
        PlaySound();
=======
        setColor(null);
		PlaySound();
>>>>>>> origin/Lewis
    }

    /**
     * Constructs a Human of a given color.
     * @param initialColor the initial color of this flower
     */
    public Human(Color initialColor) throws IOException
    {
        setColor(initialColor);
<<<<<<< HEAD
    }    
=======
		PlaySound();
    }
    
>>>>>>> origin/Lewis

    /**
     * Method:  act
     * Purpose: A human acts by getting a list of its neighbors, processing 
     *          them, getting locations to move to, selecting one of them, and 
     *          moving to the selected location.
     */
    public String act()
    {
        if (getGrid() == null)
            return "";
       
        Location current = this.getLocation();
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs,findDirection());
        makeMove(loc);
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
     * @return a list of actors that are neighbors of this critter
     */
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

//    /**
//     * Processes the actors. Implemented to "eat" (i.e. remove) all actors that
//     * are not rocks or critters. Override this method in subclasses to process
//     * neighbors in a different way. <br />
//     * Precondition: All objects in <code>actors</code> are contained in the
//     * same grid as this critter.
//     * @param actors the actors to be processed
//     */
//    public void processActors(ArrayList<Actor> actors)
//    {
//        ArrayList<Actor> enemy = new ArrayList<>();
//        for (Actor a : actors)
//        {
//                       
//            if (a instanceof Food)
//            {
//                enemy.add(a); 
//            }
//        }   
//        
//        int n = enemy.size();
//        if (n == 0)
//            return;
//        int r = (int) (Math.random() * n);
//
//        Actor other = enemy.get(r);
//        other.removeSelfFromGrid();
////            if (!(a instanceof Rock) && !(a instanceof Critter))
////                a.removeSelfFromGrid();
//        
//    }

    /**
     * Processes the actors. Implemented to "eat" (i.e. remove) all actors that
     * are not rocks or critters. Override this method in subclasses to process
     * neighbors in a different way. <br />
     * Precondition: All objects in <code>actors</code> are contained in the
     * same grid as this critter.
     * @param actors the actors to be processed
     */
    public String processActors(ArrayList<Actor> actors)
    {
        ArrayList<Actor> enemy = new ArrayList<>();
        for (Actor a : actors)
<<<<<<< HEAD
        {             
            if (a instanceof Food)
=======
        {           
            if (!(a instanceof Rock) 
                    && !(a instanceof Human) 
                    && !(a instanceof Entrance)
                    && !(a instanceof ExitPortal))
>>>>>>> origin/Lewis
            {
                enemy.add(a); 
            }
        }   
        
        int n = enemy.size();
        if (n == 0)
            return "";
        int r = (int) (Math.random() * n);

        Actor other = enemy.get(r);
        other.removeSelfFromGrid();
        String s = "";
        return new String(s);        
    }
    
    /**
     * Gets the possible locations for the next move. Implemented to return the
     * empty neighboring locations. Override this method in subclasses to look
     * elsewhere for move locations.<br />
     * Postcondition: The locations must be valid in the grid of this critter.
     * @return a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }

    /**
     * Selects the location for the next move. Implemented to randomly pick one
     * of the possible locations, or to return the current location if
     * <code>locs</code> has size 0. Override this method in subclasses that
     * have another mechanism for selecting the next move location. <br />
     * Precondition: All locations in <code>locs</code> are valid in the grid
     * of this critter
     * @param locs the possible locations for the next move
     * @param direction the direction of the closest monster or exit
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs, int direction)
    {
        Location towards = null;
        int newD = (direction + 180) % 360;
        int n = locs.size();
        
        if (n == 0)
            return getLocation();
        else if (n == 1)
            return locs.get(0);
        else
        {
            if (direction == 999)
            {
                int r = (int) (Math.random() * n);
                towards = locs.get(r);
            }
            else
            {
                int tempDirect = 360;
                for (Location L : locs)
                {   
                    int d = this.getLocation().getDirectionToward(L);
                    if (d == newD)
                        return L;
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
     * Moves this critter to the given location. Implemented to call moveTo.
     * Override this method in subclasses that want to carry out other actions
     * for moving (for example, turning or leaving traces). <br />
     * Precondition: <code>loc</code> is valid in the grid of this critter
     * @param loc the location to move to (must be valid)
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        moveTo(loc);
    }
    
<<<<<<< HEAD
   
=======

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

    
>>>>>>> origin/Lewis
    public int findDirection()
    {
        ArrayList<Location> L = getGrid().getOccupiedLocations();
        ArrayList<Actor> players = new ArrayList<>();
        for (Location i : L)
        {
            players.add(getGrid().get(i));
        }
        
        double dist = 10;
        Actor target = null;
        int direction = 999;
        
        for (Actor P : players)
        {
            if ((P instanceof Zombie) 
                    || (P instanceof Vampire) 
                    || (P instanceof ExitPortal))
            {
                if (P.getLocation().calcDistanceTo(this.getLocation()) < dist)
                    target = P;
            }             
        }
        
        if (target != null)
        {
            direction = this.getLocation().getDirectionToward(target.getLocation());
        }
        
        if (target instanceof ExitPortal)
            direction += 180;
               
        return direction;
    }
<<<<<<< HEAD
    

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
=======
>>>>>>> origin/Lewis
}
