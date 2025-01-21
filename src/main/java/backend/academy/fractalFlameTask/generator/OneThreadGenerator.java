package backend.academy.fractalFlameTask.generator;

import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;

public class OneThreadGenerator extends FractalFlameGenerator {

    protected OneThreadGenerator(
        GenerationSettings generationSettings
    ) {
        super(generationSettings);
    }

    public static OneThreadGenerator create(GenerationSettings generationSettings) {
        validateGenerationSettings(generationSettings);

        return new OneThreadGenerator(generationSettings);
    }

    @Override
    public Image generate(ImageSize imageSize) {
        validateImageSize(imageSize);

        final Image image = emptyImageGenerator.generate(imageSize);

        generateOneThread(image, iterationsCount);

        return image;
    }
}
