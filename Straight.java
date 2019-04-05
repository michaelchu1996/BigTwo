/**
 * For Straight pattern
 * @author Michael
 * @version 2.0
 */
public class Straight extends Hand{
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * after sorted, keep ranking the pattern is -1
     * @return
     */
    public boolean isValid(){
        if(super.getPlayer().getNumOfCards()!=5) return false;
        super.getPlayer().sortCardsInHand();
        int temp=super.getCard(0).rank;
        if(temp<2 || temp>10) return false;

        boolean isstraight=true;
        for(int i=1;i<5;i++){
            int temp2=super.getCard(i).rank;
            if(temp2<2) temp2+=13;
            if(temp2-temp!= 1) isstraight=false;
            else{
                temp=temp2;
            }
        }
        if(isstraight==true) return true;
        return false;
    };

    public String getType(){return "Straight";};  

}