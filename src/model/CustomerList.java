package model;

import Exeptions.IllegalEmailException;
import Exeptions.IllegalPhoneNumberException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Customer objects.
 * @author Patrik Golian
 * @version 1.0
 */

public class CustomerList implements Serializable
{
  private ArrayList<Customer> customers;

  /**
   * No-argument constructor initializing the CustomerList.
   */
  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }

  /**
   * Adds a Customer to the list after validating that the email address and phone number are unique.
   * If either is already in the system, an exception is thrown
   * @param customer the Customer object to add to the list
   * @throws IllegalEmailException if the email address already exists in the system
   * @throws IllegalPhoneNumberException if the phone number already exists in the system
   */
  //adding a new Customer object to the customerList array list
  public void addCustomer(Customer customer)
  {
    //For each element in the list
    for(int i = 0; i < customers.size(); i++)
    {
      //initializing the current customer
      Customer customer1 = customers.get(i);
      //checking the current customer's email if matches the desired one
      if(customer1.getEmailAddress().equals(customer.getEmailAddress()))
      {
        //if the argument is true break the process with throwing an argument
        throw new IllegalEmailException("The email address matches other email address in the system, enter other email address.");
      }
      //checking the current customer's phone if matches the desired one
      else if(customer1.getPhoneNumber().equals(customer.getPhoneNumber()))
      {
        //if the argument is true break the process with throwing an argument
        throw new IllegalPhoneNumberException("The phone number matches other phone number in the system, enter other phone number.");
      }
    }
    //adding the desired customer to the list
    customers.add(customer);
  }
  /**
   * Removes a specified Customer from the list, if there is one.
   * @param customer the Customer object to remove from the list
   */
  public void removeCustomer(Customer customer)
  {
    for(int i =0; i < customers.size(); i++)
    {
      Customer temp = customers.get(i);
      if(temp.equals(customer))
      {
        customers.remove(customer);
      }
    }

  }


  /**
   * Returns the number of Customer objects in the list.
   * @return the size of the customer list
   */
  public int size()
  {
    return customers.size();
  }

  /**
   * Gets a Customer object from position index from the list.
   * @param index  the position in the list of the Customer object
   * @return the Customer object at position index if one exists, else null
   */
  public Customer get(int index)
  {
    if(index<customers.size())
    {
      return customers.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns a string representation of the CustomerList.
   * @return the list of Customer objects as String
   */
  public String toString()
  {
    String returnStr = "";

    for(int i = 0; i<customers.size(); i++)
    {
      Customer temp = customers.get(i);

      returnStr += temp +"\n";
    }
    return returnStr;
  }
}
