package info.gridworld.actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author Michael Whitley and Lewis Confair
 * @date 10/22/2015
 * @description A monster is a Being that eats Humans
 */

public class Monster extends Being 
{
    /**
     * Processes the actors. Implemented to "eat" (i.e. remove) Humans.<br />
     * Precondition: All objects in <code>actors</code> are contained in the
     * same grid as this Monster.
     * @param actors the actors to be processed
     * @return String containing the action taken
     */
    public String processActors(ArrayList<Actor> actors)
    {
        String s = "";
        Location L;
        for (Actor a : actors)
        {
            if (a instanceof Human)
            {
                L = a.getLocation();
                s += "\nBeing removed "+a.getClass().toString()+" at location"+
                        L.toString();
                a.removeSelfFromGrid();
                 
            }     
        }
        return new String(s);
    }

    /**
     * Gets the possible locations for the next move. Implemented to return the
     * empty neighboring locations. Override this method in subclasses to look
     * elsewhere for move locations.<br />
     * Postcondition: The locations must be valid in the grid of this Monster.
     * @return a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }

    /**
     * Selects the location for the next move. Implemented to randomly pick one
     * of the possible locations, or to return the current location if
     * <code>locs</code> has size 0. Override this method in subclasses that
     * have another mechanism for selecting the next move location. <br />
     * Precondition: All locations in <code>locs</code> are valid in the grid
     * of this Monster
     * @param locs the possible locations for the next move
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }

    /**
     * Moves this Monster to the given location. Implemented to call moveTo.
     * Override this method in subclasses that want to carry out other actions
     * for moving (for example, turning or leaving traces). <br />
     * Precondition: <code>loc</code> is valid in the grid of this Monster
     * @param loc the location to move to (must be valid)
     */
    public void makeMove(Location loc)
    {
        moveTo(loc);
    }
    
    /**
     * Method:  act
     * 
     * @return String containing action taken
     * Purpose: A Monster acts by getting a list of its neighbors, processing 
     *          them, getting locations to move to, selecting one of them, 
     *          and moving to the selected location.
     */
    public String act()
    {
        if (getGrid() == null)
            return "";
        Location current = this.getLocation();
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
        Location next = this.getLocation();
        String s = "\nMonster moved from "+current.toString()+" to "+
                next.toString();
        return new String(s);
    }
}
