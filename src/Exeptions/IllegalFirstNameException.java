package Exeptions;

public class IllegalFirstNameException extends IllegalArgumentException
{
public IllegalFirstNameException(String msg)
{
  super("Invalid name: " + msg);
}
}
