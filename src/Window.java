import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JPanel implements KeyListener {
    JFrame frame;
    int sqrSize = 40;
    Game game = new Game(10,20);

    Window(){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(0, 0, game.getWidth() * sqrSize + 30, game.getHeight() * sqrSize + 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setBackground(Color.gray);
        setLayout(null);
        frame.add(this);
        frame.addKeyListener(this);



        game.progress();

        frame.repaint();

        Timer timer = new Timer(200, e -> tick());
        timer.start();
    }

    void tick(){
        System.out.println("tick");
        game.progress();
        game.checkLines();
        frame.repaint();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i = 0; i < game.getWidth(); i++){
            for(int j = 0; j < game.getHeight(); j++){
                if(game.getSquare(i,j) == 1){
                    g.setColor(game.getCurrentTileColor());
                    g.fillRect(sqrSize*i,sqrSize*j,sqrSize,sqrSize);

                    g.setColor(Color.black);
                    g.drawRect(sqrSize*i,sqrSize*j,sqrSize,sqrSize);
                }

                if(game.getSquare(i,j) == 2){
                    g.setColor(Color.blue);
                    g.fillRect(sqrSize*i,sqrSize*j,sqrSize,sqrSize);

                    g.setColor(Color.black);
                    g.drawRect(sqrSize*i,sqrSize*j,sqrSize,sqrSize);
                }

            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            System.out.println("w pressed");
            game.rotate();
        }
        if(e.getKeyChar() == 'a'){
            System.out.println("a pressed");
            game.leftMove();
        }

        if(e.getKeyChar() == 'd'){
            System.out.println("d pressed");
            game.rightMove();
        }

        if(e.getKeyChar() == 's'){
            System.out.println("s pressed");
            game.progress();
        }

        frame.repaint();

    }

    public void keyReleased(KeyEvent e) {

    }
}
