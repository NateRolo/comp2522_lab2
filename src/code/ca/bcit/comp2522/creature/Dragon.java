package ca.bcit.comp2522.creature;

public class Dragon extends Creature
{
    private static final int MINIMUM_FIRE_POWER;
    private static final int MAXIMUM_FIRE_POWER;

    static
    {
        MINIMUM_FIRE_POWER = 0;
        MAXIMUM_FIRE_POWER = 100;
    }

    private final int firePower;

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
