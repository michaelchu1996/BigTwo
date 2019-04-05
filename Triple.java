/**
 * Class for triple pattern
 * @author Michael
 * @version 1.1
 */
public class Triple extends Hand{

    private static final long serialVersionUID = 1L;

    public Triple(CardGamePlayer player, CardList Cards) {
        super(player,Cards);
    }
    /**
     * the last card, please, because its triple, basically they are compare in rank because thers only 4 cards
     * @return
     */
    public Card getTopCard(){
       return super.getTopCard();
    };

    /**
     * see whether three cards are the same rank.
     * @return
     */
    public boolean isValid(){
        if(super.getPlayer().getNumOfCards()!=3) return false;
        else{
            if(super.getCard(0).rank == super.getCard(1).rank && 
                super.getCard(1).rank == super.getCard(2).rank)
            return true;
            //System.out.println("trple false");
            return false;
        }
    }
    public String getType(){return "Triple";};   
}
