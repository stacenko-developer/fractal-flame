package backend.academy.fractalFlame.dto;

import backend.academy.CommonTest;
import backend.academy.fractalFlameTask.dto.Pixel;
import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_COLOR_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PixelTest extends CommonTest {

    private final int defaultHitCount = 0;
    private final int defaultColorValue = 10;

    @Test
    public void createPixelByDefaultConstructor_ShouldCorrectlyCreate() {
        final int correctValue = 0;
        final Pixel pixel = new Pixel();

        assertEquals(correctValue, pixel.red());
        assertEquals(correctValue, pixel.green());
        assertEquals(correctValue, pixel.blue());
        assertEquals(correctValue, pixel.hitCount());
    }

    @Test
    public void setNullColorToPixel_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new Pixel().setColor(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_COLOR_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForSetColorToPixel")
    public void setCorrectColorToPixel_ShouldCorrectlySet(int colorValue) {
        final Pixel pixel = new Pixel();

        pixel.setColor(new Color(colorValue, colorValue, colorValue));

        assertEquals(colorValue, pixel.red());
        assertEquals(colorValue, pixel.green());
        assertEquals(colorValue, pixel.blue());
    }

    @Test
    public void updateHitCountInPixelByNullColor_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            new Pixel().updateHitCount(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_COLOR_EXCEPTION_TEXT);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForSetColorToPixel")
    public void updateHitCountFirstTimeByCorrectColor_ShouldCorrectlyUpdate(int colorValue) {
        final Pixel pixel = new Pixel();

        pixel.updateHitCount(new Color(colorValue, colorValue, colorValue));

        assertEquals(colorValue, pixel.red());
        assertEquals(colorValue, pixel.green());
        assertEquals(colorValue, pixel.blue());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForSetColorToPixel")
    public void updateHitCountNotFirstTimeByCorrectColor_ShouldCorrectlyUpdate(int colorValue) {
        final Pixel pixel = new Pixel();
        final int firstColorValue = 0;
        final int correctColorValue = (firstColorValue + colorValue) / 2;
        final Color firstColor = new Color(firstColorValue, firstColorValue, firstColorValue);
        final Color secondColor = new Color(colorValue, colorValue, colorValue);

        pixel.updateHitCount(firstColor);
        pixel.updateHitCount(secondColor);

        assertEquals(correctColorValue, pixel.red());
        assertEquals(correctColorValue, pixel.green());
        assertEquals(correctColorValue, pixel.blue());
    }

    @Test
    public void comparePixelWithItself_ShouldReturnTrue() {
        final Pixel pixel = new Pixel();

        assertEquals(pixel, pixel);
    }

    @Test
    public void comparePixelWithNull_ShouldReturnFalse() {
        assertFalse(new Pixel().equals(null));
    }

    @Test
    public void comparePixelWithOtherClass_ShouldReturnFalse() {
        assertFalse(new Pixel().equals(new String()));
    }

    @Test
    public void compareSamePixels_ShouldReturnTrue() {
        final int defaultColorValue = 10;
        final Pixel firstPixel = getPixel(
            defaultColorValue, defaultColorValue, defaultColorValue, defaultHitCount
        );
        final Pixel secondPixel = getPixel(
            defaultColorValue, defaultColorValue, defaultColorValue, defaultHitCount
        );

        assertEquals(firstPixel, secondPixel);
        assertEquals(firstPixel.hashCode(), secondPixel.hashCode());
    }

    @Test
    public void comparePixelsWithNotEqualRed_ShouldReturnFalse() {
        final int firstRed = 1;
        final int secondRed = 2;

        final Pixel firstPixel = getPixel(
            firstRed, defaultColorValue, defaultColorValue, defaultHitCount
        );
        final Pixel secondPixel = getPixel(
            secondRed, defaultColorValue, defaultColorValue, defaultHitCount
        );

        assertNotEquals(firstPixel, secondPixel);
        assertNotEquals(firstPixel.hashCode(), secondPixel.hashCode());
    }

    @Test
    public void comparePixelsWithNotEqualGreen_ShouldReturnFalse() {
        final int firstGreen = 1;
        final int secondGreen = 2;

        final Pixel firstPixel = getPixel(
            defaultColorValue, firstGreen, defaultColorValue, defaultHitCount
        );
        final Pixel secondPixel = getPixel(
            defaultColorValue, secondGreen, defaultColorValue, defaultHitCount
        );

        assertNotEquals(firstPixel, secondPixel);
        assertNotEquals(firstPixel.hashCode(), secondPixel.hashCode());
    }

    @Test
    public void comparePixelsWithNotEqualBlue_ShouldReturnFalse() {
        final int firstBlue = 1;
        final int secondBlue = 2;

        final Pixel firstPixel = getPixel(
            defaultColorValue, defaultColorValue, firstBlue, defaultHitCount
        );
        final Pixel secondPixel = getPixel(
            defaultColorValue, defaultColorValue, secondBlue, defaultHitCount
        );

        assertNotEquals(firstPixel, secondPixel);
        assertNotEquals(firstPixel.hashCode(), secondPixel.hashCode());
    }

    @Test
    public void comparePixelsWithNotEqualHitCount_ShouldReturnFalse() {
        final int firstHitCount = 1;
        final int secondHitCount = 2;

        final Pixel firstPixel = getPixel(
            defaultColorValue, defaultColorValue, defaultColorValue, firstHitCount
        );
        final Pixel secondPixel = getPixel(
            defaultColorValue, defaultColorValue, defaultColorValue, secondHitCount
        );

        assertNotEquals(firstPixel, secondPixel);
        assertNotEquals(firstPixel.hashCode(), secondPixel.hashCode());
    }

    private Pixel getPixel(int red, int green, int blue, int hitCount) {
        final Pixel pixel = new Pixel();
        final int blackColorValue = 0;

        for (int i = 0; i < hitCount; i++) {
            pixel.updateHitCount(new Color(blackColorValue, blackColorValue, blackColorValue));
        }

        pixel.setColor(new Color(red, green, blue));

        return pixel;
    }

    private static List<Integer> getArgumentsForSetColorToPixel() {
        final int minColorValue = 0;
        final int maxColorValue = 255;

        return getNumbersByRange(minColorValue, maxColorValue);
    }
}
