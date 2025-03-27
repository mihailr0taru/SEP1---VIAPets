package model;

import java.io.Serializable;

public class VIAPets implements Serializable
{
  private SaleList saleList;
  private KennelReservationList kennelReservationList;
  private CustomerList customerList;
  private PetList petList;
  private final PetList reservedPets = new PetList();

  public VIAPets(SaleList saleList, KennelReservationList kennelReservationList, CustomerList customerList, PetList petList)
  {
    setSaleList(saleList);
    setKennelReservationList(kennelReservationList);
    setCustomerList(customerList);
    setPetList(petList);
  }
  public VIAPets()
  {
    saleList = new SaleList();
    kennelReservationList = new KennelReservationList();
    customerList = new CustomerList();
    petList = new PetList();
  }
 //set methods
  public void setCustomerList(CustomerList customerList)
  {
    this.customerList = customerList;
  }
  public void setKennelReservationList(
      KennelReservationList kennelReservationList)
  {
    this.kennelReservationList = kennelReservationList;
  }
  public void setPetList(PetList petList)
  {
    this.petList = petList;
  }
  public void setSaleList(SaleList saleList)
  {
    this.saleList = saleList;
  }
  //get methods
  public CustomerList getCustomerList()
  {
    return customerList;
  }
  public KennelReservationList getKennelReservationList()
  {
    return kennelReservationList;
  }
  public PetList getPetList()
  {
    return petList;
  }
  public SaleList getSaleList()
  {
    return saleList;
  }

  public PetList getReservedPets()
  {
    return reservedPets;
  }
}

