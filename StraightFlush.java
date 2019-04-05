/**
 * Straight Flush pattern
 * copied from Straight+Flush
 * 
 * @author Michael
 * @version 1.2
 */
public class StraightFlush extends Hand{
    
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    public boolean isValid(){
        super.getPlayer().sortCardsInHand();
        int temp=super.getCard(0).rank;
        if(temp<2 || temp>10) return false;

        boolean isstraight=true;
        for(int i=1;i<5;i++){
            int temp2=super.getCard(i).rank;
            if(temp2<2) temp2+=13;
            if(temp2-temp != 1) isstraight=false;
            else{
                temp=temp2;
            }
        }

        boolean flush=false;
        int tempsuit=super.getCard(0).suit;
        for(int i =0;i<5;i++){
            if(tempsuit==super.getCard(i).suit){
                flush=true;
            }
            else flush = false;
        }
        if(flush ==true&&isstraight==true){
            return true;

        }
         return false;
    };

    public String getType(){return "Straight";};  
}