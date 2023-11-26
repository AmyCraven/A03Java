package cs3318.a03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class colourTable {
    final int paletteSize;
    final ArrayList<int[]> palette;

    public colourTable(){
        this.paletteSize=0;
        throw new IllegalArgumentException("Invalid palette size");
    }

    public colourTable(int size) {
        if (isValidPaletteSize(size)) {
            this.paletteSize = size;
            this.palette = new ArrayList<>(size);
        } else {
            throw new IllegalArgumentException("Invalid palette size");
        }
    }

    boolean isValidPaletteSize(int size) {
        return size > 1 && size < 1025 && size % 2 == 0;
    }

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

    private void validateRGBValues(int red, int green, int blue) {
        if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
            throw new IllegalArgumentException("Invalid RGB values");
        }
    }

    private boolean isDuplicateRGB(int[] rgbValue) {
        for (int[] existingRGB : palette) {
            if (Arrays.equals(existingRGB, rgbValue)) {
                return true;
            }
        }
        return false;
    }

    public List<int[]> getPalette() {
        return new ArrayList<>(palette);
    }


}
