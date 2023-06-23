public class Role{
    private int id;
    private String nom;

    public Role(int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public int getId(){
        return this.id;
    }

    public String getNom(){
        return this.nom;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null){ return false;}
        if (o instanceof Role){ 
            Role role = (Role) o;
            return this.id == role.id && this.nom.equals(role.nom);
        }
        return false;
    }
}