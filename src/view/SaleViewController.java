package view;

import ModelManager.VIAPetsModelManager;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import model.*;

import java.util.NoSuchElementException;

public class SaleViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField typeField = new TextField();
  @FXML private TextField nameField = new TextField();
  @FXML private TextField ageField = new TextField();
  @FXML private TextField colorField = new TextField();
  @FXML private TextField priceField = new TextField();
  @FXML private TextField genderField = new TextField();
  @FXML private TextField commentField = new TextField();
  @FXML private TextField speciesField = new TextField();
  @FXML private Label speciesLabel = new Label();
  @FXML private TextField searchField = new TextField();
  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  //initializing sale table
  @FXML private TableView<Sale> SaleTableView = new TableView<>();
  @FXML private TableColumn<Sale, String> customerNameColumn;
  @FXML private TableColumn<Sale, String> customerPhoneColumn;
  @FXML private TableColumn<Sale, String> dateColumn;
  @FXML private TableColumn<Sale, String> priceColumn;

  private Sale selectedSale;
  private Pet pet;

  //specific pet data
  @FXML private Label petSpec1Label = new Label();
  @FXML private Label petSpec2Label = new Label();
  @FXML private TextField petSpec1Field = new TextField();
  @FXML private TextField petSpec2Field = new TextField();

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    searchField.setPromptText("Search by Customer Phone number");
    searchField.clear();

    typeField.setEditable(false);
    nameField.setEditable(false);
    ageField.setEditable(false);
    colorField.setEditable(false);
    priceField.setEditable(false);
    genderField.setEditable(false);
    commentField.setEditable(false);
    speciesField.setEditable(false);
    petSpec1Field.setEditable(false);
    petSpec1Field.setEditable(false);

    this.scene.getStylesheets().add(getClass().getResource("SaleView.css").toExternalForm());


    this.scene.getStylesheets()
        .add(getClass().getResource("SaleView.css").toExternalForm());

    //sale table
    customerNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getFirstName())
    );

    customerPhoneColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getPhoneNumber())
    );

    dateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDateOfSale().toString())
    );

    priceColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(String.format("%.2f", cellData.getValue().getFinalPrice()))
    );

    customerNameColumn.setSortable(false);
    customerPhoneColumn.setSortable(false);
    dateColumn.setSortable(false);
    priceColumn.setSortable(false);
    petSpec1Label.setVisible(false);
    petSpec1Field.setVisible(false);
    petSpec2Field.setVisible(false);
    petSpec2Label.setVisible(false);

    //initializing table view, using built in classes in java to get access
    // to all the selected items
    TableView.TableViewSelectionModel<Sale> selectionModel = SaleTableView.getSelectionModel();
    ObservableList<Sale> selectedItems = selectionModel.getSelectedItems();

    //implementing listener to the Observative list of selected items
    selectedItems.addListener(
        //initializing function
        new ListChangeListener<Sale>() {
          //using void statement, from superclass
          @Override
          public void onChanged(
              //importing Change class, not to directly change the source
              Change<? extends Sale> change) {
            //starting the process, knowing it can throw exeptions (NullPointException or NoSuchElementException)
            try
            {
              //creating a temporary Sale class, to only ask for the element once
              Sale temp = change.getList().getFirst();
              //checking for being the element null, if not continue
              if (temp != null)
              {
                //creating a NEW sale class, matching with the original,
                // and storing it to get access to it in other methods
                selectedSale = new Sale(temp.getPet(),temp.getCustomer(),temp.getDateOfSale(),temp.getFinalPrice());
//              selectedSale = temp;

                //checking for pet Types.
                // Different types has extra information to display
                switch (temp.getPet())
                {
                  //in case it is a cat
                  case Cat cat:
                    //saving and casting it as a cat class
                    pet = new Cat(cat);


                    if(temp.getPet().getGender().equals("Male")){
                      pet.isMale();
                    }
                    else if (temp.getPet().getGender().equals("Female"))
                    {
                      pet.isFemale();
                    }
                    //setting up specific fields according to the class
                    petSpec1Field.setText(cat.getNameOfBreeder());
                    petSpec1Label.setText("Breeder");
                    speciesLabel.setText("Breed");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  //in case of fish class
                  case Fish fish:
                    //saving and casting it as a fish object
                    pet = new Fish(fish);
                    //setting up specific fields according to the class
                    if (fish.isItPredator())
                    {
                      petSpec1Field.setText("yes");
                    }else petSpec1Field.setText("no");
                    petSpec1Label.setText("Predator");
                    if(fish.isItSaltWater())
                    {
                      petSpec2Field.setText("Salt Water");
                    }else petSpec1Field.setText("Fresh Water");
                    petSpec2Label.setText("Water Type");
                    speciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(true);
                    petSpec2Label.setVisible(true);
                    break;
                  //in case of dog
                  case Dog dog:
                    //saving and casting it as a dog class
                    pet = new Dog(dog);
                    //setting up specific fields according to the class
                    petSpec1Field.setText(dog.getNameOfBreeder());
                    petSpec1Label.setText("Breeder");
                    speciesLabel.setText("Breed");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  //in case it is rodent class
                  case Rodent rodent:
                    //saving and casting it as a rodent class
                    pet = new Rodent(rodent);
                    //setting up specific fields according to the class
                    if(rodent.doesItBite()){
                      petSpec1Field.setText("yes");
                    } else petSpec1Field.setText("no");
                    petSpec1Label.setText("Bite");
                    speciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  //in case for bird class
                  case Bird bird:
                    //saving and casting it as a cat class
                    pet = new Bird(bird);
                    //setting up specific fields according to the class
                    petSpec1Field.setText(bird.getPreferredFood());
                    petSpec1Label.setText("Food");
                    speciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  //if object is instance of various class
                  case Various various:
                    //saving and casting it as various class
                    pet = new Various(various);
                    //setting up specific fields according to the class
                    speciesLabel.setText("Species");
                    petSpec1Label.setVisible(false);
                    petSpec1Field.setVisible(false);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  //if the class is not the ones above,
                  // means it is not initialized as desired classes
                  default:
                    //throwing error message
                    System.err.println("Pet type is not correct");
                }
                //initializing common field's texts what all the pet classes have information for
                typeField.setText(temp.getPet().getTypeString());
                nameField.setText(temp.getPet().getName());
                ageField.setText(String.valueOf(temp.getPet().getAge()));
                colorField.setText(temp.getPet().getColor());
                priceField.setText(String.valueOf(temp.getPet().getPrice()));
                genderField.setText(temp.getPet().getGender());
                commentField.setText(temp.getPet().getComment());
                speciesField.setText(temp.getPet().getSpecies());
              }
            }
            //catching NullPointerException
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            //if the element could not be initialized catches NoSuchElementException
            catch (NoSuchElementException e)
            {
              System.out.println("customerTableView, customer changed");
            }
          }
        });
    //calling method for updating the table
    updateSaleTable();
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateSaleTable();
    searchField.setPromptText("Search by Customer Phone number");
    searchField.clear();
    modelManager.save();
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == backButton)
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
    else if (e.getSource() == addNewButton)
    {
      viewHandler.openView("AddSaleView");
    }
    else if (e.getSource() == searchField)
    {
      String query = searchField.getText().trim();
      if (query.isEmpty())
      {
        updateSaleTable();
      }
      else
      {
        updateSaleTable(query);
      }
    }
    else if (e.getSource() == deleteButton)
    {
      if (selectedSale != null)
      {
        System.out.println("Attempting to delete sale ");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you really want to remove the sale?", ButtonType.YES,
            ButtonType.NO);
        alert.setTitle("Delete Sale");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
          // Remove the selected sale from the model
          modelManager.getAllSales().removeSale(selectedSale);

          // Debug: Print the sales list after deletion
//          System.out.println("Sales list after deletion:");
//          SaleList sales = modelManager.getAllSales();
//          for (int i = 0; i < sales.size(); i++) {
//            Sale sale = sales.get(i);
//            System.out.println(sale);
//          }
          selectedSale = null;
          // Refresh the table
          updateSaleTable();

          // Clear input fields
          typeField.setText("");
          nameField.setText("");
          ageField.setText("");
          colorField.setText("");
          priceField.setText("");
          genderField.setText("");
          commentField.setText("");
          speciesField.setText("");
          petSpec1Field.setText("");
          petSpec2Field.setText("");

          // Clear selected sale reference


          // Show success message
          Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
              "The sale has been successfully removed.", ButtonType.OK);
          successAlert.setTitle("Sale Removed");
          successAlert.setHeaderText(null);
          successAlert.showAndWait();
        }
      }
      else
      {
        // Show warning if no sale is selected
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Please select a sale to delete.", ButtonType.OK);
        alert.setTitle("No Sale Selected");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
    }
  }

  private void updateSaleTable() {

    if (SaleTableView != null)
    {
      // Clear the current table data
      SaleTableView.getItems().clear();
    }
    // Fetch updated sales from the model and repopulate the table
    SaleList sales = modelManager.getAllSales(); // Ensure this gives the latest list
    for (int i = 0; i < sales.size(); i++)
    {
      SaleTableView.getItems().add(sales.get(i));
    }
  }

  private void updateSaleTable(String phoneNr)
  {
    if (SaleTableView != null)
    {
      SaleTableView.getItems().clear();
    }

    SaleList sales = modelManager.getAllSales();
    for (int i =0; i< sales.size();i++ )
    {
      Sale sale = sales.get(i);

      if (sale.getCustomer().getPhoneNumber().equals(phoneNr)) {
        SaleTableView.getItems().add(sale); // Add the matching sale to the table
      }
    }
  }
}


