/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.gridworld.actor;

import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.awt.Color;

/**
 *
 * @author Michael
 */
public class Zombie extends Monster
{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    
    public Zombie()
    {
        super();
        setColor(DEFAULT_COLOR);
    }
    
    public Zombie(Color zombieColor)
    {
        super();
        setColor(zombieColor);
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
}
