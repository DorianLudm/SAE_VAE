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

    private Button panier;

    private Button utilisateur;

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

        this.panier = new Button();
        this.panier.setGraphic(new ImageView(new Image("file:img/panier.png", 50, 50, true, true)));
        this.panier.setStyle("-fx-background-color: #"+this.couleur+";");
        //this.panier.setOnAction();

        this.utilisateur = new Button();
        this.utilisateur.setGraphic(new ImageView(new Image("file:img/user.png", 50, 50, true, true)));
        this.utilisateur.setStyle("-fx-background-color: #"+this.couleur+";");
        //this.utilisateur.setOnAction();
        
        // Création d'un conteneur horizontal pour les boutons
        HBox boutonsContainer = new HBox(10);


        boutonsContainer.getChildren().addAll(icon,this.recherche, this.message, this.favoris, this.panier, this.utilisateur);
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


        prixPane.setStyle("-fx-background-color: #"+this.couleur+"; -fx-background-radius: 10px;");

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

        Label recommandation = new Label("Ce qui pourrait vous intéressez");
        recommandation.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        recommandation.setTextFill(Color.web("#"+this.couleur));
        recommandation.setPadding(new Insets(15));

        HBox container = new HBox();
        
        for (int i = 1; i <= 10; i++) {
            Button button = new Button("Button " + i);
            button.setPrefSize(245, 220);
            container.getChildren().add(button);
        }
       

        ScrollPane scrollPaneR = new ScrollPane();
        scrollPaneR.setContent(container);
        scrollPaneR.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneR.setPrefSize(1200,250);



        Label actu = new Label("Fil d'actu");
        actu.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        actu.setTextFill(Color.web("#"+this.couleur));
        actu.setPadding(new Insets(15));

        HBox container2 = new HBox();
        
        for (int i = 1; i <= 10; i++) {
            Button button = new Button("Button " + i);
            button.setPrefSize(245, 220);
            container2.getChildren().add(button);
        }
       

        ScrollPane scrollPaneA = new ScrollPane();
        scrollPaneA.setContent(container2);
        scrollPaneA.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneA.setPrefSize(1200,250);

        gauche.getChildren().addAll(recommandation, scrollPaneR, actu, scrollPaneA);







        VBox droite = new VBox();
        droite.setPadding(new Insets(0,100,50,50));


        Label vente = new Label("Vos ventes");
        vente.setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
        vente.setTextFill(Color.web("#"+this.couleur));
        vente.setPadding(new Insets(15));

        VBox container3 = new VBox();
        
        for (int i = 1; i <= 10; i++) {
            Button button = new Button("Button " + i);
            button.setPrefSize(260, 220);
            container3.getChildren().add(button);
        }

        ScrollPane scrollPaneO = new ScrollPane();
        scrollPaneO.setContent(container3);
        scrollPaneO.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPaneO.setPrefSize(300,600);
        scrollPaneO.setPannable(true);



        droite.getChildren().addAll(vente,scrollPaneO);







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