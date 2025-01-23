import ca.bcit.comp2522.creature.*;

/**
 * {@code CreatureTest} serves as a test class for
 * Creature, Dragon, Elf, and Orc classes. Showcases
 * polymorphism and catching RuntimeExceptions.
 *
 * @author Haider A
 * @author Kyle C
 * @author Nathan O
 * @version 1.0
 */
public class CreatureTest
{

    /**
     * The entry point of the application.
     * <p>
     * Demonstrates polymorphic behavior of {@code Creature} objects,
     * including type checking and exception handling when invoking methods
     * specific to subclasses like {@code Dragon}, {@code Elf}, and {@code Orc}.
     * </p>
     * @param args command-line arguments (not used).
     */
    public static void main(final String[] args)
    {
        final Date dragonDateOfBirth;
        final Date elfDateOfBirth;
        final Date orcDateOfBirth;

        final Creature testDragon;
        final Creature testElf;
        final Creature testOrc;

        final boolean testDragonIsADragon;
        final boolean testElfIsAnElf;
        final boolean testOrcIsAnOrc;

        dragonDateOfBirth = new Date(2000,
                                     1,
                                     1);
        elfDateOfBirth = new Date(1990,
                                  6,
                                  12);
        orcDateOfBirth = new Date(1998,
                                  9,
                                  25);


        testDragon = new Dragon("Spyro",
                                dragonDateOfBirth,
                                100,
                                100);
        testElf = new Elf("Dobby",
                          elfDateOfBirth,
                          80,
                          49);
        testOrc = new Orc("Azog",
                          orcDateOfBirth,
                          50,
                          20);


        testDragonIsADragon = testDragon instanceof Dragon;
        testElfIsAnElf = testElf instanceof Elf;
        testOrcIsAnOrc = testOrc instanceof Orc;

        testDragon.getDetails();
        testElf.getDetails();
        testOrc.getDetails();

        System.out.println(testDragon.getClass());
        System.out.println(testElf.getClass());
        System.out.println(testOrc.getClass());

        System.out.println(testDragonIsADragon);
        System.out.println(testElfIsAnElf);
        System.out.println(testOrcIsAnOrc);

        dateExceptionTest();
        dragonExceptionTest();
        elfExceptionTest();
        orcExceptionTest();

        // Dragon breathes fire at Elf.
        try
        {
            ((Dragon) testDragon).breatheFire(testElf);
        }
        catch(LowFirePowerException e)
        {
            System.out.println(e.getMessage());
        }

        // Elf casts spell at Orc.
        try
        {
            ((Elf) testElf).castSpell(testOrc);
        }
        catch(LowManaException e)
        {
            System.out.println(e.getMessage());
        }

        // Orc goes berserk on Dragon.
        try
        {
            ((Orc) testOrc).berserk(testDragon);
        }
        catch(LowRageException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Tests for invalid date inputs by creating
     * {@code Date} objects with invalid values for
     * year, month, and day. Prints exception messages.
     */
    private static void dateExceptionTest()
    {
        final Date invalidYear;
        final Date invalidMonth;
        final Date invalidDay;

        try
        {
            invalidYear = new Date(1,
                                   1,
                                   1);
        }
        catch(final IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }

        try
        {
            invalidMonth = new Date(2000,
                                    -2,
                                    1);
        }
        catch(final IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }

        try
        {
            invalidDay = new Date(2000,
                                    1,
                                    -1);
        }
        catch(final IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    /*
     * Tests for invalid {@code Dragon} operations.
     *
     * Attempts to restore firepower with invalid value
     * and catches the exception thrown.
     */
    private static void dragonExceptionTest()
    {
        final Date dragonDateOfBirth;
        final Dragon exceptionDragon;

        try
        {
            dragonDateOfBirth = new Date(2014,
                                         10,
                                         14);

            exceptionDragon = new Dragon("Jeff",
                                                dragonDateOfBirth,
                                                100,
                                                100);

            exceptionDragon.restoreFirePower(-1);

        }
        catch(IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /*
     * Tests for invalid {@code Elf} operations.
     *
     * Attempts to restore mana with invalid value
     * and catches the exception thrown.
     */
    private static void elfExceptionTest()
    {
        final Date elfDateOfBirth;
        final Elf  exceptionElf;

        try
        {
            elfDateOfBirth = new Date(2012,
                                      12,
                                      12);

            exceptionElf = new Elf("Lou",
                                   elfDateOfBirth,
                                   100,
                                   50);

            exceptionElf.restoreMana(-1);

        }
        catch(final IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /*
     * Tests for invalid {@code Orc} operations.
     *
     * Attempts to create an {@code Orc} with invalid rage value
     * and catches the exception thrown.
     */
    private static void orcExceptionTest()
    {
        final Date orcDateOfBirth;
        final Orc  exceptionOrc;

        try
        {
            orcDateOfBirth = new Date(2000,
                                      1,
                                      1);

            exceptionOrc = new Orc("Greg",
                                   orcDateOfBirth,
                                   50,
                                   -5);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
