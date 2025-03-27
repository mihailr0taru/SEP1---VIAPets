package model;

import Exeptions.SamePetException;
import Exeptions.UnavailableReservationException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing the list of KennelReservation objects,
 * also value for maximum limit in a timeline
 * @author Richard Vegh
 * @version 1.0
 */
public class KennelReservationList implements Serializable
{
  private ArrayList<KennelReservation> kennelReservations;
  private int maxlimit = 10;

  /**
   * no-argument constructor used for setting the Kennel reservations over time not when created
   */
  public KennelReservationList()
  {
    kennelReservations = new ArrayList<KennelReservation>();
  }
  /**
   * method for adding a KennelReservation object to the list
   * @param kennelReservation a KennelReservation object
   */
  public void addReservation(KennelReservation kennelReservation, Date startdate, Date endDate){ //adding a reservation
    if(isReservationAvailable(startdate,endDate)){
      for(int i=0; i<kennelReservations.size(); i++)
      {
        KennelReservation reservation1 = kennelReservations.get(i);
        if (reservation1.getPet().equals(kennelReservation.getPet()))
        {
          throw new SamePetException();
        }

      }
      kennelReservations.add(kennelReservation);
    }
    else {
      throw new UnavailableReservationException();
    }

  }

  /**
   * method for removing KennelReservation object from the list
   * @param kennelReservation a KennelReservation object
   */
  public void removeReservation(KennelReservation kennelReservation)
  {
    for(int i =0; i < kennelReservations.size(); i++)
    {
      KennelReservation temp = kennelReservations.get(i);
      if(temp.getPet().equals(kennelReservation.getPet()))
      {
        kennelReservations.remove(i);
        break;
      }
    }
  }
  /**
   * method for calculating the number of reservations available in the given time
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @return number of available places for reservation during the given timeline
   */
  public int howMuchIsAvailable(Date startDate, Date endDate)
  {
    int howMuchIsInThatTime=0;
    for (int i = 0; i <kennelReservations.size() ; i++)
    {
      if (!(kennelReservations.get(i).getEndDate().isBefore(startDate))&& //if endDate(of reservation i) is not before startDate(of preferred value)
          kennelReservations.get(i).getStartDate().isBefore(endDate)) //and startDate(of reservation i) is before endDate(of preferred value)
      {
        howMuchIsInThatTime++;
      }
    }
    return maxlimit-howMuchIsInThatTime;
  }
  /**
   * method for checking if a reservation is available during the given timeline
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @return reservation available or not
   */
  public boolean isReservationAvailable(Date startDate, Date endDate)
  {
    return howMuchIsAvailable(startDate,endDate)>0;
  }

  /**
   * method for returning the number of the elements inside the kennel
   * @return kennel reservation array list's size
   */
  public int size()
  {
    return kennelReservations.size();
  }

  /**
   * Gets a KennelReservation object from position index from the list.
    * @param index  the position in the list of the KennelReservation object
   * @return the KennelReservation object at position index if one exists, else null
    */
  public KennelReservation get(int index)
  {
    if(index<kennelReservations.size())
    {
      return kennelReservations.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Adds a KennelReservation to the list.
   * @param newReservation the KennelReservation object to add to the list
   */
  public void addReservation(KennelReservation newReservation)
  {
    kennelReservations.add(newReservation);
  }

  /**
   * Returns a string representation of the KennelReservationList.
   * @return the list of Kennel reservation objects as String
   */
  public String toString() {

    return kennelReservations.toString();
  }


}
