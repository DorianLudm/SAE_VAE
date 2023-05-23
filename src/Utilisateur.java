public class Utilisateur {
    private int idUt;
    private String pseudo;
    private String email;
    private String role;

    public Utilisateur(int idUt, String pseudo, String email, String role){
        this.idUt = idUt;
        this.pseudo = pseudo;
        this.email = email;
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

    public String getRole(){
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

    public void setRole(String newRole){
        this.role = newRole;
    }
}
