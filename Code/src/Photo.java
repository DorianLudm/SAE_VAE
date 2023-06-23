import java.io.File;

public class Photo {
    /**L'identifiant de la photo */
    private int id;

    /**Le titre de la photo */
    private String titre;

    /**Le lien de l'image */
    private String urlImg;

    /**L'objet auquel la photo est associée */
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
     * Permet de saisir l'identifiant de la photo
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
     * Permet de saisir le titre de la photo
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    /**
     * 
     * @return le fichier correspondant au lien de la photo
     */
    public File getImg(){
        File img = new File(this.urlImg);
        return img;
    }
    
    /**
     * 
     * @return la chaîne de caractères représentant le lien de la photo
     */
    public String getURLImg() {
        return this.urlImg;
    }

    /**
     * Permet de saisir le lien de la photo
     * @param url
     */
    public void setImg(String url) {
        this.urlImg = url;
    }

    /**
     * 
     * @return l'objet auquel la photo est associée
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * Permet de saisir l'objet auquel la photo est associée
     * @param objet
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }
}
