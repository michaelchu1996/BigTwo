/**
 * Class for Quad pattern
 * @author Michae
 * @version 3.1
 */

public class Quad extends Hand{

    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * Top Card, if its Quad, checked by isValid()
     * as said below: ZZZZM/ZMMMM, the fourth/fifth card will be the Top card, just see if either one of them is repeated, is so , its top card
     * @return
     */
    //either the fourth card/fifth card is the top card after sorted
    //ZZZZM or ZMMMM are the only possiblyy after sorting
    //so either 4th/5th will be the topcard, if the Quad is on Z, return the 4th; return the 5th if M.
    public Card getTopCard(){
        super.getPlayer().sortCardsInHand();
        int suit1=super.getCard(0).rank;
        boolean yes1=true;
        for(int i=1;i<5;i++){
            if(suit1!=super.getCard(i).rank) yes1=false;
            if(i==3 && yes1==true) return super.getCard(3);
        }
        return super.getCard(4);
    };
    /**
     * as said, ZZZZM/ZMMMM
     * just need to check whether they are repeated until specific position.
     * @return
     */
    public boolean isValid(){
        super.getPlayer().sortCardsInHand();
        if(super.getPlayer().getNumOfCards()!=5) {
           // System.out.println("hehe");
            return false;
        }
        int suit1=super.getCard(0).rank;
        int suit2=super.getCard(1).rank;
        boolean yes1=true;
        boolean yes2=true;
        for(int i=1;i<5;i++){
            if(suit1!=super.getCard(i).rank) yes1=false;
            if(suit2!=super.getCard(i).rank) yes2=false;
            if(i==3 && yes1==true) return true;
        }
        if(yes2==true) return true;
        return false;
    };



    public String getType(){return "Quad";}; 
}