/**
 * Modify BigTwo Deck for BigTwo game
 * just a design concern
 * @author Michael
 * @version 1.0
 */

public class BigTwoDeck extends Deck{

    private static final long serialVersionUID = 1L;
    /**
     * void initialize() – a method for initializing a deck of Big Two cards. It should remove all cards from the deck, create 52 Big Two cards and add them to the deck.
     */
    public void initialize(){
        super.removeAllCards();
        for(int i=0;i<4;i++){
            for(int j=2;j<13;j++){
                Card card = new Card(i,j);
                addCard(card);
            }
            for(int j=0;j<2;j++){
                Card card = new Card(i, j);
                addCard(card);
            }
        }
    
    };
    
}