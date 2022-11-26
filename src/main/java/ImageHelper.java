import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {

    public BufferedImage getBufferedImg(String imgPath){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
