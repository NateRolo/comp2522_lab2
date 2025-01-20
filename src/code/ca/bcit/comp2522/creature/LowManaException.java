package ca.bcit.comp2522.creature;

public class LowManaException extends Exception
{
    LowManaException(final String message)
    {
        super(message);
    }
}
