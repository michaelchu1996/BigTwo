
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.*;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;


public class BigTwoTable{

    /**
     * CardGame game – a card game associates with this table.
        boolean[] selected – a boolean array indicating which cards are being selected.
        int activePlayer – an integer specifying the index of the active player.
        JFrame frame – the main window of the application.
        JPanel bigTwoPanel – a panel for showing the cards of each player and the cards played on the table.
        JButton playButton – a “Play” button for the active player to play the selected cards. JButton passButton – a “Pass” button for the active player to pass his/her turn to the
        next player.
        JTextArea msgArea – a text area for showing the current game status as well as end of game messages.
        Image[][] cardImages – a 2D array storing the images for the faces of the cards. Image cardBackImage – an image for the backs of the cards.
        Image[] avatars – an array storing the images for the avatars.
     */
    private CardGame game;
    private boolean[] selected =  new boolean[13];
    private int activePlayer;
    private JFrame frame = new JFrame();
    private JPanel bigTwoPanel = new JPanel(); //playerpanel d friend
    private JButton playerButton = new JButton("play");
    private JButton passButton = new JButton("pass");
    private JTextArea msgArea = new JTextArea(50,40);
    JScrollPane scroll = new JScrollPane(msgArea);
    int numofback=0;
    

    private Image[][] cardImages= new Image [4][13];
    private Image cardBackImage;
    private bigTwoPanel[] playerpanel = new bigTwoPanel[5];
    JLabel backimage = new JLabel();
    JLabel backimagearr[] = new JLabel[54];
    private Image[] avatars = new Image[4];
    JLabel playerimage[]=new JLabel[4];
    JLabel cardImage[][] = new JLabel[4][13];
    private JPanel[] cardzone = new JPanel[5];
    playerButtonListener a = new playerButtonListener();
    passButtonListener b = new passButtonListener();
    JScrollPane scrollPane = new JScrollPane(msgArea);



    /**
     *  BigTwoTable(CardGame game) – a constructor for creating a BigTwoTable. The parameter game is a reference to a card game associates with this table.
     */
    BigTwoTable(CardGame game){
        
        //this.playerpanel.setLayout(new BoxLayout(playerpanel, BoxLayout.Y_AXIS));
        this.game = game;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);

        this.frame.setTitle("BigTwo");
        JLabel gamename = new JLabel("Game");
        JPanel left = new JPanel();
        this.frame.add(gamename);
        this.frame.add(scrollPane);
        gamename.setBounds(0,0,(gamename.getPreferredSize().width), (gamename.getPreferredSize().height));
        this.scrollPane.setBounds(350, 0+gamename.getPreferredSize().height, this.scrollPane.getPreferredSize().width,this.scrollPane.getPreferredSize().height);
        
        this.frame.add(this.playerButton);
        this.frame.add(this.passButton);
        this.playerButton.setBounds(105-(this.playerButton.getPreferredSize().width/2), 800, this.playerButton.getPreferredSize().width, this.playerButton.getPreferredSize().height);
        this.passButton.setBounds(105+this.passButton.getPreferredSize().width/2, 800, this.passButton.getPreferredSize().width,this.passButton.getPreferredSize().height);
        

        //set player image
        for(int i=0;i<4;i++){
            if(true){
                try{

                    Image tempimage  = ImageIO.read(new File(String.format("player/"+i+".png")));
                    this.avatars[i]= tempimage.getScaledInstance(75,75, Image.SCALE_SMOOTH);
                }
                catch(java.io.IOException e){
                    System.out.println("CANT MAKE PLAYER");
                }
            }
        }
        //set back image
        if(true){
            try{
                
                for(int i=0;i<53;i++){
                    this.cardBackImage =  ImageIO.read(new File(String.format("cards/b.gif")));
                    this.backimagearr[i] = new JLabel(new ImageIcon(this.cardBackImage));
                }
            }
            catch(java.io.IOException e){
                System.out.println("CANT MAKE CARDBACK");
            }
        }
        
        //set poker image
        //havent use the logic to split cards!!!! remark!!

