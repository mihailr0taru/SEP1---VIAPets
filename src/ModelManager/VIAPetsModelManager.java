package ModelManager;

import model.*;
import utils.FileHandler;
import model.PetList;
import utils.XMLConvertor;

import java.io.FileNotFoundException;

import java.io.IOException;

public class VIAPetsModelManager
{
  private String fileName;
  private VIAPets viaPets;

  public VIAPetsModelManager(String fileName)
  {
    viaPets = new VIAPets();
    this.fileName = fileName;
    try
    {
      viaPets = (VIAPets) FileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }
  public void save()
  {
    try
    {
      FileHandler.writeToBinaryFile(fileName, viaPets);
      XMLConvertor.write(new VIAPetsModelManager(fileName));
      XMLConvertor.writeRes(new VIAPetsModelManager(fileName));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  //Pet Model Manager
  public PetList getAllPets()
  {
    return viaPets.getPetList();
  }

  public void addPet(Pet newPet)
  {
    PetList allPets = getAllPets();
    allPets.addPet(newPet);
    save();
  }

  public void removePet(Pet deletePet)
  {
    PetList allPets = getAllPets();
    allPets.removePet(deletePet);
    save();
  }

  // Sales Model Manager
  public SaleList getAllSales()
  {
    return viaPets.getSaleList();
  }

  public void addSale(Sale newSale)
  {
    SaleList allSales = getAllSales();
    allSales.addSale(newSale);
    save();
  }

  //CustomerModelManager

  public CustomerList getAllCustomers()
  {
    return viaPets.getCustomerList();
  }

  public void addCostumer(Customer newCustomer)
  {
    CustomerList allCostumer = getAllCustomers();
    allCostumer.addCustomer(newCustomer);
    save();
  }

  public void removeCustomer(Customer deleteCustomer)
  {
    getAllCustomers().removeCustomer(deleteCustomer);
    save();
  }




  //Kennel Reservation Model manager
  public KennelReservationList getAllReservations()
  {
    return viaPets.getKennelReservationList();
  }
  public void addReservation(KennelReservation newReservation)
  {
    KennelReservationList allReservations = getAllReservations();
    allReservations.addReservation(newReservation);
    save();
  }
  public void removeReservation(KennelReservation deleteReservation)
  {
    getAllReservations().removeReservation(deleteReservation);
    save();
  }

  public PetList getReservedPets()
  {
    return viaPets.getReservedPets();
  }



}
