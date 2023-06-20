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
import javafx.scene.shape.Line;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.metal.MetalBorders.ScrollPaneBorder;
import javax.swing.text.LabelView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.Screen;
import javafx.scene.layout.Region;

public class AppliVAE extends Application{
    private BorderPane banniere;

    private TextField recherche;

    private Button message;

    private Button favoris;

    private Button user;

    private Button home;

    private BorderPane fenetre;

    private Pane panelCentral;

    private String couleur;


    @Override
    public void init() {
        this.banniere = new BorderPane();
        this.couleur = "9370DB";
    }

    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        this.fenetre = new BorderPane();
        this.fenetre.setTop(this.bandeau());
        this.fenetre.setCenter(this.panelCentral);
        return new Scene(this.fenetre);
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
        //this.home.setOnAction();
        
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


    public void modeAccueil() {
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
        
        for (int i = 1; i <= 10; i++) {
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

            vButton.getChildren().addAll(imgO,titre,boxPrix,boxTemps);
            vButton.setSpacing(10);

            Button button = new Button();
            button.setGraphic(vButton);


            button.setStyle("-fx-background-color: #"+this.couleur+"; -fx-background-radius: 25px");

            button.setPrefSize(270, 320);
            container.getChildren().add(button);
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
        
        for (int i = 1; i <= 5; i++) {

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

            vButton.getChildren().addAll(imgO,titre,boxPrix,boxTemps);
            vButton.setSpacing(10);

            Button button = new Button();
            button.setGraphic(vButton);


            button.setStyle("-fx-background-color: #"+this.couleur+"; -fx-background-radius: 25px");

            button.setPrefSize(270, 320);
            container2.getChildren().add(button);
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
    

    public void majAffichage(){
        this.fenetre.setCenter(this.panelCentral);
    }

    /**
     * créer le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("VAE");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); // Retrieve screen dimensions
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        
        stage.setScene(this.laScene());
        this.modeAccueil(); // Appel de la méthode modeAccueil() avant la création de la scène
        this.majAffichage();
        stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
    }  
}