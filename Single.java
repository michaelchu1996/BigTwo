/**
 * Class of single pattern
 * @author Michael
 * @version 1.0
 */

public class Single extends Hand{
    private static final long serialVersionUID = 1L;

    public Single(CardGamePlayer player, CardList Cards) {
        super(player,Cards);
    }
    /**
     * return the only card
     * @return
     */
    public Card getTopCard(){
        return super.getTopCard();
        //return super.getPlayer().getCardsInHand().getCard(0);
    };

    /**
     * if size = 1, valid la
     * @return
     */
    public boolean isValid(){
        if(super.size()==1)
        return true;
        return false;
    }

    public String getType(){return "Single";};
}