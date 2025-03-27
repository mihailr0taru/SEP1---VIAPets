package utils;

import ModelManager.VIAPetsModelManager;
import model.KennelReservationList;
import model.PetList;
import parser.ParserException;
import parser.XmlJsonParser;

public class XMLConvertor {
  public static void write(VIAPetsModelManager manager)
  {
    PetList petList = manager.getAllPets();

    XmlJsonParser parser = new XmlJsonParser();

    //write the XML file
    try
    {
      parser.toXml(petList, "list.xml");
    }
    catch (ParserException e)
    {
      e.printStackTrace();
    }
  }

    public static void writeRes(VIAPetsModelManager manager)
    {
      KennelReservationList kennelReservationList = manager.getAllReservations();

      XmlJsonParser parser1 = new XmlJsonParser();

      //write the XML file
      try
      {
        parser1.toXml(kennelReservationList, "reservationList.xml");
      }
      catch (ParserException e)
      {
        e.printStackTrace();
      }

    }
}
