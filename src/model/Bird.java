package model;

import java.io.Serializable;

/**
 * A class representing a bird with type, price, color, age, gender, species, kennel status, name, price, additional comment and preferred food.
 * @author Mihail Rotaru
 * @version 1.0
 */
public class Bird extends Pet implements Serializable
{
  private String preferredFood = "NoData";

  /**
   * Four-argument constructor for creating a Bird object with specified price, color, age, and species.
   * The preferred food is initialized to "NoData".
   * @param price the bird's price
   * @param color the bird's color
   * @param age the bird's age
   * @param species the bird's species
   */
  public Bird(double price, String color, int age, String species)
  {
    super(3, price, color, age, species);
    preferredFood = "NoData";
  }

  /**
   * Copy constructor for creating a Bird object based on another Bird object.
   * Copies all attributes from the given Bird object to this new instance.
   * Constructor for easy checking used at Kennel reservation and sales reservation.
   * @param bird the Bird object to copy
   */
  public Bird(Bird bird)
  {
    super(bird.getType(), bird.getPrice(), bird.getColor(), bird.getAge(), bird.getSpecies());

    if(bird.getGender().equals("Male")){
      isMale();
    }
    else if (bird.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(bird.getComment());
    setName(bird.getName());
    if(bird.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    setPreferredFood(bird.getPreferredFood());

  }

  /**
   * Checks if the bird has access to a kennel.
   * For birds, this is always true.
   * @return true, as bird can access a kennel.
   */
  @Override public boolean hasAccessToKennel()
  {
    return true;
  }

  /**
   * Gets the bird's preferred food
   * @return the bird's preferred food
   */
  public String getPreferredFood()
  {
    return preferredFood;
  }

  /**
   * Sets the bird's preferred food
   * @param preferredFood what the bird's preferred food will be set to
   */
  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
  }
  /**
   * First compares the attributes from super class and if they match, then it compares the species and preferred food of two birds.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this bird
   */
  public boolean equals(Object obj)
  {
    if (super.equals(obj))
    {
      Bird temp = (Bird) obj;
      return getSpecies().equals(temp.getSpecies())
          && getPreferredFood().equals(temp.getPreferredFood());
    }
    return false;
  }

  /**
   * Returns a string representation of the bird.
   * @return a string representation of the bird in the format type \n price \n color \n age \n gender \n name \n comment \n kennel status \n species \n preferred food
   */
  public String toString()
  {
    return super.toString() + "\n" + preferredFood;
  }
}
