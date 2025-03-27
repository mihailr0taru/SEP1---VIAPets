package Exeptions;

public class IllegalAgeException extends RuntimeException
{
  public IllegalAgeException()
  {
    super("Age can't be a negative number");
  }
}

