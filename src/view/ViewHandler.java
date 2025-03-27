package view;

import ModelManager.VIAPetsModelManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;

  private Scene currentScene;

  private view.mainViewController mainViewController;
  //initialize customer controllers
  private ManageCustomerViewController manageCustomerViewController;
  private AddNewCustomerViewController addNewCustomerViewController;
  //initialize sale controllers
  private AddSaleViewController addSaleViewController;
  private SaleViewController saleViewController;
  //initialize kennel controllers
  private AddReservationViewController addReservationViewController;
  private ReservationViewController reservationViewController;
  //initialize pet controllers
  private AddPetViewController addPetViewController;
  private PetViewController petViewController;

  private VIAPetsModelManager modelManager;

  public ViewHandler(Stage stage, VIAPetsModelManager modelManager)
  {
    this.stage = stage;
    this.modelManager = modelManager;
  }

  public void start()
  {
    loadViewMain();
//    load customer
    loadManageCustomerView();
    loadAddNewCustomerView();
//    load sale
    loadAddSaleView();
    loadSaleView();
//    load reservation
    loadReservationView();
    loadAddReservationView();
//    load pet
    loadPetView();
    loadAddPetView();

    openView("MainView");

  }

  public void openView(String id)
  {
    switch (id)
    {
      case "MainView":
        stage.setScene(mainViewController.getScene());
        mainViewController.reset();
        break;
      case "ManageCustomerView": //opens first(Customer button)
        stage.setScene(manageCustomerViewController.getScene());
        manageCustomerViewController.reset();
        break;
      case "AddNewCustomerView":
        stage.setScene(addNewCustomerViewController.getScene());
        addNewCustomerViewController.reset();
        break;
      case "SaleView": //opens first(Sale button)
        stage.setScene(saleViewController.getScene());
        saleViewController.reset();
        break;
      case "AddSaleView":
        stage.setScene(addSaleViewController.getScene());
        addSaleViewController.reset();
        break;
      case "ReservationView": //opens first(Sale button)
        stage.setScene(reservationViewController.getScene());
        reservationViewController.reset();
        break;
      case "AddReservationView":
        stage.setScene(addReservationViewController.getScene());
        addReservationViewController.reset();
        break;
      case "PetView": //opens first(Sale button)
        stage.setScene(petViewController.getScene());
        petViewController.reset();
        break;
      case "AddPetView":
        stage.setScene(addPetViewController.getScene());
        addPetViewController.reset();
        break;
    }

    String title = "VIAPets";

    if(stage.getScene().getRoot().getUserData() != null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
    }

    stage.setTitle(title);
   // stage.setScene(currentScene);
    stage.show();
  }

  private void loadViewMain()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MainView.fxml"));
      Region root = loader.load();
      mainViewController = loader.getController();
      mainViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
//load View pet/customer/sale/reservation
  //Customer ManageCustomerView(customer1), AddNewCustomer(customer2)
  private void loadManageCustomerView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ManageCustomerView.fxml"));
      Region root = loader.load();
      manageCustomerViewController = loader.getController();
      manageCustomerViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadAddNewCustomerView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddNewCustomerView.fxml"));
      Region root = loader.load();
      addNewCustomerViewController = loader.getController();
      addNewCustomerViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  //Sale AddSaleView(sale1), SaleView(sale2)
  private void loadAddSaleView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddSaleView.fxml"));
      Region root = loader.load();
      addSaleViewController = loader.getController();
      addSaleViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadSaleView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SaleView.fxml"));
      Region root = loader.load();
      saleViewController = loader.getController();
      saleViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  //Reservation AddReservationView(reservation1), ReservationView(reservation2)
  private void loadAddReservationView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddReservationView.fxml"));
      Region root = loader.load();
      addReservationViewController = loader.getController();
      addReservationViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadReservationView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ReservationView.fxml"));
      Region root = loader.load();
      reservationViewController = loader.getController();
      reservationViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  //Pet AddPetView(pet1), PetView(pet2)
  private void loadAddPetView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddPetView.fxml"));
      Region root = loader.load();
      addPetViewController = loader.getController();
      addPetViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadPetView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("PetView.fxml"));
      Region root = loader.load();
      petViewController = loader.getController();
      petViewController.init(this, new Scene(root),modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}