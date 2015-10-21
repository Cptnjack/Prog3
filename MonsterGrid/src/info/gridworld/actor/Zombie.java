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

