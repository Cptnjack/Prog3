/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Random;
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
 * @author Michael
 */
public class Vampire extends Monster 
{
    private static final Color DEFAULT_COLOR = Color.BLACK;
    
    public Vampire() throws IOException
    {
        super();
        setColor(DEFAULT_COLOR);
        PlaySound();
    }
    
    public Vampire(Color vampireColor) throws IOException
    {
        super();
        setColor(vampireColor);
        PlaySound();
    }
    
    /**
     * Method:  act
     * @returns: void
     * Purpose: A Vampire acts by getting a list of its neighbors, processing 
     *          them, getting locations to move to, selecting one of them, 
     *          and moving to the selected location.
     */
    public String act()
    {
        if (getGrid() == null)
            return "";
        String s = "";
        Location current = this.getLocation();
        ArrayList<Actor> actors = getActors();
        s += processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
        Location next = this.getLocation();
        s += "\nVampire moved from "+current.toString()+" to "+
                next.toString();
        return new String(s);
    }
    
    /**
     * Processes the actors. Implemented to "eat" (i.e. remove) all actors that
     * are not rocks or Beings. Override this method in subclasses to process
     * neighbors in a different way. <br />
     * Precondition: All objects in <code>actors</code> are contained in the
     * same grid as this Being.
     * @param actors the actors to be processed
     */
    public String processActors(ArrayList<Actor> actors)
    {   
        double r = Math.random()*10;
        int i = Math.round((float)r);
        String s = "";
        Location L;
        for (Actor a : actors)
        {
            
            if (a instanceof Human)
            {
                //the vampire gets killed
                if(i == 0)
                {
                    L = a.getLocation();
                    s+= "\nVampire at " + this.getLocation().toString()+ " was"+
                            "killed by Human at " + L.toString();
                    this.removeSelfFromGrid();
                }
                //the human gets killed
                else if(i > 1 && i < 7 )
                {
                    L = a.getLocation();
                    s+= "\nVampire killed Human at "+L.toString();
                    a.removeSelfFromGrid();
                }
                    
                //the human is made into a Vampire
                else if( i > 7)
                {
                    L = a.getLocation();
                    a.removeSelfFromGrid();
                    Vampire v;
                    try {
                        v = new Vampire();
                        v.putSelfInGrid(getGrid(), L);
                        s+= "\nVampire killed Human at " + L.toString() + 
                                " and replaced him with another Vampire!";
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Vampire.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
        return new String(s);
    }
    
    /**
     * Gets the possible locations for the next move. Implemented to return the
     * empty neighboring locations. Override this method in subclasses to look
     * elsewhere for move locations.<br />
     * Postcondition: The locations must be valid in the grid of this Being.
     * @return a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
       
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }
    
    /**
     * Selects the location for the next move. Implemented to pick the location 
     * that moves toward the Human in the grid, or to return the current 
     * location if <code>locs</code> has size 0. Override this method in 
     * subclasses that have another mechanism for selecting the next move 
     * location. <br />
     * Precondition: All locations in <code>locs</code> are valid in the grid
     * of this Being
     * @param locs the possible locations for the next move
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        
        
        ArrayList<Location> L = getGrid().getOccupiedLocations();
        ArrayList<Actor> A = new ArrayList<>();
        
        for (Location i : L)
        {
            if(getGrid().get(i) instanceof Human)
                A.add(getGrid().get(i));
        }
        
        if(A.isEmpty())
        {
            int r = (int) (Math.random() * n);
            return locs.get(r);
        }
        Actor h = A.get(0);
        
        int d = this.getLocation().getDirectionToward(h.getLocation());
        Location next = this.getLocation().getAdjacentLocation(d);
        
        if(h.getLocation().equals(next))
            return getLocation();
     
        return next;
    }
    
    /**
     * Moves this Vampire to the given location. Implemented to call moveTo.
     * Precondition: <code>loc</code> is valid in the grid of this Vampire
     * @param loc the location to move to (must be valid)
     */
    public void makeMove(Location loc)
    {   
        setDirection(getLocation().getDirectionToward(loc));
        
        super.makeMove(loc);
        moveTo(loc);
      
    }
    
    public void PlaySound() throws IOException
    {
        try {
            
            File audioFile = new File("src\\info\\gridworld\\actor\\Vampire.wav");
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
}
