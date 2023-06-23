public class Objet {
    /**L'identifiant de l'objet */
    private int idObj;

    /**Le nom de l'objet */
    private String nom;

    /**La description de l'objet */
    private String description;

    /**L'utilisateur auquel l'objet appartient */
    private Utilisateur util;

    /**La catégorie de l'objet */
    private Categorie cat;

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
     * Permet de saisir l'identifiant de l'objet
     * @param newId
     */
    public void setId(int newId){
        this.idObj = newId;
    }

    /**
     * Permet de saisir le nom de l'objet
     * @param newNom
     */
    public void setNom(String newNom){
        this.nom = newNom;
    }

    /**
     * Permet de saisir la description de l'objet
     * @param newDesc
     */
    public void setDescription(String newDesc){
        this.description = newDesc;
    }

    /**
     * Permet de saisir l'utilisateur auquel l'objet appartient
     * @param newUtil
     */
    public void setUtilisateur(Utilisateur newUtil){
        this.util = newUtil;
    }

    /**
     * Permet de saisir la catégorie de l'objet
     * @param newCat
     */
    public void setCategorie(Categorie newCat){
        this.cat = newCat;
    }
}
