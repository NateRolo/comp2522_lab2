import ca.bcit.comp2522.creature.Elf;
import ca.bcit.comp2522.creature.Orc;
import ca.bcit.comp2522.creature.Dragon;
import ca.bcit.comp2522.creature.Date;
import ca.bcit.comp2522.creature.Creature;

/**
 *
 */
public class CreatureTest
{
    public static void main(final String[] args)
    {
        final Date dragonDateOfBirth;
        final Date elfDateOfBirth;
        final Date orcDateOfBirth;

        final Creature testDragon;
        final Creature testElf;
        final Creature testOrc;

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
    }
}
