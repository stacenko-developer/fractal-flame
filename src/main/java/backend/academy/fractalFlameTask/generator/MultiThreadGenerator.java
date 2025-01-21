package backend.academy.fractalFlameTask.generator;

import backend.academy.fractalFlameTask.dto.GenerationSettings;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.exception.IncorrectThreadsCountException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_THREADS_COUNT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_THREADS_COUNT;

public class MultiThreadGenerator extends FractalFlameGenerator {

    private final int threadsCount;

    protected MultiThreadGenerator(
        GenerationSettings generationSettings, int threadsCount
    ) {
        super(generationSettings);

        this.threadsCount = threadsCount;
    }

    public static MultiThreadGenerator create(GenerationSettings generationSettings, int threadsCount) {
        validateGenerationSettings(generationSettings);
        validateThreadsCount(threadsCount);

        return new MultiThreadGenerator(generationSettings, threadsCount);
    }

    @Override
    public Image generate(ImageSize imageSize) {
        validateImageSize(imageSize);

        final Image image = emptyImageGenerator.generate(imageSize);
        final List<Integer> iterations = splitNumber(iterationsCount, threadsCount);

        final ExecutorService executorService = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            for (int index = 0; index < threadsCount; index++) {
                final int finalIndex = index;
                executorService.execute(
                    () -> generateOneThread(image, iterations.get(finalIndex))
                );
            }

            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return image;
    }

    private List<Integer> splitNumber(int total, int parts) {
        final List<Integer> result = new ArrayList<>(parts);

        final int basePart = total / parts;
        final int remainder = total % parts;

        for (int i = 0; i < parts; i++) {
            int number = basePart;

            if (i < remainder) {
                number++;
            }

            result.add(number);
        }

        return result;
    }

    private static void validateThreadsCount(int threadsCount) {
        if (threadsCount < MIN_THREADS_COUNT || threadsCount > MAX_THREADS_COUNT) {
            throw new IncorrectThreadsCountException();
        }
    }
}
