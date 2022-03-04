/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Element;
import Notifcation.AlertType;
import Notifcation.AlertsBuilder;
import Notifcation.NotificationType;
import Notifcation.NotificationsBuilder;
import Service.CategorieCrud;
import animations.Animations;
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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Categorie;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import utils.Constants;
import utils.JFXDialogTool;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class CategorieController implements Initializable {

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
    private TableView<Categorie> TableViewCategorie;
    @FXML
    private TableColumn<Categorie, Integer> col_idCategorie;
    @FXML
    private TableColumn<Categorie, String> col_NomCategorie;
    @FXML
    private TableColumn<Categorie, String> col_ActionCategorie;
    @FXML
    private TableColumn<String, Categorie> col_GenreCategorie;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView iconNom;
    @FXML
    private ImageView iconRole;

    //////////////////////////////
    Categorie categ = new Categorie();
    CategorieCrud crudCateg = new CategorieCrud();
    /////////////////////////////
    @FXML
    private AnchorPane containerAjouterCategorie;
    @FXML
    private Text textTitreCategorie;
    @FXML
    private JFXButton btnSaveCategorie;
    @FXML
    private JFXButton btnCancelAddCategorie;
    @FXML
    private JFXButton btnModifierCategorie;
    @FXML
    private JFXComboBox<String> comboGenre;
    @FXML
    private AnchorPane ContainerDeleteCategorie;
    @FXML
    private StackPane stckCategorie;
    private JFXDialogTool dialogDeleteCategorie;
    private JFXDialogTool dialogAjouterCategorie;
    private static final Stage stage = new Stage();
    @FXML
    private JFXTextField txtNom;
    @FXML
    private AnchorPane rootCategorie;
    private ObservableList<Categorie> ListCateg;
    private ObservableList<Categorie> FiltreCateg;
    @FXML
    private ComboBox<String> CombofiltreSearch;
    @FXML
    private Label txtStatTotal;
    @FXML
    private Label txtStatMax;
    Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableCategorie();

        FiltreCateg = FXCollections.observableArrayList();
        comboGenre.getItems().addAll("Action", "Adventure", "Strategy", "Sports", "Simulation");
        CombofiltreSearch.getItems().addAll("Action", "Adventure", "Strategy", "Sports", "Simulation", "ViewAll");
        LoadStat();
                Animations.fadeInUp(rootCategorie);

    }

    private void LoadStat() {

        // Changing random data after every 1 second.GeneratePDF
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int Total = crudCateg.countTotalCatgeorie();
                txtStatTotal.setText(String.valueOf(Total));
                String MaxGenre = crudCateg.MaxUsedGenre();
                txtStatMax.setText(String.valueOf(MaxGenre));

            }
        }));
        ///Repeat indefinitely until stop() method is called.
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

    }

    private void LoadTableCategorie() {

        List<Categorie> listeCategorie = new ArrayList<>();
        listeCategorie = crudCateg.AfficherCategorie(categ);
        ObservableList<Categorie> Listeeee = FXCollections.observableArrayList(listeCategorie);

        col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("NomCategorie"));
        col_GenreCategorie.setCellValueFactory(new PropertyValueFactory<>("Genre"));

        ListCateg = FXCollections.observableArrayList(listeCategorie);
        TableViewCategorie.setItems(ListCateg);
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

                            int idCategorie = Integer.valueOf((TableViewCategorie.getSelectionModel().getSelectedItem().getIdCategorie()));
                            comboGenre.setValue(TableViewCategorie.getSelectionModel().getSelectedItem().getGenre());
                            txtNom.setText(TableViewCategorie.getSelectionModel().getSelectedItem().getNomCategorie());
                            //btnUpdateReclam.toFront();
                            textTitreCategorie.setText("Modifier Le Categorie");
                            showDialogModifierCategorie();
                        });

                        DeleteCategorie.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");
                            showDialogDeleteCategorie();
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
        col_ActionCategorie.setCellFactory(cellFoctory);
    }

    @FXML
    private void SearchAnything() {
        String WordTyped = txtSearch.getText().trim();
        if (WordTyped.isEmpty()) {
            TableViewCategorie.setItems(ListCateg);
            LoadTableCategorie();
        } else {
            FiltreCateg.clear();
            for (Categorie p : ListCateg) {
                if ((p.getNomCategorie().toLowerCase().contains(WordTyped.toLowerCase())) || (p.getGenre().toLowerCase().contains(WordTyped.toLowerCase()))) {
                    FiltreCateg.add(p);
                }
            }
            TableViewCategorie.setItems(FiltreCateg);
        }
    }

    private void showDialogModifierCategorie() {

        rootCategorie.setEffect(Constants.BOX_BLUR_EFFECT);
        //imageContainer.toFront();
        containerAjouterCategorie.setVisible(true);
        // btnSaveReclam.setDisable(false);

        dialogAjouterCategorie = new JFXDialogTool(containerAjouterCategorie, stckCategorie);
        dialogAjouterCategorie.show();
        dialogAjouterCategorie.setOnDialogOpened(ev -> {
            txtNom.requestFocus();
        });

        dialogAjouterCategorie.setOnDialogClosed(ev -> {
            closeStage();
            TableViewCategorie.setDisable(false);
            rootCategorie.setEffect(null);
            containerAjouterCategorie.setVisible(false);
            LoadTableCategorie();
        });
    }

    private void showDialogDeleteCategorie() {
        rootCategorie.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteCategorie.setVisible(true);

        dialogDeleteCategorie = new JFXDialogTool(ContainerDeleteCategorie, stckCategorie);
        dialogDeleteCategorie.show();

        dialogDeleteCategorie.setOnDialogClosed(ev -> {
            TableViewCategorie.setDisable(false);
            rootCategorie.setEffect(null);
            ContainerDeleteCategorie.setVisible(false);
            LoadTableCategorie();

        });

    }

    @FXML
    private void iconAddCategorieClicked(MouseEvent event) {
        rootCategorie.setEffect(Constants.BOX_BLUR_EFFECT);
        textTitreCategorie.setText("Ajouter une Categorie");
        containerAjouterCategorie.setVisible(true);
        btnSaveCategorie.setDisable(false);
        btnModifierCategorie.setVisible(true);
        btnSaveCategorie.toFront();
        dialogAjouterCategorie = new JFXDialogTool(containerAjouterCategorie, stckCategorie);
        dialogAjouterCategorie.show();
        dialogAjouterCategorie.setOnDialogOpened(ev -> {
            txtNom.requestFocus();
        });

        dialogAjouterCategorie.setOnDialogClosed(ev -> {
            closeStage();
            TableViewCategorie.setDisable(false);
            rootCategorie.setEffect(null);
            containerAjouterCategorie.setVisible(false);
            LoadTableCategorie();
        });
    }

    @FXML
    private void hideDialogDeleteCategorie() {
        if (dialogDeleteCategorie != null) {
            dialogDeleteCategorie.close();
        }
    }

    @FXML
    private void closeDialogAddCategorie() {
        if (dialogAjouterCategorie != null) {
            dialogAjouterCategorie.close();

            btnModifierCategorie.setVisible(true);
            btnCancelAddCategorie.setVisible(true);
        }
        txtNom.clear();
        comboGenre.getSelectionModel().clearSelection();
        LoadTableCategorie();
    }

    @FXML
    private void closeDialogAjouterCategorie(MouseEvent event) {

        if (dialogAjouterCategorie != null) {
            dialogAjouterCategorie.close();

            btnModifierCategorie.setVisible(true);
            btnCancelAddCategorie.setVisible(true);
        }
        txtNom.clear();
        comboGenre.getSelectionModel().clearSelection();
        LoadTableCategorie();

    }

    @FXML
    private void AjouterCategorie(MouseEvent event) {
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            Animations.shake(txtNom);
            return;
        }
        if (comboGenre.getSelectionModel().getSelectedItem() == null) {
            comboGenre.requestFocus();
            Animations.shake(comboGenre);
            return;
        }
        categ.setNomCategorie(txtNom.getText());
        categ.setGenre(comboGenre.getSelectionModel().getSelectedItem());

        boolean result = crudCateg.AjouterCategorie(categ); // Fonction Ajout
        if (result) {
            AlertsBuilder.create(AlertType.SUCCES, stckCategorie, containerAjouterCategorie, containerAjouterCategorie, "Categorie Added With Sucees !");
            txtNom.clear();
            comboGenre.getSelectionModel().clearSelection();
            closeDialogAddCategorie();

        } else {
            AlertsBuilder.create(AlertType.ERROR, stckCategorie, containerAjouterCategorie, rootCategorie, Constants.MESSAGE_Error);
        }

    }

    @FXML
    private void ModifierCategorie(MouseEvent event) {
        int idCateg = 0;
        if (TableViewCategorie.getSelectionModel().getSelectedItem() != null) {
            idCateg = Integer.valueOf((TableViewCategorie.getSelectionModel().getSelectedItem().getIdCategorie()));
        }
        String nom = txtNom.getText();
        String genre = comboGenre.getSelectionModel().getSelectedItem();

        if (nom.isEmpty()) {
            Animations.shake(txtNom);
            return;
        }
        if ((genre == null)) {
            Animations.shake(comboGenre);
            return;
        }

        closeDialogAddCategorie();
        Categorie cat = new Categorie(idCateg, nom, genre);
        Boolean result = crudCateg.ModifierCategorie(cat);
        closeDialogAddCategorie();

        if (result) {
            txtNom.clear();
            closeDialogAddCategorie();
            AlertsBuilder.create(AlertType.SUCCES, stckCategorie, rootCategorie, TableViewCategorie, Constants.MESSAGE_UPDATED);
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        txtNom.clear();
        LoadTableCategorie();

    }

    @FXML
    private void deleteCategorieClicked(MouseEvent event) {
        if (TableViewCategorie.getSelectionModel().getSelectedItem() != null) {
            categ = TableViewCategorie.getSelectionModel().getSelectedItem();
            Boolean result = crudCateg.SupprimerCategorie(categ.getIdCategorie());
            if (result) {
                hideDialogDeleteCategorie();
                AlertsBuilder.create(AlertType.SUCCES, stckCategorie, rootCategorie, TableViewCategorie, "Categorie: " + categ.getNomCategorie() + " " + categ.getGenre() + " \n" + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }
    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
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
    private void SearchParFiltre(MouseEvent event) {
        if (CombofiltreSearch.getSelectionModel().getSelectedItem() == null) {
            AlertsBuilder.create(AlertType.ERROR, stckCategorie, rootCategorie, TableViewCategorie, "You Need to Select Something to filtre it !");
        }
        if (CombofiltreSearch.getSelectionModel().getSelectedItem() != null) {
            String WordTyped = CombofiltreSearch.getSelectionModel().getSelectedItem();
            if (WordTyped.equals("ViewAll")) {
                TableViewCategorie.setItems(ListCateg);
                LoadTableCategorie();
            } else {
                FiltreCateg.clear();
                for (Categorie p : ListCateg) {
                    if ((p.getNomCategorie().toLowerCase().contains(WordTyped.toLowerCase()))
                            || (p.getGenre().toLowerCase().equals(WordTyped.toLowerCase()))) {
                        FiltreCateg.add(p);
                    }
                }
                TableViewCategorie.setItems(FiltreCateg);
            }
        }
    }

    @FXML
    private void GoToCategorie(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/Categorie.fxml"));
        stckCategorie.getChildren().removeAll();
        stckCategorie.getChildren().setAll(menu);
    }

    @FXML
    private void GoToJoueur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/GUI/Joueur.fxml"));
        stckCategorie.getChildren().removeAll();
        stckCategorie.getChildren().setAll(menu);
    }

    @FXML
    private void GeneratePDF(MouseEvent event) throws IOException {

        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("idCategorie");
            table.addCell("NomCategorie");
            table.addCell("Genre");
            //     table.addCell("Image : ");

            crudCateg.AfficherCategorie(categ).forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getIdCategorie()));
                table.addCell(e.getNomCategorie());
                table.addCell(e.getGenre());
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum+".pdf");
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }

    }

}
