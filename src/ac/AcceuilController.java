package ac;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.User;
import tools.Outil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AcceuilController implements Initializable
{
	@FXML
	private Label params;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		params.setText(User.param_connexion);
	}



	@FXML
	public void chagerVillage(ActionEvent event) throws IOException
	{
		String url = "/village/Village.fxml";
		Outil.load(event, url);
	}

	@FXML
	public void chagerMenage(ActionEvent event) throws IOException
	{
		String url = "/menage/Menage.fxml";
		Outil.load(event, url);
	}












}
