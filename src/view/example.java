/*package view;

import model.Student;
import model.StudentList;
import model.StudentModelManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


/**
 * A GUI tab containing components for displaying a list of students.
 * @author Allan Henriksen
 * @version 3.0
 */
/*
public class AllStudentsTab extends Tab
{
  private VBox allStudentsPane;

  private TableView<Student> allStudentsTable;
  private TableViewSelectionModel<Student> defaultSelectionModel;
  private TableColumn<Student, String> firstNameColumn;
  private TableColumn<Student, String> lastNameColumn;
  private TableColumn<Student, String> countryColumn;

  private Button getButton;

  private MyActionListener listener;

  private StudentModelManager modelManager;

  /**
   * Constructor initializing the GUI components
   * @param title The title of the tab
   * @param modelManager StudentModelManager object used for retrieving and storing student information
   */
/*
  public AllStudentsTab(String title, StudentModelManager modelManager)
  {
    super(title);

    this.modelManager = modelManager;

    listener = new MyActionListener();

    allStudentsTable = new TableView<Student>();
    defaultSelectionModel = allStudentsTable.getSelectionModel();
    allStudentsTable.setPrefHeight(290);

    firstNameColumn = new TableColumn<Student, String>("First Name");
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
    firstNameColumn.setPrefWidth(165);

    lastNameColumn = new TableColumn<Student, String>("Last Name");
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
    lastNameColumn.setPrefWidth(165);

    countryColumn = new TableColumn<Student, String>("Country");
    countryColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("country"));
    countryColumn.setPrefWidth(164);

    firstNameColumn.setReorderable(false);
    lastNameColumn.setReorderable(false);
    countryColumn.setReorderable(false);

    allStudentsTable.getColumns().add(firstNameColumn);
    allStudentsTable.getColumns().add(lastNameColumn);
    allStudentsTable.getColumns().add(countryColumn);

    getButton = new Button("Get Students");
    getButton.setOnAction(listener);

    allStudentsPane = new VBox(10);
    allStudentsPane.setAlignment(Pos.CENTER);
    allStudentsPane.getChildren().add(allStudentsTable);
    allStudentsPane.getChildren().add(getButton);

    super.setContent(allStudentsPane);
  }

  /**
   * Enables or disables selection in the allStudentsTable
   * @param bool if true then the area will be editable, if false then it will not
   */
/*
  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      allStudentsTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allStudentsTable.getSelectionModel().clearSelection();
      allStudentsTable.setSelectionModel(null);
    }
  }

  /**
   * Updates the allStudentsTable tableView with information from the students file
   */
/*
  public void updateStudentArea()
  {
    allStudentsTable.getItems().clear();
    StudentList students = modelManager.getAllStudents();

    for(int i = 0; i<students.size(); i++)
    {
      allStudentsTable.getItems().add(students.get(i));
    }
  }

  /*
   * Inner action listener class
   * @author Allan Henriksen
   * @version 4.0
   */
/*
  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent e)
    {
      if (e.getSource() == getButton)
      {
        updateStudentArea();
      }
    }
  }
}*/