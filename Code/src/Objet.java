import java.util.ArrayList;
import java.util.List;

public class Objet {
    private int idObj;
    private String nom;
    private String description;
    private Utilisateur util;
    private Categorie cat;
    private List<Photo> lesPhotos;

    /**
     * Constructeur de base de la classe Objet
     * @param idObj
     * @param nom
     * @param description
     * @param util
     * @param cat
     */
    public Objet(int idObj, String nom, String description, Utilisateur util, Categorie cat){
        this.idObj = idObj;
        this.nom = nom;
        this.description = description;
        this.util = util;
        this.cat = cat;
        this.lesPhotos = new ArrayList<>();
    }

    /**
     * 
     * @return l'identifiant de l'objet
     */
    public int getId(){
        return this.idObj;
    }

    /**
     * 
     * @return le nom de l'objet
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * 
     * @return la description de l'objet
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * 
     * @return l'utilisateur auquel l'objet appartient
     */
    public Utilisateur getUtilisateur(){
        return this.util;
    }

    /**
     * 
     * @return la catégorie de l'objet
     */
    public Categorie getCat(){
        return this.cat;
    }

    /**
     * Permet d'ajouter jusqu'à quatre photos pour l'objet
     * @param photo
     */
    public void ajoutePhoto(Photo photo){
        if (this.lesPhotos.size()<5){
            this.lesPhotos.add(photo);
        }
        else{
            this.lesPhotos.add(3, photo);
        }
        
    }

    /**
     * 
     * @return la liste des images de l'objet
     */
    public List<Photo> getLesPhotos() {
        return this.lesPhotos;
    }

    /**
     * Permet de changer l'identifiant de l'objet
     * @param newId
     */
    public void setId(int newId){
        this.idObj = newId;
    }

    /**
     * Permet de changer le nom de l'objet
     * @param newNom
     */
    public void setNom(String newNom){
        this.nom = newNom;
    }

    /**
     * Permet de changer la description de l'objet
     * @param newDesc
     */
    public void setDescription(String newDesc){
        this.description = newDesc;
    }

    /**
     * Permet de changer l'utilisateur à qui l'objet appartient
     * @param newUtil
     */
    public void setUtilisateur(Utilisateur newUtil){
        this.util = newUtil;
    }

    /**
     * Permet de changer la catégorie de l'objet
     * @param newCat
     */
    public void setCategorie(Categorie newCat){
        this.cat = newCat;
    }
}
