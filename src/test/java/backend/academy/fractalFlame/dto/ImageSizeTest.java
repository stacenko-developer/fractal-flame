package backend.academy.fractalFlame.dto;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.exception.IncorrectImageHeightException;
import backend.academy.fractalFlameTask.exception.IncorrectImageWidthException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_HEIGHT_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_WIDTH_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_HEIGHT_ERROR_SOLVING;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_WIDTH_ERROR_SOLVING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageSizeTest extends CommonTest {

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateImageSizeWithIncorrectWidth")
    public void createImageSizeWithIncorrectWidth_ShouldThrowIncorrectImageWidthException(int incorrectWidth) {
        assertThatThrownBy(() -> {
            new ImageSize(incorrectWidth, MIN_IMAGE_HEIGHT);
        }).isInstanceOf(IncorrectImageWidthException.class)
            .satisfies(exception -> {
                final IncorrectImageWidthException ex = (IncorrectImageWidthException) exception;
                assertEquals(INCORRECT_IMAGE_WIDTH_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_IMAGE_WIDTH_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateImageSizeWithIncorrectHeight")
    public void createImageSizeWithIncorrectHeight_ShouldThrowIncorrectImageHeightException(int incorrectHeight) {
        assertThatThrownBy(() -> {
            new ImageSize(MIN_IMAGE_WIDTH, incorrectHeight);
        }).isInstanceOf(IncorrectImageHeightException.class)
            .satisfies(exception -> {
                final IncorrectImageHeightException ex = (IncorrectImageHeightException) exception;
                assertEquals(INCORRECT_IMAGE_HEIGHT_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_IMAGE_HEIGHT_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateImageSizeWithCorrectArguments")
    public void createImageSizeWithCorrectArguments_ShouldCorrectlyCreate(int width, int height) {
        final ImageSize imageSize = new ImageSize(width, height);

        assertEquals(width, imageSize.width());
        assertEquals(height, imageSize.height());
    }

    private static List<Object[]> getArgumentsForCreateImageSizeWithCorrectArguments() {
        final List<Object[]> result = new ArrayList<>();
        final int deviationValue = 20;

        for (int width = MIN_IMAGE_WIDTH; width < MIN_IMAGE_WIDTH + deviationValue; width++) {
            for (int height = MIN_IMAGE_HEIGHT; height < MIN_IMAGE_HEIGHT + deviationValue; height++) {
                result.add(
                  new Object[]{
                      width,
                      height
                  }
                );
            }
        }

        return result;
    }

    private static List<Integer> getArgumentsForCreateImageSizeWithIncorrectHeight() {
        return getNumbersNotInRange(MIN_IMAGE_HEIGHT, MAX_IMAGE_HEIGHT, MIN_IMAGE_HEIGHT);
    }

    private static List<Integer> getArgumentsForCreateImageSizeWithIncorrectWidth() {
        return getNumbersNotInRange(MIN_IMAGE_WIDTH, MAX_IMAGE_WIDTH, MIN_IMAGE_WIDTH);
    }
}
