
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    /*
    Поля: Размер экрана, Поле Отображение изображения, Поле Метода Мандельброта, Поле области комплексной плоскоти
     */
    private int screen_size;
    private JImageDisplay ji_display;
    private FractalGenerator frac_gen;
    private Rectangle2D.Double rect;

    /*
    Конструктор
    Инициализирует поля
     */
    public FractalExplorer(int size) {
        screen_size = size;
        ji_display = new JImageDisplay(size, size);
        frac_gen = new Mandelbrot();
        rect = new Rectangle2D.Double();
        frac_gen.getInitialRange(rect);
    }
    /*
    Метод создает GUI и показывает его, так же добавляет скрипты
     */
    public void createAndShowGUI() {
        JFrame jFrame = new JFrame("Фракталы");
        JButton jButton = new JButton("Стереть");
        ResButton resButton = new ResButton();
        MouseListener mouseListener = new MouseListener();


        ji_display.setLayout(new BorderLayout());
        ji_display.addMouseListener(mouseListener);

        jButton.addActionListener(resButton);

        jFrame.add(ji_display, BorderLayout.CENTER);
        jFrame.add(jButton, BorderLayout.SOUTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setResizable(false);


    }
    /*
    Метод рисования фракталов
    Перебирает все значения X и Y
    С помощью Фракталов Мандельброта получает координаты х и y
    Выбирает цвет
    и закрашивает опр. пиксель
     */
    private void drawFractal(){
        for (int x = 0; x < screen_size; x++){
            for(int y = 0; y < screen_size; y++){
                double xCoord = FractalGenerator.getCoord(rect.x, rect.x + rect.width, screen_size, x);
                double yCoord = FractalGenerator.getCoord(rect.y, rect.y + rect.height, screen_size, y);
                int iter = frac_gen.numIterations(xCoord, yCoord);
                if (iter == -1) ji_display.drawPixel(x, y, 0);
                else {
                    float hue = 0.7f + (float) iter / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    ji_display.drawPixel(x,y,rgbColor);
                }
            }
        }
        ji_display.repaint();
    }

    /*
    Переносит нас в изначальное состояние
     */
    private class ResButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frac_gen.getInitialRange(rect);
            drawFractal();
        }
    }
    /*
    При клике переносит на опр. координату, и перещитывает зум и радиус действия
     */
    private class MouseListener extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            double xCoord = FractalGenerator.getCoord(rect.x, rect.x + rect.width, screen_size, x);
            double yCoord = FractalGenerator.getCoord(rect.y, rect.y + rect.height, screen_size, y);
            frac_gen.recenterAndZoomRange(rect,xCoord, yCoord, 0.5);
            drawFractal();


        }
    }
    // Запуск приложения

    public static void main(String[] args) {
        FractalExplorer frac_exp = new FractalExplorer(800);
        frac_exp.createAndShowGUI();
        frac_exp.drawFractal();
    }
}
