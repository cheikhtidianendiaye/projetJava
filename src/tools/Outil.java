package tools;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Outil
{
	public static void showConfirMessage(String titre, String message)
	{
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setTitle(titre);
		a.setContentText(message);
		a.showAndWait();
	}


	public static void showErroMessage(String titre, String message)
	{
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle(titre);
		a.setContentText(message);
		a.showAndWait();
	}

	private void LoadPage(ActionEvent event, String url) throws IOException
	{



		((Node) event.getSource()).getScene().getWindow().hide();



		Parent root = FXMLLoader.load(getClass().getResource(url));
		Scene scene = new Scene(root);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Gestion des donnees d'enqutes su decteur agricole");
		stage.show();
	}

	public static void load(ActionEvent event, String url) throws IOException
	{
		new Outil().LoadPage(event, url);
	}

}
