
import java.util.ArrayList;



public class Jest extends CardGroup {

    public Jest() {
        this.cards = new ArrayList();
    }

    public Card removeCard(int id) {
        return this.cards.remove(id);
    }

    /*
    public Card removeCard() {
        

    } */

    
}