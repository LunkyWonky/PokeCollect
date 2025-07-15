import java.util.List;

public class TestCardDAO {
    public static void main(String[] args) {
        List<Card> cards = CardDAO.getAllCards();
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found or error occurred.");
        } else {
            for (Card c : cards) {
                System.out.println("ID: " + c.setID);
                System.out.println("Name: " + c.name);
                System.out.println("Set: " + c.setName);
                System.out.println("Holo: " + c.holo);
                System.out.println("Quantity: " + c.quantity);
                System.out.println("Price: $" + c.price);
                System.out.println("-------------");
            }
        }

        Card test = new Card();
        test.name = "Glaceon";
        test.holo = false;
        test.price = 100;
        test.promo = "Worlds";
        test.quantity = 1;
        test.setID = 21;
        test.setName = "Burning Shadows";

        CardDAO.insertCard(test);
    }
}