        for(int i=0;i<4;i++){
            for(int j=1;j<14;j++){
                String num="";
                String suit="";
                switch(j){
                    case 1: 
                        num="a"; break;
                    case 10:
                        num="t"; break;
                    case 11:
                        num = "j"; break;
                    case 12:
                        num = "q";
                        break;
                    case 13:
                        num = "k";
                        break;
                    default:
                        num = num+j;
                        break;
                }
                switch(i){
                    case 0:
                    suit="d";
                    break;
                    case 1:
                    suit = "c";break;
                    case 2:
                    suit = "h"; break;
                    case 3:
                    suit="s"; break;
                }
                if(true){
                    try{
                        String temp="cards/"+num+suit+".gif";
                        this.cardImages[i][j-1] = ImageIO.read(new File(String.format(temp)));
                        cardImage[i][j - 1] = new JLabel(new ImageIcon(this.cardImages[i][j - 1]));
                        cardImage[i][j-1].setName(""+i+num);
                        cardImage[i][j-1].addMouseListener(new bigTwoPanel());
                        //System.out.println("Card Made("+num+suit+")\n");
                    }
                    catch(java.io.IOException e){
                        System.out.println("CANT MAKE CARD "+num+suit+"\n");
                    }
                }
            }
        }


        //set playerpanel
        for(int i=0;i<4;i++){
            String test = "player"+i;
            JLabel test2 = new JLabel(test);
            playerimage[i] = new JLabel(new ImageIcon(this.avatars[i]),0);
            this.playerpanel[i] = new bigTwoPanel();

            this.playerpanel[i].setBackground(Color.getHSBColor(119/360f, 35/100f,50/100f));
            this.playerpanel[i].add(test2);
            test2.setBounds(5, 0, test2.getPreferredSize().width, test2.getPreferredSize().height);
            this.playerpanel[i].add(playerimage[i]);
            playerimage[i].setName("player"+i);
            playerimage[i].setBounds(0, 25, playerimage[i].getPreferredSize().width, playerimage[i].getPreferredSize().height+20);
            playerpanel[i].setImage(this.cardBackImage);
            this.playerpanel[i].setBounds(0, gamename.getPreferredSize().height+i*155, 350, 150);
            this.frame.add(this.playerpanel[i]);
        }

        //set lasthandpanel
        this.playerpanel[4] = new bigTwoPanel();
        String testdisplay = "Played by Player";
        JLabel testdisplay2 = new JLabel(testdisplay);
        this.playerpanel[4].add(testdisplay2);
        testdisplay2.setBounds(5, 0, testdisplay2.getPreferredSize().width,testdisplay2.getPreferredSize().height);
        this.playerpanel[4].setBounds(0, gamename.getPreferredSize().height+4*155, 350, 150);
        this.playerpanel[4].setBackground(Color.getHSBColor(228/360f, 40/100f, 70/100f));
        this.frame.add(this.playerpanel[4]);

