package backend.academy.fractalFlame.generator;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.generator.ImageGenerator;
import backend.academy.fractalFlameTask.transformation.LinearTransformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_AFFINE_TRANSFORMATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_ITERATIONS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_SYMMETRY;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_SIZE_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class ImageGeneratorTest extends CommonTest {

    protected final GenerationSettings defaultGenerationSettings = new GenerationSettings(
        MIN_AFFINE_TRANSFORMATIONS_COUNT, MIN_SYMMETRY, List.of(new LinearTransformation()), MIN_ITERATIONS_COUNT
    );
    protected final ImageSize defaultImageSize = new ImageSize(MIN_IMAGE_WIDTH, MIN_IMAGE_HEIGHT);

    protected abstract ImageGenerator getImageGenerator();

    @Test
    public void generateImageWithNullImageSize_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            getImageGenerator().generate(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_IMAGE_SIZE_EXCEPTION_TEXT);
    }
}
