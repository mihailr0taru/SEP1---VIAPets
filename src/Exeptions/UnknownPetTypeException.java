package Exeptions;

public class UnknownPetTypeException extends RuntimeException
{
  public UnknownPetTypeException()
  {
    super("Unknown pet type");
  }
  public UnknownPetTypeException(String msg)
  {
    super("Invalid type: "+ msg);
  }
}
