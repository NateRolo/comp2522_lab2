package ca.bcit.comp2522.creature;


public class Dragon extends Creature
{
    private static final int MINIMUM_FIRE_POWER;
    private static final int MAXIMUM_FIRE_POWER;
    private static final int BREATHE_FIRE_COST;
    private static final int BREATHE_FIRE_DAMAGE;
    private static final int MINIMUM_RESTORE_FIREPOWER;

    static
    {
        MINIMUM_FIRE_POWER = 0;
        MAXIMUM_FIRE_POWER = 100;
        BREATHE_FIRE_COST = 10;
        BREATHE_FIRE_DAMAGE = 20;
        MINIMUM_RESTORE_FIREPOWER = 1;
    }

    private int firePower;

    /**
     * Constructs a new {@code Dragon} instance with the
     * specified attributes. Calls {@code Creature} constructor
     * and adds firePower instance variable.
     *
     * @param name the name of the dragon. Cannot be null or empty,
     * @param dateOfBirth the date the dragon was born.
     *                    Cannot be null or in the future.
     * @param health the health of the dragon, represented as an integer.
     *               Cannot be less than 0 or greater than 100.
     * @param firePower the firepower of the dragon.
     *                  Validated by the {@code validateFirePower(int)} method.
     */
    public Dragon(final String name,
                  final Date dateOfBirth,
                  final int health,
                  final int firePower)
    {
        super(name,
              dateOfBirth,
              health);
        validateFirePower(firePower);
        this.firePower = firePower;
    }

    /**
     * Prints the dragon's name, dateOfBirth, age, health, and fire power.
     * <p>
     * Overrides {@code Creature} classes getDetails()
     * by calling {@code Creature}'s getDetails() and appends
     * the dragon's fire power to the output.
     * </p>
     * <p>
     * E.g. Spyro
     *      Born: Wednesday September 9, 1998
     *      Age: 26
     *      Health : 100
     *      Fire Power: 100
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        final StringBuilder detailsBuilder;
        final String dragonDetails;

        detailsBuilder = new StringBuilder();

        detailsBuilder
                     .append("Fire Power: ")
                     .append(this.firePower);

        dragonDetails = detailsBuilder.toString();

        System.out.println(dragonDetails);
    }

    /**
     * Reduces firePower by 10 and deals 20 damage to another creature.
     * <p>
     *     If firePower is less than 10, throw a checked LowFirePowerException.
     * </p>
     *
     * @return 20 damage to another creature.
     * @throws LowFirePowerException if dragon does not have enough fire power.
     */
    public int breatheFire() throws LowFirePowerException
    {
        if(firePower < BREATHE_FIRE_COST)
        {
            throw new LowFirePowerException("You need at least " +
                                            BREATHE_FIRE_COST +
                                            " to breathe fire.");
        }

        firePower -= BREATHE_FIRE_COST;

        return BREATHE_FIRE_DAMAGE;
    }

    /**
     * Increases firePower but cannot exceed 100.
     *
     * Verifies that restore fire power amount is not negative or 0.
     *
     * @param amount the amount of firePower to restore as an int.
     */
    public void restoreFirePower(int amount)
    {
        if(amount < MINIMUM_RESTORE_FIREPOWER)
        {
            throw new IllegalArgumentException("Cannot restore fire power" +
                                               " by value of " +
                                               amount);
        }

        firePower += amount;

        if(firePower > MAXIMUM_FIRE_POWER)
        {
            firePower = MAXIMUM_FIRE_POWER;
        }
    }

    private static void validateFirePower(final int firePower)
    {
        if(firePower < MINIMUM_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power cannot " +
                                               "be less than " +
                                                MINIMUM_FIRE_POWER);
        }

        if(firePower > MAXIMUM_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power cannot " +
                                               "be more than " +
                                               MAXIMUM_FIRE_POWER);
        }
    }
}
