/**
 * Class for Flush pattern
 * @author Michael
 * @version 1.3
 */
public class Flush extends Hand{

    private static final long serialVersionUID = 1L;
    /**
     * constuctor will call for an object
     * @param player
     * @param Cards
     */
    public Flush(CardGamePlayer player, CardList Cards) {
        super(player,Cards);
    }

    /**
     * the checking procedure will be see they are the same suit
     * @return boolean
     */
    public boolean isValid(){
        if(super.getPlayer().getNumOfCards()!=5) return false;
        else{
            boolean flush=false;
            int tempsuit=super.getCard(0).suit;
            for(int i =0;i<5;i++){
                if(tempsuit==super.getCard(i).suit){
                    flush=true;
                }
                else flush = false;
            }
            if(flush ==true) return true;
            return false;
        }
    }
    public String getType(){return "Flush";};  
}

