/**
 * Class of FullHouse pattern
 * @author Michael
 * @version 2.7.3
 */
public class FullHouse extends Hand{
    public FullHouse(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * go by comparision to get the TopCard, ie 
     * if card 1 matches the rank of the card in hand, count++
     * else if card2 matchers, count2++
     * else
     * neither them is matches, set rank2 into a the card in hard
     * BECAUSE ITS BEING SORTED, so the pattern will only be AABBB/AAABB
     * so its likely to set card2 into the specific card in hand
     * @return Card
     */
    public Card getTopCard(){
        super.getTopCard();
        super.getPlayer().sortCardsInHand();
        int rank1=super.getCard(0).rank;
        int rank2=super.getCard(0).rank;
        int count1=0;
        int count2=0;
        for(int i=0;i<5;i++){
            if(rank1==super.getCard(i).rank) count1++;
            else if(rank2==super.getCard(i).rank){
                count2++;
            }
            else{
                rank2=super.getCard(i).rank;
                count2=1;
            }
        }
        if(count1==3&&count2==2 ) return super.getCard(2);
        return super.getCard(4);
    };


    /** Logic is same as before, this time has a boolean check whether the count is like 2/3 or 3/2
     * go by comparision to get the TopCard, ie 
     * if card 1 matches the rank of the card in hand, count++
     * else if card2 matchers, count2++
     * else
     * neither them is matches, set rank2 into a the card in hard
     * BECAUSE ITS BEING SORTED, so the pattern will only be AABBB/AAABB
     * so its likely to set card2 into the specific card in hand
     * @return boolean
     */
    
    public boolean isValid(){
        super.getPlayer().sortCardsInHand();
        int rank1=super.getCard(0).rank;
        int rank2=super.getCard(0).rank;
        int count1=0;
        int count2=0;
        for(int i=0;i<5;i++){
            if(rank1==super.getCard(i).rank) count1++;
            else if(rank2==super.getCard(i).rank){
                count2++;
            }
            else{
                rank2=super.getCard(i).rank;
                count2=1;
            }
        }
        if(count1==3&&count2==2 || count2==3 && count1==2) return true;
        return false;
    };

    public String getType(){return "FullHouse";}; 
}