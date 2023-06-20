public class Utilisateur {
    private int idUt;
    private String pseudo;
    private String email;
    private String mdp;
    private String active;
    private Role role;

    /**
     * Constructeur de base de la classe Utilisateur
     * @param idUt
     * @param pseudo
     * @param email
     * @param mdp
     * @param active
     * @param role
     */
    public Utilisateur(int idUt, String pseudo, String email, String mdp, String active, Role role){
        this.idUt = idUt;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.active = active;
        this.role = role;
    }

    /**
     * 
     * @return l'identifiant de l'utilisateur
     */
    public int getId(){
        return this.idUt;
    }

    /**
     * 
     * @return le pseudo de l'utilisateur
     */
    public String getPseudo(){
        return this.pseudo;
    }

    /**
     * 
     * @return l'email de l'utilisateur
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * 
     * @return le mot de passe de l'utilisateur
     */
    public String getMDP(){
        return this.mdp;
    }

    /**
     * 
     * @return le caractère qui indique si le compte de l'utilisateur est activé (O), ou non (N)
     */
    public String getActive(){
        return this.active;
    }

    /**
     * 
     * @return le rôle de l'utilisateur
     */
    public Role getRole(){
        return this.role;
    }

    /**
     * Permet de changer l'identifiant de l'utilisateur
     * @param newId
     */
    public void setId(int newId){
        this.idUt = newId;
    }

    /**
     * Permet de changer le pseudo de l'utilisateur
     * @param newPseudo
     */
    public void setPseudo(String newPseudo){
        this.pseudo = newPseudo;
    }

    /**
     * Permet de changer l'email de l'utilisateur
     * @param newEmail
     */
    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    /**
     * Permet de changer le mot de passe de l'utilisateur
     * @param newMDP
     */
    public void setMDP(String newMDP){
        this.mdp = newMDP;
    }

    /**
     * Permet de changer le caractère qui indique si le compte de l'utilisateur est activé (O) ou non (N)
     * @param activ
     */
    public void setActive(String activ){
        this.active = activ;
    }

    /**
     * Permet de changer le rôle de l'utilisateur
     * @param role
     */
    public void setRole(Role role){
        this.role = role;
    }
}
