package sample.java.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Sample JUnit tests.
 */
public class SampleJavaProjectTest {

    /**
     * Holds an instance of the class we are testing.
     */
    private SampleJavaProject sjp;

    /**
     * Captures console output for testing.
     */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Stores the original System.out.
     */
    private final PrintStream originalOut = System.out;

    /**
     * JUnit set up method.
     */
    @Before
    public final void setUp() {
        sjp = new SampleJavaProject();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Restore original System.out after each test.
     */
    @After
    public final void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests the generated setter and getter methods.
     */
    @Test
    public final void testGetSet() {
        sjp.setName("foo");
        assertEquals("foo", sjp.getName());
    }

    /**
     * Tests that null input is rejected.
     */
    @Test(expected = NullPointerException.class)
    public final void nullTest() {
        sjp.setName(null);
    }

    /**
     * Tests that blank input is rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void emptyNameTest() {
        sjp.setName("   ");
    }

    /**
     * Tests that invalid characters are rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void invalidNameTest() {
        sjp.setName("jing123");
    }

    /**
     * Tests trimming behaviour.
     */
    @Test
    public final void trimNameTest() {
        sjp.setName("  Jing Yin  ");
        assertEquals("Jing Yin", sjp.getName());
    }

    /**
     * Tests valid lower boundary for times.
     */
    @Test
    public final void validMinTimesTest() {
        sjp.setTimes(1);
        assertEquals(1, sjp.getTimes());
    }

    /**
     * Tests valid upper boundary for times.
     */
    @Test
    public final void validMaxTimesTest() {
        sjp.setTimes(10);
        assertEquals(10, sjp.getTimes());
    }

    /**
     * Tests that times below range is rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void invalidLowTimesTest() {
        sjp.setTimes(0);
    }

    /**
     * Tests that times above range is rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void invalidHighTimesTest() {
        sjp.setTimes(11);
    }

    /**
     * Tests greeting message is formatted in uppercase.
     */
    @Test
    public final void buildGreetingMessageTest() {
        sjp.setName("Chris");
        String expected = "HELLO, CHRIS! WELCOME TO OUR PROJECT!";
        assertEquals(expected, sjp.buildGreetingMessage());
    }

    /**
     * Tests sayHello prints message the correct number of times.
     */
    @Test
    public final void sayHelloTimesTest() {
        sjp.setName("Chris");
        sjp.setTimes(3);

        sjp.sayHello();

        String output = outputStream.toString();
        String expectedLine = "HELLO, CHRIS! WELCOME TO OUR PROJECT!";
        int occurrences = output.split(expectedLine, -1).length - 1;

        assertEquals(3, occurrences);
    }

    /**
     * Tests sayHello output is uppercase.
     */
    @Test
    public final void sayHelloUppercaseOutputTest() {
        sjp.setName("Chris");
        sjp.setTimes(1);

        sjp.sayHello();

        String output = outputStream.toString().trim();
        assertTrue(output.equals(output.toUpperCase()));
    }
}