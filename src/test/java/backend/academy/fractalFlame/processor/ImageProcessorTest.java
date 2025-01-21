package backend.academy.fractalFlame.processor;

import backend.academy.fractalFlameTask.processor.ImageProcessor;
import org.junit.jupiter.api.Test;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class ImageProcessorTest {

    protected abstract ImageProcessor getImageProcessor();

    @Test
    public void imageProcessorWithNullImage_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            getImageProcessor().process(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_IMAGE_EXCEPTION_TEXT);
    }
}
