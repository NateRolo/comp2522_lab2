package ca.bcit.comp2522.creature;

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
