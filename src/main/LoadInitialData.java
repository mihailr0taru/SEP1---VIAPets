package main;

import ModelManager.VIAPetsModelManager;
import model.*;

import utils.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {


    VIAPets viaPets = new VIAPets();
    try
    {
      FileHandler.writeToBinaryFile("VIAPets.bin", viaPets);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }
    System.out.println("Done");

    VIAPetsModelManager modelManager = new VIAPetsModelManager("VIAPets.bin");

//    Cat temp = new Cat(123, "grey", 12,"greatdane");
//    temp.hasAccessToKennel();
//    temp.isInKennel();
//    modelManager.addPet(temp);
//    modelManager.addPet(new Dog(123, "grey", 12,"greatdane"));
//    modelManager.addCostumer(new Customer("Richard", "Vegh","9999", "ricky@gmail.com"));
//    modelManager.addCostumer(new Customer("Richad", "Vegh","999", "ricky3@gmail.com"));
//    modelManager.addCostumer(new Customer("Ricard", "Vegh","999912", "ricky2@gmail.com"));
//    modelManager.addCostumer(new Customer("Rihard", "Vegh","9999222", "ricky1@gmail.com"));
//    modelManager.addPet(new Cat(1,"grey",30,"mastiff"));
//
//    //Sale View Test
//    Customer customer1 = new Customer("Carolina", "Zavidei", "55221008", "caro@gmail.com");
//    modelManager.addCostumer(customer1);
//    Dog dog1 = new Dog(100, "yellow", 2, "labrador");
//    modelManager.addPet(dog1);
//    Sale sale1 = new Sale(dog1, customer1);
//    modelManager.addSale(sale1);


  }
}
