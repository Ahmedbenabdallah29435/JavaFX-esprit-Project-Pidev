/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public interface PersonneInterface <T>{
     void ajouter(T t) throws SQLException;
    List<T> afficher();
    public void modifier(T t) throws SQLException;
    public void supprimer(int t) throws SQLException;
    public List<T> rechercherParNom(String nom) throws SQLException;
    public List<T> affichageActive() throws SQLException;
    public List<T> affichageDesactive() throws SQLException;
    public String totalFANActive() throws SQLException;
    public String totalFANDesactive() throws SQLException;
    public String totalADMINActive() throws SQLException;
    public String totalADMINdesactive() throws SQLException;
    public String totalRESPOActive() throws SQLException;
    public String totalRESPODesactive() throws SQLException;
    public List<T> rechercherParPrenom(String email) throws SQLException;
     public List<T> rechercherParAdresse(String adresse) throws SQLException;
}
