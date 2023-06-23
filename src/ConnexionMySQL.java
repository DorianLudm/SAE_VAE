import java.sql.*;

public class ConnexionMySQL {
        private Connection mysql=null;
        private boolean connecte=false;
        public ConnexionMySQL() throws ClassNotFoundException{}

        public void connecter() throws SQLException {
                this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-mariadb:3306/DBludmann", "ludmann", "ludmann");                                                                                                                                                           
                this.connecte=this.mysql!=null;
        }

        public void close() throws SQLException {
                // fermer la connexion                                                                                                                                                                                                       
                this.connecte=false;
        }

        public boolean isConnecte() { return this.connecte;}

        public Statement createStatement() throws SQLException {
                return this.mysql.createStatement();
        }

        public PreparedStatement prepareStatement(String requete) throws SQLException{
                return this.mysql.prepareStatement(requete);
        }

        public Connection getCon(){
                return this.mysql;
        }
}
