/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

import java.util.ArrayList;

import java.util.Random;
/**
 *
 * @author Michael
 */
public class Vampire extends Monster 
{
    private static final Color DEFAULT_COLOR = Color.BLACK;
    
    public Vampire()
    {
        super();
        setColor(DEFAULT_COLOR);
    }
    
    public Vampire(Color vampireColor)
    {
        super();
        setColor(vampireColor);
    }
    
    /**
     * Method:  act
     * @returns: void
     * Purpose: A Vampire acts by getting a list of its neighbors, processing 
     *          them, getting locations to move to, selecting one of them, 
     *          and moving to the selected location.
     */
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
        
    }
    
    /**
     * Processes the actors. Implemented to "eat" (i.e. remove) all actors that
     * are not rocks or Beings. Override this method in subclasses to process
     * neighbors in a different way. <br />
     * Precondition: All objects in <code>actors</code> are contained in the
     * same grid as this Being.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {   
        double r = Math.random()*10;
        int i = Math.round((float)r);
        
        for (Actor a : actors)
        {
            
            if (a instanceof Human)
            {
                //the vampire gets killed
                if(i == 0)
                    this.removeSelfFromGrid();
                //the human gets killed
                else if(i > 1 && i < 7 )
                    a.removeSelfFromGrid();
                //the human is made into a Vampire
                else if( i > 7)
                {
                    
                    Location L = a.getLocation();
                    a.removeSelfFromGrid();
                    Vampire v = new Vampire();
                    v.putSelfInGrid(getGrid(), L);
                }
            }
        }
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
}
