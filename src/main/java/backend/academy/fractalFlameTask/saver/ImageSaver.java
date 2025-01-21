package backend.academy.fractalFlameTask.saver;

import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.enums.ImageFormat;
import backend.academy.fractalFlameTask.exception.ImageSaveException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_FILE_PATH_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_FORMAT_EXCEPTION_TEXT;


public class ImageSaver {

    public void save(Image image, Path path, ImageFormat format) {
        validateImage(image);
        validatePath(path);
        validateImageFormat(format);

        try {
            ImageIO.write(convertToBufferedImage(image), format.name(), path.toFile());
        } catch (IOException ex) {
            throw new ImageSaveException(ex);
        }
    }

    private void validateImage(Image image) {
        if (image == null) {
            throw new NullPointerException(NULL_IMAGE_EXCEPTION_TEXT);
        }
    }

    private void validatePath(Path path) {
        if (path == null) {
            throw new NullPointerException(NULL_FILE_PATH_EXCEPTION_TEXT);
        }
    }

    private void validateImageFormat(ImageFormat format) {
        if (format == null) {
            throw new NullPointerException(NULL_IMAGE_FORMAT_EXCEPTION_TEXT);
        }
    }

    private BufferedImage convertToBufferedImage(Image image) {
        final int width = image.imageSize().width();
        final int height = image.imageSize().height();
        final BufferedImage bufferedImage = new BufferedImage(width,
            height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Pixel pixel = image.pixel(x, y);
                final Color color = new Color(pixel.red(), pixel.green(), pixel.blue());

                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        return bufferedImage;
    }
}
