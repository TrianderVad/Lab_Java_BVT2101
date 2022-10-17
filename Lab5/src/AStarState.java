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
     * Если не осталось открытых позиций возращает null
     * Задаем список ключей, потом задаем итератор
     * Создаем переменнюу bestWP и присваеваем значение null
     * Потом best_cost и присваем максималньое значение
     * Перебираем список, если общая стоимость wp меньше best_cost, то
     * bestWP = waypoint
     * best_cost = wp_total_cost
     * Возращаем best_wp
     **/
    public Waypoint getMinOpenWaypoint(){
        if (numOpenWaypoints() == 0) {
            return null;
        }
        Set open_waypoint_key = open_waypoint.keySet();
        Iterator iterator = open_waypoint_key.iterator();
        Waypoint bestWP = null;
        float best_cost = Float.MAX_VALUE;



        while (iterator.hasNext()){
            Location location = (Location)iterator.next();
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
     * Это метод добляет новый waypoint в словарь open_waypoint
     * Если цена предыдущих ходов меньше, чем у нынешнего wp
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


    /** Метод возращает раз open_waypoint **/
    public int numOpenWaypoints()
    {

        return open_waypoint.size();
    }


    /**
     * Этот метод удаляет позицию из open_waypoint и добавляет в close_waypoint
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoint.remove(loc);

        close_waypoint.put(loc,waypoint);

        // TODO:  Implement.
    }

    /**
     * Проверка, есть ли ключ позиции в словаре close_waypoint
     **/

    public boolean isLocationClosed(Location loc)
    {
        return close_waypoint.containsKey(loc);
    }
}

