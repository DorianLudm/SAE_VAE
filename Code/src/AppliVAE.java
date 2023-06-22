import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData ;
import javafx.scene.control.ButtonType ;
import java.util.List;


import java.util.Arrays;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.scene.control.ListCell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javax.swing.plaf.LabelUI;
import javax.swing.text.LabelView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.Screen;
import javafx.scene.layout.Region;


public class AppliVAE extends Application{

    private Scene scene;

    private BorderPane banniere;

    private TextField recherche;

    private Button message;

    private Button favoris;

    private Button user;

    private Button home;

    private BorderPane fenetre;

    private Pane panelCentral;

    private String couleur;

    private ConnexionIHM vueConnexion;

    private Stage stage;

    private ModeleVAE modele;



    @Override
    public void init() {
        
        this.banniere = new BorderPane();
        this.fenetre = new BorderPane();
        this.couleur = "9370db";
        this.vueConnexion = new ConnexionIHM(this);

        this.modele = new ModeleVAE(this.vueConnexion.getSQL(),this);

    }

    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        this.fenetre = new BorderPane();
        this.fenetre.setTop(this.bandeau());
        this.fenetre.setCenter(this.panelCentral);
        this.scene = new Scene(this.fenetre);
        return this.scene;
    }


    private BorderPane bandeau() {
        
        this.banniere.setStyle("-fx-background-color: #"+this.couleur+";");

        Label vae = new Label("VAE");
        vae.setFont(Font.font("Ubuntu", FontWeight.BOLD, 50));
        vae.setTextFill(Color.web("#FFFFFF"));
        vae.setPadding(new Insets(15));



        ImageView icon = new ImageView(new Image("file:img/loupe.png", 55, 55, true, true));


        this.recherche = new TextField();
        this.recherche.setPromptText("Rechercher");
        this.recherche.setPrefHeight(60);
        this.recherche.setPrefWidth(500);
        this.recherche.setStyle("-fx-background-radius: 30;-fx-font-size: 25px; -fx-prompt-text-fill: #9370DB;");
        

        this.message = new Button();
        this.message.setGraphic(new ImageView(new Image("file:img/message.png", 50, 50, true, true)));
        this.message.setStyle("-fx-background-color: #"+this.couleur+";");
        //this.message.setOnAction();

        this.favoris = new Button();
        this.favoris.setGraphic(new ImageView(new Image("file:img/favoris.png", 50, 50, true, true)));
        this.favoris.setStyle("-fx-background-color: #"+this.couleur+";");
        //this.favoris.setOnAction();

        this.user = new Button();
        this.user.setGraphic(new ImageView(new Image("file:img/user2.png", 50, 50, true, true)));
        this.user.setStyle("-fx-background-color: #"+this.couleur+";");
        //this.user.setOnAction();

        this.home = new Button();
        this.home.setGraphic(new ImageView(new Image("file:img/accueil.png", 50, 50, true, true)));
        this.home.setStyle("-fx-background-color: #"+this.couleur+";");
        this.home.setOnAction(new ControleurAccueil(this));
        
        // Création d'un conteneur horizontal pour les boutons
        HBox boutonsContainer = new HBox(10);


        boutonsContainer.getChildren().addAll(icon,this.recherche, this.message, this.favoris, this.user, this.home);
        boutonsContainer.setPadding(new Insets(15));
        
        // Alignement horizontal du titre et des boutons
        HBox.setHgrow(vae, Priority.ALWAYS);
        HBox.setHgrow(boutonsContainer, Priority.ALWAYS);
        
        // Ajout des éléments à la bannière
        banniere.setLeft(vae);
        banniere.setRight(boutonsContainer);
        
        return banniere;
    }

    public void modeAjout() throws SQLException{
        HBox res = new HBox();
        VBox gauche = new VBox();

        Label ajoutenchereL = new Label("Ajouter une enchère");
        ajoutenchereL.setStyle("-fx-text-fill: #"+this.couleur+";");
        ajoutenchereL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ajoutenchereL.setPadding(new Insets(20,10,20,10));

        HBox image1 = new HBox();
        ImageView img1 = new ImageView(new Image("file:img/app_photo.png", 250, 250, true, true));
        ImageView img2 = new ImageView(new Image("file:img/app_photo.png", 250, 250, true, true));
        Button img1B = new Button();
        img1B.setGraphic(img1);
        img1B.setStyle("-fx-background-color: #FFF5EE;-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        img1B.setOnAction(e -> openImageFile(img1));
        img1B.setPadding(new Insets(10,10,10,10));
        Button img2B = new Button();
        img2B.setGraphic(img2);
        img2B.setStyle("-fx-background-color: #FFF5EE;-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        img2B.setOnAction(e -> openImageFile(img2));
        img2B.setPadding(new Insets(10,10,10,10));
        image1.getChildren().addAll(img1B, img2B);
        image1.setPadding(new Insets(20,10,20,10));
        image1.setSpacing(10);

        HBox image2 = new HBox();
        ImageView img3 = new ImageView(new Image("file:img/app_photo.png", 250, 250, true, true));
        ImageView img4 = new ImageView(new Image("file:img/app_photo.png", 250, 250, true, true));
        Button img3B = new Button();
        img3B.setGraphic(img3);
        img3B.setStyle("-fx-background-color: #FFF5EE;-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        img3B.setOnAction(e -> openImageFile(img3));
        img3B.setPadding(new Insets(10,10,10,10));
        Button img4B = new Button();
        img4B.setGraphic(img4);
        img4B.setStyle("-fx-background-color: #FFF5EE;-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        img4B.setOnAction(e -> openImageFile(img4));
        img4B.setPadding(new Insets(10,10,10,10));
        image2.getChildren().addAll(img3B, img4B);
        image2.setPadding(new Insets(20,10,20,10));
        image2.setSpacing(10);

        ImageView plus = new ImageView(new Image("file:img/union.png", 100, 100, true, true));
        Button ajouterphoto = new Button("Ajouter une photo", plus);
        ajouterphoto.setStyle("-fx-background-color: #"+this.couleur+";-fx-text-fill: #FFFFFF;-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        ajouterphoto.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));

        List<ImageView> listeImage = new ArrayList<>();
        listeImage.add(img1);
        listeImage.add(img2);
        listeImage.add(img3);
        listeImage.add(img4);

        for (Object image : listeImage) {
            ajouterphoto.setOnAction(e -> openImageFile((ImageView)image));
        }

        gauche.getChildren().addAll(ajoutenchereL, image1, image2, ajouterphoto);
        gauche.setSpacing(10);
        gauche.setPadding(new Insets(0, 150, 0, 100));
        gauche.setStyle("-fx-border-width: 0 5px 0 0;-fx-border-color: #"+this.couleur+";-fx-border-style: solid;");
        gauche.setAlignment(Pos.CENTER);
  
        VBox droite = new VBox();
  
        HBox nom = new HBox();
        Label nomL = new Label("Nom de l'article :");
        nomL.setStyle("-fx-text-fill: #"+this.couleur+";");
        nomL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        nomL.setPadding(new Insets(0, 50, 0, 0));
        TextField nomT = new TextField();
        nomT.setStyle("-fx-border-color: #"+this.couleur+";-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        nom.getChildren().addAll(nomL, nomT);
        nom.setPadding(new Insets(20,10,20,10));
        nom.setAlignment(Pos.CENTER_LEFT);

        HBox prixdeb = new HBox();
        Label prixdebL = new Label("Prix de départ :");
        prixdebL.setStyle("-fx-text-fill: #"+this.couleur+";");
        prixdebL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        prixdebL.setPadding(new Insets(0, 50, 0, 0));
        TextField prixdebT = new TextField();
        prixdebT.setStyle("-fx-border-color: #"+this.couleur+";-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        prixdeb.getChildren().addAll(prixdebL, prixdebT);
        prixdeb.setPadding(new Insets(20,10,20,10));
        prixdeb.setAlignment(Pos.CENTER_LEFT);

        HBox prixmin = new HBox();
        Label prixminL = new Label("Prix de vente minimum :");
        prixminL.setStyle("-fx-text-fill: #"+this.couleur+";");
        prixminL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        prixminL.setPadding(new Insets(0, 50, 0, 0));
        TextField prixminT = new TextField();
        prixminT.setStyle("-fx-border-color: #"+this.couleur+";-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        prixmin.getChildren().addAll(prixminL, prixminT);
        prixmin.setPadding(new Insets(20,10,20,10));
        prixmin.setAlignment(Pos.CENTER_LEFT);

        HBox categorie = new HBox();
        Label categorieL = new Label("Catégories :");
        categorieL.setStyle("-fx-text-fill: #"+this.couleur+";");
        categorieL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        categorieL.setPadding(new Insets(0, 50, 0, 0));
        ComboBox<String> categorieC = new ComboBox<String>();
        for (String cate : this.modele.getCategorie()) {
            categorieC.getItems().add(cate);
        }
        categorieC.setStyle("-fx-background-color: #"+this.couleur+";-fx-text-fill: #FFFFFF;");
        categorieC.setValue("Choisissez une catégorie");
        categorie.getChildren().addAll(categorieL, categorieC);
        categorie.setPadding(new Insets(20,10,20,10));
        categorie.setAlignment(Pos.CENTER_LEFT);

        HBox etat = new HBox();
        Label etatL = new Label("Etat de l'article :");
        etatL.setStyle("-fx-text-fill: #"+this.couleur+";");
        etatL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        etatL.setPadding(new Insets(0, 50, 0, 0));
        String tbe = "Très bon état";
        String be = "Bon état";
        String c = "Correct";
        String me = "Mauvais état";
        String tme = "Très mauvais état";
        ComboBox<String> etatC = new ComboBox<String>();
        etatC.getItems().addAll(tbe,be,c,me,tme);
        etatC.setValue(tbe);
        etatC.setStyle("-fx-background-color: #"+this.couleur+";-fx-text-fill: #FFFFFF;");
        etat.setPadding(new Insets(20,10,20,10));
        etat.getChildren().addAll(etatL, etatC);
        etat.setAlignment(Pos.CENTER_LEFT);

        HBox desc = new HBox();
        Label description = new Label("Description de l'article :                                  ");
        description.setStyle("-fx-text-fill: #"+this.couleur+";");
        description.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        description.setPadding(new Insets(20,10,20,10));
        desc.getChildren().addAll(description);
        desc.setAlignment(Pos.CENTER_LEFT);


        TextArea descriptionT = new TextArea();
        descriptionT.setPadding(new Insets(8));
        descriptionT.setStyle("-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        descriptionT.setWrapText(true);

        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-background-color: #"+this.couleur+";-fx-text-fill: #FFFFFF;-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        ajouter.setPadding(new Insets(10,20,10,20));
        ajouter.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));

        droite.getChildren().addAll(nom, prixdeb, prixmin, categorie, etat, description, descriptionT, ajouter);
        droite.setSpacing(10);
        droite.setPadding(new Insets(0,0,0,300));
        droite.setAlignment(Pos.CENTER_RIGHT);


        res.getChildren().addAll(gauche, droite);

        this.panelCentral = res;
    }

    private void openImageFile(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString(), 250, 250, true, true);
            imageView.setImage(image);
        }
    }

    public void modeObjets(){
        
        HBox resO = new HBox();


        VBox gaucheO = new VBox();

        HBox image = new HBox();
        ImageView flecheGauche = new ImageView(new Image("file:img/Polygongauche.png", 50, 50, true, true));
        Button imgfg = new Button();
        imgfg.setGraphic(flecheGauche);
        imgfg.setStyle("-fx-background-color: #FFF5EE");
        ImageView imageObjet = new ImageView(new Image("file:img/test.gif", 700, 700, true, true));
        ImageView flechedroit = new ImageView(new Image("file:img/Polygondroit.png", 50, 50, true, true));
        Button imgfd = new Button();
        imgfd.setStyle("-fx-background-color: #FFF5EE");
        imgfd.setGraphic(flechedroit);
        image.getChildren().addAll(imgfg, imageObjet, imgfd);
        image.setAlignment(Pos.CENTER);

        HBox animation = new HBox();
        ImageView ani1 = new ImageView(new Image("file:img/Ellipse.png", 50, 50, true, true));
        ImageView ani2 = new ImageView(new Image("file:img/Rectangle.png", 50, 50, true, true));
        ImageView ani3 = new ImageView(new Image("file:img/Rectangle.png", 50, 50, true, true));
        ImageView ani4 = new ImageView(new Image("file:img/Rectangle.png", 50, 50, true, true));
        animation.getChildren().addAll(ani1, ani2, ani3, ani4);
        animation.setSpacing(10);
        animation.setAlignment(Pos.CENTER);

        HBox prix = new HBox();
        Label prixL = new Label("Prix actuel : ####");
        prixL.setStyle("-fx-text-fill: #"+this.couleur+";");
        prixL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ImageView prixI = new ImageView(new Image("file:img/euro2.png", 50, 50, true, true));
        prix.getChildren().addAll(prixL, prixI);
        prix.setSpacing(10);
        prix.setAlignment(Pos.CENTER_LEFT);
        prix.setPadding(new Insets(0, 0, 0, 60));

        
        Label surenchere = new Label("Surenchère minimal : +####");
        surenchere.setStyle("-fx-text-fill: #"+this.couleur+";");
        surenchere.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        surenchere.setPadding(new Insets(0, 0, 0, 60));

        HBox encherir = new HBox();
        HBox encherir1 = new HBox();
        Label encherirL = new Label("Entrez votre prix :");
        encherirL.setStyle("-fx-text-fill: #FFFFFF;");
        encherirL.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        TextField encherirT = new TextField();
        encherirT.setStyle("-fx-background-color: #"+this.couleur+";");
        encherirT.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
        encherir1.getChildren().addAll(encherirL, encherirT);
        encherir1.setStyle("-fx-background-color: #"+this.couleur+";-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        encherir1.setPadding(new Insets(10,10,10,10));
        Button encherirB = new Button("Enchérir");
        encherirB.setStyle("-fx-background-color: #"+this.couleur+";-fx-text-fill: #FFFFFF;-fx-border-width: 3px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        encherirB.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        encherir.getChildren().addAll(encherir1, encherirB);
        encherir.setSpacing(10);
        encherir.setAlignment(Pos.CENTER_LEFT);
        encherir.setPadding(new Insets(0, 0, 0, 60));

        gaucheO.getChildren().addAll(image, animation, prix, surenchere, encherir);
        gaucheO.setSpacing(10);
        gaucheO.setPadding(new Insets(0, 150, 0, 50));
        gaucheO.setAlignment(Pos.CENTER_LEFT);


        VBox droiteO = new VBox();

        Label nomArticle = new Label("####");
        nomArticle.setStyle("-fx-text-fill: #"+this.couleur+";");
        nomArticle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));

        HBox hbox1 = new HBox();
        Label ajouteLe = new Label("Ajouté le : ####");
        ajouteLe.setStyle("-fx-text-fill: #"+this.couleur+";");
        ajouteLe.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ImageView ajouteLeI = new ImageView(new Image("file:img/calendar.png", 50, 50, true, true));
        Label prixDep = new Label("Prix de départ : ####");
        prixDep.setStyle("-fx-text-fill: #"+this.couleur+";");
        prixDep.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ImageView prixDepI = new ImageView(new Image("file:img/euro2.png", 50, 50, true, true));
        hbox1.getChildren().addAll(ajouteLe, ajouteLeI, prixDep, prixDepI);
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER_LEFT);


        HBox hbox2 = new HBox();
        Label tempsRestant = new Label("Temps restant : ####");
        tempsRestant.setStyle("-fx-text-fill: #"+this.couleur+";");
        tempsRestant.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ImageView tempsRestantI = new ImageView(new Image("file:img/stopwatch.png", 50, 50, true, true));
        Label favoris = new Label("Favoris : ####");
        favoris.setStyle("-fx-text-fill: #"+this.couleur+";");
        favoris.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        ImageView favorisI = new ImageView(new Image("file:img/favourite-heart.png", 50, 50, true, true));
        hbox2.getChildren().addAll(tempsRestant, tempsRestantI, favoris, favorisI);
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.CENTER_LEFT);



        Label etatArticle = new Label("Etat de l'article : ####");
        etatArticle.setStyle("-fx-text-fill: #"+this.couleur+";");
        etatArticle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));




        Label description = new Label("Description :                                                                                        ");
        description.setStyle("-fx-text-fill: #"+this.couleur+";-fx-border-width: 5px 0 0 0;-fx-border-color: #"+this.couleur+";-fx-border-style: solid;");
        description.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));

        Label desctext = new Label("#######################################\n#######################################\n#######################################\n#######################################\n");
        desctext.setStyle("-fx-text-fill: #"+this.couleur+";-fx-border-color: #"+this.couleur+";-fx-border-width: 5px;-fx-border-radius: 30px;-fx-background-radius: 30px;");
        desctext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        desctext.setPadding(new Insets(10,10,10,10));

        droiteO.getChildren().addAll(nomArticle, hbox1, hbox2, etatArticle, description, desctext);
        droiteO.setSpacing(50);
        droiteO.setPadding(new Insets(50,0,0,0));
        droiteO.setAlignment(Pos.CENTER_LEFT);

        resO.getChildren().addAll(gaucheO, droiteO);

        this.panelCentral = resO;
    }

    public void modeAccueil() throws SQLException{
        BorderPane panel = new BorderPane();
        HBox top = new HBox();

        TitledPane prixPane = new TitledPane("Prix",null);
        prixPane.setExpanded(false);


        prixPane.setStyle("-fx-color: #"+this.couleur+"; -fx-background-radius: 10px;");



        VBox prixContent = new VBox();
        prixContent.setSpacing(10);
        TextField prixMinTextField = new TextField();
        prixMinTextField.setPromptText("Prix Min");
        TextField prixMaxTextField = new TextField();
        prixMaxTextField.setPromptText("Prix Max");
        prixContent.getChildren().addAll(prixMinTextField, prixMaxTextField);
        prixPane.setContent(prixContent);

        ComboBox<String> categoriesComboBox = new ComboBox<>();
        categoriesComboBox.getItems().addAll("Catégorie 1", "Catégorie 2", "Catégorie 3");
        categoriesComboBox.setPromptText("Catégories");
        categoriesComboBox.setStyle("-fx-background-color: #"+this.couleur+"; -fx-text-fill: #FFFFFF;");

        ComboBox<String> etatComboBox = new ComboBox<>();
        etatComboBox.getItems().addAll("Très bon état", "Bon état", "Correct", "Mauvais état", "Très mauvais état");
        etatComboBox.setPromptText("État");
        etatComboBox.setStyle("-fx-background-color: #"+this.couleur+"; -fx-text-fill: white;");

        ComboBox<String> filtreComboBox = new ComboBox<>();
        filtreComboBox.getItems().addAll("Filtre 1", "Filtre 2", "Filtre 3");
        filtreComboBox.setPromptText("Filtre");
        filtreComboBox.setStyle("-fx-background-color: #"+this.couleur+"; -fx-text-fill: white;");

        top.getChildren().addAll(prixPane, categoriesComboBox, etatComboBox, filtreComboBox);
        top.setSpacing(10);
        top.setPadding(new Insets(10));




        //Partie gauche

        VBox gauche = new VBox();
        gauche.setPadding(new Insets(0,10,50,100));
        gauche.setSpacing(5);
        gauche.setStyle("-fx-border-width: 0 5 0 0 ; -fx-border-color: #"+this.couleur+"; -fx-border-style: solid ;");

        Label recommandation = new Label("Ce qui pourrait vous intéressez");
        recommandation.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        recommandation.setTextFill(Color.web("#"+this.couleur));
        recommandation.setPadding(new Insets(15));

        HBox container = new HBox();
        
        for (Button but: this.modele.getEncheresRecentes(10,this.couleur)) {
            container.getChildren().add(but);
        }
       

        ScrollPane scrollPaneR = new ScrollPane();
        container.setSpacing(20);
        scrollPaneR.setContent(container);
        scrollPaneR.setStyle("-fx-background-color: transparent; -fx-background-color: linear-gradient(to right, transparent, white); -fx-border-width: 0 0 0 2 ; -fx-border-color: #"+this.couleur+"; -fx-border-style: solid ;");

        scrollPaneR.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneR.setVbarPolicy(ScrollBarPolicy.NEVER);

        scrollPaneR.setPrefSize(1200,600);



        Label actu = new Label("Fil d'actu");
        actu.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        actu.setTextFill(Color.web("#"+this.couleur));
        actu.setPadding(new Insets(15));

        HBox container2 = new HBox();
        
        for (Button but: this.modele.getEncheresRecentes(10,this.couleur)) {
            container2.getChildren().add(but);
        }
       

        ScrollPane scrollPaneA = new ScrollPane();
        scrollPaneA.setContent(container2);
        scrollPaneA.setStyle("-fx-background-color: transparent; -fx-background-color: linear-gradient(to right, transparent, white); -fx-border-width: 0 0 0 2 ; -fx-border-color: #"+this.couleur+"; -fx-border-style: solid ;");
        container2.setSpacing(20);
        scrollPaneA.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneA.setVbarPolicy(ScrollBarPolicy.NEVER);


        scrollPaneA.setPrefSize(1200,600);
        


        gauche.getChildren().addAll(recommandation, scrollPaneR, actu, scrollPaneA);






        VBox droite = new VBox();
        droite.setSpacing(10);
        droite.setPadding(new Insets(0,100,50,50));


        Label vente = new Label("Vos ventes");
        vente.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        vente.setTextFill(Color.web("#"+this.couleur));
        vente.setPadding(new Insets(15));

        VBox container3 = new VBox();
        
        for (int i = 1; i <= 10; i++) {
            
            HBox hButton = new HBox();
            
            
            VBox vButton = new VBox();
            ImageView imgO = new ImageView(new Image("file:img/app_photo.png", 200,200, true, true));
            Label titre = new Label("Nom de l'objet");
            titre.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
            titre.setTextFill(Color.web("#FFFFFF"));

            HBox boxPrix = new HBox();
            Label prix = new Label("prix de l'objet");
            prix.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
            prix.setTextFill(Color.web("#FFFFFF"));
            ImageView logoPrix = new ImageView(new Image("file:img/euro.png", 30, 30, true, true));
            boxPrix.getChildren().addAll(prix, logoPrix);

            HBox boxTemps = new HBox();
            Label temps = new Label("temps restant");
            temps.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
            temps.setTextFill(Color.web("#FFFFFF"));
            ImageView logoTemps = new ImageView(new Image("file:img/chrono.png", 30, 30, true, true));
            boxTemps.getChildren().addAll(temps, logoTemps);

            HBox boxFavoris = new HBox();
            Label favoris = new Label("Nombre de favoris");
            favoris.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
            favoris.setTextFill(Color.web("#FFFFFF"));
            ImageView logoFavoris = new ImageView(new Image("file:img/coeur.png", 30, 30, true, true));
            boxFavoris.getChildren().addAll(favoris, logoFavoris);

            vButton.getChildren().addAll(titre,boxPrix,boxTemps,boxFavoris);
            vButton.setSpacing(10);
            vButton.setAlignment(Pos.CENTER);
            

            hButton.getChildren().addAll(imgO,vButton);
            hButton.setAlignment(Pos.CENTER);
            hButton.setSpacing(10);

            Button button = new Button();
            // button.setOnAction(new ControleurObjet(this));
            button.setGraphic(hButton);


            button.setStyle("-fx-background-color: #"+this.couleur+"; -fx-background-radius: 25px");

            button.setPrefSize(410, 220);
            container3.getChildren().add(button);
        }

        ScrollPane scrollPaneO = new ScrollPane();
        scrollPaneO.setStyle("-fx-background-color: transparent;");
        scrollPaneO.setContent(container3);
        scrollPaneO.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneO.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPaneO.setPrefSize(450,600);
        scrollPaneO.setPannable(true);
        container3.setSpacing(10);


        HBox contenantBouton = new HBox();
        contenantBouton.setAlignment(Pos.CENTER);
        contenantBouton.setSpacing(10);
        ImageView plus = new ImageView(new Image("file:img/plus.png", 80, 80, true, true));
        Label ajout = new Label("Ajouter un Objet");
        ajout.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
        ajout.setTextFill(Color.web("#FFFFFF"));
        contenantBouton.getChildren().addAll(plus, ajout);

        Button boutonAjout = new Button();
        boutonAjout.setPrefSize(320, 120);
        boutonAjout.setGraphic(contenantBouton);
        boutonAjout.setStyle("-fx-background-color: #"+this.couleur+"; -fx-background-radius: 25px");
        boutonAjout.setOnAction(new ControleurNouvelleEnchere(this));

        droite.getChildren().addAll(vente,scrollPaneO,boutonAjout);

        panel.setTop(top);
        panel.setLeft(gauche);
        panel.setRight(droite);
    
        this.panelCentral = panel;
    }

    //public void modeUser()

    public void afficheFenetreConexion(){
        GridPane root = new ConnexionIHM(this);
        this.scene.setRoot(root);
    }

    public void majAffichage(){
        this.fenetre.setTop(this.banniere);
        this.fenetre.setCenter(this.panelCentral);
    }



    public void afficheApp() throws SQLException{
        if (this.stage == null) {
            this.stage = new Stage();
        }
    

    
        this.stage.setTitle("VAE");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.stage.setX(screenBounds.getMinX());
        this.stage.setY(screenBounds.getMinY());
        this.stage.setWidth(screenBounds.getWidth());
        this.stage.setHeight(screenBounds.getHeight());
    

        this.modeAccueil();
     
        this.scene = this.laScene();
    
        this.stage.setScene(this.scene);
        this.majAffichage();
        this.stage.show();
    }
    
    


    /**
     * créer le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage){
        GridPane root = new ConnexionIHM(this);
        this.scene = new Scene(root, 400, 650);
        this.stage = stage;

        this.stage.setTitle("VAE");
        // Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); // Retrieve screen dimensions
        // stage.setX(screenBounds.getMinX());
        // stage.setY(screenBounds.getMinY());
        // stage.setWidth(screenBounds.getWidth());
        // stage.setHeight(screenBounds.getHeight());
        
        this.stage.setScene(this.scene);
        
        this.majAffichage();
        this.stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
    }  
}