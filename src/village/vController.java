package village;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.StyledEditorKit.BoldAction;

import dao.IVillage;
import dao.VillageDB;
import entities.User;
import entities.Village;
import tools.Outil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

public class vController implements Initializable
{

		private IVillage iv ;
	 	@FXML
	    private Button btnValider;

	    @FXML
	    private TextField txt_nomV;

	    @FXML
	    private TableView<Village> village_table;

	    @FXML
	    private TableColumn<Village, Integer> id_coloumn;

	    @FXML
	    private TableColumn<Village, String> nom_coloumn;

	    @FXML
	    private Button btnActualiser;

	    @FXML
	    private Button btnModifier;

	    @FXML
	    private Button btnSupprimer;

	    @FXML
	    private Label params_lbl;


	    @FXML
	    public void valider(ActionEvent event)
	    {
	    	String nom = txt_nomV.getText();
	    	if(nom.trim().equals(""))
	    	{
	    		Outil.showErroMessage("Erreur", "Entrer un nom de village SVP !!");
	    	}
	    	else
	    	{
	    		iv= new VillageDB();
	    		Village v = new Village();
	    		v.setNomV(nom);
	    		if(iv.add(v)!=0)
	    		{
	    			Outil.showConfirMessage("Felicitation", "Donnees ajoutees");
	    			loadTable();
	    			txt_nomV.setText("");
	    		}
	    		else
	    		{
	    			Outil.showErroMessage("Erreur", "Donnees non ajoutees");
	    		}
	    	}
	    }



	    @FXML
	    public void supprimer(ActionEvent event)
	    {
	    	// suppression dans la tableView
	    	/*
	    		ObservableList<Village> selectedRow,allVillage;
	    		allVillage = village_table.getItems();
	    		selectedRow = village_table.getSelectionModel().getSelectedItems();
	    		for (Village v : selectedRow)
	    		{
	    		   // pour chaque village selectionne , je le supprime de l'ensembe des village
	    		    allVillage.remove(v);
	    		}
	    	*/


	    	ObservableList<Village> selectedRow,allVillage;
    		allVillage = village_table.getItems();
    		selectedRow = village_table.getSelectionModel().getSelectedItems();
    		if(selectedRow.isEmpty())
			{
				Outil.showErroMessage("Erreur", "Veuillez selectionner une ligne SVP !!");
			}
    		else
    		{
    			for (Village v : selectedRow)
    			{
        				 iv = new VillageDB();

        				 if(iv.delete(v.getIdV()))
        				 {
        					 Outil.showConfirMessage("Felicitation", "Donnees supprimees");
        					 loadTable();
        				 }
        				 else
        				 {
        					 Outil.showErroMessage("Erreur", "Donnees non supprimees");
        				 }
        		}
    		}

    		}





		@Override
		public void initialize(URL location, ResourceBundle resources)
		{
			// gestion de l'utilisateur connecte
			params_lbl.setText(User.param_connexion);

			// chaque colonne est associe a un attribut dans la classe village
			id_coloumn.setCellValueFactory(new PropertyValueFactory<>("idV"));
			nom_coloumn.setCellValueFactory(new PropertyValueFactory<>("nomV"));
			loadTable();


			// pouvoir selectionne plusieurs lignes
			village_table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			// update table to allow for the nomV
			village_table.setEditable(true);

			nom_coloumn.setCellFactory(TextFieldTableCell.forTableColumn());
		}

		private void loadTable()
		{
			iv = new VillageDB();
			ObservableList<Village> l_village = FXCollections.observableArrayList();
			iv.liste().stream()
								.forEach(v->l_village.add(v));
			// chargement des donnees dans la table view
			// le tableView doit charger une list(ObservableList) de village
			village_table.setItems(l_village);
		}



		@FXML
		public void chagerAcceuil(ActionEvent event) throws IOException
		{
			String url = "/ac/Ac.fxml";
			Outil.load(event, url);
		}


		@FXML
		public void changerFirst(CellEditEvent edit)
		{
			Village vSelected = village_table.getSelectionModel().getSelectedItem();
			vSelected.setNomV(edit.getNewValue().toString());
			iv = new VillageDB();

			if(iv.update(vSelected))
			{
				Outil.showConfirMessage("Felicitation", "Modification effectuee");
				loadTable();

				// pour recuperer la valeur saisie
				//txt_nomV.setText(vSelected.getNomV());
			}
			else
			{
				Outil.showConfirMessage("Felicitation", "Modification non effectuee");
			}
		}


		 @FXML
		 public void tableClick(MouseEvent event)
		 {}

		  @FXML
		  public void Actualiser(ActionEvent event)
		  {
			loadTable();
		  }



}
