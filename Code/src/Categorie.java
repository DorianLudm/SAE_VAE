public class Categorie {
    private int idCat;
    private String nom;

    /**
     * Constructeur de base de la classe Categorie
     * @param id
     * @param nom
     */
    public Categorie(int id, String nom){
        this.idCat = id;
        this.nom = nom;
    }

    /**
     * 
     * @return l'identifiant de la catégorie
     */
    public int getId(){
        return this.idCat;
    }

    /**
     * 
     * @return le nom de la catégorie
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Permet de changer l'identifiant de la catégorie
     * @param id
     */
    public void setId(int id){
        this.idCat = id;
    }

    /**
     * Permet de changer le nom de la catégorie
     * @param name
     */
    public void setNom(String name){
        this.nom = name;
    }
}
