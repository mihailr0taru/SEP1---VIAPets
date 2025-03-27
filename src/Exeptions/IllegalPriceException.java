package Exeptions;

public class IllegalPriceException extends RuntimeException
{
  public IllegalPriceException()
  {
    super("Price can't be a negative number");
  }
}
