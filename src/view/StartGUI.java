package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ModelManager.VIAPetsModelManager;

import java.io.IOException;

public class StartGUI extends Application
{
  VIAPetsModelManager modelManager = new VIAPetsModelManager("VIAPets.bin");
  public void start(Stage window)
  {
    ViewHandler viewHandler = new ViewHandler(window, modelManager);
    viewHandler.start();
    window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
    window.setResizable(false);
  }
}

