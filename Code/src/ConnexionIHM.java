import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.SQLException;


public class ConnexionIHM extends GridPane{
    /**
    Le TextField dans lequel l'utilisateur rentre son pseudo 
    */
    private TextField username;

    /**
    Le TextField dans lequel l'utilisateur rentre son mot de passe
    */
    private PasswordField password;

    /**
    Le TextField dans lequel l'utilisateur rentre son mail lors de l'inscription
    */
    private TextField mail;

    /**
    Le stage de l'application
    */
    private Stage stage;
    /**
    La connexion vers la BD
    */
    private ConnexionBD sql;

    /**
    Le bouton utilisé pour naviguer entre les interfaces
    */
    private Button swapper;

    /**
    Le boutton utilisé pour se connecter ou s'inscrire
    */
    private Button connecter;

    /**
    Les informations de l'utilisateur qui s'est connecté/inscrit
    */
    private Utilisateur user;

    /**
    La taille max des boutons
    */

    private static final double BUTTON_HEIGHT = 150;
    private VBox mainBox;
    private AppliVAE appliVAE;



    public ConnexionIHM(){
        super();
        this.username = new TextField();
        this.password = new PasswordField();
        this.mail = new TextField();
        this.swapper = new Button("");
        this.connecter = new Button("");
        this.mainBox = new VBox();
        this.appliVAE = new AppliVAE();
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
        swapper.setOnAction(new GestionSwapConnexion(this));
        connecter.setOnAction(new GestionConnexion(this, this.sql, this.appliVAE));

        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        this.setBackground(new Background(backGround));

        //Ajout des boutons
        Button user = new Button();
        ImageView view = new ImageView(new Image("file:img/User.png"));
        view.setFitHeight(BUTTON_HEIGHT);
        view.setPreserveRatio(true);
        user.setGraphic(view);
        user.setContentDisplay(ContentDisplay.TOP);
        user.setOnAction(new GestionConnexion(this, this.sql, this.appliVAE));
        user.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-width: 5em;" +
                "-fx-border-radius:50%;"
        );
        VBox box = new VBox();
        this.connecter.setText("Démarrer!");
        box.getChildren().add(this.connecter);
        box.setAlignment(Pos.CENTER);

        //Ajout des éléments à this
        this.add(user,0,0);
        this.add(box,0,1);
        this.setAlignment(Pos.CENTER);

        //Ajout de la GridPane dans l'attribut mainPane
        // this.mainPane = this;
        this.stage = stage;

