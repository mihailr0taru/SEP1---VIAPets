package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Sale Objects
 * @author Carolina Valencia Zavidei
 * @version 1.0
 */
public class SaleList implements Serializable
{
  private ArrayList<Sale> sales;

  /**
   * No-argument constructor initializing the SaleList.
   */
  public SaleList()
  {
    sales = new ArrayList<>();
  }

  /**
   * One-argument constructor initializing the SaleList with a given list of sales.
   * @param saleList an ArrayList of Sale objects to initalize the list.
   */
  public SaleList(ArrayList<Sale> saleList)
  {
    sales = saleList;
  }

  /**
   * Adds a Sale to the list.
   * @param sale the Sale to add to the list.
   */
  public void addSale(Sale sale)
  {
    sales.add(sale);
  }

  /**
   * Removes a Sale from the list
   * @param sale the Sale object to remove from the list.
   */
  public void removeSale(Sale sale) {
    for(int i =0; i < sales.size(); i++)
    {
      Sale temp = sales.get(i);
      if(temp.getPet().equals(sale.getPet()))
      {
        sales.remove(i);
        break;
      }
    }
  }

  /**
   * Returns the size of the sales list.
   * @return the number of Sale objects in the list.
   */
  public int size()
  {
    return sales.size();
  }

  /**
   *
   * @param index the index of the Sale object to retrieve.
   * @return the Sale object at the specified index, or null if the index is invalid.
   */
  public Sale get(int index)
  {
    if(index < sales.size())
    {
      return sales.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns a string representation of the SaleList.
   * @return a string representation of the list of sales.
   */
  public String toString()
  {
    return "All sales: " + sales;
  }
}
