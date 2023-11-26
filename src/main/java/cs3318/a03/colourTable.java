package cs3318.a03;

import java.util.ArrayList;

public class colourTable {
    final int paletteSize;
    final ArrayList<int[]> palette;

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


}
