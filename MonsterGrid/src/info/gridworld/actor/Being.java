package info.gridworld.actor;

import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>Being</code> is an actor that moves through its world, processing
 * other actors in some way and then picking a new location. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Being extends Actor
{
    /**
     * A Being acts by getting a list of its neighbors, processing them,
     * getting locations to move to, selecting one of them, and moving to the
     * selected location.
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
     * Gets the actors for processing. The actors must be contained in the same
     * grid as this Being. Implemented to return the actors that occupy
     * neighboring grid locations. Override this method in subclasses to look
     * elsewhere for actors to process.
     * @return a list of actors that are neighbors of this Being
     */
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
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
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Being))
                a.removeSelfFromGrid();
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
     * Selects the location for the next move. Implemented to randomly pick one
     * of the possible locations, or to return the current location if
     * <code>locs</code> has size 0. Override this method in subclasses that
     * have another mechanism for selecting the next move location. <br />
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
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }

    /**
     * Moves this Being to the given location. Implemented to call moveTo.
     * Override this method in subclasses that want to carry out other actions
     * for moving (for example, turning or leaving traces). <br />
     * Precondition: <code>loc</code> is valid in the grid of this Being
     * @param loc the location to move to (must be valid)
     */
    public void makeMove(Location loc)
    {
        moveTo(loc);
    }
}
