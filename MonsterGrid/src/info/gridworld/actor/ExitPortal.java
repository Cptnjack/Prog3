/*
 * Name:    Lewis Confair and Michael Whitley
 * File:    ExitPortal.java
 * Purpose: Provides a class for a ExitPortal actor.
 */
package info.gridworld.actor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * An ExitPortal is an actor that does not move and removes a any humans that
 * are on the grid adjacent to the ExitPortal
 */
public class ExitPortal extends Actor
{
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Method:  ExitPortal
     * Purpose: Default constructor for ExitPortal, constructs a black 
     *          ExitPortal
     */
    public ExitPortal()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Method:  ExitPortal
     * Purpose: Parameterized constructor for ExitPortal, constructs an 
     *          ExitPortal with the color passed
     * @param ExitColor:    the color for the new ExitPortal
     */
    public ExitPortal(Color ExitColor)
    {
        setColor(ExitColor);
    }

    /**
     * Method:  act
     * Purpose: An ExitPortal acts by getting a list of neighbors and processing 
     *          them
     * @return String:  returns a string to be output in the panel 
     */
    public String act()
    {   
        //if this actor is not on the grid return ""
        if (getGrid() == null)
            return "";
        
        //get the list of adjacent actors and pass it to processActors for
        //processing
        ArrayList<Actor> actors = getActors();        
        processActors(actors);
        return "";
    }
    
    /**
     * Method:  getActors
     * Purpose: Gets the actors that are in the same grid as the ExitPortal
     *          for processing.
     * @return: a list of actors that are neighbors of this critter
     */
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }
    
    /**
     * Method:  processActors
     * Purpose: Processes the actors. The ExitPortal will consume(remove) any
     *          humans that are adjacent to the ExitPortal
     * @param actors:   the list of actors adjacent to the exit portal
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {           
            if ((a instanceof Human))
            {
                a.removeSelfFromGrid(); 
            }
        }   
    }
}
