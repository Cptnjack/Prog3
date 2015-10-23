/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Michael
 */
public class Zombie extends Monster
{
    public static boolean playCompleted;
    private static final Color DEFAULT_COLOR = Color.GREEN;
    
    public Zombie() throws IOException
    {
        super();
        setColor(DEFAULT_COLOR);
        PlaySound();
    }
    
    public Zombie(Color zombieColor) throws IOException
    {
        super();
        setColor(zombieColor);
        PlaySound();
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
        double r = Math.random();
        if( r < 0.5)
        {
            super.makeMove(loc);
            moveTo(loc);
        }
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
        s += "\nZombie moved from "+current.toString()+" to "+
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
                //the zombie gets killed
                if(i <4)
                {
                    L = a.getLocation();
                    s += "\nZombie was killed by Human at "+ L.toString();
                    this.removeSelfFromGrid();
                }
                //the human gets killed
                else if(i > 4 && i < 7 )
                {
                    L = a.getLocation();
                    s += "\nZombie killed Human" + " at " + L.toString();
                    a.removeSelfFromGrid();
                }
                    
                //the human is made into a zombie
                else if( i > 7)
                {
                    L = a.getLocation();
                    a.removeSelfFromGrid();
                    Zombie z;
                    try {
                        z = new Zombie();
                        z.putSelfInGrid(getGrid(), L);
                        s+= "\nZombie killed Human at " + L.toString() + 
                                "and replaced him with another Zombie!";
                    } catch (IOException ex) {
                        Logger.getLogger(Vampire.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
        return new String(s);
    }
    
    
    public void PlaySound() throws IOException
    {
        try {
            
            File audioFile = new File("src\\info\\gridworld\\actor\\Zombie.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.start();
            

            try {
                Thread.sleep(3000);
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

