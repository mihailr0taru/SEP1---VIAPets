package Exeptions;

public class InvalidNameException extends RuntimeException
{
  public InvalidNameException()
  {
    super("Invalid name");
  }

  public InvalidNameException(String msg)
  {
    super("Invalid name: " + msg);
  }

}