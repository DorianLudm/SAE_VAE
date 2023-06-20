import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Vente {
    private int id;
    private String debVente;
    private String finVente;
    private double prixMin;
    private double prixBase;
    private Objet objet;
    private Statut statut;

    

    public Vente(int id, String debVente, String finVente, double prixMin, double prixBase, Objet objet, Statut statut) {
        this.id = id;
        this.debVente = debVente;
        this.finVente = finVente;
        this.prixMin = prixMin;
        this.prixBase = prixBase;
        this.objet = objet;
        this.statut = statut;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getDebutVente(){
        return this.debVente;
    }

    public String getFinVente(){
        return this.finVente;
    }

    public double getPrixMin(){
        return this.prixMin;
    }

    public double getPrixBase(){
        return this.prixBase;
    }

    public Objet getObjet(){
        return this.objet;
    }

    public void setDebutVente(String debut){
        this.debVente = debut;
    }

    public void setFinVente(String fin){
        this.finVente = fin;
    }

    public void setPrixMin(double newPrixMin){
        this.prixMin = newPrixMin;
    }

    public void setPrixBase(double newPrixBase){
        this.prixBase = newPrixBase;
    }

    public void setObjet(Objet obj){
        this.objet = obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String tempsRestant() throws ParseException{
        String res = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(this.finVente, formatter);
        LocalDateTime dateActuel = LocalDateTime.now();
        Duration tempsDiff = Duration.between((Temporal) dateActuel, dateTime);
        return tempsDiff.toString();
    }
}
