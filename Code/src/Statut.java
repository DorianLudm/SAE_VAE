public class Statut {
    /**L'identifiant du statut */
    private int id;

    /**Le nom du statut */
    private String nom;

    /**
     * Constructeur de base de la classe Role
     * @param id
     * @param nom
     */
    public Statut(int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    /**
     * 
     * @return l'identifiant du statut
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de saisir l'identifiant du statut
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return le nom du statut
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de saisir le nom du statut
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
