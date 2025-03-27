package Exeptions;

public class IllegalNameException extends RuntimeException
{
  public IllegalNameException()
  {
    super("Name can't contain numbers");
  }

  public IllegalNameException(String e)
  {
    super("Name can't contain numbers: "+ e);
  }
}


