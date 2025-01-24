package ca.bcit.comp2522.creature;

/**
 * The {@code Dragon} class represents a powerful creature capable of breathing
 * fire.
 *
 * <p>
 *     A {@code Dragon} extends the {@code Creature} class and adds unique
*      attributes and abilities, such as firepower and the ability to breathe fire.
*      The firepower is carefully managed and must fall within specified limits.
 * </p>
 *
 * <p>The {@code Dragon} class provides the following functionality:</p>
 * <ul>
 *   <li>Construction of a Dragon with specific attributes, including name,
 *       date of birth, health, and firepower.</li>
 *   <li>Displaying detailed information about the Dragon, including its
 *       firepower.</li>
 *   <li>Breathing fire to attack opponents, reducing firepower by a fixed
 *       cost.</li>
 *   <li>Restoring firepower up to a maximum limit.</li>
 * </ul>
 *
 * <p>
 *     Firepower is validated to ensure it stays within the allowable range.
 *     Exceptions are thrown for invalid inputs or actions, such as attempting to
 *     breathe fire without sufficient firepower.
 * </p>
 *
 * @author Haider A
 * @author Kyle C
 * @author Nathan O
 * @version 1.0
 */
public class Dragon extends Creature
{
    private static final int MINIMUM_FIRE_POWER = 0;
    private static final int MAXIMUM_FIRE_POWER = 100;
    private static final int BREATHE_FIRE_COST = 10;
    private static final int BREATHE_FIRE_DAMAGE = 20;
    private static final int MINIMUM_RESTORE_FIREPOWER = 1;

    private int firePower;

    /**
     * Constructs a new {@code Dragon} instance with the
     * specified attributes. Calls {@code Creature} constructor
     * and adds firePower instance variable.
     *
     * @param name         the name of the dragon.
     *                     Cannot be null or empty,
     * @param dateOfBirth  the date the dragon was born.
     *                     Cannot be null or in the future.
     * @param health       the health of the dragon, represented as an integer.
     *                     Cannot be less than 0 or greater than 100.
     * @param firePower    the firepower of the dragon.
     *                     Validated by the {@code validateFirePower(int)} method.
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
     * the dragon's firepower to the output.
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
     * Reduces firePower by {@code BREATH_FIRE_COST}
     * and deals @{BREATHE_FIRE_DAMAGE} damage to another creature.
     * <p>
     *     If firePower is less than {@code BREATHE_FIRE_COST},
     *     throw a checked LowFirePowerException.
     * </p>
     *
     * @throws LowFirePowerException if dragon does not have enough firepower.
     */
    public void breatheFire(final Creature opponent) throws LowFirePowerException
    {
        final boolean powerLowerThanCost;

        powerLowerThanCost = firePower < BREATHE_FIRE_COST

        if(powerLowerThanCost)
        {
            throw new LowFirePowerException("You need at least " +
                                            BREATHE_FIRE_COST +
                                            " to breathe fire.");
        }

        firePower -= BREATHE_FIRE_COST;

        opponent.takeDamage(BREATHE_FIRE_DAMAGE);
    }

    /**
     * Increases firePower but cannot exceed {@code MAXIMUM_FIRE_POWER}.
     *
     * Verifies that restore fire power amount is valid.
     *
     * @param amount the amount of firePower to restore as an int.
     */
    public void restoreFirePower(int amount)
    {
        final boolean amountLowerThanMinimum;
        final boolean powerHigherThanMaximum;

        amountLowerThanMinimum = amount < MINIMUM_FIRE_POWER;
        powerHigherThanMaximum = firePower > MAXIMUM_FIRE_POWER;

        if(amountLowerThanMinimum)
        {
            throw new IllegalArgumentException("Cannot restore fire power" +
                                               " by value of " +
                                               amount);
        }

        firePower += amount;

        if(powerHigherThanMaximum)
        {
            firePower = MAXIMUM_FIRE_POWER;
        }
    }

    /*
     * Validates {@code firePower} input.
     *
     * If firepower is less than {@code MINIMUM_FIRE_POWER}
     * or greater than {@code MAXIMUM_FIRE_POWER},
     * throw unchecked IllegalArgumentException.
     */
    private static void validateFirePower(final int firePower)
    {
        final boolean powerLowerThanMinimum;
        final boolean powerHigherThanMaximum;

        powerLowerThanMinimum = firePower < MINIMUM_FIRE_POWER;
        powerHigherThanMaximum = firePower > MAXIMUM_FIRE_POWER;

        if(powerLowerThanMinimum)
        {
            throw new IllegalArgumentException("Fire power cannot " +
                                               "be less than " +
                                                MINIMUM_FIRE_POWER);
        }

        if(powerHigherThanMaximum)
        {
            throw new IllegalArgumentException("Fire power cannot " +
                                               "be more than " +
                                               MAXIMUM_FIRE_POWER);
        }
    }
}
