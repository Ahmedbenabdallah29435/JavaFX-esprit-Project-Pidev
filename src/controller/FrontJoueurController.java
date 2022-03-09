/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Notifcation.AlertType;
import Notifcation.AlertsBuilder;
import Notifcation.NotificationType;
import Notifcation.NotificationsBuilder;
import Service.JoueurCrud;
import animations.Animations;
import bruteforce.Bruteforce;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entity.Joueur;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import utils.Constants;
import utils.JFXDialogTool;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class FrontJoueurController implements Initializable {

    @FXML
    private StackPane stckJoueur;
    @FXML
    private AnchorPane rootJoueur;
    @FXML
    private AnchorPane containerAjouterJoueur;
    @FXML
    private Text textTitreUser;
    @FXML
    private JFXButton btnSaveJoueur;
    @FXML
    private JFXButton btnCancelAddJoueur;
    @FXML
    private JFXButton btnModifierJoueur;
    @FXML
    private JFXTextField txtLocationJoueur;
    @FXML
    private JFXTextField txtPrenomJoueur;
    @FXML
    private JFXTextField txtNomJoueur;
    @FXML
    private JFXDatePicker txtDateNaissanceJoueur;
    @FXML
    private JFXComboBox<String> comboSexeJoueur;
    @FXML
    private JFXTextField txtAgeJoueur;
    @FXML
    private JFXComboBox<String> comboCategorie;
    @FXML
    private ImageView DragimgRec;
    @FXML
    private Pane ContainerJoueur;
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
    private TableView<Joueur> TableViewJoueur;
    @FXML
    private TableColumn<Joueur, String> col_NomJoueur;
    @FXML
    private TableColumn<Joueur, String> col_PrenomJoueur;
    @FXML
    private TableColumn<Joueur, ImageView> col_SexeJoueur;
    @FXML
    private TableColumn<Joueur, Date> col_NaissanceJoueur;
    @FXML
    private TableColumn<Joueur, Integer> col_AgeJoueur;
    @FXML
    private TableColumn<Joueur, String> col_VilleJoueur;
    @FXML
    private TableColumn<Joueur, String> col_imageJoueur;
    @FXML
    private TableColumn<Joueur, String> col_Categorie;
    @FXML
    private TableColumn<Joueur, String> col_ActionJoueur;
    @FXML
    private AnchorPane ContainerCodeQR;
    @FXML
    private ImageView imgQRCodeGen;
    @FXML
    private AnchorPane ContainerDeleteJoueur;
    @FXML
    private Circle imgOnline;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> CombofiltreSearch;
    private static final Stage stage = new Stage();
    private JFXDialogTool dialogDeleteJoueur;
    private JFXDialogTool dialogAjouterJoueur;
    private JFXDialogTool dialogCodeQR;
    private ObservableList<Joueur> ListJou;
    private ObservableList<Joueur> FiltreJou;
    //////////////////////////////
    Joueur Joueur = new Joueur();
    JoueurCrud JoueurCrud = new JoueurCrud();
    private String path = "";
    String ImagePath = "";
    String idCategorieSelected;
    private Image genQRCodeImg; // Generated QR Code image
    @FXML
    private JFXColorPicker colorpicker;
    @FXML
    private JFXColorPicker colorpicker1;
    @FXML
    private Rectangle rectangle1;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Pane PanePull;
    @FXML
    private TextField tfNomEquipe;
    @FXML
    private Label nomEquipemaillot;
    @FXML
    private JFXButton btnSavePull;
    @FXML
    private ImageView imageViewPull;
    File filePull = new File(System.getProperty("user.home") + "/Desktop/Pull.png");
    
    
    public static int idUserConnected = 1;  // ======>>>>>>>>>>>>>> idUserConnected

    /////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        comboSexeJoueur.getItems().addAll("Homme", "Femme");
        FiltreJou = FXCollections.observableArrayList();
        LoadTableJoueur();
        CombofiltreSearch.getItems().addAll("Homme", "Femme", "ViewAll");
        RemplirComboCategorie();

        Animations.fadeInUp(rootJoueur);

        //////////////////////////////////////////////////////// Pull
        colorpicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                rectangle.setFill(colorpicker.getValue());
                colorpicker.setVisible(false);
                colorpicker1.setVisible(true);

            }
        });
        colorpicker1.setOnAction(new EventHandler() {
            public void handle(Event t) {
                rectangle1.setFill(colorpicker1.getValue());
                colorpicker.setVisible(true);
                colorpicker1.setVisible(false);
            }
        });
        tfNomEquipe.textProperty().addListener((observable, oldValue, newValue) -> {
            nomEquipemaillot.setText(newValue);
            nomEquipemaillot.setVisible(true);

        });

        if (filePull.exists()) {
            try {
                tfNomEquipe.setVisible(false);
                colorpicker1.setVisible(false);
                colorpicker.setVisible(false);
                btnSavePull.setVisible(false);
                nomEquipemaillot.setVisible(false);
                //PanePull.setVisible(false);
                ///
                Image img = new Image(new FileInputStream(filePull));
                imageViewPull.setImage(img);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontJoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        imageViewPull.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                imageViewPull.setImage(new Image("/ressource/big.png"));
                tfNomEquipe.setVisible(true);
                colorpicker1.setVisible(true);
                colorpicker.setVisible(true);
                btnSavePull.setVisible(true);
                nomEquipemaillot.setVisible(true);
            }
        });
        
        
        
        
        Calendar cal = Calendar.getInstance();

        int years = cal.get(Calendar.YEAR)-8;
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        System.out.println(" " + years+" "+month+" "+day);
        ////////////////////////////////////////
        DatePicker maxDate = new DatePicker(); 
        maxDate.setValue(LocalDate.of(years, month, day)); // Max date available will be 
        ///////////////////////////////////////////////////////
        final Callback<DatePicker, DateCell> dayCellFactory;

        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(maxDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
//Finally, we just need to update our DatePicker cell factory as follow:
        txtDateNaissanceJoueur.setDayCellFactory(dayCellFactory);

    

    }

    private void RemplirComboCategorie() {
        String choix = comboCategorie.getSelectionModel().getSelectedItem();
        System.out.println(choix);
        try {
            String requeteee = "SELECT * FROM categorie ";
            Statement psttt = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rss = psttt.executeQuery(requeteee);
            while (rss.next()) {
                comboCategorie.getItems().addAll(rss.getString("idCategorie") + "   " + rss.getString("NomCategorie"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void hideDialogCodeQR() {
        if (dialogCodeQR != null) {
            dialogCodeQR.close();
        }
    }

    @FXML
    private void OpenCamera(MouseEvent event) {

    }

    @FXML
    private void SavePull(ActionEvent event) {

        WritableImage image = PanePull.snapshot(new SnapshotParameters(), null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filePull);
            tfNomEquipe.setVisible(false);
            colorpicker1.setVisible(false);
            colorpicker.setVisible(false);
            btnSavePull.setVisible(false);
            //PanePull.setVisible(false);
            Image img = new Image(new FileInputStream(filePull));
            imageViewPull.setImage(img);

        } catch (IOException ex) {
            Logger.getLogger(FrontJoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class SexeUserCellValueFactory implements Callback<TableColumn.CellDataFeatures<Joueur, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Joueur, ImageView> param) {
            Joueur item = param.getValue();

            ImageView Sexe;

            if (item.getSexe().equals("Homme")) {
                Sexe = new ImageView(new Image("/ressource/man.png"));
            } else {
                Sexe = new ImageView(new Image("/ressource/women.png"));
            }
            return new SimpleObjectProperty<>(Sexe);
        }
    }

    private void LoadTableJoueur() {

        List<Joueur> listeJoueur = new ArrayList<>();
        Joueur.setMyid(idUserConnected);
        listeJoueur = JoueurCrud.AfficherForUser(Joueur);
        ObservableList<Joueur> Listeeee = FXCollections.observableArrayList(listeJoueur);
        col_NomJoueur.setCellValueFactory(new PropertyValueFactory<>("NomJoueur"));
        col_PrenomJoueur.setCellValueFactory(new PropertyValueFactory<>("PrenomJoueur"));
        col_Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        col_NaissanceJoueur.setCellValueFactory(new PropertyValueFactory<>("DateDeNaissance"));
        col_AgeJoueur.setCellValueFactory(new PropertyValueFactory<>("Age"));
        col_SexeJoueur.setCellValueFactory(new SexeUserCellValueFactory());
        col_VilleJoueur.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        col_imageJoueur.setCellValueFactory(new PropertyValueFactory<>("imgJoueur"));

        //
        ListJou = FXCollections.observableArrayList(listeJoueur);
        TableViewJoueur.setItems(ListJou);
        ///

        //add cell of button edit 
        Callback<TableColumn<Joueur, String>, TableCell<Joueur, String>> cellFoctory = (TableColumn<Joueur, String> param) -> {
            //make cell containing buttons

            final TableCell<Joueur, String> cell = new TableCell<Joueur, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView DeleteJoueur, EditJoueur;
                        EditJoueur = new ImageView(new Image("/ressource/editcateg.png"));
                        EditJoueur.setFitHeight(30);
                        EditJoueur.setFitWidth(30);
                        setGraphic(EditJoueur);

                        DeleteJoueur = new ImageView(new Image("/ressource/deletecateg.png"));
                        DeleteJoueur.setFitHeight(30);
                        DeleteJoueur.setFitWidth(30);
                        setGraphic(DeleteJoueur);

                        EditJoueur.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");

                            int idJoueur = Integer.valueOf((TableViewJoueur.getSelectionModel().getSelectedItem().getIdJoueur()));
                            //btnUpdateReclam.toFront();
                            
                            System.out.println(""+idJoueur);

                            txtNomJoueur.setText(TableViewJoueur.getSelectionModel().getSelectedItem().getNomJoueur());
                            txtPrenomJoueur.setText(TableViewJoueur.getSelectionModel().getSelectedItem().getPrenomJoueur());
                            txtLocationJoueur.setText(TableViewJoueur.getSelectionModel().getSelectedItem().getVille());
                            txtDateNaissanceJoueur.setValue(TableViewJoueur.getSelectionModel().getSelectedItem().getDateDeNaissance().toLocalDate());
                            txtAgeJoueur.setText(String.valueOf(TableViewJoueur.getSelectionModel().getSelectedItem().getAge()));

                            comboSexeJoueur.setValue(TableViewJoueur.getSelectionModel().getSelectedItem().getSexe());
                            comboCategorie.setValue(TableViewJoueur.getSelectionModel().getSelectedItem().getCategorie());

                            textTitreUser.setText("Modifier Le Joueur");
                            ///////////////////////
                            try {
                                String requeteee = "SELECT imgJoueur FROM joueur WHERE idJoueur  = '" + idJoueur + "'";
                                Statement psttt = Myconnexion.getInstance().getCnx().createStatement();
                                ResultSet rss = psttt.executeQuery(requeteee);
                                while (rss.next()) {
                                    ImagePath = rss.getString(1);//bech najmt njyb mnha nom image 
                                    path = ImagePath = rss.getString(1);//bech najmt njyb mnha nom image
                                    Image img = new Image(new FileInputStream(ImagePath));
                                    DragimgRec.setImage(img);
                                }

                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            } catch (FileNotFoundException ex) {
                                DragimgRec.setImage(new Image(getClass().getResource("/ressource/drag-drop.gif").toExternalForm()));
                            }

                            //////////////////////
                            //RemplirComboCategorie();
                            showDialogModifierJoueur();
                        });

                        DeleteJoueur.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon delete is pressed !");
                            showDialogDeleteJoueur();
                        });
                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(DeleteJoueur, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditJoueur, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(EditJoueur, DeleteJoueur);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_ActionJoueur.setCellFactory(cellFoctory);

        /////// OpenCodeQR
        TableViewJoueur.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                String nom = TableViewJoueur.getSelectionModel().getSelectedItem().getNomJoueur();
                String prenom = TableViewJoueur.getSelectionModel().getSelectedItem().getPrenomJoueur();
                String ville = TableViewJoueur.getSelectionModel().getSelectedItem().getVille();
                String dateNaissance = TableViewJoueur.getSelectionModel().getSelectedItem().getDateDeNaissance().toString();
                int Age = TableViewJoueur.getSelectionModel().getSelectedItem().getAge();
                String Sexe = TableViewJoueur.getSelectionModel().getSelectedItem().getSexe();
                String Categorie = TableViewJoueur.getSelectionModel().getSelectedItem().getCategorie();
                String AllInfo = " nom " + nom + "\n prenom " + prenom + "\n ville " + ville + "\n dateNaissance " + dateNaissance + "\n Age " + Age + "\n Sexe " + Sexe + "\n Categorie " + Categorie + "";
                ////////////////////////:
                System.out.println("" + AllInfo);
                if (!AllInfo.isEmpty()) {
                    String foregroundColor = "#2E3437";
                    String backgroundColor = "#FFFFFF";
                    genQRCodeImg = encode(AllInfo, Integer.parseInt("300"), Integer.parseInt("300"), foregroundColor, backgroundColor);
                    if (genQRCodeImg != null) {
                        imgQRCodeGen.setImage(genQRCodeImg);
                    }

                    showDialogCodeQR();
                }
            }
        });
    }

    @FXML
    private void SearchAnything(KeyEvent event) {
        String WordTyped = txtSearch.getText().trim();
        if (WordTyped.isEmpty()) {
            TableViewJoueur.setItems(ListJou);
            LoadTableJoueur();
        } else {
            FiltreJou.clear();
            for (Joueur p : ListJou) {
                if ((p.getNomJoueur().toLowerCase().contains(WordTyped.toLowerCase())) || (p.getPrenomJoueur().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getDateDeNaissance().toString().contains(WordTyped.toLowerCase())) || (p.getSexe().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getVille().toLowerCase().contains(WordTyped.toLowerCase()))
                        || (p.getCategorie().toLowerCase().contains(WordTyped.toLowerCase()))) {
                    FiltreJou.add(p);
                }
            }
            TableViewJoueur.setItems(FiltreJou);
        }
    }

    @FXML
    private void SearchParFiltre(MouseEvent event) {
        if (CombofiltreSearch.getSelectionModel().getSelectedItem() == null) {
            AlertsBuilder.create(AlertType.SUCCES, stckJoueur, rootJoueur, TableViewJoueur, "You Need to Select Something to filtre it !");
        }
        if (CombofiltreSearch.getSelectionModel().getSelectedItem() != null) {
            String WordTyped = CombofiltreSearch.getSelectionModel().getSelectedItem();
            System.out.println("selected ===>>>" + WordTyped);
            if (WordTyped.equals("ViewAll")) {
                TableViewJoueur.setItems(ListJou);
                LoadTableJoueur();
            } else {
                FiltreJou.clear();
                for (Joueur p : ListJou) {
                    if ((p.getNomJoueur().toLowerCase().contains(WordTyped.toLowerCase())) || (p.getPrenomJoueur().toLowerCase().contains(WordTyped.toLowerCase()))
                            || (p.getDateDeNaissance().toString().contains(WordTyped.toLowerCase())) || (p.getSexe().toLowerCase().contains(WordTyped.toLowerCase()))
                            || (p.getVille().toLowerCase().contains(WordTyped.toLowerCase()))
                            || (p.getCategorie().toLowerCase().contains(WordTyped.toLowerCase()))) {
                        FiltreJou.add(p);
                    }
                }
                TableViewJoueur.setItems(FiltreJou);
            }
        }
    }

    private void showDialogModifierJoueur() {

        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        //imageContainer.toFront();
        containerAjouterJoueur.setVisible(true);
        // btnSaveReclam.setDisable(false);

        dialogAjouterJoueur = new JFXDialogTool(containerAjouterJoueur, stckJoueur);
        dialogAjouterJoueur.show();
        dialogAjouterJoueur.setOnDialogOpened(ev -> {
            txtNomJoueur.requestFocus();
        });

        dialogAjouterJoueur.setOnDialogClosed(ev -> {
            closeStage();
            TableViewJoueur.setDisable(false);
            rootJoueur.setEffect(null);
            containerAjouterJoueur.setVisible(false);
            LoadTableJoueur();
        });
    }

    private void showDialogDeleteJoueur() {
        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteJoueur.setVisible(true);

        dialogDeleteJoueur = new JFXDialogTool(ContainerDeleteJoueur, stckJoueur);
        dialogDeleteJoueur.show();

        dialogDeleteJoueur.setOnDialogClosed(ev -> {
            TableViewJoueur.setDisable(false);
            rootJoueur.setEffect(null);
            ContainerDeleteJoueur.setVisible(false);
            LoadTableJoueur();

        });

    }

    @FXML
    private void iconAddJoueurClicked(MouseEvent event) {
        //RemplirComboCategorie();
        if (ImagePath == "") {
            DragimgRec.setImage(new Image(getClass().getResource("/ressource/drag-drop.gif").toExternalForm()));
        }
        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        textTitreUser.setText("Ajouter une Joueur");
        containerAjouterJoueur.setVisible(true);
        btnSaveJoueur.setDisable(false);
        btnModifierJoueur.setVisible(true);
        btnSaveJoueur.toFront();
        dialogAjouterJoueur = new JFXDialogTool(containerAjouterJoueur, stckJoueur);
        dialogAjouterJoueur.show();
        dialogAjouterJoueur.setOnDialogOpened(ev -> {
            txtNomJoueur.requestFocus();
        });
        dialogAjouterJoueur.setOnDialogClosed(ev -> {
            closeStage();
            TableViewJoueur.setDisable(false);
            rootJoueur.setEffect(null);
            containerAjouterJoueur.setVisible(false);
            LoadTableJoueur();
        });
    }

    @FXML
    private void deleteJoueurClicked(MouseEvent event) {
        if (TableViewJoueur.getSelectionModel().getSelectedItem() != null) {
            Joueur = TableViewJoueur.getSelectionModel().getSelectedItem();
            Boolean result = JoueurCrud.SupprimerJoueur(Joueur.getIdJoueur());
            if (result) {
                hideDialogDeleteJoueur();
                AlertsBuilder.create(AlertType.SUCCES, stckJoueur, rootJoueur, TableViewJoueur, "Nom: " + Joueur.getNomJoueur() + " " + Joueur.getPrenomJoueur() + " \n" + "a été supprimé");
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }
    }

    @FXML
    private void AjouterJoueur(MouseEvent event) {

        if (txtNomJoueur.getText().isEmpty()) {
            txtNomJoueur.requestFocus();
            Animations.shake(txtNomJoueur);
            return;
        }
        if (txtPrenomJoueur.getText().isEmpty()) {
            txtPrenomJoueur.requestFocus();
            Animations.shake(txtPrenomJoueur);
            return;
        }
        if (txtLocationJoueur.getText().isEmpty()) {
            txtLocationJoueur.requestFocus();
            Animations.shake(txtLocationJoueur);
            return;
        }
        if (txtDateNaissanceJoueur.getEditor().getText().isEmpty()) {
            txtDateNaissanceJoueur.requestFocus();
            Animations.shake(txtDateNaissanceJoueur);
            return;
        }
        if (comboCategorie.getSelectionModel().getSelectedItem() == null) {
            comboCategorie.requestFocus();
            Animations.shake(comboCategorie);
            return;
        }
        if (comboSexeJoueur.getSelectionModel().getSelectedItem() == null) {
            comboSexeJoueur.requestFocus();
            Animations.shake(comboSexeJoueur);
            return;
        }
        Joueur.setMyid(idUserConnected);
        Joueur.setNomJoueur(txtNomJoueur.getText());
        Joueur.setPrenomJoueur(txtPrenomJoueur.getText());
        Joueur.setDateDeNaissance(java.sql.Date.valueOf(txtDateNaissanceJoueur.getValue()));
        Joueur.setSexe(comboSexeJoueur.getSelectionModel().getSelectedItem());
        idCategorieSelected = comboCategorie.getSelectionModel().getSelectedItem().substring(0, 3);
        Joueur.setCategorie(idCategorieSelected); // idCategorieSelected
        Joueur.setVille(txtLocationJoueur.getText());
        Joueur.setAge(Integer.valueOf(txtAgeJoueur.getText()));
        /////////////////////////
        Joueur.setImgJ(path);
        DragimgRec.setImage(new Image(getClass().getResource("/ressource/drag-drop.gif").toExternalForm()));
        path = "";
        ImagePath = "";
        ///////////////////////
        boolean result = JoueurCrud.AjouterJoueur(Joueur); // Fonction Ajout
        if (result) {
            AlertsBuilder.create(AlertType.SUCCES, stckJoueur, containerAjouterJoueur, containerAjouterJoueur, "Joueur Added With Sucees !");
            txtNomJoueur.clear();
            comboCategorie.getSelectionModel().clearSelection();
            comboSexeJoueur.getSelectionModel().clearSelection();
            closeDialogAddJoueur();

        } else {
            AlertsBuilder.create(AlertType.ERROR, stckJoueur, containerAjouterJoueur, rootJoueur, Constants.MESSAGE_Error);
        }

    }

    @FXML
    private void ModifierJoueur(MouseEvent event) {

        int id = 0;
        if (TableViewJoueur.getSelectionModel().getSelectedItem() != null) {
            id = Integer.valueOf((TableViewJoueur.getSelectionModel().getSelectedItem().getIdJoueur()));
        }

        if (txtNomJoueur.getText().isEmpty()) {
            txtNomJoueur.requestFocus();
            Animations.shake(txtNomJoueur);
            return;
        }
        if (txtPrenomJoueur.getText().isEmpty()) {
            txtPrenomJoueur.requestFocus();
            Animations.shake(txtPrenomJoueur);
            return;
        }
        if (txtLocationJoueur.getText().isEmpty()) {
            txtLocationJoueur.requestFocus();
            Animations.shake(txtLocationJoueur);
            return;
        }
        if (txtDateNaissanceJoueur.getEditor().getText().isEmpty()) {
            txtDateNaissanceJoueur.requestFocus();
            Animations.shake(txtDateNaissanceJoueur);
            return;
        }
        if (comboCategorie.getSelectionModel().getSelectedItem() == null) {
            comboCategorie.requestFocus();
            Animations.shake(comboCategorie);
            return;
        }
        if (comboSexeJoueur.getSelectionModel().getSelectedItem() == null) {
            comboSexeJoueur.requestFocus();
            Animations.shake(comboSexeJoueur);
            return;
        }
        Joueur.setMyid(idUserConnected);
        Joueur.setIdJoueur(id);
        Joueur.setNomJoueur(txtNomJoueur.getText());
        Joueur.setPrenomJoueur(txtPrenomJoueur.getText());
        Joueur.setDateDeNaissance(java.sql.Date.valueOf(txtDateNaissanceJoueur.getValue()));
        Joueur.setSexe(comboSexeJoueur.getSelectionModel().getSelectedItem());
        idCategorieSelected = comboCategorie.getSelectionModel().getSelectedItem().substring(0, 3);
        Joueur.setCategorie(idCategorieSelected); // idCategorieSelected
        Joueur.setVille(txtLocationJoueur.getText());
        Joueur.setAge(Integer.valueOf(txtAgeJoueur.getText()));
        /////////////////////////
        Joueur.setImgJ(path);
        DragimgRec.setImage(new Image(getClass().getResource("/ressource/drag-drop.gif").toExternalForm()));
        path = "";
        ImagePath = "";
        System.out.println("===========================>>>>>>>>>>>>>>>>>>> " + idCategorieSelected);
        ///////////////////////
        Boolean result = JoueurCrud.ModifierJoueur(Joueur);

        if (result) {
            closeDialogAddJoueur();
            AlertsBuilder.create(AlertType.SUCCES, stckJoueur, rootJoueur, TableViewJoueur, Constants.MESSAGE_UPDATED);
            DragimgRec.setImage(new Image(getClass().getResource("/ressource/drag-drop.gif").toExternalForm()));
            path = "";
            ImagePath = "";
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        LoadTableJoueur();
    }

    @FXML
    private void closeDialogAddJoueur() {

        if (dialogAjouterJoueur != null) {
            dialogAjouterJoueur.close();
            ImagePath = "";
            btnModifierJoueur.setVisible(true);
            btnCancelAddJoueur.setVisible(true);
        }
        txtNomJoueur.clear();
        txtPrenomJoueur.clear();
        txtLocationJoueur.clear();
        txtDateNaissanceJoueur.setValue(null);
        comboCategorie.getSelectionModel().clearSelection();
        comboSexeJoueur.getSelectionModel().clearSelection();
        LoadTableJoueur();
    }

    @FXML
    private void closeDialogAjouterJoueur(MouseEvent event) {

        if (dialogAjouterJoueur != null) {
            dialogAjouterJoueur.close();
            ImagePath = "";
            btnModifierJoueur.setVisible(true);
            btnCancelAddJoueur.setVisible(true);
        }
        txtNomJoueur.clear();
        txtPrenomJoueur.clear();
        txtLocationJoueur.clear();
        txtDateNaissanceJoueur.setValue(null);
        comboCategorie.getSelectionModel().clearSelection();
        comboSexeJoueur.getSelectionModel().clearSelection();
        LoadTableJoueur();
    }

    @FXML
    private void hideDialogDeleteJoueur() {
        if (dialogDeleteJoueur != null) {
            dialogDeleteJoueur.close();
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

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void handleDragOver_reclamation(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop_reclamation(DragEvent event) throws FileNotFoundException {
        //File dest = new File("C:\\wamp64\\www\\BruteForce\\Web\\Uploads\\images");
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        DragimgRec.setImage(img);
        path = files.get(0).getAbsolutePath();
        System.out.println(files.get(0));

        //FileUtils.copyFileToDirectory(files.get(0), dest);
    }

    @FXML
    private void MetierAGE(ActionEvent event) {
        int age = 0;
        int year = 0, month = 0, days = 0;
        if (stage != null) {
            if (!txtDateNaissanceJoueur.getEditor().getText().isEmpty()) {
                year = txtDateNaissanceJoueur.getValue().getYear();
                month = txtDateNaissanceJoueur.getValue().getMonthValue();
                days = txtDateNaissanceJoueur.getValue().getDayOfMonth();
                age = LocalDate.now().minus(Period.of(year, month, days)).getYear();
                txtAgeJoueur.setText(String.valueOf(age));
            }
        }
    }

    public static Image encode(String data, int width, int height, String foregroundColor, String backgroundColor) {
        try {
            BitMatrix byteMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.decode(backgroundColor));
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.decode(foregroundColor));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void showDialogCodeQR() {

        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerCodeQR.setVisible(true);

        dialogCodeQR = new JFXDialogTool(ContainerCodeQR, stckJoueur);
        dialogCodeQR.show();

        dialogCodeQR.setOnDialogClosed(ev -> {
            closeStage();
            TableViewJoueur.setDisable(false);
            rootJoueur.setEffect(null);
            ContainerCodeQR.setVisible(false);
            LoadTableJoueur();
        });
    }

}
