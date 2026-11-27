import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String n, String p){
        this.nom = n;
        this.prenom = p;
        this.id = -1;
    }

    public void setId(int id){
        if (id >= 0){
            this.id = id;
        }
    }
    public static ArrayList<Personne> findAll() throws SQLException {
        ArrayList<Personne> personne = new ArrayList<>();
        String rq = "SELECT * FROM Personne";
        Statement stmt = DBConnection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(rq);
        Personne p;
        if (rs.next()){
            p = new Personne(rs.getString("nom"),rs.getString("prenom"));
            p.setId(rs.getInt("id"));
            personne.add(p);
        }
        return personne;
    }

    public static Personne findById(int id) throws SQLException{
        String rq = "SELECT * FROM Personne WHERE id = ?";
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(rq);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Personne p = null;
        if (rs.next()){
            p = new Personne(rs.getString("nom"),rs.getString("prenom"));
            p.setId(id);
        }
        return p;
    }

    public static ArrayList<Personne> findByName(String nom) throws SQLException{
        String rq = "SELECT * FROM Personne WHERE nom = ?";
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(rq);
        stmt.setString(1, nom);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Personne> personne = new ArrayList<>();
        Personne p;
        if (rs.next()){
            p = new Personne(rs.getString("nom"),rs.getString("prenom"));
            p.setId(rs.getInt("id"));
            personne.add(p);
        }
        return personne;
    }

    
}
