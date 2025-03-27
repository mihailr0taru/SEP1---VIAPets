package Exeptions;

public class UnsupportedActionException extends RuntimeException
{
  public UnsupportedActionException(String e)
  {
    super("Unsupported action: " + e);
  }
}