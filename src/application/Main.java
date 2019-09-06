package application;

import java.io.IOException;

import dao.IUser;
import dao.UserDB;
import entities.User;
import tools.Outil;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Main extends Application
{
	@FXML
	 private PasswordField txt_password;

	@FXML
	 private TextField txt_email;

	@FXML
	 private Button btn_connexion;

	@FXML
	public void getConnexion(ActionEvent event) throws IOException
	{
		String login = txt_email.getText();
		String password = txt_password.getText();

		if(login.trim().equals("") || password.trim().equals(""))
		{
			Outil.showErroMessage("Erreur", "Veuillez remplir tous les champs SVP !");
		}
		else
		{
			IUser iu = new UserDB();
			User u = iu.getLogin(login, password);
			if(u!=null)
			{
				User.param_connexion = "Bienvenue : " + u.getCivilite() + " " + u.getPrenom()+ " "+ u.getNom();
				String url = "/ac/Ac.fxml";
				Outil.load(event, url);

			}
			else
			{
				Outil.showErroMessage("Erreur", "Login ou password incorrect !! ");
			}
		}
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}


	public static void main(String[] args)
	{
		launch(args);
	}


}
