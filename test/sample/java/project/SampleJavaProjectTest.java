package sample.java.project;

import static org.junit.Assert.assertEquals;

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
     * JUnit set up method.
     */
    @Before
    public final void setUp() {
        sjp = new SampleJavaProject();
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
}