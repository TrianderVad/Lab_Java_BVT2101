import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Все координаты вводятся в десятичном формате");
        // Ввод Координат первой точки
        System.out.println("Введите координаты для первой точки:");
        double x1 = console.nextDouble();
        double y1 = console.nextDouble();
        double z1 = console.nextDouble();
        // Ввод Координат второй точки
        System.out.println("Введите координаты для второй точки:");
        double x2 = console.nextDouble();
        double y2 = console.nextDouble();
        double z2 = console.nextDouble();
        // Ввод Координат третьей точки
        System.out.println("Введите координаты для третьей точки:");
        double x3 = console.nextDouble();
        double y3 = console.nextDouble();
        double z3 = console.nextDouble();

        // Создается объект Point3d в которого вносятся эти параметры
        Point3d f_point = new Point3d(x1,y1,z1);
        Point3d s_point = new Point3d(x2,y2,z2);
        Point3d t_point= new Point3d(x3,y3,z3);
        // Сравнение на то, чтобы точки были разные (если точки одинаковые, площадь не вычисляется)
        if (Point3d.isEquals(f_point,s_point) || Point3d.isEquals(f_point,t_point) || Point3d.isEquals(t_point,s_point)){
            System.out.println("Ошибка: Есть одинаковые точки!");
        }
        else {
            System.out.println("Площадь равна: " + computeArea(f_point,s_point,t_point));
        }
    }
        /* Логика метода ComputerArea
        Вычисляется длина всех трех прямых треугольника (расстояние между точками)
        Вычисляется полумериметр (a+b+c)/2
        По формуле Герона находится и возращается площадь с округлением до 2-ух
         */
        public static double computeArea(Point3d f_point, Point3d s_point, Point3d t_point){
        double a_long = Point3d.distanceTo(f_point,s_point);
        double b_long = Point3d.distanceTo(s_point,t_point);
        double c_long = Point3d.distanceTo(f_point,t_point);
        double semi_perim = (a_long + b_long + c_long)/2;
        return Math.round(Math.pow(semi_perim * (semi_perim-a_long) * (semi_perim-b_long) * (semi_perim-c_long),0.5)*100d)/100d;
        }
}

