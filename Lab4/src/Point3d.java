public class Point3d extends Point2d{
    // Координаты Х, Y, Z
    private double xCoord;
    private double yCoord;
    private double zCoord;
    // Конструктор инициализации

    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    // Конструктор по умолчанию
    public Point3d() {
        this(0, 0, 0);
    }

    // Возрвращение координат X, Y, Z


    public double getZ() {
        return zCoord;
    }

    // Установка значения координат X, Y, Z


    public void setZ(double val) {
        zCoord = val;
    }


    //Мето isEquals проверяет координаты на совпадение
    public static boolean isEquals(Point3d obj1, Point3d obj2) {
        if ((obj1.xCoord == obj2.xCoord) && (obj1.yCoord == obj2.yCoord) && ((obj1.zCoord == obj2.zCoord))) {
           return true;
        }
        return false;
    }
    // Метод distanceTo ищет расстояние между точками
    public static double distanceTo(Point3d obj1, Point3d obj2){
        return  Math.pow(Math.pow(obj2.xCoord - obj1.xCoord, 2) + Math.pow(obj2.yCoord - obj1.yCoord, 2)
                + Math.pow(obj2.zCoord - obj1.zCoord, 2),0.5);
    }
    /* Проверка
    public static void main(String[] args) {
        Point3d f_point = new Point3d();
        Point3d s_point = new Point3d(1,1,1);
        System.out.println("Точки равны? " + isEquals(f_point,s_point));
        System.out.println("Расстояние между точками: " + distanceTo(f_point,s_point));
    }

     */

}

