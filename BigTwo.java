import java.util.ArrayList;
/**
 * The BigTwo game and its class
 * Please be noted that the void main function is at the bottom.
 * @author Michael
 * @version 1.1
 */
public class BigTwo{

    /**
     * 
     * Deck deck – a deck of cards.
     * ArrayList<CardGamePLayer> playerList – a list of players.
     * ArrayList<Hand> handsOnTable – a list of hands played on the table.
     * int currentIdx – an integer specifying the index of the current player.
     * BigTwoConsole bigTwoConsole – a BigTwoConsole object for providing the user interface.
     */
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>();
    private ArrayList<Hand> handsOnTable = new ArrayList<Hand>();
    private int currentIdx;
    private BigTwoConsole bigTwoConsole;
    
    /**
     * BigTwo() – a constructor for creating a Big Two card game. You should create 4 players and add them to the player list. 
     * You should also create a ‘console’ (i.e., a BigTwoConsole object) for providing the user interface.
     */
    public BigTwo(){
        for(int i=0;i<4;i++){
            CardGamePlayer temp=new CardGamePlayer();
            playerList.add(temp);
        }


    }
    /**
     * method for retrieving the deck of cards being used.
     * @return retrieving the deck of cards being used.
     */
    public Deck getDeck(){ return this.deck;};
    /**
     * a method for retrieving the list of players.
     * @return retrieving the list of players.
     */
    public ArrayList<CardGamePlayer> getPlayerList(){ return this.playerList;};
    /**
     * a  method for retrieving the list of hands played on the table.
     * @return retrieving the list of hands played on the table.
     */ 
    public ArrayList<Hand> getHandsOnTable(){ return this.handsOnTable;};
    /**
     *  a method for retrieving the index of the current player.
     * @return retrieving the index of the current player.
     */
    public int getCurrentIdx(){return this.currentIdx;};


