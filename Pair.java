/**
 * Class of Pair pattern
 * @author Michael
 * @version 1.0
 */
public class Pair extends Hand{

    private static final long serialVersionUID = 1L;

    public Pair(CardGamePlayer player, CardList Cards) {
        super(player,Cards);
    }
    /**
     * after sorted, the top card = last card in hand
     * @return
     */
    public Card getTopCard(){
       return super.getTopCard();
    };
    /**
     * check whether its valid by its the same rank
     * @return
     */
    public boolean isValid(){
        if(super.getPlayer().getCardsInHand().size()!=2) {
            return false;
        }
        else{
            if(super.getCard(0).rank == super.getCard(1).rank)
         {
            return true;
        }
            return false;
        }
    }
    public String getType(){return "Pair";};   
}
