package Exeptions;

public class IllegalEmailException extends IllegalArgumentException
{
  public IllegalEmailException(String msg)
  {
    super("Invalid value:" + msg);
  }
}
