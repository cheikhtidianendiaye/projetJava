package menage;


import java.awt.color.CMMException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import dao.IMenage;
import dao.IVillage;
import dao.MenageDB;
import dao.VillageDB;
import entities.Menage;
import entities.User;
import entities.Village;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import tools.Outil;

public class mController implements Initializable
{
	private IMenage im ;
	private IVillage iv;


    @FXML
    private AnchorPane btnRetour;

    @FXML
    private Button btnActualiser;

    @FXML
    private Button btnSupprimer;

    @FXML
    private ComboBox<Village> cbbVillage;

    @FXML
    private Label params_lbl;

    @FXML
    private TableView<Menage> table_menage;

    @FXML
    private TableColumn<Menage, Integer> idColloumn;

    @FXML
    private TableColumn<Menage, String> nomColoumn;

    @FXML
    private TableColumn<Menage, Integer> idVM_Coloumn;

    @FXML
    private TextField txt_nomM;

    @FXML
    private Button btnValider;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		params_lbl.setText(User.param_connexion);


		// chaque colonne est associe a un attribut dans la classe village
		idColloumn.setCellValueFactory(new PropertyValueFactory<>("idM"));
		nomColoumn.setCellValueFactory(new PropertyValueFactory<>("nomFamille"));
		idVM_Coloumn.setCellValueFactory(new PropertyValueFactory<>("idV"));



		//chargement du combobox
		iv = new VillageDB();
		ObservableList<Village> listeDeTypeObservablelist = FXCollections.observableArrayList(iv.liste());

		for(Village v : listeDeTypeObservablelist)
		{

			cbbVillage.setItems(listeDeTypeObservablelist);
		}




		table_menage.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		table_menage.setEditable(true);
		nomColoumn.setCellFactory(TextFieldTableCell.forTableColumn());

		loadTable();
	}

	@FXML
	public void valider(ActionEvent event)
	{
		String nom = txt_nomM.getText();
		int idV = cbbVillage.getValue().getIdV();
		System.out.println(nom);
		System.out.println(idV);





		if((nom.trim().equals("")))

    	{
    		Outil.showErroMessage("Erreur", "Veuillez remplir les deux champs SVP !!");
    	}
    	else
    	{
    		im= new MenageDB();
    		Menage m = new Menage();
    		m.setNomFamille(nom);
    		//m.setIdV(idV);
    		if(im.add(m)!=0)
    		{
    			Outil.showConfirMessage("Felicitation", "Donnees ajoutees");
    			loadTable();
    			txt_nomM.setText("");
    			cbbVillage.setValue(null);

    		}
    		else
    		{
    			Outil.showErroMessage("Erreur", "Donnees non ajoutees");
    		}
    	}


	}






	private void loadTable()
	{
		im = new MenageDB();
		ObservableList<Menage> l_menage = FXCollections.observableArrayList();
		im.liste().stream()
							.forEach(m->l_menage.add(m));
		table_menage.setItems(l_menage);
	}

	@FXML
	public void chagerAcceuil(ActionEvent event) throws IOException
	{
		String url = "/ac/Ac.fxml";
		Outil.load(event, url);
	}





	@FXML
	private void actualiser(ActionEvent event)
	{
		loadTable();
	}

	@FXML
	private void Supprimer(ActionEvent event)
	{

		ObservableList<Menage> selectedRow,allMenage;
		allMenage = table_menage.getItems();
		selectedRow = table_menage.getSelectionModel().getSelectedItems();
		if(selectedRow.isEmpty())
		{
			Outil.showErroMessage("Erreur", "Veuillez selectionner une ligne SVP !!");
		}
		else
		{
			for (Menage m : selectedRow)
			{
					 im = new MenageDB();

					 if(im.delete(m.getIdM()))
					 {
						 Outil.showConfirMessage("Felicitation", "Donnees supprimees");
						 loadTable();
					 }
					 else
					 {Outil.showErroMessage("Erreur", "Donnees non supprimees");}
		}
	}


	}
}
