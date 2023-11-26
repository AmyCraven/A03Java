package cs3318.a03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class colourTable {
    final int paletteSize;
    final ArrayList<int[]> palette;

    /**
     * The colourTable function takes in a palette size and creates an array of colours.
     * If the size is left empty
     *
     */
    public colourTable(){
        this.paletteSize=0;
        throw new IllegalArgumentException("Invalid palette size");
    }

    /**
     * The colourTable function is a constructor for the colourTable class.
     * It takes in an integer size and creates a new ArrayList of that size.
     *
     *
     * @param size Set the size of the palette
     *
     */
    public colourTable(int size) {
        if (isValidPaletteSize(size)) {
            this.paletteSize = size;
            this.palette = new ArrayList<>(size);
        } else {
            throw new IllegalArgumentException("Invalid palette size");
        }
    }

    /**
     * The isValidPaletteSize function checks to see if the size of the palette is valid.
     * The size must be greater than 1 and less than 1025, and it must also be an even number.
     *
     *
     * @param size Determine if the size of the palette is valid
     *
     */
    boolean isValidPaletteSize(int size) {
        return size > 1 && size < 1025 && size % 2 == 0;
    }

    /**
     * The addToPalette function adds a color to the palette.
     *
     *
     * @param  red Represent the red value of a color
     * @param  green Represent the green value of a color
     * @param  blue Represent the blue value of a color
     *
     */
    public void addToPalette(int red, int green, int blue) {
        if (palette.size() >= paletteSize) {
            throw new IllegalStateException("Palette is at its capacity, cannot add more colors.");
        }

        validateRGBValues(red, green, blue);
        int[] RGBValue = {red, green, blue};

        if (isDuplicateRGB(RGBValue)) {
            throw new IllegalArgumentException("Duplicate RGB values are not allowed in the palette.");
        }

        palette.add(RGBValue);
    }

    /**
     * The validateRGBValues function checks to make sure that the RGB values are valid.
     *
     * @param  red the red value of a color
     * @param  green the green value of a color
     * @param  blue  the blue value of a color
     *
     */
    private void validateRGBValues(int red, int green, int blue) {
        if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
            throw new IllegalArgumentException("Invalid RGB values");
        }
    }

    /**
     * The isDuplicateRGB function checks to see if the RGB value passed in as a parameter is already present in the palette.
     *
     * @param rgbValue Pass the rgb values of a pixel to the function
     *
     * @return True if the rgb value passed to it as an argument is already in the palette, False if no duplicate found
     */
    private boolean isDuplicateRGB(int[] rgbValue) {
        for (int[] existingRGB : palette) {
            if (Arrays.equals(existingRGB, rgbValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The getPalette function returns the palette of the image.
     * @return A copy of the palette
     */
    public List<int[]> getPalette() {
        return new ArrayList<>(palette);
    }


}