        this.frame.setSize(760,850);
        this.msgArea.append("");
        this.enable();
        this.frame.setVisible(true);
    };



	/**
     * void setActivePlayer(int activePlayer) – a method for setting the index of the active player (i.e., the current player).
     * @param active Player
     */
    void setActivePlayer(int activePlayer){
        this.activePlayer = activePlayer;
    };
    /**
     * int[] getSelected() – a method for getting an array of indices of the cards selected.
     * @return array of selected
     */
    int[] getSelected(){

        int j=0;
        for(int i=0;i<this.selected.length;i++){
            if(this.selected[i] == true) j++;
        }
        int[] tobereturn = new int[j];
        //msgArea.append(Integer.toString(j)+" ITS J.length\n");
        j=0;
        for(int i=0;i<this.selected.length;i++){
            if(this.selected[i]==true){
                tobereturn[j]=i;
                j++;
            }
        }
        //msgArea.append("tobereturn.length: "+Integer.toString(tobereturn.length));
        /*
        for(int i=0;i<tobereturn.length;i++){
            msgArea.append(Integer.toString(i)+"\n");
        }*/
        return tobereturn;
    };
    /**
     * void resetSelected() – a method for resetting the list of selected cards.
     */
    void resetSelected(){
        Arrays.fill(selected,false);
    };
    void removeall(){
        for(int i=0;i<5;i++){
            playerpanel[i].RemoveAll();
            //this.msgArea.append("Removed\n");
        }
    }
    /**
     * void repaint() – a method for repainting the GUI.
     */
    void repaint(){

        for(int i=0;i<5;i++){

            if(i!=4){
            String test = "player"+i;
            JLabel test2 = new JLabel(test);
            playerimage[i] = new JLabel(new ImageIcon(this.avatars[i]),0);
            this.playerpanel[i].add(test2);
            test2.setBounds(5, 0, test2.getPreferredSize().width, test2.getPreferredSize().height);
            this.playerpanel[i].add(playerimage[i]);
            playerimage[i].setName("player"+i);
            playerimage[i].setBounds(0, 25, playerimage[i].getPreferredSize().width, playerimage[i].getPreferredSize().height+20);
            }
            else{
                String testdisplay = "Played by Player";
                JLabel testdisplay2 = new JLabel(testdisplay);
                this.playerpanel[4].add(testdisplay2);
                testdisplay2.setBounds(5, 0, testdisplay2.getPreferredSize().width,testdisplay2.getPreferredSize().height);
                this.playerpanel[4].setBounds(0, testdisplay2.getPreferredSize().height+4*155, 350, 150);
            }

        }


        for(int i=0;i<4;i++){
            int temp=75;
            if(game.getCurrentIdx()==i){
                for(int j=0;j<this.game.getPlayerList().get(i).getCardsInHand().size();j++){
                        int tempsuit=this.game.getPlayerList().get(i).getCardsInHand().getCard(j).suit;
                        int tempnum = this.game.getPlayerList().get(i).getCardsInHand().getCard(j).rank;
                        this.playerpanel[i].add(cardImage[tempsuit][tempnum]);
                        cardImage[tempsuit][tempnum].setBounds(temp, 25, cardImage[tempsuit][tempnum].getPreferredSize().width, cardImage[tempsuit][tempnum].getPreferredSize().height);
                        temp+=15;
                    }

            }
            else{
                    for(int j=0;j<this.game.getPlayerList().get(i).getCardsInHand().size();j++){
                        //msgArea.append(Integer.toString(game.getPlayerList().get(i).getCardsInHand().size())+"sisze()\n");
                        this.playerpanel[i].add(backimagearr[numofback]);
                        backimagearr[numofback].setBounds(temp, 25, backimagearr[numofback].getPreferredSize().width, backimagearr[numofback].getPreferredSize().height);
                        if(numofback>39)
                        numofback=0;
                        else numofback++;
                        temp+=15;
                    }
                }

        }
        
        if(game.getHandsOnTable().size()!=0){
            for(int i=0;i<game.getHandsOnTable().get(game.getHandsOnTable().size()-1).size();i++){
                int suit = game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getCard(i).suit;
                int num = game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getCard(i).rank;
                playerpanel[4].add(cardImage[suit][num]);
                cardImage[suit][num].setBounds(i*15, 25, cardImage[suit][num].getPreferredSize().width, cardImage[suit][num].getPreferredSize().height);
            }
        }
        

    };
    /**
     * void printMsg(String msg) – a method for printing the specified string to the message area of the GUI.
     */
    void printMsg(String msg){
        this.msgArea.append(msg);
    };
    /**
     * void clearMsgArea() – a method for clearing the message area of the GUI.
     */
    void clearMsgArea(){
        this.msgArea.setText(null);
    };

    /**
     * void reset() – a method for resetting the GUI. You should (i) reset the list of selected cards using resetSelected() method from the CardGameTable interface; (ii) clear the message area using the clearMsgArea() method from the CardGameTable interface; and (iii) enable user interactions using the enable() method from the CardGameTable interface.
     */
    void reset(){
        this.resetSelected();
        this.clearMsgArea();
        this.enable();
        for(int i=0;i<4;i++){
            this.playerpanel[i].removeAll();
        }
    };

    /**
     * void enable() – a method for enabling user interactions with the GUI. You should (i) enable the “Play” button and “Pass” button (i.e., making them clickable); and (ii) enable the BigTwoPanel for selection of cards through mouse clicks.
     */
    void enable(){
        this.playerButton.addActionListener(a);
        this.passButton.addActionListener(b);
    };
    /**
     * void disable() – a method for disabling user interactions with the GUI. You should (i) disable the “Play” button and “Pass” button (i.e., making them not clickable); and (ii) disable the BigTwoPanel for selection of cards through mouse clicks.
     */
    void disable(){
        this.a.setActive(false);
        this.b.setActive(false);
    };
    
    /**
     * inner classes below
     */

     /**
      * 
class PlayButtonListener – an inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle button-click events for the “Play” button. When the “Play” button is clicked, you should call the makeMove() method of your CardGame object to make a move.
      */
    class playerButtonListener implements ActionListener {
        private boolean active = true;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void actionPerformed(ActionEvent event) {
            if (active) {
                int[] temp = getSelected();
                if(temp.length!=0){
                        game.checkMove(game.getCurrentIdx(), getSelected());
                }
                else msgArea.append("Not a legit move!!!\n");

                //msgArea.append("playbutton\n");
            }
            else{
                System.out.println("Not active by playerbuttonlistener");
            }
        }

    }
    /**
     * class PassButtonListener – an inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle button-click events for the “Pass” button. When the “Pass” button is clicked, you should call the makeMove() method of your CardGame object to make a move.
     */
    class passButtonListener  implements ActionListener{
        private boolean active = true;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void actionPerformed(ActionEvent event) {
            if (active) {
                if(game.getHandsOnTable().size()!=0){
                    {
                        if(game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer().getName()!=game.getPlayerList().get(game.getCurrentIdx()).getName()){
                            int [] temp = null;
                            game.makeMove(game.getCurrentIdx(), temp);
                        }
                        else{
                            msgArea.append("Not a legit move!!!\n");
                        }
                    }

                }
                else{
                    msgArea.append("Not a legit move!!!\n");
                }

            }
            else{
                System.out.println("Not active by PASSbuttonlistener");
            }
        }
    }


    /**
     * class BigTwoPanel – an inner class that extends the JPanel class and implements the MouseListener interface. Overrides the paintComponent() method inherited from the JPanel class to draw the card game table. Implements the mouseClicked() method from the MouseListener interface to handle mouse click events.
     * As I dont think its useful
     */
    class bigTwoPanel extends JPanel implements MouseListener{
        private static final long serialVersionUID = 1473391298554396184L;
        boolean selectcarded = false;
        public void mouseClicked(MouseEvent event){
            //if(event.getComponent().getName()!=null)
            //msgArea.append(event.getComponent().getName()+"\n");
            if(event.getComponent().getY()==25)
            event.getComponent().setBounds(event.getComponent().getX(), event.getComponent().getY()-5, event.getComponent().getWidth(), event.getComponent().getHeight());
            
            else if(event.getComponent().getY()==20)
            event.getComponent().setBounds(event.getComponent().getX(), event.getComponent().getY()+5, event.getComponent().getWidth(), event.getComponent().getHeight());
            Component name = this.selecteditem(event);
            String tempname = "";
            if(name.getName()!=null){
                tempname=name.getName();
            };
            char[] temp1 = tempname.toCharArray();
                int number;
                //msgArea.append("temp1: "+temp1[1]+"\n");
                switch(temp1[1]){
                case 'a': 
                    number=0; break;
                case 't':
                    number=9; break;
                case 'j':
                    number = 10; break;
                case 'q':
                    number = 11;
                    break;
                case 'k':
                    number = 12;
                    break;
                default:
                    number =Character.getNumericValue(temp1[1])-1;
                    break;
                }
            //msgArea.append("hello\n");
            //msgArea.append(Integer.toString(game.getPlayerList().get(game.getCurrentIdx()).getNumOfCards()));
            for(int i=0;i<game.getPlayerList().get(game.getCurrentIdx()).getNumOfCards();i++){
                //msgArea.append("rank: "+Integer.toString(game.getPlayerList().get(game.getCurrentIdx()).getCardsInHand().getCard(i).rank)+" ");
                //msgArea.append("suit: "+Integer.toString(game.getPlayerList().get(game.getCurrentIdx()).getCardsInHand().getCard(i).suit)+"\n");
                if(game.getPlayerList().get(game.getCurrentIdx()).getCardsInHand().getCard(i).rank == number && 
                game.getPlayerList().get(game.getCurrentIdx()).getCardsInHand().getCard(i).suit==Character.getNumericValue(temp1[0])){
                    selected[i]=selected[i]==true?false:true;
                }

            }
            /*
            for(int i=0;i<selected.length;i++){
                if(selected[i]==true){
                    msgArea.append(Integer.toString(i)+"haha\n");
                }
            }
            */
        }
        public void mouseEntered(MouseEvent e) {}  
        public void mouseExited(MouseEvent e) {}  
        public void mousePressed(MouseEvent e) {}  
        public void mouseReleased(MouseEvent event) { 


        }  
        bigTwoPanel(){
            //new bigTwoPanel();
            //new JPanel(null);
            this.setLayout(null);
            //System.out.println(this.getComponentCount());

        }
        public void RemoveAll(){
            this.removeAll();
        }
        Component selecteditem(MouseEvent event){
            return event.getComponent();
        }   
        private Image image;
        private Graphics g;

        public void setImage(Image image) {
            this.image = image;
            this.g = this.image.getGraphics();
        }
        public Graphics getGrap(){
            return this.g;
        }
        public void paintComponent(Graphics g, int x) {
            super.paintComponent(g);
        }
    }
    




    
}