package cs3318.a03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    private colourTable testPalette;
    private colourTable fullPalette;
    @BeforeEach
    public void setUp() {
        testPalette = new colourTable(20);
        fullPalette = new colourTable(2);
        fullPalette.addToPalette(1,2,3);
        fullPalette.addToPalette(2,3,4);
    }

    @ParameterizedTest
    @MethodSource("rgbValuesProvider")
    public void addingValidRGBValuesTest(int red, int green, int blue) {
        testPalette.addToPalette(red, green, blue);
        int[] addedRGB = testPalette.getPalette().get(0);
        assertArrayEquals(new int[]{red, green, blue}, addedRGB);
    }

    @Test
    public void rejectingAddToFullPaletteTest(){
        Executable addToFullPaletteExecutable = () -> fullPalette.addToPalette(1, 1, 1);

        assertThrows(IllegalStateException.class, addToFullPaletteExecutable,
                "Palette is at its capacity, cannot add more colors");
    }

    private static Stream<Arguments> rgbValuesProvider() {
        return Stream.of(
                Arguments.of(255, 0, 0),    // Red
                Arguments.of(0, 255, 0),    // Green
                Arguments.of(0, 0, 255)    // Blue
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRgbValuesProvider")
    public void addingInvalidRGBValuesThrowsException(int red, int green, int blue) {

        Executable addToPaletteExecutable = () -> testPalette.addToPalette(red, green, blue);

        assertThrows(IllegalArgumentException.class, addToPaletteExecutable,
                "Adding invalid RGB values should throw an exception");
    }

    private static Stream<Arguments> invalidRgbValuesProvider() {
        return Stream.of(
                Arguments.of(-1, 0, 0),
                Arguments.of(256, 0, 0),
                Arguments.of(0, -1, 0),
                Arguments.of(0, 256, 0)
        );
    }

    @Test
    public void rejectingAddingDuplicateTest(){
        colourTable dupTestTable = new colourTable(2);
        dupTestTable.addToPalette(1,2,3);
        Executable addDuplicateColour = () -> dupTestTable.addToPalette(1,2,3);
        assertThrows(IllegalArgumentException.class, addDuplicateColour,"Duplicate RGB values are not allowed in the palette.");
    }

}
