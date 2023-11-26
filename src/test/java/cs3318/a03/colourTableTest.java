package cs3318.a03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class colourTableTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 1020, 8})
    public void validPaletteSizeTest(int paletteSize){
        colourTable myTable = new colourTable(paletteSize);
        if (myTable.isValidPaletteSize(paletteSize)) {
            assertTrue(myTable.isValidPaletteSize(paletteSize));
        }
    }

}
