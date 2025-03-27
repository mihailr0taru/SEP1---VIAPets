package model;

import Exeptions.IllegalFirstNameException;
import Exeptions.IllegalLastNameException;
import Exeptions.InvalidNameException;

import java.io.Serializable;

/**
 * A class representing a customer with first name, last name, phone number, and email address.
 * @author Patrik Golian
 * @version 1.0
 */
public class Customer implements Serializable {

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String emailAddress;

  /**
   * Four-argument constructor.
   * @param firstName the customer's first name
   * @param lastName the customer's last name
   * @param phoneNumber the customer's phone number
   * @param emailAddress the customer's email address
   */
  public Customer(String firstName, String lastName, String phoneNumber, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  /**
   * Gets the customer's first name.
   * @return the customer's first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the customer's last name.
   * @return the customer's last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the customer's full name, consisting of the first name and last name.
   * @return the customer's full name
   */
  public String getName() {
    return firstName + " " + lastName;
  }

  /**
   * Checks if the given name matches the customer's full name.
   * @param name the name to check
   * @return true if the name matches the customer's full name, otherwise false
   */
  public boolean isEqual(String name) {
    return name.equals(getName());
  }

  /**
   * Gets the customer's phone number.
   * @return the customer's phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Gets the customer's email address.
   * @return the customer's email address
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets the customer's last name., the name must be at least 3 characters long.
   * @param firstName what the customer's first name will be set to
   * @throws InvalidNameException if the first name is null or shorter than 3 characters
   * @throws IllegalFirstNameException if the first name does not contain only letters
   */
  public void setFirstName(String firstName) {
    if (firstName == null || firstName.length() < 3) {
      throw new InvalidNameException("First name must be at least 3 characters long.");
    }

    if (!firstName.matches("[a-zA-z ]+"))
    {
      throw new IllegalFirstNameException("The first name can only contain letters.");
    }
    this.firstName = firstName;
  }

  /**
   * Sets the customer's last name.
   * @param lastName what the customer's last name will be set to
   * @throws IllegalLastNameException if the last name does not contain only letters
   */
  public void setLastName(String lastName) {
    if (!lastName.matches("[a-zA-z ]+"))
    {
      throw new IllegalLastNameException("The last name can only contain letters");
    }
    this.lastName = lastName;
  }

  /**
   * Sets the customer's phone number, the phone number must only contain digits.
   * @param phoneNumber what the customer's phone number will be set to
   * @throws IllegalArgumentException if the phone number contains something else than just digits
   */
  public void setPhoneNumber(String phoneNumber) {
    if (!phoneNumber.matches("\\d+")) {
      throw new IllegalArgumentException("Phone number can only contain numbers.");
    }
    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets the customer's email address, the email must follow a valid format.
   * @param emailAddress what the customer's email address  will be set to
   * @throws IllegalArgumentException if the email format is invalid
   */
  public void setEmailAddress(String emailAddress) {
    if (!emailAddress.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
      throw new IllegalArgumentException("Invalid email format. Expected format: user@host.domain.");
    }
    this.emailAddress = emailAddress;
  }

  /**
   * Compares first name, last name, phone number and email address of two customers.
   * @param obj the object to compare with
   * @return  true if the given object is equal to this customer
   */
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Customer other = (Customer) obj;
    return firstName.equals(other.firstName) &&
        lastName.equals(other.lastName) &&
        phoneNumber.equals(other.phoneNumber) &&
        emailAddress.equals(other.emailAddress);
  }

  /**
   * Returns a string representation of the customer.
   * @return a string representation of the customer in the format: "first name  last name phone number and email address"
   */
  public String toString() {
    return firstName + " " + lastName + " " + phoneNumber + " " + emailAddress;
  }
}



