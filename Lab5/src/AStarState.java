import java.util.*;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;



    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
   /* public HashMap open_waypoint = new HashMap<Location, Waypoint>();
    public HashMap close_waypoint = new HashMap<Location, Waypoint>();

    */
    public HashMap<Location, Waypoint> open_waypoint = new HashMap();
    public HashMap<Location, Waypoint> close_waypoint = new HashMap();





    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint(){
        if (numOpenWaypoints() == 0) {
            return null;
        }
        Set open_waypoint_key = open_waypoint.keySet();
        Iterator iterator = open_waypoint_key.iterator();
        Waypoint bestWP = null;
        float best_cost = 99999;

        while (iterator.hasNext()){
            Location location = (Location)iterator.next();
            System.out.println(open_waypoint.values());
            Waypoint waypoint = open_waypoint.get(location);

            float wp_total_cost = waypoint.getTotalCost();

            if(wp_total_cost < best_cost){
                bestWP = waypoint;
                best_cost = wp_total_cost;
            }
        }
        return bestWP;

        // TODO:  Implement.
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location location = newWP.getLocation();

        if(open_waypoint.containsKey(location)){
            Waypoint currect_wp = open_waypoint.get(location);
            if(newWP.getPreviousCost() < currect_wp.getPreviousCost()){
                open_waypoint.put(location,newWP);
                return true;
            }
            return false;
        }
        open_waypoint.put(location,newWP);
        return true;



        // TODO:  Implement.

    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return open_waypoint.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = close_waypoint.get(loc);
        close_waypoint.remove(loc);
        open_waypoint.put(loc,waypoint);

        // TODO:  Implement.
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return close_waypoint.containsKey(loc);
    }
}