    /**
     * a method for starting the game with a (shuffled) deck of cards supplied as the argument. It implements the Big Two game logics.
     * @param deck shuffled deck of cards
     */
    public void start(BigTwoDeck deck){
        // for shuffle the deck and prepare for games
        this.deck=deck;
        deck.shuffle();
        for(int i=0;i<4;i++){   
            for(int j=0;j<13;j++){
                Card temp= new BigTwoCard(deck.getCard(0).suit, deck.getCard(0).rank);
                playerList.get(i).addCard(temp);
                if(temp.rank==2&&temp.suit==0) this.currentIdx=i;
                deck.removeCard(0);
            }
            playerList.get(i).sortCardsInHand();
        }
        ///////////////////////////////////////////////
        //for console before, and game logic below
        boolean ended = false;
        int index=0; //index = number of round.
        CardList selected = new CardList();
        Hand thisround1=null;
        boolean diamond3=false;
        while(!ended){
            bigTwoConsole =  new BigTwoConsole(this);
            bigTwoConsole.setActivePlayer(getCurrentIdx());//if no object inside, print all deck
            bigTwoConsole.repaint();
            boolean legit=false;
 
            int[] temp = null;
            //System.out.println(this.currentIdx+"\n");
            ///////////////////////////
            //the input process
            //////////////////////////
            while(legit==false){
                selected.removeAllCards();
                temp = bigTwoConsole.getSelected(); //XXX's turn
                Hand thisround;
                if(temp!=null){
                    for(int i=0;i<temp.length;i++){
                        Card tempcard=new BigTwoCard(getPlayerList().get(getCurrentIdx()).getCardsInHand().getCard(temp[i]).suit,
                            getPlayerList().get(getCurrentIdx()).getCardsInHand().getCard(temp[i]).rank);
                        selected.addCard(tempcard);
                        if(tempcard.rank==2&&tempcard.suit==0) diamond3=true;
                    }
                }
                //for priting the CardList
                /*
                if(temp!=null){
                    for(int i=0;i<temp.length;i++){
                            System.out.println(selected.getCard(i).rank);
                    }
                }
                */
                //if(index==0 && diamond3==true)
                //////
                //check whether its empty card set
                //////
                Hand thisround3=composeHand(getPlayerList().get(getCurrentIdx()), selected);
                if(temp==null&&(diamond3==true||index!=0)){
                    if(getPlayerList().get(getCurrentIdx()).getName()==handsOnTable.get(index-1).getPlayer().getName()||index==0){
                            System.out.println("Not a legit move!!!");
                            continue;
                        }
                    //handsOnTable.add(thisround);
                    legit=true;
                    System.out.println("{Pass}");
                    //System.out.println("see if its null");
                    
                }
                /////
                //if !empty + diamond3 found
                else if(diamond3==true){
                    //System.out.println(thisround3.getType());
                    //thisround3.print();
                    //System.out.println(thisround3.isValid());
                    if(temp==null||thisround3==null) {
                        System.out.println("Not a legit move!!!");
                        continue;
                    }

                    if(index!=0&&thisround3.isValid()){
                        //System.out.print("this round is vald");
                        if(thisround3.beats(handsOnTable.get(index-1))||(handsOnTable.get(index-1).getPlayer().getName()==thisround3.getPlayer().getName()&&thisround3!=null)){
                            this.handsOnTable.add(thisround3);
                            //handsOnTable.get(index-1).print();
                            getPlayerList().get(this.currentIdx).removeCards(selected);
                            index++;
                            legit=true;
                        }
                        else{
                            System.out.println("Not a legit move!!!");
                            continue;
                        }
                    }
                    /// if its the first round
                    else{
                        //System.out.println("1st round");
                        getPlayerList().get(this.currentIdx).removeCards(selected);
                        //System.out.println(thisround4.getTopCard());
                        //System.out.println(thisround4.getPlayer().getCardsInHand().getCard(0));
                        //System.out.println(thisround4.isEmpty());
                        //thisround4.print(true, true);
                        this.handsOnTable.add(index, thisround3);
                        index++;
                        //System.out.println(handsOnTable.get(0).getTopCard());
                        legit=true;
                    }
                    break;
                }
                else{
                    System.out.println("Not a legit move!!!");
                    diamond3=false;
                }
                selected.removeAllCards();
            }

        if(this.getPlayerList().get(getCurrentIdx()).getNumOfCards()==0) ended = true;
        this.currentIdx++;
        this.currentIdx=this.currentIdx%4;
        
        //System.out.println(getHandsOnTable().get(index-1).getCard(0));

        /*
            for(int i=0;i<getPlayerList().size();i++){
                System.out.println(handsOnTable.
            }
        */
        }
        bigTwoConsole.setActivePlayer(-1);//if no object inside, print all deck
        bigTwoConsole.repaint();
        System.out.println("Game ends");
        for(int i=0;i<getPlayerList().size();i++){
            if(getPlayerList().get(i).getCardsInHand().size()!=0)
            System.out.println(getPlayerList().get(i).getName()+" has "+getPlayerList().get(i).getNumOfCards()+" cards in hand.");
            else
            System.out.println(getPlayerList().get(i).getName()+" wins the game.");
        }
 
    }
    /**
     * a method for returning a valid hand from the specified list of cards of the player. Returns null is no valid hand can be composed from the specified list of cards.
     * @param player
     * @param cards
     * @return Valid hand type or invalid hand by return null
     */
    public static Hand composeHand(CardGamePlayer player, CardList cards){
        cards.sort();
        if(cards.isEmpty()||cards.size()==4 || cards.size()>5) return null;
        Hand temp;
        if(cards.size()==1){
            temp = new Single(player, cards);
            //System.out.println(temp.getTopCard().rank);
            return temp;
        }
        else if(cards.size()==2){
            temp = new Pair(player, cards);
            if(temp.isValid()==true) return temp;
            else 
            {
                temp.removeAllCards();
                return null;
            }
        }
        else if(cards.size()==3){
            temp = new Triple(player, cards);
                  if(temp.isValid()==true) return temp;
                  else 
                  {
                      temp.removeAllCards();
                      return null;
                  }
        }
            Hand temp1[] =  new Hand[5];
            temp1[0] = new StraightFlush(player, cards);
            temp1[1]=   new Quad(player, cards);
            temp1[2] = new FullHouse(player, cards);
            temp1[3] = new Flush(player, cards);
            temp1[4] = new Straight(player, cards);
            for(int i=0;i<5;i++){
                if(temp1[i].isValid()==true){
                    //System.out.println(temp1[i].getType());
                    return temp1[i];
                }
            }

        
        return null;
    };
    /**
     * void main(String[] args) – a method for starting a Big Two card game. It should create a Big Two card game, create and shuffle a deck of cards, and start the game with the deck of cards.
     * @param args
     */
    public static void main(String[] args){
        BigTwo game = new BigTwo ();
        BigTwoDeck test = new BigTwoDeck();
        test.shuffle();
        game.start(test);
    }
}