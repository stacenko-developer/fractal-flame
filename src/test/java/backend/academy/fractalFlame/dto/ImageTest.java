package backend.academy.fractalFlame.dto;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.Image;
import backend.academy.fractalFlameTask.dto.ImageSize;
import backend.academy.fractalFlameTask.dto.Pixel;
import backend.academy.fractalFlameTask.exception.IncorrectImagePixelsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.INCORRECT_IMAGE_PIXELS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_IMAGE_SIZE_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_PIXELS_EXCEPTION_TEXT;
import static backend.academy.fractalFlameTask.constants.ProblemSolving.INCORRECT_IMAGE_PIXELS_ERROR_SOLVING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ImageTest extends CommonTest {

    @Test
    public void createImageWithNullImageSize_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new Image(new Pixel[]{}, null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_IMAGE_SIZE_EXCEPTION_TEXT);
    }

    @Test
    public void createImageWithNullPixels_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new Image(null, new ImageSize(MIN_IMAGE_WIDTH, MIN_IMAGE_HEIGHT));
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_PIXELS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateImageWithIncorrectImagePixels")
    public void createImageWithIncorrectSize_ShouldThrowIncorrectImagePixelsException(
        int incorrectPixelsCount
    ) {
        assertThatThrownBy(() -> {
            new Image(new Pixel[incorrectPixelsCount], new ImageSize(MIN_IMAGE_WIDTH, MIN_IMAGE_HEIGHT));
        }).isInstanceOf(IncorrectImagePixelsException.class)
            .satisfies(exception -> {
                final IncorrectImagePixelsException ex = (IncorrectImagePixelsException) exception;
                assertEquals(INCORRECT_IMAGE_PIXELS_ERROR_SOLVING, ex.getSolution());
            })
            .hasMessageContaining(INCORRECT_IMAGE_PIXELS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getIncorrectPixels")
    public void createImageWithNullPixel_ShouldThrowNullPointerException(
        Pixel[] incorrectPixels
    ) {
        assertThatThrownBy(() -> {
            new Image(incorrectPixels, new ImageSize(MIN_IMAGE_WIDTH, MIN_IMAGE_HEIGHT));
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_PIXELS_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForCreateImageWithCorrectArguments")
    public void createImageWithCorrectArguments_ShouldCorrectlyCreate(int size) {
        final Pixel[] pixels = new Pixel[size * size];
        Arrays.fill(pixels, new Pixel());

        final Image image = new Image(pixels, new ImageSize(size, size));

        assertEquals(size, image.imageSize().width());
        assertEquals(size, image.imageSize().height());
        assertEquals(pixels.length, image.pixels().length);
        assertArrayEquals(pixels, image.pixels());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetPixelFromImageByCorrectIndex")
    public void getPixelFromImageByCorrectIndex_ShouldReturnPixel(int index) {
        assertNotNull(getPixel(index, index));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetPixelFromImageByOutOfRangeIndex")
    public void getPixelFromImageByOutOfRangeIndex_ShouldReturnNull(int x, int y) {
        assertNull(getPixel(x, y));
    }

    private static List<Integer> getArgumentsForGetPixelFromImageByCorrectIndex() {
        final int minValue = 0;
        final int maxValue = MIN_IMAGE_WIDTH - 1;

        return getNumbersByRange(minValue, maxValue);
    }

    private static List<Object[]> getArgumentsForGetPixelFromImageByOutOfRangeIndex() {
        final int minNotPositiveValue = -100;
        final int maxNotPositiveValue = -1;
        final List<Object[]> result = new ArrayList<>();

        final List<Integer> outOfRangeValues = getNumbersByRange(MIN_IMAGE_WIDTH, MIN_IMAGE_WIDTH +
            DEFAULT_DEVIATION_VALUE);
        final List<Integer> notPositiveNumbers = getNumbersByRange(minNotPositiveValue, maxNotPositiveValue);
        final List<Integer> correctNumbers = getArgumentsForGetPixelFromImageByCorrectIndex();

        for (int index = 0; index < correctNumbers.size(); index++) {
            result.add(new Object[]{
                outOfRangeValues.get(index), outOfRangeValues.get(index)
            });
            result.add(new Object[]{
                notPositiveNumbers.get(index), notPositiveNumbers.get(index)
            });
            result.add(new Object[]{
                correctNumbers.get(index), outOfRangeValues.get(index)
            });
            result.add(new Object[]{
               correctNumbers.get(index), notPositiveNumbers.get(index)
            });
        }

        return result;
    }

    private static List<Integer> getArgumentsForCreateImageWithCorrectArguments() {
        return getNumbersByRange(MIN_IMAGE_WIDTH, MIN_IMAGE_WIDTH + DEFAULT_DEVIATION_VALUE);
    }

    private static List<Object[]> getIncorrectPixels() {
        final List<Object[]> result = new ArrayList<>();

        for (int index = MIN_IMAGE_WIDTH; index < MIN_IMAGE_WIDTH * MIN_IMAGE_WIDTH; index += MIN_IMAGE_WIDTH) {
            final Pixel[] pixels = new Pixel[MIN_IMAGE_WIDTH * MIN_IMAGE_WIDTH];
            Arrays.fill(pixels, new Pixel());
            pixels[index] = null;
            result.add(new Object[]{
                pixels
            });
        }

        return result;
    }

    private static List<Integer> getArgumentsForCreateImageWithIncorrectImagePixels() {
        return getNumbersNotInRange(MIN_IMAGE_WIDTH, MAX_IMAGE_WIDTH, MIN_IMAGE_WIDTH);
    }

    private Pixel getPixel(int x, int y) {
        final Pixel[] pixels = new Pixel[MIN_IMAGE_WIDTH * MIN_IMAGE_HEIGHT];
        Arrays.fill(pixels, new Pixel());

        final Image image = new Image(pixels, new ImageSize(MIN_IMAGE_WIDTH, MIN_IMAGE_HEIGHT));
        return image.pixel(x, y);
    }
}
