package model;

import java.io.Serializable;
/**
 * A class representing a fish with type, price, color, age, gender,
 * species, kennel status, name, price, additional comment, if it's a
 * predator and if it's a saltwater.
 * @author Mihail Rotaru
 * @version 1.0
 */
public class Fish extends Pet implements Serializable
{
  private boolean predator;
  private boolean saltWater;

  /**
   * Constructor for creating a Fish object with specified price, color, age, and species.
   * The predator is initialized to isNotPredator
   * The saltwater is initialized to isNotSaltWater
   * @param price the fish's price
   * @param color the fish's color
   * @param age the fish's age
   * @param species the fish's species
   */
  public Fish(double price, String color, int age, String species)
  {
    super(4, price, color, age,species);
    isNotPredator();
    isNotSaltWater();
  }
  /**
   * Copy constructor for creating a Fish object based on another Fish object.
   * Copies all attributes from the given Fish object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param fish the Fish object to copy
   */
  public Fish(Fish fish)
  {
    super(fish.getType(), fish.getPrice(), fish.getColor(), fish.getAge(), fish.getSpecies());

    if(fish.getGender().equals("Male")){
      isMale();
    }
    else if (fish.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(fish.getComment());
    setName(fish.getName());
    if(fish.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    if (isItPredator())
    {
      isPredator();
    }
    else {
      isNotPredator();
    }
    if (isItSaltWater())
    {
      isSaltWater();
    }
    else {
      isNotSaltWater();
    }

  }
  /**
   * Checks if the fish has access to a kennel.
   * For fish, this is always false.
   * @return false, as fish can't access a kennel
   */
  @Override public boolean hasAccessToKennel()
  {
    return false;
  }

  /**
   * Checks if the fish is predator or not.
   * @return true, if it is predator, false if it is not
   */
  public boolean isItPredator()
  {
    return predator;
  }

  /**
   * Checks if the fish is saltwater or not.
   * @return true, if it is saltwater, false if it is not
   */
  public boolean isItSaltWater()
  {
    return saltWater;
  }

  /**
   * This method sets the fish to predator fish.
   */
  public void isPredator()
  {
    predator = true;
  }

  /**
   * This method sets the fish to not predator fish.
   */
  public void isNotPredator()
  {
    predator = false;
  }

  /**
   * This method sets the fish to saltwater fish.
   */
  public void isSaltWater()
  {
    saltWater = true;
  }

  /**
   * This method sets the fish to not saltwater fish.
   */
  public void isNotSaltWater()
  {
    saltWater = false;
  }
  /**
   * First compares the attributes from super class and if they match,
   * then it compares the predator status and water status of two fish.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this fish
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Fish temp = (Fish) obj;
    return super.equals(temp) && predator == temp.isItPredator()
        && saltWater == temp.isItSaltWater();
  }

  /**
   * Returns a string representation of the fish.
   * @return a string representation of the fish in the format type \n price
   * \n color \n age \n gender \n name \n comment \n kennel status \n species
   * \n predator status \n water status
   */
  public String toString()
  {
    return super.toString() + "\n" + predator + "\n"
        + saltWater;
  }
}
