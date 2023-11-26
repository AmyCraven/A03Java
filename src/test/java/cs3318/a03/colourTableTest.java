package cs3318.a03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @ParameterizedTest
    @ValueSource(ints = {3, 1030, 5})
    public void invalidPaletteSizeTest(int paletteSize) {

        Executable colourTableConstructor = () -> new colourTable(paletteSize);
        assertThrows(IllegalArgumentException.class, colourTableConstructor,
                "Invalid palette size");
    }

    @Test
    public void noPaletteSizeTest(){
        Executable colourTableConstructor = () -> new colourTable();
        assertThrows(IllegalArgumentException.class, colourTableConstructor,
                "Invalid Amount");
    }


}
