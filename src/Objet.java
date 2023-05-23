public class Objet {
    private int idObj;
    private String nom;
    private String description;

    public Objet(int idObj, String nom, String description){
        this.idObj = idObj;
        this.nom = nom;
        this.description = description;
    }

    public int getId(){
        return this.idObj;
    }

    public String getNom(){
        return this.nom;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(int newId){
        this.idObj = newId;
    }

    public void setNom(String newNom){
        this.nom = newNom;
    }

    public void setDescription(String newDesc){
        this.description = newDesc;
    }
}
