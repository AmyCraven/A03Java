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

    /**
     * The validPaletteSizeTest function tests the isValidPaletteSize function in the colourTable class.
     * It takes an integer as a parameter and checks if it is a valid palette size.
     *
     *
     * @param paletteSize Test the isValidPaletteSize method
     *
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 1020, 8})
    public void validPaletteSizeTest(int paletteSize){
        colourTable myTable = new colourTable(paletteSize);
        if (myTable.isValidPaletteSize(paletteSize)) {
            assertTrue(myTable.isValidPaletteSize(paletteSize));
        }
    }

    /**
     * The invalidPaletteSizeTest function tests the colourTable constructor to ensure that it throws an
     * IllegalArgumentException when passed a palette size is <2 >1025 and not a multiple of 2.
     *
     * @param paletteSize Test the colourTable constructor with different values
     *
     */
    @ParameterizedTest
    @ValueSource(ints = {3, 1030, 5})
    public void invalidPaletteSizeTest(int paletteSize) {

        Executable colourTableConstructor = () -> new colourTable(paletteSize);
        assertThrows(IllegalArgumentException.class, colourTableConstructor,
                "Invalid palette size");
    }

    /**
     * The noPaletteSizeTest function tests the colourTable constructor to ensure that it throws an
     * IllegalArgumentException when no palette size is provided.

     *
     */
    @Test
    public void noPaletteSizeTest(){
        Executable colourTableConstructor = () -> new colourTable();
        assertThrows(IllegalArgumentException.class, colourTableConstructor,
                "Invalid Amount");
    }

    private colourTable testPalette;
    private colourTable fullPalette;

    /**
     * Set up function to make testing easier. i.e. not creating new instances of the class everytime
     * it's needed
     */
    @BeforeEach
    public void setUp() {
        testPalette = new colourTable(20);
        fullPalette = new colourTable(2);
        fullPalette.addToPalette(1,2,3);
        fullPalette.addToPalette(2,3,4);
    }

    /**
     * The addingValidRGBValuesTest function tests the addToPalette function
     * by passing it valid RGB values and checking that they are added to the palette correctly.
     * @param  red Provide the red value for the rgb color to be added
     * @param  green Provide the green value for the rgb color to be added
     * @param  blue Provide the blue value for the rgb color to be added
     *
     */
    @ParameterizedTest
    @MethodSource("rgbValuesProvider")
    public void addingValidRGBValuesTest(int red, int green, int blue) {
        testPalette.addToPalette(red, green, blue);
        int[] addedRGB = testPalette.getPalette().get(0);
        assertArrayEquals(new int[]{red, green, blue}, addedRGB);
    }

    /**
     * The rejectingAddToFullPaletteTest function tests the addToPalette function in the Palette class.
     * it attempts to add another color using the addToPalette function to a full palette.
     * This should throw an IllegalStateException because we are trying to exceed our
     * palette's capacity of 2 colors by adding to it.
     *
     */
    @Test
    public void rejectingAddToFullPaletteTest(){
        Executable addToFullPaletteExecutable = () -> fullPalette.addToPalette(1, 1, 1);

        assertThrows(IllegalStateException.class, addToFullPaletteExecutable,
                "Palette is at its capacity, cannot add more colors");
    }

    // valid RGB values
    private static Stream<Arguments> rgbValuesProvider() {
        return Stream.of(
                Arguments.of(255, 0, 0),    // Red
                Arguments.of(0, 255, 0),    // Green
                Arguments.of(0, 0, 255)    // Blue
        );
    }

    /**
     * The addingInvalidRGBValuesThrowsException function tests the addToPalette function with invalid RGB values.
     * The test is parameterized, and uses a method source to provide the parameters for each test case.
     * The method source provides an array of arrays of integers, where each inner array contains three integers: red, green and blue.
     * Each inner array represents one test case; in this example there are four such cases (four arrays).

     *
     * @param red the red value to be tested
     * @param green the green value to be tested
     * @param blue the blue value to be tested
     *
     */
    @ParameterizedTest
    @MethodSource("invalidRgbValuesProvider")
    public void addingInvalidRGBValuesThrowsException(int red, int green, int blue) {

        Executable addToPaletteExecutable = () -> testPalette.addToPalette(red, green, blue);

        assertThrows(IllegalArgumentException.class, addToPaletteExecutable,
                "Adding invalid RGB values should throw an exception");
    }

    // invalid RGB values
    private static Stream<Arguments> invalidRgbValuesProvider() {
        return Stream.of(
                Arguments.of(-1, 0, 0),
                Arguments.of(256, 0, 0),
                Arguments.of(0, -1, 0),
                Arguments.of(0, 256, 0)
        );
    }

    /**
     * The rejectingAddingDuplicateTest function tests the addToPalette function to ensure that it
     * throws an IllegalArgumentException when a duplicate colour is added.
     */
    @Test
    public void rejectingAddingDuplicateTest(){
        colourTable dupTestTable = new colourTable(2);
        dupTestTable.addToPalette(1,2,3);
        Executable addDuplicateColour = () -> dupTestTable.addToPalette(1,2,3);
        assertThrows(IllegalArgumentException.class, addDuplicateColour,"Duplicate RGB values are not allowed in the palette.");
    }

}
