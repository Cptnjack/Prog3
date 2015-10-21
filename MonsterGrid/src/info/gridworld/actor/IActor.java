/*
 * Name:    Lewis Confair and Michael Whitley
 * File:    IActor.java
 * Purpose: Provides an interface for an entity with color, direction, location
 *          and grid.
 */
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Purpose: IActor provides an interface for an entity with color, direction,
 *          location and grid.
 * @author: Lewis Confair and Michael Whitley
 */
public interface IActor 
{
    /**
     * Method:  getColor
     * Purpose: Accessor method for the current color of this actor
     * @return: the color of this actor
     */
    Color getColor();
   
    /**
     * Method:  setColor
     * Purpose: Mutator method for the current color of this actor
     * @param newColor: the new color
     */
    void setColor(Color newColor);

    /**
     * Method:  getDirection
     * Purpose: Accessor method for the current direction of this actor.
     * @return: the direction of this actor
     */
    int getDirection();

    /**
     * Method:  setDirection
     * Purpose: Mutator method for the direction of this actor.
     * @param newDirection: the new direction this actor is to be set to, that 
     *                      is equivalent to newDirection.
     */
    void setDirection(int newDirection);

    /**
     * Method:  getGrid
     * Purpose: Accessor method for the grid in which this actor is located.
     * @return: the grid of this actor, or null if this actor is not contained
     *          in a grid
     */
    Grid<Actor> getGrid();

    /**
     * Method:  getLocation
     * Purpose: Accessor function for the location of this actor.
     * Precondition: This actor is contained in a grid
     * @return: the location of this actor
     */
    Location getLocation();

    /**
     * Method:  putSelfInGrid
     * Purpose: Puts this actor into a grid. If there is another actor at the 
     *          given location, it is removed.
     * Precondition:    (1) This actor is not contained in a grid 
     *                  (2) loc is valid in gr
     * @param gr:   the grid into which this actor should be placed
     * @param loc:  the location into which the actor should be placed
     */
    void putSelfInGrid(Grid<Actor> gr, Location loc);

    /**
     * Method:  removeSelfFromGrid
     * Purpose: Removes this actor from its grid.
     * Precondition:    This actor is contained in a grid
     */
    void removeSelfFromGrid();

//    /**
//     * Method:  moveTo
//     * Purpose: Moves this actor to a new location. If there is another actor 
//     *          at the given location, it is removed. 
//     * Precondition:    (1) This actor is contained in a grid 
//     *                  (2) newLocation is valid in the grid of this actor
//     * @param newLocation:  the new location
//     */
//    void moveTo(Location newLocation);

    /**
     * Method:  act
     * Purpose: Reverses the direction of this actor. Override this method 
     *          in subclasses of Actor to define types of actors with different
     *          behavior
     */
    void act();

    /**
     * Method: toString
     * Purpose: Creates a string that describes this actor.
     * @return: a string with the location, direction, and color of this actor
     */
    String toString();
}