import java.io.File;

public class Photo {
    private int id;
    private String titre;
    private File img;
    private Objet objet;

   public Photo(int id, String titre, Objet obj){
        this.id = id;
        this.titre = titre;
        this.objet = obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public File getImg(){
        return this.img;
    }
    
    public String getURLImg() {
        return img.getAbsolutePath();
    }

    public void setImg(String url) {
        this.img = new File(url);
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }
}
