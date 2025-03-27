package Exeptions;

public class IllegalTypeException extends RuntimeException
{
  public IllegalTypeException()
  {
    super("Invalid type");
  }
}
