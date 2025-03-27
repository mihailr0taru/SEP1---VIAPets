package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Pet objects.
 * @author Mihail Rotaru
 * @version 1.0
 */
public class PetList implements Serializable
{
  private ArrayList<Pet> pets;

  /**
   * No-argument constructor initializing the PetList.
   */
  public PetList()
  {
    pets = new ArrayList<>();
  }

  /**
   * Adds a Pet to the list.
   * @param pet the pet to add to the list
   */
  public void addPet(Pet pet)
  {
    pets.add(pet);
  }

  /**
   * Removes a Pet to the list.
   * @param pet the pet to removed from the list
   */
  public void removePet(Pet pet)
  {
    pets.remove(pet);
  }

  /**
   * Returns the number of Pet objects in the list.
   * @return the size of the pet list
   */
  public int size()
  {
    return pets.size();
  }

  /**
   * Gets a Pet object from position index from the list.
   * @param index the position in the list of the Pet object
   * @return the Pet object at position index if one exists, else null
   */
  public Pet get(int index)
  {
    if(index<pets.size())
    {
      return pets.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Searches for pets in the list by its type.
   * @param type the type that is searched for
   * @return A PetList containing all pets having this type
   */
  public PetList getPetsByType(int type)
  {
    PetList temp = new PetList();
    for (int i = 0; i < pets.size(); i++)
    {
      if (pets.get(i).getType() == type)
      {
        temp.pets.add(pets.get(i));
      }
    }
    return temp;
  }

  /**
   * Searches for pets in the list by its color.
   * @param color the color that is searched for
   * @return a PetList containing all pets having this color
   */
  public PetList getPetsByColor(String color)
  {
    PetList temp = new PetList();
    for (int i = 0; i < pets.size(); i++)
    {
      if (pets.get(i).getColor().equals(color))
      {
        temp.pets.add(pets.get(i));
      }
    }
    return temp;
  }

  /**
   * Searches for pets in the list by its species.
   * @param species the species that is searched for
   * @return a PetList containing all pets having this species
   */
  public PetList getPetsBySpecies(String species)
  {
    PetList temp = new PetList();
    for (Pet pet : pets)
    {
      if (pet.getSpecies().equals(species))
      {
        temp.addPet(pet);
      }
    }
    return temp;
  }

  /**
   * Returns a string representation of the PetList.
   * @return the list of Pet objects as String
   */
  public String toString()
  {
    String temp = "";
    for (Pet pet : pets)
    {
      temp += pet.toString() + "\n\n";
    }
    return temp;
  }
}
