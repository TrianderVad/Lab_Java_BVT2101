import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent {
    private BufferedImage b_image; //Поле для изображения
/*
Конструктор, который инициализирует изображение (ширину, длину, тип кодировки)
Настраивает размер отображеемого изображения
 */
    public JImageDisplay(int width, int height){
        b_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }
    /*
    Метод отрисовывает изображение
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(b_image, 0, 0, b_image.getWidth(), b_image.getHeight(), null);
    }
    /*
    Метод очищает картинку (переходит по пикселям и красит их в черный цвет)
     */
    public void clearImage(){
        int[] black_mass = new int[b_image.getHeight()*b_image.getWidth()];
        b_image.setRGB(0,0, b_image.getWidth(), b_image.getHeight(), black_mass, 0, 1);
    }
    /*
    Красит пиксель в опр. цвет
     */
    public void drawPixel(int x, int y, int rgbColor){
        b_image.setRGB(x,y,rgbColor);
    }
}