        //Affichage de la fenêtre
        // this.afficherFenetre();
    }

    /**
    * Affiche la page de connection
    */
    public void connection(){
        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        this.setBackground(new Background(backGround));

        //Création de la boite principale
        BackgroundImage backGround2 = new BackgroundImage(new Image("file:img/Background2.png",254,311,false,true), null, null, null, null);
        this.mainBox.setBackground(new Background(backGround2));

        // this.mainBox = new VBox();
        this.mainBox.getChildren().clear();
        this.mainBox.setPrefWidth(254);
        this.mainBox.setPrefHeight(311);
        Label username = new Label("Nom d'utilisateur");
        username.setFont(Font.font("Arial",FontWeight.BOLD,18));
        username.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(username, new Insets(120, 40, 5, 10));
        this.username = new TextField();
        VBox.setMargin(this.username, new Insets(0, 40, 10, 10));
        Label password = new Label("Mot de passe");
        password.setFont(Font.font("Arial",FontWeight.BOLD,18));
        password.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(password, new Insets(0, 40, 5, 10));
        this.password = new PasswordField();
        VBox.setMargin(this.password, new Insets(0, 40, 10, 10));

        //Création de la boite des boutons
        HBox box = new HBox();
        this.connecter.setText("Connexion");
        HBox.setMargin(this.connecter, new Insets(1));
        this.swapper.setText("Inscrivez-vous!");
        HBox.setMargin(this.swapper, new Insets(1));
        box.getChildren().addAll(this.connecter,this.swapper);
        VBox.setMargin(box, new Insets(0, 35, 10, 10));
        this.mainBox.getChildren().addAll(username,this.username,password,this.password,box);
        

        //Ajout des éléments à this
        this.getChildren().clear();
        this.add(this.mainBox,0,0);
        this.setAlignment(Pos.CENTER);

        //Ajout de la GridPane dans l'attribut mainPane
        // this.mainPane = this;

        //Affichage de la fenêtre
        // this.afficherFenetre();
    }

    /**
    * Affiche la page d'inscription
    */
    public void inscription(){
        BackgroundImage backGround = new BackgroundImage(new Image("file:img/BackgroundWithLogo.png",400,650,false,true), null, null, null, null);
        this.setBackground(new Background(backGround));

        // this.mainBox = new VBox();
        this.mainBox.getChildren().clear();
        BackgroundImage backGround2 = new BackgroundImage(new Image("file:img/BgInscription.png",254,400,false,true), null, null, null, null);
        this.mainBox.setBackground(new Background(backGround2));
        this.mainBox.setPrefWidth(254);
        this.mainBox.setPrefHeight(400);


        //Username
        Label username = new Label("Nom d'utilisateur");
        username.setFont(Font.font("Arial",FontWeight.BOLD,18));
        username.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(username, new Insets(120, 40, 5, 10));
        this.username = new TextField();
        VBox.setMargin(this.username, new Insets(0, 40, 10, 10));

        //PassWord
        Label password = new Label("Mot de passe");
        password.setFont(Font.font("Arial",FontWeight.BOLD,18));
        password.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(password, new Insets(0, 40, 5, 10));
        this.password = new PasswordField();
        VBox.setMargin(this.password, new Insets(0, 40, 10, 10));

        //Mail
        Label mail = new Label("Adresse Mail");
        mail.setFont(Font.font("Arial",FontWeight.BOLD,18));
        mail.setTextFill(Color.web("#ffffff"));
        VBox.setMargin(mail, new Insets(0, 40, 5, 10));
        this.mail = new TextField();
        VBox.setMargin(this.mail, new Insets(0, 40, 10, 10));

        //Button
        this.connecter.setText("Inscription");
        VBox.setMargin(this.connecter, new Insets(0, 40, 10, 10));

        //Lower
        Label text = new Label("Vous avez déjà un compte ?");
        text.setTextFill(Color.web("#ffffff"));
        text.setFont(Font.font("Arial",FontWeight.BOLD,12));
        VBox.setMargin(text, new Insets(0, 40, 2, 10));
        this.swapper.setText("Connectez-vous!");
        VBox.setMargin(this.swapper, new Insets(0, 40, 10, 10));
        this.mainBox.getChildren().addAll(username,this.username,password,this.password,mail,this.mail,this.connecter,text,this.swapper);

        //Ajout des éléments à this
        this.getChildren().clear();
        this.add(this.mainBox,0,0);
        this.setAlignment(Pos.CENTER);

        //Ajout de la GridPane dans l'attribut mainPane
        // this.mainPane = this;

        //Affichage de la fenêtre
        // this.afficherFenetre();
    }

    /**
    * Obtient le nom d'utilisateur entrée dans le textfield
    * @return Le nom de l'utilisateur 
    */
    public String getNomUt(){
        return this.username.getText();
    }

    /**
    * Obtient le mot de passe de l'utilisateur entrée dans le textfield
    * @return Le mot de passe de l'utilisateur
    */
    public String getPassword(){
        return this.password.getText();
    }

    /**
    * Obtient l'addresse mail de l'utilisateur entrée dans le textfield
    * @return L'addresse mail de l'utilisateur 
    */
    public String getMail(){
        return this.mail.getText();
    }

    /**
    * Affiche la fenêtre
    */
    // public void afficherFenetre(){
    //     //Affichage de la fenêtre
    //     Scene scene = new Scene(this,400,650);
    //     this.stage.setScene(scene);
    //     this.stage.setTitle("Fenêtre de connexion");
    //     this.stage.show();
    //     this.stage.setResizable(false);
    // }


    /**
    * Utilisée pour affiché un pop-up lorsqu'une erreur SQL intervient
    * @return L'alerte pour l'erreur du SQL
    */
    public Alert erreurSQL() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ErreurSQL");
		alert.setContentText("L'application n'a pas réussi à se connecter à la base de données!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
	}

    /**
    * Utilisée pour affiché un pop-up lorsqu'une erreur de connexion utilisateur intervient
    * @return L'alerte lorsque les détails de la connexion ne sont pas valides
    */
    public Alert erreurConnexion() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erreur Connexion");
		alert.setContentText("Erreur de connexion!\nVeuillez vérifier votre nom et votre mot de passe!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
	}

    /**
    * Envoie vers la page d'acceuil de VAE
    * @param Utitlisateur l'utilisateur qui s'est connecté
    */
    public void mainPage(Utilisateur user){
        this.user = user;
    }

    public Utilisateur getUser(){
        return this.user;
    }

}