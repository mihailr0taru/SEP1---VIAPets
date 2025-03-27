package model;

import java.io.Serializable;


/**
 * A class representing a rodent with type, price, color, age, gender,
 * species, kennel status, name, price, additional comment and if it bites or not.
 * @author Mihail Rotaru
 * @version 1.0
 */
public class Rodent extends Pet implements Serializable
{
  private boolean bite =false;

  /**
   * Four-argument constructor for creating a Rodent object with
   * specified price, color, age, and species.
   * The bite is initialized to false.
   * @param price the rodent's price
   * @param color the rodent's color
   * @param age the rodent's age
   * @param species the rodent's species
   */
  public Rodent(double price, String color, int age, String species)
  {
    super(5, price, color, age, species);
    bite = false;
  }

  /**
   * Copy constructor for creating a Rodent object based on another Rodent object.
   * Copies all attributes from the given Rodent object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param rodent the Rodent object to copy
   */
  public Rodent(Rodent rodent)
  {
    super(rodent.getType(), rodent.getPrice(), rodent.getColor(), rodent.getAge(), rodent.getSpecies());

    if(rodent.getGender().equals("Male")){
      isMale();
    }
    else if (rodent.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(rodent.getComment());
    setName(rodent.getName());
    if(rodent.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    if (rodent.doesItBite())
    {
      isBiting();
    }
    else {
      isNotBiting();
    }

  }

  /**
   * Checks if the rodent has access to a kennel.
   * For rodents, this is always false.
   * @return false, as rodent can't access a kennel
   */
  @Override public boolean hasAccessToKennel()
  {
    return false;
  }

  /**
   * Checks if the rodent bites or not
   * @return true, if it bites, false if it does not bite
   */
  public boolean doesItBite()
  {
    return bite;
  }

  /**
   * This method sets the rodent to bite.
   */
  public void isBiting()
  {
    bite = true;
  }

  /**
   * This method sets the rodent to not bite.
   */
  public void isNotBiting()
  {
    bite = false;
  }

  /**
   * First compares the attributes from super class and if they match,
   * then it compares the biting status of two rodents.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this rodent
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    Rodent temp = (Rodent)obj;
    return super.equals(obj) && doesItBite() == temp.doesItBite();
  }

  /**
   * Returns a string representation of the rodent.
   * @return a string representation of the rodent in the format
   * type \n price \n color \n age \n gender \n name \n comment \n
   * kennel status \n species \n biting status
   */
  public String toString()
  {
    return super.toString() + "\n" + doesItBite();
  }
}
