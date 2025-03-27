package view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ModelManager.VIAPetsModelManager;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerList;
import Exeptions.IllegalEmailException;
import Exeptions.IllegalPhoneNumberException;

import java.util.NoSuchElementException;

public class ManageCustomerViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;
  private Customer selectedCustomer;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNumberField;
  @FXML private TextField emailAddressField;
  @FXML private TextField searchField;
  @FXML private Button backButton;
  @FXML private Button editButton;
  @FXML private Button deleteButton;
  @FXML private Button manageButton;
  @FXML private Button addNewButton;
  @FXML private TableView<Customer> fleTableView = new TableView<>();
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> emailAddressColumn;



  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
    emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("emailAddress"));
    this.scene.getStylesheets().add(getClass().getResource("ManageCustomerView.css").toExternalForm());

    firstNameColumn.setSortable(false);
    lastNameColumn.setSortable(false);
    emailAddressColumn.setSortable(false);

    TableView.TableViewSelectionModel<Customer> selectionModel =
        fleTableView.getSelectionModel();
    ObservableList<Customer> selectedItems =
        selectionModel.getSelectedItems();

    selectedItems.addListener(
        new ListChangeListener<Customer>() {
          @Override
          public void onChanged(
              Change<? extends Customer> change) {
            try
            {
              Customer temp = change.getList().getFirst();
              if (temp != null)
              {
                selectedCustomer = new Customer(temp.getFirstName(),temp.getLastName(), temp.getPhoneNumber(), temp.getEmailAddress());
                firstNameField.setText(temp.getFirstName());
                lastNameField.setText(temp.getLastName());
                emailAddressField.setText(temp.getEmailAddress());
                phoneNumberField.setText(temp.getPhoneNumber());
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("Table view, customer changed");
            }
          }
        });

    updateTableView();
  }
  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateTableView();
    modelManager.save();
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource()== backButton)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to go back?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Back");
        alert.setHeaderText(null);

        alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        viewHandler.openView("MainView");
      }
    }

    else if(e.getSource()== addNewButton)
    {
      viewHandler.openView("AddNewCustomerView");
    }
    else if(e.getSource()== editButton)
    {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String phoneNumber = phoneNumberField.getText();
      String emailAddress = emailAddressField.getText();

      if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All fields must be filled out!");
        alert.showAndWait();
        return;
      }

      if (firstName == null || firstName.length() < 3)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("First name must be at least 3 characters long.");
        alert.showAndWait();
        return;
      }

      if (!phoneNumber.matches("\\d+"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Phone number must contain only digits!");
        alert.showAndWait();
        return;
      }

      if (!emailAddress.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid email format. Expected format: user@host.domain.");
        alert.showAndWait();
        return;
      }

      if (!firstName.matches("[a-zA-z ]+"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("First name can contain only letters!");
        alert.showAndWait();
        return;
      }

      if (!lastName.matches("[a-zA-z ]+"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Last name can contain only letters!");
        alert.showAndWait();
        return;
      }

      try
      {
        Customer customer = new Customer(firstName,lastName,phoneNumber,emailAddress);
        modelManager.removeCustomer(selectedCustomer);
        modelManager.addCostumer(customer);
        updateTableView();
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailAddressField.setText("");
      }
      catch (IllegalEmailException illegalEmailException){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The email address matches other email address in the system, enter other email address.");
        alert.showAndWait();
        return;
      }
      catch(IllegalPhoneNumberException illegalPhoneNumberException)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The phone number matches other phone number in the system, enter other phone number.");
        alert.showAndWait();
        return;
      }
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Changed!");
      alert.setHeaderText(null);
      alert.setContentText("The customer data has been changed.");
      alert.showAndWait();
    }

    else if(e.getSource() == deleteButton)
    {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String phoneNumber = phoneNumberField.getText();
      String emailAddress = emailAddressField.getText();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to delete?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Delete");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Deleted!");
        alert1.setHeaderText(null);
        alert1.setContentText("The customer has been deleted.");
        alert1.showAndWait();
        Customer customer = new Customer(firstName,lastName,phoneNumber,emailAddress);
        modelManager.removeCustomer(customer);
        updateTableView();
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailAddressField.setText("");
      }
    }
    else if (e.getSource() == fleTableView)
    {
      Customer temp = (Customer) fleTableView.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        emailAddressField.setText(temp.getEmailAddress());
      }
    }
    else if (e.getSource() == searchField)
    {
      String query = searchField.getText();
      if (query.isEmpty()) {
        updateTableView();
      }
      else
      {
        updateTableView(query);
      }
    }
  }

  private void updateTableView()
  {
    if (fleTableView != null)
    {
      fleTableView.getItems().clear();
    }
    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }
  }

  private void updateTableView(String name)
  {
    if (fleTableView != null)
    {
      fleTableView.getItems().clear();
    }

    CustomerList customers = modelManager.getAllCustomers();
    for (int i =0; i< customers.size();i++ )
    {
      Customer customer = customers.get(i);

      if (customer.getFirstName().equals(name) || customer.getLastName().equals(name))
      {
        fleTableView.getItems().add(customer);
      }
    }
  }

}
/*if (fleTableView != null)
    {
      fleTableView.getItems().clear();
    }
    CustomerList customers = modelManager.getCustomerByName(name);
    for (int i = 0; i < customers.size(); i++)
    {
      fleTableView.getItems().add(customers.get(i));
    }*/


