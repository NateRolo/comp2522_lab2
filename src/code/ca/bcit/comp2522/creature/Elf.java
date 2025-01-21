package ca.bcit.comp2522.creature;

public class Elf extends Creature
{
    private static final int MAXIMUM_MANA;
    private static final int MINIMUM_MANA;
    private static final int MINIMUM_RESTORE_MANA;

    private static final int CAST_SPELL_COST;
    private static final int CAST_SPELL_DAMAGE;

    private int mana;

    static
    {
        MAXIMUM_MANA = 50;
        MINIMUM_MANA = 0;
        MINIMUM_RESTORE_MANA = 1;

        CAST_SPELL_COST = 5;
        CAST_SPELL_DAMAGE = 10;
    }

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
     * @return damage to other creature as an int.
     * @throws LowManaException if elf does not have enouhg mana to cast spell.
     */
    public void castSpell(final Creature opponent) throws LowManaException
    {
        final boolean insufficientMana;

        insufficientMana = mana < CAST_SPELL_COST;

        if (insufficientMana)
        {
            throw new LowManaException("You need at least " +
                                       CAST_SPELL_COST +
                                       " to cast a spell");
        }

        mana -= CAST_SPELL_COST;

        opponent.takeDamage(CAST_SPELL_DAMAGE);
    }

    /**
     * Increases mana but cannot exceed 50.
     * <p>
     * Verifies that restore mana amount is not negative or 0.
     * </p>
     * @param amount the amount of mana to restore as an int.
     */
    public void restoreMana(int amount)
    {
        final boolean manaExceedsMaximum;
        final boolean amountLesserThanMinimum;

        amountLesserThanMinimum = amount < MINIMUM_RESTORE_MANA;

        if (amountLesserThanMinimum)
        {
            throw new IllegalArgumentException("Cannot restore mana" +
                    " by value of " +
                    amount);
        }

        manaExceedsMaximum = mana + amount > MAXIMUM_MANA;

        if (manaExceedsMaximum) {
            mana = MAXIMUM_MANA;
        }

        mana += amount;
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

        if (manaExceedsMaximum)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value greater than " +
                                               MAXIMUM_MANA);
        }

        if (manaLowerThanMinimum)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value less than " +
                                               MINIMUM_MANA);
        }
    }
}
