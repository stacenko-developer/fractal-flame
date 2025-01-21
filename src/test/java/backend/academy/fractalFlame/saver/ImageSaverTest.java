package backend.academy.fractalFlame.saver;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.enums.ImageFormat;
import backend.academy.fractalFlameTask.exception.ImageSaveException;
import backend.academy.fractalFlameTask.saver.ImageSaver;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;
import static backend.academy.fractalFlameTask.constants.ConstValues.RESOURCES_PATH;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.IMAGE_SAVE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_FILE_PATH_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_FORMAT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.IMAGE_SAVE_ERROR_SOLVING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageSaverTest extends CommonTest {

    private final String defaultFileName = "image.png";
    private final String defaultFilePathStr = RESOURCES_PATH + defaultFileName;
    private final Path defaultPath = Path.of(defaultFilePathStr);
    private final ImageFormat defaultImageFormat = ImageFormat.PNG;

    @Test
    public void saveImageWithNullImage_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new ImageSaver().save(null, defaultPath, defaultImageFormat);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_IMAGE_EXCEPTION_TEXT);
    }

    @Test
    public void saveImageWithNullPath_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new ImageSaver().save(getDefaultImage(), null, defaultImageFormat);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_FILE_PATH_EXCEPTION_TEXT);
    }

    @Test
    public void saveImageWithNullImageFormat_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new ImageSaver().save(getDefaultImage(), defaultPath, null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_IMAGE_FORMAT_EXCEPTION_TEXT);
    }

    @Test
    public void saveImageByIncorrectPath_ShouldThrowImageSaveException() {
        final Path incorrectPath = Path.of("d/d/image.png");

        assertThatThrownBy(() -> {
            new ImageSaver().save(getDefaultImage(), incorrectPath, defaultImageFormat);
        }).isInstanceOf(ImageSaveException.class)
            .satisfies(exception -> {
                final ImageSaveException ex = (ImageSaveException) exception;
                assertEquals(IMAGE_SAVE_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(IMAGE_SAVE_EXCEPTION_TEXT);
    }

    @Test
    public void saveImageWithCorrectArguments_ShouldCorrectlySaveImage() throws IOException {
        new ImageSaver().save(getDefaultImage(), defaultPath, defaultImageFormat);

        final File file = new File(defaultFilePathStr);
        final BufferedImage bufferedImage = ImageIO.read(file);

        assertNotNull(bufferedImage);

        Files.delete(defaultPath);
    }

    private Image getDefaultImage() {
        final int size = 100;
        final Pixel[] pixels = new Pixel[size * size];
        Arrays.fill(pixels, new Pixel());

        return new Image(pixels, new ImageSize(size, size));
    }
}
