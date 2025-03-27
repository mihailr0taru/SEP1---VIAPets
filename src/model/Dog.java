package model;

import Exeptions.IllegalNameException;

import java.io.Serializable;

/**
 * A class representing a dog with attributes such price, color,
 * age, gender, species, kennel status, name, additional comments, as name of breeder.
 * @author Mihai Rotaru
 * @version 1.0
 */
public class Dog extends Pet implements Serializable
{
  private String nameOfBreeder = "NoName";

  /**
   * Constructor for creating a Dog object with specified price, color, age, and species.
   * The name of the breeder is initialized to "NoName".
   * @param price the dog's price
   * @param color the dog's color
   * @param age the dog's age
   * @param species the dog's species
   */
  public Dog(double price, String color, int age, String species)
  {
    super(1, price, color, age, species);
    nameOfBreeder = "NoName";
  }

  /**
   * Copy constructor for creating a Dog object based on another Dog object.
   * Copies all attributes from the given Dog object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param dog the Dog object to copy
   */
  public Dog(Dog dog)
  {
    super(dog.getType(), dog.getPrice(), dog.getColor(), dog.getAge(), dog.getSpecies());

    if(dog.getGender().equals("Male"))
    {
      isMale();
    }
    else if (dog.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(dog.getComment());
    setName(dog.getName());

    if (dog.getInKennel())
    {
      isInKennel();
    }
    else
    {
      isNotInKennel();
    }
    setNameOfBreeder(dog.getNameOfBreeder());
  }

  /**
   * Retrieves the name of the dog's breeder.
   * @return the name of the breeder.
   */
  public String getNameOfBreeder()
  {
    return nameOfBreeder;
  }

  /**
   * Sets the name of the dog's breeder.
   * @param nameOfBreeder the name of the breeder ro set.
   * @throws IllegalNameException if the name contains invalid characters.
   */
  public void setNameOfBreeder(String nameOfBreeder)
  {
    if (nameOfBreeder.matches("[a-zA-z ]+"))
    {
      this.nameOfBreeder = nameOfBreeder;
    }
    else
      throw new IllegalNameException("( name of breeder)");
  }

  /**
   * Checks if the dog has access to a kennel.
   * For dogs, this is always true.
   * @return true, as dog can access a kennel.
   */
  @Override
  public boolean hasAccessToKennel()
  {
    return true;
  }

  /**
   * Compares this Dog object with another for equality.
   * @param obj the object to compare with.
   * @return true if the given object is equal to this Dog object.
   */
  @Override
  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Dog temp = (Dog)obj;
    return super.equals(temp) && nameOfBreeder.equals(temp.getNameOfBreeder());
  }

  /**
   * Returns a string representation of the dog.
   * @return a string representation of the dog's information.
   */
  public String toString()
  {
    return super.toString() + "\n" + nameOfBreeder;
  }
}
