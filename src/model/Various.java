package model;

import java.io.Serializable;

/**
 * A class representing a various (type of pet) with attributes such as price, color, age, gender, species, kennel status, name, and additional comments.
 * @author Mihai Rotaru
 * @version 1.0
 */
public class Various extends Pet implements Serializable
{

  /**
   * Constructor for creating a Various object with specified attributes.
   * @param price the pet's price
   * @param color the pet's color
   * @param age the pet's age
   * @param species the pet's species
   */
  public Various(double price, String color, int age, String species)
  {
    super(6,price,color,age,species);
  }

  /**
   * Copy constructor for creating a Various object based on another Various object.
   * Copies all attributes from the given Various object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param various the Various object to copy.
   */
  public Various(Various various)
  {
    super(various.getType(), various.getPrice(), various.getColor(), various.getAge(), various.getSpecies());

    if (various.getGender().equals("Male"))
    {
      isMale();
    }
    else if (various.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(various.getComment());
    setName(various.getName());
    if(various.getInKennel())
    {
      isInKennel();
    }
    else
    {
      isNotInKennel();
    }
  }

  /**
   * Pets of various type do not have access to kennel.
   * @return false, as pets of various type do not have kennel access.
   */
  @Override
  public boolean hasAccessToKennel()
  {
    return false;
  }

  /**
   * Compares this Various object with another object for equality.
   * @param obj the object to compare with
   * @return true if the given object is equal to this Various object.
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    Various temp = (Various) obj;
    return super.equals(temp);
  }

  /**
   * Returns a string representation of the pet of type various.
   * @return a string representation of the pet of type various.
   */
  public String toString()
  {
    return super.toString();
  }
}
