package ca.bcit.comp2522.creature;

public class Elf extends Creature
{
    private static final int MAXIMUM_MANA;
    private static final int MINIMUM_MANA;

    private static final int CAST_SPELL_COST;
    private static final int CAST_SPELL_DAMAGE;

    private int mana;

    static
    {
        MAXIMUM_MANA = 50;
        MINIMUM_MANA = 0;

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
                .append("Mana: ")
                .append(this.mana);

        elfDetails = detailsBuilder.toString();

        System.out.println(elfDetails);
    }

    public int castSpell() throws LowManaException
    {
        if(mana < CAST_SPELL_COST)
        {
            throw new LowManaException("You need at least " +
                                       CAST_SPELL_COST +
                                       " to cast a spell");
        }

        mana -= CAST_SPELL_COST;

        return CAST_SPELL_DAMAGE;
    }

    private static void validateMana(final int mana)
    {
        if (mana > MAXIMUM_MANA)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value greater than " +
                                               MAXIMUM_MANA);
        }

        if (mana < MINIMUM_MANA)
        {
            throw new IllegalArgumentException("Cannot set mana to " +
                                               "value less than " +
                                               MINIMUM_MANA);
        }
    }



}
