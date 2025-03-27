package Exeptions;

public class IllegalPhoneNumberException extends IllegalArgumentException
{
  public IllegalPhoneNumberException(String msg)
  {
    super("Invalid value: " + msg);
  }
}
