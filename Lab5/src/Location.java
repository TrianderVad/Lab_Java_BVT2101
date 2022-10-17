import java.util.Objects;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /*
    Логика метода equals
    Он сравнивает два объекта в классе, есть несколько правил
    1. Любой объект равен самому себе
    2. Он должен быть симметричен, если a.equals(b), то и b.equals(a) == true
    3. Транизитвность, если a == b, b == c, то a == c
    4. Неравенство с null
    5. Сравнивать объекты в своем классе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }
    /*
    Логика метода hashCode
    Он нужен для облегчения сравнения
    Если Хэш коды объектов равны, то мы отправляем в сравнение equals
    Если нет, то они не равны
    Правила:
    1. Если equals == true, то и хэш код одинаковый
    2. Если хэщкод вызывается несколько раз, то он должен быть один и тот-же
    3. Правила 1 не работает наоборот
     */

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }
}
