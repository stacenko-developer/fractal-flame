package backend.academy.fractalFlameTask.dto;

import java.awt.Color;
import java.util.Objects;
import lombok.Getter;
import static backend.academy.fractalFlameTask.constants.ExceptionTextValues.NULL_COLOR_EXCEPTION_TEXT;

@Getter
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;

    public void setColor(Color color) {
        validateColor(color);
        setRGB(color);
    }

    public void updateHitCount(Color color) {
        validateColor(color);

        if (hitCount == 0) {
            setRGB(color);
        } else {
            setAverageRGB(color);
        }

        hitCount++;
    }

    private int getAverage(int currentValue, int newValue) {
        return (currentValue + newValue) / 2;
    }

    private void setAverageRGB(Color color) {
        red = getAverage(red, color.getRed());
        green = getAverage(green, color.getGreen());
        blue = getAverage(blue, color.getBlue());
    }

    private void setRGB(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    private void validateColor(Color color) {
        if (color == null) {
            throw new NullPointerException(NULL_COLOR_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Pixel that = (Pixel) o;

        return Objects.equals(red, that.red)
            && Objects.equals(green, that.green)
            && Objects.equals(blue, that.blue)
            && Objects.equals(hitCount, that.hitCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, hitCount);
    }
}
