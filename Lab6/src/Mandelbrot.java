import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000; // Поле Макс значения итераций
    /*
    Метод определяет область комлексной плоскости
     */
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;

    }
    /*
    Метод реализует фрактал Мальденброта
     */
    @Override
    public int numIterations(double x, double y) {
        double real = 0;
        double image = 0;
        int iter = 0;
        while (iter < MAX_ITERATIONS && (real * real + image * image) < 4) {
            double real2 = real * real - image * image + x;
            double image2 = real * image * 2 + y;
            real = real2;
            image = image2;
            iter++;


        }
        if (iter == MAX_ITERATIONS) return -1;
        return iter;
    }


}
