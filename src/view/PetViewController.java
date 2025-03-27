package view;

import Exeptions.*;
import ModelManager.VIAPetsModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.NoSuchElementException;

public class PetViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

  private Pet selectedPet;
  @FXML private Button addPetButton;
  @FXML private Button backButton;
  @FXML private TableView<Pet> petTableView;
  @FXML private TableColumn<Pet, String> breedspecColumn;
  @FXML private TableColumn<Pet, String> colorColumn;
  @FXML private TableColumn<Pet, String> genderColumn;
  @FXML private TableColumn<Pet, String> kennelColumn;
  @FXML private ComboBox<String> typeComboBox;
  @FXML private TextField priceField;
  @FXML private TextField colorField;
  @FXML private TextField ageField;
  @FXML private TextField nameField;
  @FXML private TextArea commentArea;
  @FXML private RadioButton maleRadio;
  @FXML private RadioButton femaleRadio;
  @FXML private CheckBox kennelCheck;
  @FXML private TextField breedField;
  @FXML private TextField breederField;
  @FXML private Label breedLabel;
  @FXML private Label breederLabel;
  @FXML private Label speciesLabel;
  @FXML private TextField speciesField;
  @FXML private Label preferredFoodLabel;
  @FXML private TextField preferredFoodField;
  @FXML private Label bitesLabel;
  @FXML private ToggleGroup genderToggleGroup;
  @FXML private ToggleGroup bitesToggleGroup;
  @FXML private RadioButton bitesRadioButton;
  @FXML private RadioButton doesNotBiteRadioButton;
  @FXML private Label predatorLabel;
  @FXML private RadioButton predatorRadioButton;
  @FXML private RadioButton notPredatorRadioButton;
  @FXML private Label saltWaterLabel;
  @FXML private RadioButton saltWaterRadioButton;
  @FXML private RadioButton sweetWaterRadioButton;
  @FXML private ToggleGroup predatorToggleGroup;
  @FXML private ToggleGroup saltWaterToggleGroup;
  @FXML private Button editButton;
  @FXML private Button deleteButton;
  @FXML private TextField typeTextField;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    hideAllFields();
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

    this.scene.getStylesheets().add(getClass().getResource("PetView.css").toExternalForm());

    maleRadio.setToggleGroup(genderToggleGroup);
    femaleRadio.setToggleGroup(genderToggleGroup);

    bitesRadioButton.setToggleGroup(bitesToggleGroup);
    doesNotBiteRadioButton.setToggleGroup(bitesToggleGroup);

    predatorRadioButton.setToggleGroup(predatorToggleGroup);
    notPredatorRadioButton.setToggleGroup(predatorToggleGroup);

    saltWaterRadioButton.setToggleGroup(saltWaterToggleGroup);
    sweetWaterRadioButton.setToggleGroup(saltWaterToggleGroup);

    String[] types = {"Dog", "Cat", "Bird", "Fish", "Rodent", "Various", "All"};
    if (typeComboBox.getItems().isEmpty())
    {
      typeComboBox.getItems().addAll(types);
    }

    breedspecColumn.setCellValueFactory(
        new PropertyValueFactory<Pet, String>("species"));
    colorColumn.setCellValueFactory(
        new PropertyValueFactory<Pet, String>("color"));
    genderColumn.setCellValueFactory(
        new PropertyValueFactory<Pet, String>("gender"));
    kennelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        Boolean.toString(cellData.getValue().getInKennel())));

    breedspecColumn.setSortable(false);
    colorColumn.setSortable(false);
    genderColumn.setSortable(false);
    kennelColumn.setSortable(false);

    updatePetTable();

    TableView.TableViewSelectionModel<Pet> selectionModel = petTableView.getSelectionModel();
    ObservableList<Pet> selectedItems = selectionModel.getSelectedItems();
    selectedItems.addListener(new ListChangeListener<Pet>()
    {
      @Override public void onChanged(Change<? extends Pet> change)
      {
        try
        {
          Pet temp = change.getList().getFirst();
          if (temp != null)
          {
            switch (temp)
            {
              case Cat cat:
                selectedPet = new Cat(cat);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              case Fish fish:
                selectedPet = new Fish(fish);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              case Dog dog:
                selectedPet = new Dog(dog);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              case Rodent rodent:
                selectedPet = new Rodent(rodent);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              case Bird bird:
                selectedPet = new Bird(bird);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              case Various various:
                selectedPet = new Various(various);
                if (temp.getGender().equals("Male"))
                {
                  selectedPet.isMale();
                }
                else if (temp.getGender().equals("Female"))
                {
                  selectedPet.isFemale();
                }
                break;
              default:
                System.err.println("Pet tpe is not correct");
            }

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
  }

  private void updatePetTable()
  {
    if (petTableView != null)
    {
      petTableView.getItems().clear();
    }

    PetList pets = modelManager.getAllPets();
    for (int i = 0; i < pets.size(); i++)
    {
      petTableView.getItems().add(pets.get(i));
    }
  }

  private void updatePetTable(int type)
  {

    if (petTableView != null)
    {
      petTableView.getItems().clear();
    }

    PetList pets = modelManager.getAllPets().getPetsByType(type);
    for (int i = 0; i < pets.size(); i++)
    {
      petTableView.getItems().add(pets.get(i));
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == addPetButton)
    {
      viewHandler.openView("AddPetView");
    }

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

    if (e.getSource() == typeComboBox)
    {
      selectedPet = null;
      int selected = typeComboBox.getSelectionModel().getSelectedIndex() + 1;
      switch (selected)
      {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
          updatePetTable(selected);
          break;
        case 7:
        default:
          updatePetTable();
          break;
      }
    }
  }

  public void tableDisplayAction()
  {
    if (checkClick())
    {
      int typeTemp = petTableView.getSelectionModel().getSelectedItem()
          .getType();
      hideAllFields();
      clearAllFields();
      switch (typeTemp)
      {
        case 1:
        {
          Dog dog = (Dog) petTableView.getSelectionModel().getSelectedItem();
          breedLabel.setVisible(true);
          breedField.setVisible(true);
          breederLabel.setVisible(true);
          breederField.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(dog);
          if (!dog.getNameOfBreeder().equals("NoName"))
          {
            breederField.setText(dog.getNameOfBreeder());
          }
        }
        break;
        case 2:
        {
          Cat cat = (Cat) petTableView.getSelectionModel().getSelectedItem();
          breedLabel.setVisible(true);
          breedField.setVisible(true);
          breederLabel.setVisible(true);
          breederField.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(cat);
          if (!cat.getNameOfBreeder().equals("NoName"))
          {
            breederField.setText(cat.getNameOfBreeder());
          }
        }
        break;
        case 3:
        {
          Bird bird = (Bird) petTableView.getSelectionModel().getSelectedItem();
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          preferredFoodField.setVisible(true);
          preferredFoodLabel.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(bird);
          if (!bird.getPreferredFood().equals("NoData"))
          {
            preferredFoodField.setText(bird.getPreferredFood());
          }
        }
        break;
        case 5:
        {
          Rodent rodent = (Rodent) petTableView.getSelectionModel()
              .getSelectedItem();
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          bitesLabel.setVisible(true);
          bitesRadioButton.setVisible(true);
          doesNotBiteRadioButton.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(rodent);
          if (rodent.doesItBite())
          {
            bitesRadioButton.setSelected(true);
          }
          else
          {
            doesNotBiteRadioButton.setSelected(true);
          }
        }
        break;
        case 4:
        {
          Fish fish = (Fish) petTableView.getSelectionModel().getSelectedItem();
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          predatorLabel.setVisible(true);
          predatorRadioButton.setVisible(true);
          notPredatorRadioButton.setVisible(true);
          saltWaterLabel.setVisible(true);
          saltWaterRadioButton.setVisible(true);
          sweetWaterRadioButton.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(fish);
          if (fish.isItPredator())
          {
            predatorRadioButton.setSelected(true);
          }
          else
          {
            notPredatorRadioButton.setSelected(true);
          }
          if (fish.isItSaltWater())
          {
            saltWaterRadioButton.setSelected(true);
          }
          else
          {
            sweetWaterRadioButton.setSelected(true);
          }
        }
        break;
        case 6:
        {
          Various various = (Various) petTableView.getSelectionModel()
              .getSelectedItem();
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          deleteButton.setVisible(true);
          editButton.setVisible(true);
          displayGeneralInfo(various);
        }
        break;
        default:
          break;
      }
    }
  }

  public void hideAllFields()
  {
    breedLabel.setVisible(false);
    breedField.setVisible(false);
    breederLabel.setVisible(false);
    breederField.setVisible(false);
    speciesLabel.setVisible(false);
    speciesField.setVisible(false);
    preferredFoodField.setVisible(false);
    preferredFoodLabel.setVisible(false);
    bitesLabel.setVisible(false);
    bitesRadioButton.setVisible(false);
    doesNotBiteRadioButton.setVisible(false);
    predatorLabel.setVisible(false);
    predatorRadioButton.setVisible(false);
    notPredatorRadioButton.setVisible(false);
    saltWaterLabel.setVisible(false);
    saltWaterRadioButton.setVisible(false);
    sweetWaterRadioButton.setVisible(false);
    deleteButton.setVisible(false);
    editButton.setVisible(false);
  }

  public void clearAllFields()
  {
    {
      breedField.clear();
      breederField.clear();
      speciesField.clear();
      preferredFoodField.clear();
      bitesRadioButton.setSelected(false);
      doesNotBiteRadioButton.setSelected(false);
      predatorRadioButton.setSelected(false);
      notPredatorRadioButton.setSelected(false);
      saltWaterRadioButton.setSelected(false);
      sweetWaterRadioButton.setSelected(false);
      colorField.clear();
      typeTextField.clear();
      ageField.clear();
      nameField.clear();
      commentArea.clear();
      maleRadio.setSelected(false);
      femaleRadio.setSelected(false);

    }
  }

  public void displayGeneralInfo(Pet tempPet)
  {
    typeTextField.setText(tempPet.getTypeString());
    priceField.setText(Double.toString(tempPet.getPrice()));
    if (tempPet.getInKennel())
    {
      kennelCheck.setSelected(true);
      priceField.clear();
    }
    else
    {
      kennelCheck.setSelected(false);
    }
    colorField.setText(tempPet.getColor());
    ageField.setText(Integer.toString(tempPet.getAge()));
    if (tempPet.getGender().equals("Male"))
    {
      maleRadio.setSelected(true);
    }
    else if (tempPet.getGender().equals("Female"))
    {
      femaleRadio.setSelected(true);
    }
    if (!tempPet.getName().equals("NoName"))
    {
      nameField.setText(tempPet.getName());
    }

    if (!tempPet.getComment().equals("NoComment"))
    {
      commentArea.setText(tempPet.getComment());
    }

    switch (tempPet.getType())
    {
      case 1:
      case 2:
      {
        breedField.setText(tempPet.getSpecies());
      }
      break;
      case 3:
      case 4:
      case 5:
      case 6:
      {
        speciesField.setText(tempPet.getSpecies());
      }
    }
  }

  public boolean checkClick()
  {
    Pet pet = petTableView.getSelectionModel().getSelectedItem();
    if (pet == null)
    {
      return false;
    }
    return true;
  }

  public void deleteButtonAction()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to delete this pet?", ButtonType.YES,
        ButtonType.NO);
    alert.setTitle("Delete");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      modelManager.removePet(
          petTableView.getSelectionModel().getSelectedItem());
      Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
      alertDelete.setTitle("Delete");
      alertDelete.setHeaderText(null);
      alertDelete.setContentText("Pet was successfully deleted!");
      alertDelete.showAndWait();
      updatePetTable();
    }
  }

  public void editButtonAction()
  {
    Alert alertEdit = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to edit this pet?", ButtonType.YES, ButtonType.NO);
    alertEdit.setTitle("Edit");
    alertEdit.setHeaderText(null);

    alertEdit.showAndWait();

    if (alertEdit.getResult() == ButtonType.YES)
    {
      double price = 0;
      if (!kennelCheck.isSelected())
      {
        try
        {
          price = Double.parseDouble(priceField.getText());
        }
        catch (NumberFormatException e)
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Price can only contain digits!");
          alert.showAndWait();
          return;
        }
      }
      String color = colorField.getText();
      int age = -1;
      try
      {
        age = Integer.parseInt(ageField.getText());
      }
      catch (NumberFormatException e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Age can only contain digits!");
        alert.showAndWait();
        return;
      }
      String name = nameField.getText();
      String comment = commentArea.getText();
      boolean kennelStatus = kennelCheck.isSelected();
      String breed = breedField.getText();
      String breeder = breederField.getText();
      String gender;
      if (maleRadio.isSelected())
      {
        gender = "Male";
      }
      else if (femaleRadio.isSelected())
      {
        gender = "Female";
      }
      else
        gender = "";
      String preferredFood = preferredFoodField.getText();
      String species = speciesField.getText();
      boolean predator;
      if (predatorRadioButton.isSelected())
      {
        predator = true;
      }
      else if (notPredatorRadioButton.isSelected())
      {
        predator = false;
      }
      else
        predator = false;
      boolean saltWater;
      if (saltWaterRadioButton.isSelected())
      {
        saltWater = true;
      }
      else if (sweetWaterRadioButton.isSelected())
      {
        saltWater = false;
      }
      else
        saltWater = false;
      boolean bites;
      if (bitesRadioButton.isSelected())
      {
        bites = true;
      }
      else
        bites = false;
      switch (petTableView.getSelectionModel().getSelectedItem().getType())
      {
        case 1:
        {
          Dog dog;
          if (color.isEmpty() || age == -1 || breed.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            dog = new Dog(price, color, age, breed);
            if (!breeder.isEmpty())
            {
              dog.setNameOfBreeder(breeder);
            }
            if (kennelStatus)
            {
              dog.isInKennel();
            }
            if (gender.equals("Male"))
            {
              dog.isMale();
            }
            else if (gender.equals("Female"))
            {
              dog.isFemale();
            }
            if (!name.isEmpty())
            {
              dog.setName(name);
            }
            if (!comment.isEmpty())
            {
              dog.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (dog.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(dog);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            breedField.clear();
            breederField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }

        }
        break;
        case 2:
        {
          Cat cat;
          if (color.isEmpty() || age == -1 || breed.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            cat = new Cat(price, color, age, breed);
            if (!breeder.isEmpty())
            {
              cat.setNameOfBreeder(breeder);
            }
            if (kennelStatus)
            {
              cat.isInKennel();
            }
            if (gender.equals("Male"))
            {
              cat.isMale();
            }
            else if (gender.equals("Female"))
            {
              cat.isFemale();
            }
            if (!name.isEmpty())
            {
              cat.setName(name);
            }
            if (!comment.isEmpty())
            {
              cat.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (cat.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(cat);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            breedField.clear();
            breederField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }
        }
        break;
        case 3:
        {
          Bird bird;
          if (color.isEmpty() || age == -1 || species.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            bird = new Bird(price, color, age, species);
            if (!preferredFood.isEmpty())
            {
              bird.setPreferredFood(preferredFood);
            }
            if (kennelStatus)
            {
              bird.isInKennel();
            }
            if (gender.equals("Male"))
            {
              bird.isMale();
            }
            else if (gender.equals("Female"))
            {
              bird.isFemale();
            }
            if (!name.isEmpty())
            {
              bird.setName(name);
            }
            if (!comment.isEmpty())
            {
              bird.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (bird.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(bird);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            speciesField.clear();
            preferredFoodField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }
        }
        break;
        case 4:
        {
          Fish fish;
          if (color.isEmpty() || age == -1 || species.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            fish = new Fish(price, color, age, species);
            if (predator)
            {
              fish.isPredator();
            }
            else
              fish.isNotPredator();
            if (saltWater)
            {
              fish.isSaltWater();
            }
            else
              fish.isNotSaltWater();
            if (kennelStatus)
            {
              fish.isInKennel();
            }
            if (gender.equals("Male"))
            {
              fish.isMale();
            }
            else if (gender.equals("Female"))
            {
              fish.isFemale();
            }
            if (!name.isEmpty())
            {
              fish.setName(name);
            }
            if (!comment.isEmpty())
            {
              fish.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (fish.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(fish);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            speciesField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }
        }
        break;
        case 5:
        {
          Rodent rodent;
          if (color.isEmpty() || age == -1 || species.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            rodent = new Rodent(price, color, age, species);
            if (bites)
            {
              rodent.isBiting();
            }
            else
              rodent.isNotBiting();
            if (kennelStatus)
            {
              rodent.isInKennel();
            }
            if (gender.equals("Male"))
            {
              rodent.isMale();
            }
            else if (gender.equals("Female"))
            {
              rodent.isFemale();
            }
            if (!name.isEmpty())
            {
              rodent.setName(name);
            }
            if (!comment.isEmpty())
            {
              rodent.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (rodent.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(rodent);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            speciesField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }
        }
        break;
        case 6:
        {
          Various various;
          if (color.isEmpty() || age == -1 || species.isEmpty())
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                "Price(if not in kennel),color,age and breed fields needs to be completed!");
            alert.showAndWait();
            return;
          }
          try
          {
            various = new Various(price, color, age, species);
            if (kennelStatus)
            {
              various.isInKennel();
            }
            if (gender.equals("Male"))
            {
              various.isMale();
            }
            else if (gender.equals("Female"))
            {
              various.isFemale();
            }
            if (!name.isEmpty())
            {
              various.setName(name);
            }
            if (!comment.isEmpty())
            {
              various.setComment(comment);
            }
          }
          catch (IllegalTypeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalPriceException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalAgeException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (UnsupportedActionException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalNameException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }
          catch (IllegalBreedSpecException e)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
          }

          boolean matches = false;
          for (int i = 0; i < modelManager.getAllPets().size(); i++)
          {
            if (various.equals(modelManager.getAllPets().get(i)))
            {
              matches = true;
              break;
            }
          }
          if (!matches)
          {
            modelManager.addPet(various);
            modelManager.removePet(
                petTableView.getSelectionModel().getSelectedItem());
            priceField.clear();
            colorField.clear();
            ageField.clear();
            nameField.clear();
            commentArea.clear();
            speciesField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText(null);
            alert.setContentText("Pet was successfully edited!");
            alert.showAndWait();
          }
          else
          {
            Alert alertMatches = new Alert(Alert.AlertType.ERROR);
            alertMatches.setTitle("Error");
            alertMatches.setHeaderText(null);
            alertMatches.setContentText(
                "This pet matches all the information of another pet!");
            alertMatches.showAndWait();
            return;
          }
        }
      }
      updatePetTable();
    }
  }

  public void kennelCheckAction()
  {
    if(kennelCheck.isSelected())
    {
      priceField.setEditable(false);
      priceField.clear();
    }
    else
    {
      priceField.setEditable(true);
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
    updatePetTable();
    modelManager.save();
  }
}
