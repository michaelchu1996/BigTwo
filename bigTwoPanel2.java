import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
    class bigTwoPanel2 extends JPanel implements MouseListener{
            public void mouseClicked(MouseEvent event){
                //msgArea.append(event.getClass().getName()+"\n");
            }
       
            public void mouseEntered(MouseEvent e) {}  
            public void mouseExited(MouseEvent e) {}  
            public void mousePressed(MouseEvent e) {}  
            public void mouseReleased(MouseEvent e) {}  
        //bigTwoPanel.addMouseListener(this);
        bigTwoPanel2(){
            //new bigTwoPanel();
            addMouseListener(this);

        }
        public static void main(String[] args) {  
            new bigTwoPanel2();
        }  
    }