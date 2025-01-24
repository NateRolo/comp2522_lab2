package ca.bcit.comp2522.creature;

/**
 * The {@code Elf} class represents a magical creature with health, mana, and
 * spell-casting abilities.
 *
 * <p>This class extends the {@code Creature} class, adding unique properties
 * and behaviors specific to an Elf, such as managing mana, casting spells,
 * and restoring mana.</p>
 *
 * <p>The {@code Elf} class provides the following functionality:</p>
 * <ul>
 *   <li>Constructing an Elf with specific attributes such as name, date of
 *       birth, health, and mana.</li>
 *   <li>Overriding {@code getDetails()} to include additional Elf-specific
 *       attributes.</li>
 *   <li>Casting spells that reduce mana and deal damage to opponents.</li>
 *   <li>Restoring mana while adhering to defined limits.</li>
 * </ul>
 *
 * <p>All input values are validated to ensure they meet the constraints
 * defined for an Elf, such as mana boundaries. Exceptions are thrown for
 * invalid inputs.</p>
 *
 * <p>This class relies on the {@code LowManaException} to signal situations
 * where an Elf attempts to cast a spell without sufficient mana.</p>
 *
 * @author Haider A
 * @author Kyle C
 * @author Nathan O
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int MAXIMUM_MANA = 50;
    private static final int MINIMUM_MANA = 0;
    private static final int MINIMUM_RESTORE_MANA = 1;

    private static final int CAST_SPELL_COST = 5;
    private static final int CAST_SPELL_DAMAGE = 10;

    private int mana;

    /**
     * Constructs an {@code Elf} instance with the specified name,
     * date of birth, health, and mana.
     *
     * @param name        the name of the Elf.
     *                    Must not be null or empty.
     * @param dateOfBirth the date of birth of the Elf.
     *                    Must not be null.
     * @param health      the health value of the Elf.
     *                    Must be a positive integer.
     * @param mana        the mana value of the Elf.
     *                    Must meet the validation criteria defined in
     *                    {@link #validateMana(int)}.
     */
    public Elf(final String name,
               final Date dateOfBirth,
               final int health,
               final int mana)
    {
        super(name,
              dateOfBirth,
              health);
        validateMana(mana);
        this.mana = mana;
    }

    /**
     * Prints the Elf's name, dateOfBirth, age, health, and mana.
     * <p>
     * Overrides {@code getDetails()} from {@code Creature} class.
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        final StringBuilder detailsBuilder;
        final String elfDetails;

        detailsBuilder = new StringBuilder();

        detailsBuilder
                .append("\n")
                .append("Mana: ")
                .append(this.mana);

        elfDetails = detailsBuilder.toString();

        System.out.println(elfDetails);
    }

    /**
     * Reduces mana by {@code CAST_SPELL_COST}
     * and deals {@code CAST_SPELL_DAMAGE} to another creature.
     * <p>
     *     If mana is less than {@code CAST_SPELL_COST}, throw a
     *     checked LowManaException.
     * </p>
     *
     * @throws LowManaException if elf does not have enough mana to cast spell.
     */
    public void castSpell(final Creature opponent) throws LowManaException
    {
        final boolean insufficientMana;

        insufficientMana = mana < CAST_SPELL_COST;

        if(insufficientMana)
        {
            throw new LowManaException("You need at least " +
                                       CAST_SPELL_COST +
                                       " to cast a spell");
        }

        mana -= CAST_SPELL_COST;

        opponent.takeDamage(CAST_SPELL_DAMAGE);
    }

    /**
     * Increases mana but cannot exceed {@code MAXIMUM_MANA}.
     * <p>
     * Verifies that restore mana amount is not less than {@code MINIMUM_MANA}.
     * </p>
     * @param amount the amount of mana to restore as an int.
     */
    public void restoreMana(int amount)
    {
        final boolean manaExceedsMaximum;
        final boolean amountLesserThanMinimum;

        amountLesserThanMinimum = amount < MINIMUM_RESTORE_MANA;

        if(amountLesserThanMinimum)
        {
            throw new IllegalArgumentException("Cannot restore mana" +
                    " by value of " +
                    amount);
        }

        manaExceedsMaximum = mana + amount > MAXIMUM_MANA;

        mana += amount;

        if(manaExceedsMaximum) {
            mana = MAXIMUM_MANA;
        }


    }

    /*
     * Validates Elf's mana.
     *
     * If Mana is less than MINIMUM_MANA or greater than MAXIMUM_MANA,
     * throw an unchecked IllegalArgumentException.
     */
    private static void validateMana(final int mana)
    {
        final boolean manaExceedsMaximum;
        final boolean manaLowerThanMinimum;

        manaExceedsMaximum = mana > MAXIMUM_MANA;
        manaLowerThanMinimum = mana < MINIMUM_MANA;

        if(manaExceedsMaximum)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value greater than " +
                                               MAXIMUM_MANA);
        }

        if(manaLowerThanMinimum)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value less than " +
                                               MINIMUM_MANA);
        }
    }
}
