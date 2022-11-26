
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

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
        JComboBox<String> jComboBox = new JComboBox<String>();
        jComboBox.addItem(Mandelbrot.class.toString());
        jComboBox.addItem(Tricorn.class.toString());
        jComboBox.addItem(BurningShip.class.toString());

        JLabel jLabel = new JLabel("Fractals");
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        jPanel.add(jComboBox);




        JFrame jFrame = new JFrame("Фракталы");
        JPanel jPanel_s = new JPanel();
        JButton jButton_2 = new JButton("Сохранить");
        jButton_2.setActionCommand("save");
        JButton jButton = new JButton("Стереть");
        jButton.setActionCommand("reset");

        jPanel_s.add(jButton_2);
        jPanel_s.add(jButton);

        ResButton resButton = new ResButton();
        GetSelIt getSelIt = new GetSelIt();
        MouseListener mouseListener = new MouseListener();


        ji_display.setLayout(new BorderLayout());
        ji_display.addMouseListener(mouseListener);

        jButton.addActionListener(resButton);
        jButton_2.addActionListener(resButton);
        jComboBox.addActionListener(getSelIt);

        jFrame.add(ji_display, BorderLayout.CENTER);
        jFrame.add(jPanel_s, BorderLayout.SOUTH);
        jFrame.add(jPanel, BorderLayout.NORTH);

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
            switch (e.getActionCommand()) {
                case ("save"):
                    JFileChooser chooser = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                    chooser.setFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                    int user_sec = chooser.showSaveDialog(ji_display);
                    if(JFileChooser.APPROVE_OPTION == user_sec){
                        File file = new File(chooser.getSelectedFile().toString()+".png");
                        try {
                            ImageIO.write(ji_display.b_image, "png",file);
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(ji_display, exception.getMessage(),
                                    "Изображение не сохранено",JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    break;
                case ("reset"):
                    frac_gen.getInitialRange(rect);
                    drawFractal();
                    break;
            }
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
            ji_display.clearImage();
            drawFractal();


        }
    }
    // Смена фрактала
    private class GetSelIt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             JComboBox box = (JComboBox) e.getSource();
             switch ((String) box.getSelectedItem()){
                 case ("class Tricorn"):
                     frac_gen = new Tricorn();
                     break;
                 case ("class BurningShip"):
                     frac_gen = new BurningShip();
                     break;
                 case ("class Mandelbrot"):
                     frac_gen = new Mandelbrot();
                     break;
             }

        }
    }

    // Запуск приложения

    public static void main(String[] args) {
        FractalExplorer frac_exp = new FractalExplorer(800);
        frac_exp.createAndShowGUI();
        frac_exp.drawFractal();
    }
}
