package view;

import Exeptions.*;
import ModelManager.VIAPetsModelManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;

public class AddPetViewController
{
  private Scene scene;
  private VIAPetsModelManager modelManager;
  private ViewHandler viewHandler;

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
  @FXML private RadioButton NotPredatorRadioButton;
  @FXML private Label saltWaterLabel;
  @FXML private RadioButton saltWaterRadioButton;
  @FXML private RadioButton sweetWaterRadioButton;
  @FXML private ToggleGroup predatorToggleGroup;
  @FXML private ToggleGroup saltWaterToggleGroup;
  @FXML private Button saveButton;
  @FXML private Button backButton;
  @FXML private Button viewPetsButton;

  public void init(ViewHandler viewHandler, Scene scene,
      VIAPetsModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

    this.scene.getStylesheets().add(getClass().getResource("AddPetView.css").toExternalForm());

    maleRadio.setToggleGroup(genderToggleGroup);
    femaleRadio.setToggleGroup(genderToggleGroup);
    maleRadio.isSelected();

    bitesRadioButton.setToggleGroup(bitesToggleGroup);
    doesNotBiteRadioButton.setToggleGroup(bitesToggleGroup);
    doesNotBiteRadioButton.isSelected();

    predatorRadioButton.setToggleGroup(predatorToggleGroup);
    NotPredatorRadioButton.setToggleGroup(predatorToggleGroup);
    NotPredatorRadioButton.isSelected();

    saltWaterRadioButton.setToggleGroup(saltWaterToggleGroup);
    sweetWaterRadioButton.setToggleGroup(saltWaterToggleGroup);
    sweetWaterRadioButton.isSelected();
  }

  public void addPet()
  {
    String typeString = typeComboBox.getSelectionModel().getSelectedItem();
    System.out.printf(typeString);
    int type = switch (typeString)
    {
      case "Dog" -> 1;
      case "Cat" -> 2;
      case "Bird" -> 3;
      case "Fish" -> 4;
      case "Rodent" -> 5;
      case "Various" -> 6;
      default -> 0;
    };
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
    else if (NotPredatorRadioButton.isSelected())
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
    switch (type)
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(dog.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(dog);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          breedField.clear();
          breederField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(cat.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(cat);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          breedField.clear();
          breederField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(bird.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(bird);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          speciesField.clear();
          preferredFoodField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(fish.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(fish);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          speciesField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(rodent.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(rodent);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          speciesField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
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
        for(int i = 0; i < modelManager.getAllPets().size(); i++)
        {
          if(various.equals(modelManager.getAllPets().get(i)))
          {
            matches = true;
            break;
          }
        }
        if(!matches)
        {
          modelManager.addPet(various);
          priceField.clear();
          colorField.clear();
          ageField.clear();
          nameField.clear();
          commentArea.clear();
          speciesField.clear();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Save");
          alert.setHeaderText(null);
          alert.setContentText("Pet was successfully saved!");
          alert.showAndWait();
        } else
        {
          Alert alertMatches = new Alert(Alert.AlertType.ERROR);
          alertMatches.setTitle("Error");
          alertMatches.setHeaderText(null);
          alertMatches.setContentText("This pet matches all the information of another pet!");
          alertMatches.showAndWait();
          return;
        }
      }
    }

  }

  public void kennelBlock()
  {
    if (kennelCheck.isSelected())
    {
      priceField.setEditable(false);
      priceField.clear();
    }

    if (!kennelCheck.isSelected())
    {
      priceField.setEditable(true);
    }
  }

  public void priceCheck()
  {

  }

  @FXML public void backButtonAction()
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

  @FXML public void viewPetButtonAction()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to go to \"View Pets\"?", ButtonType.YES, ButtonType.NO);
    alert.setTitle("Back");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      viewHandler.openView("PetView");
    }
  }

  public void initialize()
  {
    String[] types = {"Dog", "Cat", "Bird", "Fish", "Rodent", "Various"};
    if (typeComboBox.getItems().isEmpty())
    {
      typeComboBox.getItems().addAll(types);
    }

    typeComboBox.setOnAction((event) -> {

      hideAllFields();
      switch (typeComboBox.getValue())
      {
        case "Dog":
        {
          breedLabel.setVisible(true);
          breedField.setVisible(true);
          breederLabel.setVisible(true);
          breederField.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        case "Cat":
        {
          breedLabel.setVisible(true);
          breedField.setVisible(true);
          breederLabel.setVisible(true);
          breederField.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        case "Bird":
        {
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          preferredFoodField.setVisible(true);
          preferredFoodLabel.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        case "Rodent":
        {
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          bitesLabel.setVisible(true);
          bitesRadioButton.setVisible(true);
          doesNotBiteRadioButton.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        case "Fish":
        {
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          predatorLabel.setVisible(true);
          predatorRadioButton.setVisible(true);
          NotPredatorRadioButton.setVisible(true);
          saltWaterLabel.setVisible(true);
          saltWaterRadioButton.setVisible(true);
          sweetWaterRadioButton.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        case "Various":
        {
          speciesLabel.setVisible(true);
          speciesField.setVisible(true);
          saveButton.setVisible(true);
        }
        break;
        default:
          break;
      }
    });
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
    NotPredatorRadioButton.setVisible(false);
    saltWaterLabel.setVisible(false);
    saltWaterRadioButton.setVisible(false);
    sweetWaterRadioButton.setVisible(false);
    saveButton.setVisible(false);
  }

  public Scene getScene()
  {
    return scene;
  }

  public void reset()
  {
  }
}
