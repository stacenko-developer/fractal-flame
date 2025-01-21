package backend.academy.fractalFlame.generator;

import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.generator.ImageGenerator;
import backend.academy.fractalFlameTask.generator.OneThreadGenerator;
import backend.academy.fractalFlameTask.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_GENERATION_SETTINGS_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OneThreadGeneratorTest extends ImageGeneratorTest {

    @Override
    protected ImageGenerator getImageGenerator() {
        return OneThreadGenerator.create(defaultGenerationSettings);
    }

    @Test
    public void createOneThreadGeneratorWithNullGenerationSettings_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            OneThreadGenerator.create(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_GENERATION_SETTINGS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getAllCorrectArgumentsForGenerationSettings")
    public void generateFractalFlameByOneThreadGeneratorWithCorrectGenerationSettings_ShouldCorrectlyGenerate(
        int affineTransformationsCount, int symmetry,
        List<Transformation> transformations,
        int iterationsCount
    ) {
        final GenerationSettings generationSettings = new GenerationSettings(
            affineTransformationsCount, symmetry, transformations, iterationsCount
        );
        final OneThreadGenerator oneThreadGenerator = OneThreadGenerator.create(generationSettings);
        final Image image = oneThreadGenerator.generate(defaultImageSize);

        assertNotNull(image);
    }
}
