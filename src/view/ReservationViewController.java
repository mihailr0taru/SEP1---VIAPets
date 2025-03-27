package view;

import ModelManager.VIAPetsModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;

import java.util.NoSuchElementException;

public class ReservationViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button backButton;
  @FXML private Button addNewButton;
  @FXML private Button deleteButton;

  //initializing sale table
  @FXML private TableView<KennelReservation> KennelTableView = new TableView<>();
  @FXML private TableColumn<KennelReservation, String> costumerColumn = new TableColumn<>("CostumerNameColumn");
  @FXML private TableColumn<KennelReservation, String> startDateColumn = new TableColumn<>("StartDateColumn");
  @FXML private TableColumn<KennelReservation, String> endDateColumn = new TableColumn<>("EndDateColumn");
  @FXML private TableColumn<KennelReservation, String> priceColumn = new TableColumn<>("PriceColumn");
  private Pet pet;
  private Customer customer;
  private Date start;
  private Date end;
  private double price;
  private KennelReservation selectedReservation;

  @FXML private TextField petTypeField= new TextField();
  @FXML private TextField petNameField= new TextField();
  @FXML private TextField petAgeField= new TextField();
  @FXML private TextField petColorField= new TextField();
  @FXML private TextField petGenderField= new TextField();
  @FXML private TextField petCommentField= new TextField();
  @FXML private TextField petSpeciesField = new TextField();
  @FXML private Label petSpeciesLabel = new Label();

  @FXML private  TextField searchField = new TextField();

  //specific pet data
  @FXML private Label petSpec1Label = new Label();
  @FXML private Label petSpec2Label = new Label();
  @FXML private TextField petSpec1Field = new TextField();
  @FXML private TextField petSpec2Field = new TextField();

  public void init(ViewHandler viewHandler, Scene scene, VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    searchField.setPromptText("Search by Customer Phone number");
    searchField.clear();

    this.scene.getStylesheets().add(getClass().getResource("ReservationView.css").toExternalForm());
    //reservation table;
    costumerColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCustomer().getFirstName())
    );

    startDateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getStartDate().toString())
    );
    endDateColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getEndDate().toString())
    );
    priceColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(Double.toString(cellData.getValue().getFinalPrice()))
    );


    petTypeField.setEditable(false);
    petNameField.setEditable(false);
    petAgeField.setEditable(false);
    petColorField.setEditable(false);
    petGenderField.setEditable(false);
    petCommentField.setEditable(false);
    petSpeciesField.setEditable(false);
    petSpec1Field.setEditable(false);
    petSpec1Field.setEditable(false);

    costumerColumn.setSortable(false);
    startDateColumn.setSortable(false);
    endDateColumn.setSortable(false);
    priceColumn.setSortable(false);
    petSpec1Label.setVisible(false);
    petSpec1Field.setVisible(false);
    petSpec2Field.setVisible(false);
    petSpec2Label.setVisible(false);
    TableView.TableViewSelectionModel<KennelReservation> selectionModel =
        KennelTableView.getSelectionModel();
    ObservableList<KennelReservation> selectedItems =
        selectionModel.getSelectedItems();

    selectedItems.addListener(
        new ListChangeListener<KennelReservation>() {
          @Override
          public void onChanged(
              Change<? extends KennelReservation> change) {
            try
            {
              KennelReservation temp = KennelTableView.getSelectionModel().getSelectedItem();
              if (temp != null)
              {
                selectedReservation = temp;



                switch (temp.getPet())
                {
                  case Cat cat:
                    pet = new Cat(cat);


                    if(temp.getPet().getGender().equals("Male")){
                      pet.isMale();
                    }
                    else if (temp.getPet().getGender().equals("Female"))
                    {
                      pet.isFemale();
                    }
                    petSpec1Field.setText(cat.getNameOfBreeder());
                    petSpec1Label.setText("Breeder");
                    petSpeciesLabel.setText("Breed");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  case Fish fish:
                    pet = new Fish(fish);
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
                    petSpeciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(true);
                    petSpec2Label.setVisible(true);
                    break;
                  case Dog dog:
                    pet = new Dog(dog);
                    petSpec1Field.setText(dog.getNameOfBreeder());
                    petSpec1Label.setText("Breeder");
                    petSpeciesLabel.setText("Breed");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  case Rodent rodent:
                    pet = new Rodent(rodent);
                    if(rodent.doesItBite()){
                      petSpec1Field.setText("yes");
                  } else petSpec1Field.setText("no");
                    petSpec1Label.setText("Bite");
                    petSpeciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  case Bird bird:
                    pet = new Bird(bird);
                    petSpec1Field.setText(bird.getPreferredFood());
                    petSpec1Label.setText("Food");
                    petSpeciesLabel.setText("Species");
                    petSpec1Label.setVisible(true);
                    petSpec1Field.setVisible(true);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  case Various various:
                    pet = new Various(various);
                    petSpeciesLabel.setText("Species");
                    petSpec1Label.setVisible(false);
                    petSpec1Field.setVisible(false);
                    petSpec2Field.setVisible(false);
                    petSpec2Label.setVisible(false);
                    break;
                  default:
                    System.err.println("Pet tpe is not correct");
                }
                customer = temp.getCustomer();
                start = temp.getStartDate();
                end = temp.getEndDate();
                price = temp.getPrice();
                selectedReservation = new KennelReservation(pet,customer,start,end,price);

                petNameField.setText(pet.getName());
                petGenderField.setText(pet.getGender());
                petCommentField.setText(pet.getComment());
                petTypeField.setText(pet.getTypeString());
                petAgeField.setText(String.valueOf(pet.getAge()));
                petColorField.setText(pet.getColor());
                petSpeciesField.setText(pet.getSpecies());
              }
            }
            catch (NullPointerException e)
            {
              System.out.println("no elements found");
            }
            catch (NoSuchElementException e)
            {
              System.out.println("petTableView, pet changed");
            }
          }
        });

    updateTable();
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updateTable();
    selectedReservation=null;
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
    else if (e.getSource() ==  searchField)
    {
      String query = searchField.getText().trim();
      if (query.isEmpty())
      {
        updateTable();
      }
      else
      {
        updateTable(query);
      }
    }
    else if (e.getSource() == addNewButton)
    {
      viewHandler.openView("AddReservationView");
    }
    else if(e.getSource() == deleteButton)
    {
      if (selectedReservation!=null)
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Do you really want to delete it?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Back");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
          modelManager.getReservedPets().removePet(selectedReservation.getPet());
          modelManager.removeReservation(selectedReservation);
          updateTable();
        }
      }

    }
  }

  //method for updating the table
  public void updateTable()
  {
    //Check if the table view is not null
    if (KennelTableView != null)
    {
      //Clear all items in the table view
      KennelTableView.getItems().clear();
    }

    // Retrieve all kennel reservations from the model manager
    KennelReservationList reservations = modelManager.getAllReservations();

    // Iterate through each reservation in the list
    for (int i = 0; i < reservations.size(); i++)
    {
      // Add the reservation to the table view
      KennelTableView.getItems().add(reservations.get(i));
    }
  }
  public void updateTable(String phone)
  {
    // Check if the table view is not null
    if (KennelTableView != null)
    {
      // Clear all items in the table view
      KennelTableView.getItems().clear();
    }

    // Retrieve all kennel reservations from the model manager
    KennelReservationList reservations = modelManager.getAllReservations();

    // Iterate through each reservation in the list
    for (int i = 0; i < reservations.size(); i++)
    {
      // Check if the reservation's customer phone number matches the provided phone number
      if (reservations.get(i).getCustomer().getPhoneNumber().equals(phone))
      {
        // Add the matching reservation to the table view
        KennelTableView.getItems().add(reservations.get(i));
      }
    }
  }
}
