import java.io.File;

public class Photo {
    /**L'identifiant de la photo */
    private int id;

    /**Le titre  */
    private String titre;
    private String urlImg;
    private Objet objet;

   public Photo(int id, String titre, String url, Objet obj){
        this.id = id;
        this.titre = titre;
        this.urlImg = url;
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
        File img = new File(this.urlImg);
        return img;
    }
    
    public String getURLImg() {
        return this.urlImg;
    }

    public void setImg(String url) {
        this.urlImg = url;
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }
}
