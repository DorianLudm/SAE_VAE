import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ConnexionIHM extends Application{
    private TextField tf1; //NomUt
    private PasswordField tf2; //Password
    private TextField tf3; //Email
    private GridPane mainPane;
    private Stage stage;
    private ConnexionBD sql;
    private static final double BUTTON_HEIGHT = 150;

    public ConnexionIHM(){
        super();
        this.tf1 = new TextField();
        this.tf2 = new PasswordField();
        this.tf3 = new TextField();
    }

    @Override
    public void init(){
        try{
            this.sql = new ConnexionBD();
            this.sql.connecter();
        }
        catch(SQLException sqlE){
            System.out.println("Erreur lors du chargement de la base");
        }
        catch(ClassNotFoundException sqlE){
            System.out.println("Erreur lors du chargement de la base");
        }
    }

    @Override
    public void start(Stage stage){
        GridPane root = new GridPane();
        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        root.setBackground(new Background(backGround));

        //Ajout des bouttons
        Button user = new Button();
        ImageView view = new ImageView(new Image("file:img/User.png"));
        view.setFitHeight(BUTTON_HEIGHT);
        view.setPreserveRatio(true);
        user.setGraphic(view);
        user.setContentDisplay(ContentDisplay.TOP);
        user.setOnAction(new GestionConnexion(this, this.sql));
        user.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-width: 5em;" +
                "-fx-border-radius:50%;"
        );
        VBox box = new VBox();
        Button connexion = new Button("Démarrer!");
        connexion.setOnAction(new GestionConnexion(this, this.sql));
        box.getChildren().add(connexion);
        box.setAlignment(Pos.CENTER);
        //Ajout des éléments à root
        root.add(user,0,0);
        root.add(box,0,1);
        root.setAlignment(Pos.CENTER);
        //Ajout de la GridPane dans l'attribut mainPane
        this.mainPane = root;
        this.stage = stage;

        //Affichage de la fenêtre
        this.afficherFenetre();
    }

    public void connection(){
        GridPane root = new GridPane();
        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        root.setBackground(new Background(backGround));
        //Création de la boite principale
        VBox mainBox = new VBox();
        BackgroundImage backGround2 = new BackgroundImage(new Image("file:img/Background2.png",254,311,false,true), null, null, null, null);
        mainBox.setBackground(new Background(backGround2));
        mainBox.setPrefWidth(254);
        mainBox.setPrefHeight(311);
        Label username = new Label("Nom d'utilisateur");
        username.setFont(Font.font("Arial",FontWeight.BOLD,18));
        username.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(username, new Insets(120, 40, 5, 10));
        this.tf1 = new TextField();
        VBox.setMargin(this.tf1, new Insets(0, 40, 10, 10));
        Label password = new Label("Mot de passe");
        password.setFont(Font.font("Arial",FontWeight.BOLD,18));
        password.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(password, new Insets(0, 40, 5, 10));
        this.tf2 = new PasswordField();
        VBox.setMargin(this.tf2, new Insets(0, 40, 10, 10));
        //Création de la boite des boutons
        HBox box = new HBox();
        Button connexion = new Button("Connexion");
        connexion.setOnAction(new GestionConnexion(this, this.sql));
        HBox.setMargin(connexion, new Insets(1));
        Button inscription = new Button("Inscrivez-vous!");
        inscription.setOnAction(new GestionSwapConnexion(this));
        HBox.setMargin(inscription, new Insets(1));
        box.getChildren().addAll(connexion,inscription);
        VBox.setMargin(box, new Insets(0, 35, 10, 10));
        mainBox.getChildren().addAll(username,this.tf1,password,this.tf2,box);
        

        //Ajout des éléments à root
        root.add(mainBox,0,0);
        root.setAlignment(Pos.CENTER);

        //Ajout de la GridPane dans l'attribut mainPane
        this.mainPane = root;

        //Affichage de la fenêtre
        this.afficherFenetre();
    }

    public void inscription(){
        GridPane root = new GridPane();
        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        root.setBackground(new Background(backGround));

        VBox mainBox = new VBox();
        BackgroundImage backGround2 = new BackgroundImage(new Image("file:img/BgInscription.png",254,400,false,true), null, null, null, null);
        mainBox.setBackground(new Background(backGround2));
        mainBox.setPrefWidth(254);
        mainBox.setPrefHeight(400);
        //Username
        Label username = new Label("Nom d'utilisateur");
        username.setFont(Font.font("Arial",FontWeight.BOLD,18));
        username.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(username, new Insets(120, 40, 5, 10));
        this.tf1 = new TextField();
        VBox.setMargin(this.tf1, new Insets(0, 40, 10, 10));
        //PassWord
        Label password = new Label("Mot de passe");
        password.setFont(Font.font("Arial",FontWeight.BOLD,18));
        password.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(password, new Insets(0, 40, 5, 10));
        this.tf2 = new PasswordField();
        VBox.setMargin(this.tf2, new Insets(0, 40, 10, 10));
        //Mail
        Label mail = new Label("Adresse Mail");
        mail.setFont(Font.font("Arial",FontWeight.BOLD,18));
        mail.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(mail, new Insets(0, 40, 5, 10));
        this.tf3 = new TextField();
        VBox.setMargin(this.tf3, new Insets(0, 40, 10, 10));
        //Button
        Button inscription = new Button("Inscription");
        inscription.setOnAction(new GestionConnexion(this, this.sql));
        VBox.setMargin(inscription, new Insets(0, 40, 10, 10));
        //Lower
        Label text = new Label("Vous avez déjà un compte ?");
        text.setTextFill(Color.web("#ffffff"));
        text.setFont(Font.font("Arial",FontWeight.BOLD,12));
        VBox.setMargin(text, new Insets(0, 40, 2, 10));
        Button connexion = new Button("Connectez-vous!");
        connexion.setOnAction(new GestionSwapConnexion(this));
        VBox.setMargin(connexion, new Insets(0, 40, 10, 10));
        mainBox.getChildren().addAll(username,this.tf1,password,this.tf2,mail,this.tf3,inscription,text,connexion);        
        //Ajout des éléments à root
        root.add(mainBox,0,0);
        root.setAlignment(Pos.CENTER);

        //Ajout de la GridPane dans l'attribut mainPane
        this.mainPane = root;

        //Affichage de la fenêtre
        this.afficherFenetre();
    }

    public String getNomUt(){
        return this.tf1.getText();
    }

    public String getPassword(){
        return this.tf2.getText();
    }

    public String getMail(){
        return this.tf3.getText();
    }

    public void afficherFenetre(){
        //Affichage de la fenêtre
        Scene scene = new Scene(this.mainPane,400,650);
        this.stage.setScene(scene);
        this.stage.setTitle("Fenêtre de connexion");
        this.stage.show();
        this.stage.setResizable(false);
    }

    public Alert erreurSQL() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ErreurSQL");
		alert.setContentText("L'application n'a pas réussi à se connecter à la base de données!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
	}

    public Alert erreurConnexion() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erreur Connexion");
		alert.setContentText("Erreur de connexion!\nVeuillez vérifier votre nom et votre mot de passe!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
	}

    public void mainPage(Utilisateur user){
        System.out.println(user.toString());
    }

    public void setConnexion(ConnexionBD connexion){
        this.sql = connexion;
    }

    public static void main(String[] args) {
        launch(ConnexionIHM.class, args);
    }
}