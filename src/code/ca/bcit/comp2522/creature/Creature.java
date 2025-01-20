package ca.bcit.comp2522.creature;

public class Creature
{
    private static final Date CURRENT_DATE;
    private static final int CURRENT_YEAR;
    private static final int CURRENT_MONTH;
    private static final int CURRENT_DAY;
    private static final int BIRTHDAY_NOT_PASSED_OFFSET;

    private static final int MAXIMUM_HEALTH;
    private static final int MINIMUM_HEALTH;
    private static final int NO_HEALTH;

    private static final int MINIMUM_DAMAGE;

    static
    {
        CURRENT_DATE = new Date(2025, 1, 16);
        CURRENT_YEAR = CURRENT_DATE.getYear();
        CURRENT_MONTH = CURRENT_DATE.getMonthAsInt();
        CURRENT_DAY = CURRENT_DATE.getDay();
        BIRTHDAY_NOT_PASSED_OFFSET = 1;

        MAXIMUM_HEALTH = 100;
        MINIMUM_HEALTH = 1;
        NO_HEALTH = 0;

        MINIMUM_DAMAGE = 0;
    }


    private final String name;
    private final Date dateOfBirth;

    private int health;

    public Creature(final String name,
                    final Date dateOfBirth,
                    final int health)
    {
        validateName(name);
        validateDateOfBirth(dateOfBirth);
        validateHealth(health);

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
    }

    /*
     * Returns true if health is greater than 0.
     */
    private boolean isAlive()
    {
        if(this.health < MINIMUM_HEALTH)
        {
            return false;
        }
        return true;
    }

    /*
     * Reduces health by damage. If health goes below 0, set it to 0.
     * If damage is negative, throw unchecked DamageException.
     */
    private void takeDamage(final int damage)
    {
        if(damage < MINIMUM_DAMAGE)
        {
            throw new DamageException("Invalid damage value: " + damage);
        }

        health -= damage;

        if(health < MINIMUM_HEALTH)
        {
            health = NO_HEALTH;
        }
    }

    /*
     * Increases health by healAmount but cannot exceed 100.
     * If healing amount is negative, throw an unchecked HealingException.
     */
    private void heal(int healAmount)
    {
        health += healAmount;

        if(health > MAXIMUM_HEALTH)
        {
            health = MAXIMUM_HEALTH;
        }
    }

    /**
     * Calculates the creature's age in years based on its date of birth.
     * <p>
     * If creature's birthday has not passed in current year, subtract 1 from age.
     *</p>
     * @return the creature's age in years based on its date of birth.
     */
    public int getAgeYears()
    {
        final int yearBorn;
        final int monthBorn;
        final int dayBorn;
        final int ageInYears;

        yearBorn = dateOfBirth.getYear();
        monthBorn = dateOfBirth.getMonthAsInt();
        dayBorn = dateOfBirth.getDay();

        if(monthBorn > CURRENT_MONTH ||
           (monthBorn == CURRENT_MONTH &&
            dayBorn > CURRENT_DAY))
        {
            ageInYears = yearBorn - CURRENT_YEAR -
                         BIRTHDAY_NOT_PASSED_OFFSET;
        }
        else
        {
            ageInYears = yearBorn - CURRENT_YEAR;
        }

        return ageInYears;
    }

    /**
     * Print's the creature's name, dateOfBirth, age, and health.
     * <p>
     * E.g. Spyro
     *      Born: Wednesday September 9, 1998
     *      Age: 26
     *      Health : 100
     * </p>
     */
    public void getDetails()
    {
        final String dateOfBirth;
        final int ageInYears;

        final StringBuilder detailsBuilder;
        final String creatureDetails;

        dateOfBirth = this.dateOfBirth.getFullDate();
        ageInYears = this.getAgeYears();

        detailsBuilder = new StringBuilder();

        detailsBuilder.append(name)
                      .append("\n")
                      .append("Born: ")
                      .append(dateOfBirth)
                      .append("\n")
                      .append("Age: ")
                      .append(ageInYears)
                      .append("\n")
                      .append("Health: ")
                      .append(health);

        creatureDetails = detailsBuilder.toString();

        System.out.println(creatureDetails);
    }

    /*
     * Validates name of creature.
     *
     * If name is null or blank, throw IllegalArgumentException.
     */
    private static void validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
    }

    /*
     * Validates date of birth. Birth year cannot be greater than current year.
     *
     * If birthYear is equal to CURRENT_YEAR, validates
     * that birthMonth is less than CURRENT_MONTH.
     *
     * if birthYear and birthMonth are equal to CURRENT_YEAR and CURRENT_MONTH
     * validate that birthDay is less than CURRENT_DAY.
     *
     * Throws error if Date is invalid.
     */
    private static void validateDateOfBirth(final Date dateOfBirth) {
        final int birthYear;
        final int birthMonth;
        final int birthDay;

        birthYear = dateOfBirth.getYear();
        birthMonth = dateOfBirth.getMonthAsInt();
        birthDay = dateOfBirth.getDay();

        if (birthYear > CURRENT_YEAR) {
            throw new IllegalArgumentException("Birth year cannot be in the future");
        }

        if (birthYear == CURRENT_YEAR) {
            if (birthMonth > CURRENT_MONTH) {
                throw new IllegalArgumentException("Birth month cannot be in the future.");
            }
        }

        if (birthYear == CURRENT_YEAR &&
            birthMonth == CURRENT_MONTH &&
            birthDay == CURRENT_DAY) {
            throw new IllegalArgumentException("Birthday cannot be in the future");
        }
    }

    /*
     * Validates creature's health.
     *
     * If health is less than 0 or greater than 100,
     * throw an unchecked IllegalArgumentException.
     */
    private static void validateHealth(final int health)
    {
        if (health < MINIMUM_HEALTH)
        {
            throw new IllegalArgumentException("Health cannot be less than " +
                                               MINIMUM_HEALTH);
        }

        if (health > MAXIMUM_HEALTH)
        {
            throw new IllegalArgumentException("Health cannot be greater than " +
                                               MAXIMUM_HEALTH);
        }
    }
}

