public class Utilisateur {
    private int idUt;
    private String pseudo;
    private String email;
    private String mdp;
    private String active;
    private Role role;

    public Utilisateur(int idUt, String pseudo, String email, String mdp, String active, Role role){
        this.idUt = idUt;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.active = active;
        this.role = role;
    }

    public int getId(){
        return this.idUt;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getMDP(){
        return this.mdp;
    }

    public String getActive(){
        return this.active;
    }

    public Role getRole(){
        return this.role;
    }

    public void setId(int newId){
        this.idUt = newId;
    }

    public void setPseudo(String newPseudo){
        this.pseudo = newPseudo;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setMDP(String newMDP){
        this.mdp = newMDP;
    }

    public void setActive(String activ){
        this.active = activ;
    }

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
