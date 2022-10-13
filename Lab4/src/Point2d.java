//Двумерный класс точки
public class Point2d {
    // Координата Х
    private double xCoord;
    // Координата Y
    private double yCoord;

    // Конструктор инициализации
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    // Конструктор по умолчанию
    public Point2d() {
        this(0, 0);
    }

    // Возрвращение координаты X
    public double getX() {
        return xCoord;
    }

    // Возрвращение координаты Y
    public double getY() {
        return yCoord;
    }

    // Установка значения координаты X
    public void setX(double val) {
        xCoord = val;
    }

    // Установка значения координаты Y
    public void setY(double val) {
        yCoord = val;
    }
    /*
    public static void main (String[] args){
       Point2d myPoint = new Point2d();
       Point2d secondPoint = new Point2d();
       System.out.print(isEquals(myPoint, secondPoint));
       //System.out.print(isEquals(myPoint.xCoord, myPoint.yCoord, secondPoint.xCoord, secondPoint.yCoord));
       //System.out.print(myPoint.xCoord + " " + myPoint.yCoord);

    }
    //Мето isEquals проверяет координаты на совпадение
    public static boolean isEquals(Point2d obj1, Point2d obj2) {
        boolean checker = false;
        if ((obj1.xCoord == obj2.xCoord) && (obj1.yCoord == obj2.yCoord)) {
            checker = true;
        }
        return checker;

     */
}

