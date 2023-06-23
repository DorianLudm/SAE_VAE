public class Categorie {
    private int idCat;
    private String nom;

    public Categorie(int id, String nom){
        this.idCat = id;
        this.nom = nom;
    }

    public int getId(){
        return this.idCat;
    }

    public String getNom(){
        return this.nom;
    }

    public void setId(int id){
        this.idCat = id;
    }

    public void setNom(String name){
        this.nom = name;
    }
}
