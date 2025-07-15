import java.sql.*;
import java.util.*;

public class CardDAO {
    static String url = "jdbc:mysql://localhost:3306/tcg";

    static String username = "root";
    
    static String password = "12qwaszx12qwaszx!!";
        
    public static List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");}       
        catch (ClassNotFoundException e) {
            e.printStackTrace();}


        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM collection")) {

            while (rs.next()) {
                Card c = new Card();
                c.name = rs.getString("name");
                c.setName = rs.getString("setName");
                c.setID = rs.getInt("ID");
                c.holo = rs.getBoolean("holo");
                c.quantity = rs.getInt("quantity");
                c.price = rs.getDouble("price");
                c.promo = rs.getString("promo");
                cards.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static boolean insertCard(Card card){
        String sql = "INSERT INTO collection (Name, SetName, ID, Price, Quantity, Holo, Promo) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, card.name);
            ps.setString(2, card.setName);
            ps.setInt(3, card.setID);
            ps.setDouble(4, card.price);
            ps.setInt(5, card.quantity);
            ps.setBoolean(6, card.holo);
            ps.setString(7, card.promo);

            

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}



