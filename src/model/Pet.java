package model;

import Exeptions.*;

import java.io.Serializable;

/**
 * An abstract class called Pet that serves as a base for all the subclasses.
 * @author Mihail Rotaru
 * @version 1.0
 */
public abstract class Pet implements Serializable
{
  private int type;
  private double price;
  private String color;
  private int age;
  private String gender;
  private String name;
  private String comment;
  private boolean inKennel;
  private String species = "";

  /**
   * A 5-argument constructor that creates a new Pet object.
   * @param type An integer that indicates in which subclass is the pet can
   *             have values between 1 and 6.
   * @param price A double that indicates the price of the animal, if it's
   *              not in the kennel, otherwise it's set 0.
   * @param color A String that stores the color information about the pet.
   * @param age A integer that stores the age information about the pet.
   * @param species A String that stores the species information about the pet.
   */
  public Pet(int type, double price, String color, int age, String species)
  {
    isNotInKennel();
    setType(type);
    setPrice(price);
    setColor(color);
    setAge(age);
    setSpecies(species);
    gender = "NotAssigned";
    name = "NoName";
    comment = "NoComment";
  }

  /**
   * Gets the type of the pet.
   * @return An integer between 1 and 6, that represents the type of the pet.
   */
  public int getType()
  {
    return type;
  }

  /**
   * Gets the species of the pet.
   * @return A string that contains the species of the pet.
   */
  public String getSpecies()
  {
    return species;
  }

  /**
   * Gets the type of the pet in a String format.
   * @return A String which can be Dog,Cat,Bird,Fish,Rodent or Various,
   * which represent the type of the pet.
   */
  public String getTypeString()
  {
    String temp = "";
    switch (type)
    {
      case 1:
        temp += "Dog";
        break;
      case 2:
        temp += "Cat";
        break;
      case 3:
        temp += "Bird";
        break;
      case 4:
        temp += "Fish";
        break;
      case 5:
        temp += "Rodent";
        break;
      case 6:
        temp += "Various";
        break;
    }
    return temp;
  }

  /**
   * Gets the price of the pet.
   * @return A double bigger than or equal to 0 that represents the price of the pet.
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * Gets the color of the pet.
   * @return A String that contains the color of the pet.
   */
  public String getColor()
  {
    return color;
  }

  /**
   * Gets the age of the pet.
   * @return An integer bigger or equal to 0 that contains the age of the pet.
   */
  public int getAge()
  {
    return age;
  }

  /**
   *
   * @return
   */
  public String getGender()
  {
    return gender;
  }

  public String getName()
  {
    return name;
  }

  public String getComment()
  {
    return comment;
  }

  public boolean getInKennel()
  {
    return inKennel;
  }

  private void setType(int type)
  {
    switch (type)
    {
      case 1, 2, 3, 4, 5, 6:
        this.type = type;
        break;
      default:
        throw new IllegalTypeException();
    }
  }

  public void setPrice(double price)
  {
    if (!inKennel)
    {
      if (price >= 0)
      {
        this.price = price;
      }
      else
        throw new IllegalPriceException();
    }
    else
      throw new UnsupportedActionException(
          "Can't set a price for a pet in the kennel");
  }

  public void setColor(String color)
  {
    this.color = color;
  }

  public void setAge(int age)
  {
    if (age >= 0)
    {
      this.age = age;
    }
    else
      throw new IllegalAgeException();
  }

  public void isMale()
  {
    gender = "Male";
  }

  public void isFemale()
  {
    gender = "Female";
  }

  public void setName(String name)
  {
    if (name.matches("[a-zA-z ]+"))
    {
      this.name = name;
    }
    else
      throw new IllegalNameException();
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public void isInKennel()
  {
    if (hasAccessToKennel())
    {
      inKennel = true;
      price = 0;
    }
    else
      throw new UnsupportedActionException(
          "This type doesn't have access to the kennel");
  }

  public void isNotInKennel()
  {
    inKennel = false;
  }

  public void setSpecies(String species)
  {
    if (species.matches("[a-zA-Z ]+"))
    {
      this.species = species;
    }
    else
      throw new IllegalBreedSpecException();
  }

  public abstract boolean hasAccessToKennel();

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Pet temp = (Pet) obj;

    return type == temp.getType() && price == temp.getPrice() && color.equals(
        temp.getColor()) && age == temp.getAge() && gender.equals(
        temp.getGender()) && name.equals(temp.getName()) && comment.equals(
        temp.getComment()) && inKennel == temp.getInKennel();
  }

  public String toString()
  {
    return getTypeString() + "\n" + getPrice() + "\n" + getColor() + "\n"
        + getAge() + "\n" + getGender() + "\n" + getName() + "\n" + getComment()
        + "\n" + getInKennel() + "\n" + getSpecies();
  }
}
