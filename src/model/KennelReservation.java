package model;

import java.io.Serializable;

/**
 * A class containing representing a Kennel reservation with
 * a price for one day, a pet object, 2 date object(start and end date)
 * and a customer object.
 * @author Richard Vegh
 * @version 1.0
 */
public class KennelReservation implements Serializable
{
  private Pet pet;
  private Date startDate, endDate;
  private Customer customer;
  private double pricePerDay;

  /**
   * five-argument constructor initializing kennelReservation, assuming
   * the price is set by the method(not using the initial 20)
   * @param customer Costumer object about a set costumer
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @param pet Object about a pet
   * @param pricePerDay price in dollar for one day of booking
   */
  public KennelReservation(Pet pet,Customer customer,Date startDate, Date endDate, double pricePerDay)
  {
    this.customer = customer;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pet = pet;
    this.pricePerDay = pricePerDay;
  }

  /**
   * five-argument constructor initializing kennelReservation, assuming
   * the price is set by the method(not using the initial 20) and is a String
   * @param customer Costumer object about a set costumer
   * @param startDate qDate object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @param pet Object about a pet
   * @param pricePerDay price in dollar for one day of booking
   */
  public KennelReservation(Pet pet,Customer customer,Date startDate, Date endDate, String pricePerDay)
  {
    this.customer = customer;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pet = pet;
    this.pricePerDay = Double.parseDouble(pricePerDay);
  }

  /**
   * method for giving access to Pet object
   * @return Pet object
   */
  public Pet getPet()
  {
    return pet;
  }

  /**
   * method for giving access to Customer object
   * @return Customer Object
   */
  public Customer getCustomer()
  {
    return customer;
  }

  /**
   * method for giving access to startdate
   * @return start date as Date object
   */
  public Date getStartDate()
  {
    return startDate;
  }

  /**
   * method for giving access to end date
   * @return end date as Date object
   */
  public Date getEndDate()
  {
    return endDate;
  }

  /**
   * method for calculating the days between the start date and end date
   * in kennelReservation, works by calling static method in Date class
   * @return the days between the time period
   */
  public int getPeriod()
  {
    return Date.calculatePeriod(startDate,endDate);
  }
  /**
   * returning the price for set period
   * @return price for one day multiplied by the duration in days
   */
  public double getFinalPrice()
  {
    return getPeriod()*pricePerDay;
  }

  /**
   * method for giving access to price per day value inside class
   * @return pricePerDay integer
   */
  public double getPrice()
  {
    return pricePerDay;
  }

  /**
   * Compares price per day value, Pet class, start and end date
   * as Date Class and Customer class between two Kennel Reservations.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this customer
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    KennelReservation other = (KennelReservation) obj;

    return pricePerDay == other.pricePerDay&&
        pet.equals(other.pet)&&
        startDate.equals(other.startDate)&&
        endDate.equals(other.endDate)&&
        customer.equals(other.customer);
  }

  /**
   * toString method for kennel reservation
   * @return A string with the initial values of the class, separated by ::
   */
  public String toString()
  {
    return startDate+"::"+endDate;
  }
}
