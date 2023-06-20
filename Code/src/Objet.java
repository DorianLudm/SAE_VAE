import java.util.ArrayList;
import java.util.List;

public class Objet {
    private int idObj;
    private String nom;
    private String description;
    private Utilisateur util;
    private Categorie cat;
    private List<Photo> lesPhotos;

    public Objet(int idObj, String nom, String description, Utilisateur util, Categorie cat){
        this.idObj = idObj;
        this.nom = nom;
        this.description = description;
        this.util = util;
        this.cat = cat;
        this.lesPhotos = new ArrayList<>();
    }

    public int getId(){
        return this.idObj;
    }

    public String getNom(){
        return this.nom;
    }

    public String getDescription(){
        return this.description;
    }

    public Utilisateur getUtilisateur(){
        return this.util;
    }

    public Categorie getCat(){
        return this.cat;
    }

    public void ajoutePhoto(Photo photo){
        this.lesPhotos.add(photo);
    }

    public List<Photo> getLesPhotos() {
        return this.lesPhotos;
    }

    public void setId(int newId){
        this.idObj = newId;
    }

    public void setNom(String newNom){
        this.nom = newNom;
    }

    public void setDescription(String newDesc){
        this.description = newDesc;
    }

    public void setUtilisateur(Utilisateur newUtil){
        this.util = newUtil;
    }

    public void setCategorie(Categorie newCat){
        this.cat = newCat;
    }
}
