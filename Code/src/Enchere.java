import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Enchere {
    private Utilisateur encherisseur;
    private Vente vente;
    private String dateheure;
    private double montant;

    /**
     * Constructeur de base de la classe Enchere
     * @param encherisseur
     * @param vente
     * @param dateheure
     * @param montant
     */
    public Enchere(Utilisateur encherisseur, Vente vente, String dateheure, double montant){
        this.encherisseur = encherisseur;
        this.vente = vente;
        this.dateheure = dateheure;
        this.montant = montant;
    }

    /**
     * 
     * @return l'utilisateur qui enchérit
     */
    public Utilisateur getUtilisateur(){
        return this.encherisseur;
    }

    /**
     * 
     * @return la vente sur laquelle on enchérit
     */
    public Vente getVente(){
        return this.vente;
    }

    /**
     * 
     * @return la date et l'heure de l'enchère
     */
    public String getDateHeure(){
        return this.dateheure;
    }

    /**
     * 
     * @return le montant de l'enchère effectuée
     */
    public double getMontant(){
        return this.montant;
    }

    /**
     * Permet de changer l'enchérisseur
     * @param newUt
     */
    public void setEncherisseur(Utilisateur newUt){
        this.encherisseur = newUt;
    }

    /**
     * Permet de changer la vente sur lquelle on peut enchérir
     * @param newVente
     */
    public void setVente(Vente newVente){
        this.vente = newVente;
    }

    /**
     * Permet de changer la date et l'heure de l'enchère effectuée
     * @param time
     */
    public void setDateHeure(String time){
        this.dateheure = time;
    }

    /**
     * Permet de changer le montant de l'enchère
     * @param newMontant
     */
    public void setMontant(double newMontant){
        this.montant = newMontant;
    }

    public VBox toVBox(){
        VBox res = new VBox();
        ImageView image = new ImageView();
        try{
            image = new ImageView(new Image(this.vente.getObjet().getLesPhotos().get(0)));
        }
        catch(Exception e){
            image = new ImageView(new Image("file:/e"));
        }
        Label nomItem = new Label(this.vente.getObjet().getNom());
        Label prix = new Label(String.valueOf(this.montant) + "€");
        Label temps = new Label(this.vente.tempsRestant());
        res.getChildren().addAll(image, nomItem, prix, temps);
        return res;
    }

    public HBox toHBox(){
        HBox res = new HBox();
        VBox rightBox = new VBox();
        ImageView image = new ImageView();
        try{
            image = new ImageView(new Image(this.vente.getObjet().getLesPhotos().get(0)));
        }
        catch(Exception e){
            image = new ImageView(new Image("file:/e"));
        }
        Label nomItem = new Label(this.vente.getObjet().getNom());
        Label prix = new Label(String.valueOf(this.montant) + "€");
        Label temps = new Label(this.vente.tempsRestant());
        rightBox.getChildren().addAll(nomItem, prix, temps);
        res.getChildren().addAll(image, rightBox);
        return res;
    }
}