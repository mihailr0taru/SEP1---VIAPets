package Exeptions;

public class IllegalLastNameException extends IllegalArgumentException
{
  public IllegalLastNameException(String msg)
  {
    super("Invalid name: " + msg);
  }
}
