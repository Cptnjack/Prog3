/*
 * Name:    Lewis Confair and Michael Whitley
 * File:    Food.java
 * Purpose: Provides an interface for an entity with color, direction, location
 *          and grid.
 */

package info.gridworld.actor;

import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lconfair
 */

    

/**
 * A <code>Rock</code> is an actor that does nothing. It is commonly used to
 * block other actors from moving. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class Food extends Actor
{   
    /**
     * Constructs a Food.
     */
    public Food()
    {
        setColor(null);
    }

    /**
     * Constructs Food of a given color.
     * @param initialColor the initial color of this flower
     */
    public Food(Color initialColor)
    {
        setColor(initialColor);
    }
  
    /**
     * Overrides the <code>act</code> method in the <code>Actor</code> class
     * to do nothing.
     */
    public String act()
    {
        return "";
    }
}
