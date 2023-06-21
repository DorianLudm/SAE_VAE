import java.io.File;

public class Photo {
    private int id;
    private String titre;
    private String urlImg;
    private Objet objet;

    /**
     * Constructeur de base de la classe Photo
     * @param id
     * @param titre
     * @param url
     * @param obj
     */
    public Photo(int id, String titre, String url, Objet obj){
        this.id = id;
        this.titre = titre;
        this.urlImg = url;
        this.objet = obj;
    }

    /**
     * 
     * @return l'identifiant de la photo
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant de la photo
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return le titre de la photo
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Permet de changer le titre de la photo
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    /**
     * 
     * @return l'image en elle-même
     */
    public File getImg(){
        File img = new File(this.urlImg);
        return img;
    }
    
    /**
     * 
     * @return le lien de l'image
     */
    public String getURLImg() {
        return this.urlImg;
    }

    /**
     * Permet de changer l'url de la photo
     * @param url
     */
    public void setImg(String url) {
        this.urlImg = url;
    }

    /**
     * 
     * @return l'objet auquel la photo appartient
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * Permet de changer l'objet auquel la photo appartient
     * @param objet
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }
}
