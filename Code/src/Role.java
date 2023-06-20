public class Role{
    private int id;
    private String nom;

    /**
     * Constructeur de base de la classe Role
     * @param id
     * @param nom
     */
    public Role(int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    /**
     * 
     * @return l'identifiant du rôle
     */
    public int getId(){
        return this.id;
    }

    /**
     * 
     * @return le nom du rôle
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Permet de changer l'identifiant du rôle
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Permet de changer le nom du rôle
     * @param nom
     */
    public void setNom(String nom){
        this.nom = nom;
    }
}