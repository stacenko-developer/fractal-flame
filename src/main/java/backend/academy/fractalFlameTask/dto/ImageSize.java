package backend.academy.fractalFlameTask.dto;

import backend.academy.fractalFlameTask.exception.IncorrectImageHeightException;
import backend.academy.fractalFlameTask.exception.IncorrectImageWidthException;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MAX_IMAGE_WIDTH;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_HEIGHT;
import static backend.academy.fractalFlameTask.constants.ConstValues.MIN_IMAGE_WIDTH;

public record ImageSize(int width, int height) {

    public ImageSize {
        validateHeight(height);
        validateWidth(width);
    }

    private void validateHeight(int height) {
        if (height < MIN_IMAGE_HEIGHT || height > MAX_IMAGE_HEIGHT) {
            throw new IncorrectImageHeightException();
        }
    }

    private void validateWidth(int width) {
        if (width < MIN_IMAGE_WIDTH || width > MAX_IMAGE_WIDTH) {
            throw new IncorrectImageWidthException();
        }
    }
}
