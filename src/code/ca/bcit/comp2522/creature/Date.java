package ca.bcit.comp2522.creature;

/**
 * Represents a Gregorian calendar date with validation and formatting capabilities.
 * <p>
 * This class validates input for year, month, and day according to Gregorian calendar rules.
 * It provides methods to retrieve individual date components, format dates in various styles,
 * and calculate the day of the week using modular arithmetic.
 * </p>
 * <p>
 * Key Features:
 * <ul>
 *   <li>Validation for year (1800 to current year), month (1-12), and day (varies by month/year).</li>
 *   <li>Determines if a year is a leap year based on Gregorian rules.</li>
 *   <li>Provides date components such as year, month name, and day of the month.</li>
 *   <li>Formats dates in two styles:
 *       <ul>
 *           <li>"YYYY-MM-DD" (ISO format)</li>
 *           <li>"DayOfWeek MonthName Day, Year" (readable format)</li>
 *       </ul>
 *   </li>
 *   <li>Calculates the day of the week using a seven-step modular arithmetic process.</li>
 * </ul>
 * <p>
 * Example Usage:
 * <pre>
 *     Date date = new Date(1977, 10, 31);
 *     String fullDate = date.getFullDate();        // "Monday October 31, 1977"
 *     String dayOfWeek = date.getDayOfTheWeek();  // "Monday"
 *     String isoDate = date.getYYYYMMDD();        // "1977-10-31"
 * </pre>
 * <p>
 * Notes:
 * <ul>
 *   <li>Dates before 1800 are not supported.</li>
 *   <li>Leap years are determined by divisibility rules for 4, 100, and 400.</li>
 *   <li>Invalid dates throw {@link IllegalArgumentException} during construction.</li>
 * </ul>
 *
 * @author Haider A
 * @author Kyle C
 * @author Nathan O
 * @version 1.0
 */
public class Date
{
    private static final int MINIMUM_YEAR = 1800;
    private static final int CURRENT_YEAR = 2025;

    private static final int JAN = 1;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int APR = 4;
    private static final int MAY = 5;
    private static final int JUN = 6;
    private static final int JUL = 7;
    private static final int AUG = 8;
    private static final int SEP = 9;
    private static final int OCT = 10;
    private static final int NOV = 11;
    private static final int DEC = 12;

    private static final int JAN_CODE = 1;
    private static final int FEB_CODE = 4;
    private static final int MAR_CODE = 4;
    private static final int APR_CODE = 0;
    private static final int MAY_CODE = 2;
    private static final int JUN_CODE = 5;
    private static final int JUL_CODE = 0;
    private static final int AUG_CODE = 3;
    private static final int SEP_CODE = 6;
    private static final int OCT_CODE = 1;
    private static final int NOV_CODE = 4;
    private static final int DEC_CODE = 6;

    private static final int SATURDAY_RESULT  = 0;
    private static final int SUNDAY_RESULT    = 1;
    private static final int MONDAY_RESULT    = 2;
    private static final int TUESDAY_RESULT   = 3;
    private static final int WEDNESDAY_RESULT = 4;
    private static final int THURSDAY_RESULT  = 5;
    private static final int FRIDAY_RESULT    = 6;

    private static final int MINIMUM_DAY           = 1;
    private static final int MAX_DAY_SHORT_MONTH   = 30;
    private static final int MAX_DAY_LONG_MONTH    = 31;
    private static final int MAX_DAY_FEB           = 28;
    private static final int MAX_DAY_FEB_LEAP_YEAR = 29;

    private static final int LEAP_YEAR_RULE        = 4;
    private static final int NON_LEAP_CENTURY_RULE = 100;
    private static final int LEAP_CENTURY_RULE     = 400;
    private static final int NO_REMAINDER          = 0;

    private static final int NINETEENTH_CENTURY   = 1800;
    private static final int TWENTIETH_CENTURY    = 1900;
    private static final int TWENTY_FIRST_CENTURY = 2000;

    private static final int NINETEENTH_CENTURY_OFFSET   = 2;
    private static final int TWENTY_FIRST_CENTURY_OFFSET = 6;
    private static final int LEAP_YEAR_OFFSET            = 6;

    private static final int DIVISOR_FOR_TWELVES_IN_YEAR    = 12;
    private static final int DIVISOR_FOR_FOURS_IN_REMAINDER = 4;
    private static final int DIVISOR_FOR_SEVENS_IN_TOTAL    = 7;

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a new Date object with the specified year, month, and day.
     * Validates that the input values conform to the Gregorian calendar's constraints.
     *
     * @param year  the year of the date (must be between 1800 and the current year)
     * @param month the month of the date (1 = January, 12 = December)
     * @param day   the day of the month (validated for month/year constraints)
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(day, month, year);

