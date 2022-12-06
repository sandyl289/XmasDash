import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageHelper {

    public BufferedImage getBufferedImg(String imgPath){
        URL path = ImageHelper.class.getResource(imgPath);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
