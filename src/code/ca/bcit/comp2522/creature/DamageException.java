package ca.bcit.comp2522.creature;

public class DamageException extends RuntimeException
{
    DamageException(final String message)
    {
        super(message);
    }
}