        this.year  = year;
        this.month = month;
        this.day   = day;
    }

    /**
     * Retrieves the year component of the date.
     *
     * @return the year
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Retrieves the month component of the date as a string.
     *
     * @return the full name of the month (e.g., "January", "February")
     */
    public String getMonthAsString()
    {
        final String monthAsString;

        switch (this.month) {
            case JAN -> monthAsString = "January";
            case FEB -> monthAsString = "February";
            case MAR -> monthAsString = "March";
            case APR -> monthAsString = "April";
            case MAY -> monthAsString = "May";
            case JUN -> monthAsString = "June";
            case JUL -> monthAsString = "July";
            case AUG -> monthAsString = "August";
            case SEP -> monthAsString = "September";
            case OCT -> monthAsString = "October";
            case NOV -> monthAsString = "November";
            case DEC -> monthAsString = "December";
            default -> monthAsString = "N/A";
        }
        return monthAsString;
    }

    /**
     * Returns the month component of the date as an int.
     *
     * @return the number of the month as an int (e.g., January == 1)
     */
    public int getMonthAsInt()
    {
        return this.month;
    }

    /**
     * Retrieves the day of the month.
     *
     * @return The day of the month stored in the `day` field.
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     * Formats the date fields (`year`, `month`, `day`)
     * into a string in the format "YYYY-MM-DD".
     *
     * @return A string representing the date in the format "YYYY-MM-DD".
     */
    public String getYYYYMMDD()
    {
        StringBuilder builder;
        final String  dateFormatted;

        builder = new StringBuilder();
        builder.append(this.year)
               .append("-")
               .append(this.month)
               .append("-")
               .append(this.day);
        dateFormatted = builder.toString();

        return dateFormatted;
    }


    /**
     * Determines the day of the week for the current date.
     * Implements the lab's seven-step calculation process for the day of the week.
     * <p>
     * To get the day of the week, do the following seven steps for dates in the 1900s:
     * e.g. October 31 1977:
     * step 1: calculate the number of twelves in 77:
     * 6
     * step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 =
     * 5
     * step 3: calculate the number of fours in step 2: 5/4 = 1.25, so
     * 1
     * step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 =
     * 43
     * step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 =
     * 44
     * step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
     * step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
     * Extra notes:
     * a) for January/February dates in leap years, add 6 at the start
     * b) for all dates in the 2000s, add 6 at the start
     * c) for all dates in the 1800s, add 2 at the start
     * </p>
     *
     * @return the name of the day (e.g., "Monday", "Tuesday")
     */
    public String getDayOfTheWeek()
    {
        final int    lastTwoDigitsOfYear;
        final int    dayOfMonth;
        final int    numberOfTwelvesInYear;
        final int    yearRemainderAfterTwelves;
        final int    numOfFoursInRemainder;
        final int    thisMonthCode;
        final int    totalWithoutMonthCode;
        final int    totalWithMonthCode;
        final int    remainderOfTotal;
        final String dayOfWeek;
        int          fullYear;

//      step 1: calculate the number of twelves year
        fullYear   = this.year;
        dayOfMonth = this.day;

        if (isLeapYear(this.year) &&
            (this.month == JAN || this.month == FEB)) {
            fullYear += LEAP_YEAR_OFFSET;
        }

        if (this.year >= NINETEENTH_CENTURY &&
            this.year < TWENTIETH_CENTURY) {
            fullYear += NINETEENTH_CENTURY_OFFSET;
        }

        if (this.year >= TWENTY_FIRST_CENTURY) {
            fullYear += TWENTY_FIRST_CENTURY_OFFSET;
        }

        if (year >= TWENTY_FIRST_CENTURY) {
            lastTwoDigitsOfYear = fullYear - TWENTY_FIRST_CENTURY;
        } else if (year >= TWENTIETH_CENTURY) {
            lastTwoDigitsOfYear = fullYear - TWENTIETH_CENTURY;
        } else {
            lastTwoDigitsOfYear = fullYear - NINETEENTH_CENTURY;
        }

        numberOfTwelvesInYear = lastTwoDigitsOfYear / DIVISOR_FOR_TWELVES_IN_YEAR;

//      step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 =
        yearRemainderAfterTwelves = lastTwoDigitsOfYear % DIVISOR_FOR_TWELVES_IN_YEAR;

//      step 3: calculate the number of fours in step 2: 5/4 = 1.25, so
        numOfFoursInRemainder = yearRemainderAfterTwelves / DIVISOR_FOR_FOURS_IN_REMAINDER;

        // step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 =
        totalWithoutMonthCode = numberOfTwelvesInYear + yearRemainderAfterTwelves +
                                numOfFoursInRemainder +
                                dayOfMonth;

        // step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 =
        switch (this.month) {
            case JAN -> thisMonthCode = JAN_CODE;
            case FEB -> thisMonthCode = FEB_CODE;
            case MAR -> thisMonthCode = MAR_CODE;
            case APR -> thisMonthCode = APR_CODE;
            case MAY -> thisMonthCode = MAY_CODE;
            case JUN -> thisMonthCode = JUN_CODE;
            case JUL -> thisMonthCode = JUL_CODE;
            case AUG -> thisMonthCode = AUG_CODE;
            case SEP -> thisMonthCode = SEP_CODE;
            case OCT -> thisMonthCode = OCT_CODE;
            case NOV -> thisMonthCode = NOV_CODE;
            case DEC -> thisMonthCode = DEC_CODE;
            default -> throw new IllegalArgumentException("Invalid month: " + this.month);
        }

        totalWithMonthCode = totalWithoutMonthCode + thisMonthCode;

//      step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
        remainderOfTotal = totalWithMonthCode % DIVISOR_FOR_SEVENS_IN_TOTAL;

//      step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
        switch (remainderOfTotal) {
            case SATURDAY_RESULT -> dayOfWeek = "Saturday";
            case SUNDAY_RESULT -> dayOfWeek = "Sunday";
            case MONDAY_RESULT -> dayOfWeek = "Monday";
            case TUESDAY_RESULT -> dayOfWeek = "Tuesday";
            case WEDNESDAY_RESULT -> dayOfWeek = "Wednesday";
            case THURSDAY_RESULT -> dayOfWeek = "Thursday";
            case FRIDAY_RESULT -> dayOfWeek = "Friday";
            default -> dayOfWeek = "N/A";
        }
        return dayOfWeek;
    }

    /**
     * <p>
     * Returns a formatted string of the date, including the day of the week,
     * month name, day of the month, and year.
     * </p>
     * Example:
     * - "Monday October 31, 1977"
     * - "Monday March 15, 2021"
     *
     * <p>
     * The method computes the day of the week, retrieves the month name,
     * and formats the full date as a readable string.
     * </p>
     *
     * @return a formatted string (e.g., "Monday October 31, 1977")
     */
    public String getFullDate()
    {
        StringBuilder builder;
        final String  dayOfWeek;
        final String  month;
        final int     dayOfMonth;
        final int     year;
        final String  fullDate;

        builder    = new StringBuilder();
        dayOfWeek  = this.getDayOfTheWeek();
        month      = this.getMonthAsString();
        dayOfMonth = this.day;
        year       = this.year;

        builder.append(dayOfWeek)
               .append(" ")
               .append(month)
               .append(" ")
               .append(dayOfMonth)
               .append(",")
               .append(" ")
               .append(year);
        fullDate = builder.toString();

        return fullDate;
    }

    /*
     * Validates the year input to ensure it falls within the permissible range.
     * The valid range is from MINIMUM_YEAR (1800) to CURRENT_YEAR (2025), inclusive,
     * as specified in the lab requirements.
     *
     * @param year the year to validate
     */
    private static void validateYear(final int year)
    {
        if (year < MINIMUM_YEAR ||
            year > CURRENT_YEAR) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }

    /*
     * Determines if the given year is a leap year based on Gregorian calendar rules:
     * - A year is a leap year if it is divisible by LEAP_YEAR_RULE (4).
     * - It is not a leap year if divisible by NON_LEAP_CENTURY_RULE (100),
     *   unless it is also divisible by LEAP_CENTURY_RULE (400).
     *
     * Examples:
     * - 2000 is a leap year (divisible by 400).
     * - 1900 is not a leap year (divisible by 100 but not 400).
     * - 2004 is a leap year (divisible by 4 but not 100).
     *
     * @param year the year to check
     * @return true if the year is a leap year; false otherwise
     */
    private static boolean isLeapYear(final int year)
    {
        return (year % LEAP_YEAR_RULE == NO_REMAINDER &&
                (year % NON_LEAP_CENTURY_RULE != NO_REMAINDER ||
                 year % LEAP_CENTURY_RULE == NO_REMAINDER));
    }

    /*
     * Validates whether the given month is within the valid range.
     *
     * @param month The month to validate.
     */
    private static void validateMonth(final int month)
    {
        if (month < JAN ||
            month > DEC) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /*
     * Validates the day input to ensure it is valid for the specified month and year.
     * The method checks:
     * - If the day is at least MINIMUM_DAY (1) and does not exceed MAX_DAY_LONG_MONTH (31).
     * - For months with 30 days (April, June, September, November),
     *       the day cannot exceed MAX_DAY_SHORT_MONTH (30).
     * - For February (FEB), the day must not exceed MAX_DAY_FEB (28) in a non-leap year
     *   or MAX_DAY_FEB_LEAP_YEAR (29) in a leap year.
     *
     * Examples:
     * - Day 30 in February (non-leap year) is invalid.
     * - Day 31 in April is invalid.
     *
     * @param day the day to validate
     * @param month the month for which the day is being validated
     * @param year the year to consider for leap year checks
     */
    private static void validateDay(final int day, final int month, final int year)
    {
        if (day < MINIMUM_DAY ||
            day > MAX_DAY_LONG_MONTH) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }

        if ((month == APR ||
             month == JUN ||
             month == SEP ||
             month == NOV) &&
            day > MAX_DAY_SHORT_MONTH) {
            throw new IllegalArgumentException("Invalid day for this month: " + day);
        }

        if (month == FEB &&
            day > (isLeapYear(year) ?
                   MAX_DAY_FEB_LEAP_YEAR :
                   MAX_DAY_FEB)) {
            throw new IllegalArgumentException("Invalid day: " + day +
                                               " for month: " + month +
                                               " in year: " + year);
        }
    }
}
