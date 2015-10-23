package info.gridworld.actor;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
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

/*
 * @author Michael Whitley and Lewis Confair
 * @date 10/22/2015
 * @description: A Vampire is a Monster that chases Humans and attempts to eat
 *               them. There is a chance that the Vampire will just kill the
 *               Human, the Human will kill the Vampire, or the Vampire will
 *               change the Human into a Vampire
 * 
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
     * @return: String containing the action taken
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
        Location loc = selectMoveLocation(moveLocs,findDirection());
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
     * @return String containing action taken
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
                        Logger.getLogger(
                                Vampire.class.getName()).log(Level.SEVERE, 
                                        null, ex);
                    }
                    
                }
            }
        }
        return new String(s);
    }
    
    /**
     * Selects the location for the next move. Implemented to chase a Human,
     * or to return the current location if
     * <code>locs</code> has size 0.<br />
     * Precondition: All locations in <code>locs</code> are valid in the grid
     * of this Vampire
     * @param locs the possible locations for the next move
     * @param direction the direction of the Human
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs, int direction)
    {
        Location towards = null;
        int newD = (direction ) % 360;
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
     * Selects the direction in which the Vampire is to move next
     * @return int containing the direction in which to move next
     */
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
            if ((P instanceof Human))
            {
                if (P.getLocation().calcDistanceTo(this.getLocation()) < dist)
                    target = P;
            }             
        }
        
        if (target != null)
        {
            direction = this.getLocation().getDirectionToward(
                    target.getLocation());
        }
        
        return direction;
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
    
    /**
     * Plays the sound of the Vampire
     * @throws IOException 
     */
    public void PlaySound() throws IOException
    {
        try {
            
            File audioFile = new File(
                    "src\\info\\gridworld\\actor\\Vampire.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    audioFile);
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
