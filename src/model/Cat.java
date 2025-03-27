package model;

import Exeptions.IllegalNameException;

import java.io.Serializable;
/**
 * A class representing a cat with type, price, color, age, gender, breed, kennel status, name, price, additional comment and name of breeder.
 * @author Mihail Rotaru
 * @version 1.0
 */
public class Cat extends Pet implements Serializable
{
  private String nameOfBreeder = "NoName";

  /**
   * Four-argument constructor for creating a Cat object with specified price, color, age, and species.
   * The name of the breeder is initialized to "NoName".
   * @param price the cat's price
   * @param color the cat's color
   * @param age the cat's age
   * @param species the cat's species
   */
  public Cat(double price, String color, int age, String species)
  {
    super(2, price, color, age, species);
    nameOfBreeder = "NoName";
  }

  /**
   * Copy constructor for creating a Cat object based on another Cat object.
   * Copies all attributes from the given Cat object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param cat the Cat object to copy
   */
  public Cat(Cat cat)
  {
    super(cat.getType(), cat.getPrice(), cat.getColor(), cat.getAge(), cat.getSpecies());

    if(cat.getGender().equals("Male")){
      isMale();
    }
    else if (cat.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(cat.getComment());
    setName(cat.getName());
    if(cat.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    setNameOfBreeder(cat.getNameOfBreeder());

  }

  /**
   * Gets the cat's name of breeder.
   * @return the cat's name of breeder
   */
  public String getNameOfBreeder()
  {
    return nameOfBreeder;
  }

  /**
   * Sets the cat's name of breeder.
   * @param nameOfBreeder what the cat's name of breeder will be set to
   */
  public void setNameOfBreeder(String nameOfBreeder)
  {
    if (nameOfBreeder.matches("[a-zA-z ]+"))
    {
      this.nameOfBreeder = nameOfBreeder;
    }
    else
      throw new IllegalNameException("(name of breeder)");
  }
  /**
   * Checks if the cat has access to a kennel.
   * For cats, this is always true.
   * @return true, as cat can access a kennel.
   */
  @Override
  public boolean hasAccessToKennel()
  {
    return true;
  }


  /**
   * First compares the attributes from super class and if they match, then it compares the name of breeder of two cats.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this cat
   */
  @Override
  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Cat temp = (Cat)obj;
    return super.equals(temp) && nameOfBreeder.equals(temp.getNameOfBreeder());
  }

  /**
   * Returns a string representation of the cat.
   * @return a string representation of the cat in the format type \n price \n color \n age \n gender \n name \n comment \n kennel status \n species \n name of breeder
   */
  public String toString()
  {
    return super.toString() + "\n" + nameOfBreeder;
  }
}
