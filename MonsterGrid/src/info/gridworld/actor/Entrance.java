/*
 * Name:    Lewis Confair and Michael Whitley
 * File:    Entrance.java
 * Purpose: Provides a class for a Entrance actor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An Entrance is an actor that does not move and spawns a Human if there are no
 * humans currently on the grid
 */
public class Entrance extends Actor
{
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Method: Entrance
     * Purpose: default constructor for a black entrance.
     */
    public Entrance()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Method: Entrance
     * Purpose: parameterized constructor for an entrance of color entranceColor.
     * @param entranceColor:    the color for the entrance
     */
    public Entrance(Color entranceColor)
    {
        setColor(entranceColor);
    }

    /**
     * Method:  act
     * Purpose: An entrance acts by getting a list of neighbors and processing 
     *          them
     * @return String: returns a string to be output in the panel 
     */
    public String act()
    {
        if (getGrid() == null)
            return "";       
        try {
            processActors();
        } catch (IOException ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";        
    }
    
    /**
     * Method:  getActors
     * Purpose: Gets the actors that are in the same grid for processing. 
     *          Implemented to return the actors that occupy neighboring grid 
     *          locations. 
     * @return: a list of actors that are neighbors of this critter
     */
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }
    
    /**
     * Method:  processActors
     * Purpose: Processes the actors. If there are no humans on the grid the 
     *          entrance will spawn a new one
     * @throws java.io.IOException
     */
    public void processActors() throws IOException
    {
        Grid<Actor> gr = getGrid();        
              
        //check all the actors on the grid. If there is a human return
        ArrayList<Location> L = getGrid().getOccupiedLocations();
        Actor player = null;
        for (Location i : L)
        {
            player = getGrid().get(i);
            if ( player instanceof Human)
                return;
        }
        
        //if there are no humans on the grid find an open spot and spawn a new 
        //human in that location
        ArrayList<Location> open = getGrid().getEmptyAdjacentLocations(getLocation());
        int n = open.size();
        if (n == 0)
            return;
        int r = (int) (Math.random() * n);
        
        Location place = open.get(r);
        Human h = new Human();
        h.putSelfInGrid(gr , place);      
    }
}
