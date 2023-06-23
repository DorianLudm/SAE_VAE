public class Utilisateur {
    /**L'identifiant de l'utilisateur */
    private int idUt;

    /**Le pseudo de l'utilisateur */
    private String pseudo;

    /**L'email de l'utilisateur */
    private String email;

    /**Le mot de passe de l'utilisateur */
    private String mdp;

    /**Si le compte est actif ou non */
    private String active;

    /**Le rôle de l'utilisateur */
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
     * @return la chaîne de caractère qui indique si le compte est activé ("O"), ou non ("N")
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
     * Permet de saisir l'identifiant
     * @param newId
     */
    public void setId(int newId){
        this.idUt = newId;
    }

    /**
     * Permet de saisir le pseudo
     * @param newPseudo
     */
    public void setPseudo(String newPseudo){
        this.pseudo = newPseudo;
    }

    /**
     * Permet de saisir l'email de l'utilisateur
     * @param newEmail
     */
    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    /**
     * Permet de saisir le mot de passe de l'utilisateur
     * @param newMDP
     */
    public void setMDP(String newMDP){
        this.mdp = newMDP;
    }

    /**
     * Permet de saisir "O" pour indiquer que le compte est actif, "N" sinon
     * @param activ
     */
    public void setActive(String activ){
        this.active = activ;
    }

    /**
     * Permet de saisir le rôle de l'utilisateur
     * @param role
     */
    public void setRole(Role role){
        this.role = role;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){return true;}
        if (o == null){return false;}
        if (o instanceof Utilisateur){
            Utilisateur u = (Utilisateur) o;
            return this.idUt == u.idUt && this.pseudo.equals(u.pseudo) && this.email.equals(u.email) && this.mdp.equals(u.mdp) && this.active.equals(u.active) && this.role.equals(u.role);
        }
        return false;
    
    }
}
