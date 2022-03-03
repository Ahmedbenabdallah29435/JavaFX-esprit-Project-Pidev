/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.CategorieCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import bruteforce.Bruteforce;
import entity.Categorie;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class CategorieController implements Initializable {

    @FXML
    private StackPane stckUsers;
    @FXML
    private AnchorPane rootUsers;
    @FXML
    private Pane ContainerUsersAdmin;
    @FXML
    private HBox Userinformations;
    @FXML
    private Group parentImage;
    @FXML
    private Pane imageProfileContainer;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private MaterialDesignIconView icon;
    @FXML
    private Text textName;
    @FXML
    private Text textUserType;
    @FXML
    private ImageView verified;
    @FXML
    private TabPane PaneTableau;
    @FXML
    private TableView<Categorie> TableViewUsers;
    @FXML
    private TableColumn<Categorie, Integer> col_idCategorie;
    @FXML
    private TableColumn<Categorie, String> col_NomCategorie;
    @FXML
    private TableColumn<Categorie, String> col_ActionUser;
    @FXML
    private AnchorPane ContainerDeleteUser;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> CombofiltreSearch;
    @FXML
    private AnchorPane containerAjouterJoueur;
    @FXML
    private Text textTitreUser;
    @FXML
    private JFXButton btnSaveUser;
    @FXML
    private JFXButton btnCancelAddUser;
    @FXML
    private JFXButton btnModifierUser;
    @FXML
    private JFXTextField txtPrenomLogin;
    @FXML
    private JFXTextField txtNomLogin;
    @FXML
    private ImageView iconPrenom;
    @FXML
    private ImageView iconNom;
    @FXML
    private JFXComboBox<String> comboRole;
    @FXML
    private ImageView iconRole;
    
    //////////////////////////////
    Categorie categ = new Categorie();
    CategorieCrud crudCateg = new CategorieCrud();
    /////////////////////////////
    private ObservableList<Categorie> ListUsers;
    private ObservableList<Categorie> FiltreUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableUsers();
    }    

    @FXML
    private void SettingClicked(MouseEvent event) {
    }

    @FXML
    private void LogoutClicked(MouseEvent event) {
    }

    @FXML
    private void iconAddCategorieClicked(MouseEvent event) {
    }

    @FXML
    private void hideDialogDeleteUser(MouseEvent event) {
    }

    @FXML
    private void deleteUserClicked(MouseEvent event) {
    }

    @FXML
    private void close_app(ActionEvent event) {     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure do you want Exit !");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void minimize_app(ActionEvent event) {
        Bruteforce.stage.setIconified(true);
    }

    @FXML
    private void SearchAnything() {
//        String WordTyped = txtSearch.getText().trim();
//        if (WordTyped.isEmpty()) {
//            TableViewUsers.setItems(ListUsers);
//        } else {
//            FiltreUsers.clear();
//            for (Categorie p : ListUsers) {
//                if ((p.getNomCategorie().toLowerCase().contains(WordTyped.toLowerCase())))
//                    FiltreUsers.add(p);
//                }
//            }
//            TableViewUsers.setItems(FiltreUsers);
        }
    

    @FXML
    private void SearchParFiltre(MouseEvent event) {
    }

    @FXML
    private void AjouterUser(MouseEvent event) {
    }

    @FXML
    private void closeDialogAjouterUser(MouseEvent event) {
    }

    @FXML
    private void ModifierUser(MouseEvent event) {
    }

    @FXML
    private void closeDialogAddUser(MouseEvent event) {
    }
    
    
    
    private void LoadTableUsers() {

        List<Categorie> listeUsers = new ArrayList<>();
        listeUsers = crudCateg.AfficherCategorie(categ);
        ObservableList<Categorie> Liste = FXCollections.observableArrayList(listeUsers);

        col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("NomCategorie"));

      // col_SexeUser.setCellValueFactory(new SexeUserCellValueFactory());

        // col_ActionUser.setCellValueFactory(new );
        // TableViewUsers.setItems(Liste);

        ////Hedhy mte3 Search bel Nom
        ListUsers = FXCollections.observableArrayList(listeUsers);
        TableViewUsers.setItems(ListUsers);
        ///

        //add cell of button edit 
        Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFoctory = (TableColumn<Categorie, String> param) -> {
            //make cell containing buttons

            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView DeleteCategorie, EditCategorie;
                        EditCategorie = new ImageView(new Image("/ressource/editcateg.png"));
                        EditCategorie.setFitHeight(30);
                        EditCategorie.setFitWidth(30);
                        setGraphic(EditCategorie);

                        DeleteCategorie = new ImageView(new Image("/ressource/deletecateg.png"));
                        DeleteCategorie.setFitHeight(30);
                        DeleteCategorie.setFitWidth(30);
                        setGraphic(DeleteCategorie);


                        EditCategorie.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");

                          //  showDialogDeleteUser();
                        });

                        DeleteCategorie.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");

                        });       
                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(DeleteCategorie, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditCategorie, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(EditCategorie, DeleteCategorie);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_ActionUser.setCellFactory(cellFoctory);
    }
    
    
    
    
}
