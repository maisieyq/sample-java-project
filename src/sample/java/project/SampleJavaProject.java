package sample.java.project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The main class of the application. It contains the main() method,
 * the first method called.
 */
@NoArgsConstructor
@AllArgsConstructor
public class SampleJavaProject implements Runnable {

    /** The delay between printed messages. */
    private static final long PRINT_DELAY = 1000L;

    /** The name to be printed in the output message. */
    @Parameter(names = "--name", description = "set the user's name", required = true)
    private String name = "world";

    /** Command line parameter for --loop. */
    @Parameter(names = "--loop", description = "print endlessly, hotswap demo")
    private boolean loop = false;

    /** Command line parameter for --help. */
    @Parameter(names = { "-h", "--help" }, description = "print help message")
    private boolean help = false;

    /**
     * Getter for name.
     * @return validated name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name with validation.
     * @param name input name
     */
    public void setName(final String name) {
        validateName(name);
        this.name = name.trim();
    }

    /**
     * Validate the input name.
     * Rules:
     * 1. cannot be null
     * 2. cannot be empty / blank
     * 3. only letters and spaces are allowed
     *
     * @param input input name
     */
    private void validateName(final String input) {
        if (input == null) {
            throw new NullPointerException("Name cannot be null");
        }

        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (!input.trim().matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException(
                "Name can only contain letters and spaces"
            );
        }
    }

    /**
     * Print the output string.
     * @param args application input arguments
     */
    public static void main(final String[] args) {

        /* Parse command line arguments. */
        SampleJavaProject sjp = new SampleJavaProject();

        try {
            JCommander jc = new JCommander(sjp, args);

            // validate CLI input after JCommander injects the value
            sjp.setName(sjp.name);

            if (sjp.help) {
                jc.usage();
                return;
            }
        } catch (ParameterException | IllegalArgumentException | NullPointerException e) {
            System.err.println(("error: " + e.getMessage()).toUpperCase());
            new JCommander(new SampleJavaProject()).usage();
            System.exit(-1);
        }

        sjp.run();
    }

    /**
     * Print the greeting string in uppercase.
     */
    public final void sayHello() {
        String message = String.format(
            "Hello, %s! Welcome to our project!",
            name
        );
        System.out.println(message.toUpperCase());
    }

    @Override
    public final void run() {
        do {
            sayHello();
            try {
                Thread.sleep(PRINT_DELAY);
            } catch (InterruptedException e) {
                return;
            }
        } while (loop);
    }
}