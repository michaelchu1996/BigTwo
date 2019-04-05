import java.io.Serializable;

import javax.lang.model.util.ElementScanner6;
/**
 * Modify the Card order by BigTwo game rules
 * @author Michael
 * @version 1.0
 */
public class BigTwoCard extends Card{

    private static final long serialVersionUID = 1L;
    /**
     * a constructor for building a card with the specified suit and rank. suit is an integer between 0 and 3, and rank is an integer between 0 and 12.
     * @param suit integer between 0 and 3
     * @param rank integer between 0 and 12
     */
    public BigTwoCard(int suit, int rank){
        super(suit, rank);
    };
    /**
     * int compareTo(Card card) – a method for comparing the order of this card with the specified card. Returns a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card.
     * @param card poker card
     * @return 1 if bigger, 0 if same, -1 if smaller
     */
    public int compareTo(Card card){
        int test=0;
        if(super.rank>2 && card.rank>2){
            test=super.compareTo(card);
            return test;
        }
        if(super.rank == card.rank){
            test=super.compareTo(card);
            return test;
        }
        else{
            int tempcard=card.rank;
            int thiscard=super.rank;
            tempcard=tempcard<2?tempcard+13:tempcard;
            thiscard=thiscard<2?thiscard+13:thiscard;
            if(thiscard>tempcard){
                return 1;
            }
                return -1;
        }
    };
}