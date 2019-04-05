/**
 * Hand Class to modify each/the card with hands and its operation
 * @author Michael
 * @version 4.5
 */
public abstract class Hand extends CardList {

    private static final long serialVersionUID = 1L;
    /////////////I wonder will it affect the real.player if we dont make a new object.

    /**
     * CardGamePlayer player – the player who plays this hand.
     */
    private CardGamePlayer player = new CardGamePlayer();
    /**
     * Hand(CardGamePlayer player, CardList cards) – a constructor for building a hand with the specified player and list of cards.
     * @param player
     * @param cards
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player.setName(player.getName());
        for(int i=0;i<cards.size();i++){
            super.addCard(cards.getCard(i));
            this.player.addCard(cards.getCard(i));
        }
        this.player.sortCardsInHand();
        /*
        for(int i=0;i<cards.size();i++){
            super.print();
        }
        */
        for(int i=0;i<cards.size();i++){
            //System.out.println(this.player.getCardsInHand().getCard(i).rank+"\n");
        }
    };
/**
 * a method for retrieving the player of this hand.
 * @return the object player
 */
public CardGamePlayer getPlayer(){
    return this.player;
};

/**
 * retrieving the top card of this hand.
 * @return the biggest card
 */
public Card getTopCard(){
    this.player.sortCardsInHand();
    return super.getCard(super.size()-1); 

};
/**
 * checking if this hand beats a specified hand.
 * Be noticed that it will be overried based on specific situation, ie: Full House/Quad
 * basically they are the same in comparsion in remaining classes, ie top card vs top card
 * @param hand
 * @return
 */
public boolean beats(Hand hand){
    if(hand.getType()==this.getType()){
        BigTwoCard playertest=new BigTwoCard(this.getTopCard().suit, this.getTopCard().rank);
        Card handtest=hand.getTopCard();
        int result=playertest.compareTo(handtest);
        if(result>0) return true;
        return false;
    }
    int handvalue=0;
    int myvalue=0;
    switch(hand.getType()){
        case "Straight": handvalue=0; break;
        case "Flush": handvalue=1; break;
        case "FullHouse": handvalue=2; break;
        case "Quad": handvalue=3; break;
        case"StraightFlush": handvalue=4; break;
    }

    switch(this.getType()){
        case "Straight": myvalue=0; break;
        case "Flush": myvalue=1; break;
        case "FullHouse": myvalue=2; break;
        case "Quad": myvalue=3; break;
        case"StraightFlush": myvalue=4; break;
    }
    if(handvalue<myvalue) return true;
    return false;
};
/**
 *  **abstract** a method for checking if this is a valid hand.
 * @return boolean
 */
abstract boolean isValid();
/**
 *  **abstract** a method for returning a string specifying the type of this hand.
 * @return
 */
abstract String getType();
}