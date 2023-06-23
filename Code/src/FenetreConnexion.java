import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class FenetreConnexion extends Application{
    private TextField tf1;
    private PasswordField tf2;

    
    public FenetreConnexion(){
        super();
        this.tf1 = new TextField();
        this.tf2 = new PasswordField();
    }

    @Override
    public void init(){}

    @Override
    public void start(Stage stage){
        HBox root = new HBox();
        Pane window = this.showFenetre();
        root.getChildren().add(window);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Fenetre de connexion");
        stage.show();
    }

    public GridPane showFenetre(){
        GridPane grid = new GridPane();
        grid.add(new Label("Entrez vos identifiants"),0,0,3,1);
        grid.add(new Label("Identifiant"),0,1);
        grid.add(new TextField(),1,1);
        grid.add(new Label("Mot de passe"),0,2);
        grid.add(new PasswordField(),1,2);
        GridPane grid2 = new GridPane();
        Button buttonConnexion = new Button("Connexion");
        grid2.add(buttonConnexion,0,0);
        grid2.setAlignment(Pos.CENTER_RIGHT);
        grid.add(grid2,1,3);
        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    public static void main(String[] args) {
        launch(FenetreConnexion.class, args);
    }
}