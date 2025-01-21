package ca.bcit.comp2522.creature;

/**
 * The {@code Orc} class represents a type of creature with unique rage
 * attributes.
 *
 * <p>An {@code Orc} extends the {@code Creature} class and introduces a
 * rage mechanic, which is validated and managed through this class. Rage
 * must remain within defined boundaries.</p>
 *
 * <p>The {@code Orc} class provides:</p>
 * <ul>
 *   <li>Construction of an Orc with specified name, date of birth, health,
 *       and rage.</li>
 *   <li>Validation of rage to ensure it falls within allowable limits.</li>
 * </ul>
 *
 * @author Kyle C
 * @author Haider A
 * @author Nathan O
 * @version 1.0
 */
public class Orc extends Creature
{
    private static final int MAXIMUM_RAGE;
    private static final int MINIMUM_RAGE;

    private int rage;

    static
    {
        MAXIMUM_RAGE = 30;
        MINIMUM_RAGE = 0;
    }

    /**
     * Constructs an {@code Orc} instance with the specified name, date of
     * birth, health, and rage.
     *
     * @param name        the name of the Orc.
     *                    Must not be null or empty.
     * @param dateOfBirth the date of birth of the Orc.
     *                    Must not be null.
     * @param health      the health value of the Orc.
     *                    Must be a positive
     *                    integer.
     * @param rage        the rage value of the Orc.
     *                    Must meet the validation
     *                    criteria defined in {@link #validateRage(int)}.
     */
    public Orc(final String name,
               final Date dateOfBirth,
               final int health,
               final int rage)
    {
        super(name,
              dateOfBirth,
              health);

        validateRage(rage);
        this.rage = rage;
    }

    @Override
    public void getDetails()
    {
        super.getDetails();

        super.getDetails();

        final StringBuilder detailsBuilder;
        final String orcDetails;

        detailsBuilder = new StringBuilder();

        detailsBuilder
                .append("\n")
                .append("Rage: ")
                .append(this.rage);

        orcDetails = detailsBuilder.toString();

        System.out.println(orcDetails);
    }
    /*
     * Validates the given rage value to ensure it falls within allowable
     * boundaries.
     *
     * <p>This method checks that the rage value is between the predefined
     * {@code MINIMUM_RAGE} and {@code MAXIMUM_RAGE}. If the value is
     * invalid, an {@code IllegalArgumentException} is thrown.</p>
     *
     * @param rage the rage value to validate.
     *
     */
    private static void validateRage(final int rage)
    {
        final boolean rageExceedsMaximum;
        final boolean rageLowerThanMinimum;

        rageExceedsMaximum = rage > MAXIMUM_RAGE;
        rageLowerThanMinimum = rage < MINIMUM_RAGE;

        if(rageExceedsMaximum)
        {
            throw new IllegalArgumentException("Rage cannot exceed " +
                                               MAXIMUM_RAGE);
        }

        if(rageLowerThanMinimum)
        {
            throw new IllegalArgumentException("Rage cannot be lower than" +
                                               MINIMUM_RAGE);
        }
    }
}
