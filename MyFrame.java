import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame implements KeyListener{

	static Draw drawing = new Draw();

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
           drawing.moveRight();
           System.out.println("pos: " + drawing.x + "," + drawing.y);
       }

       else if(e.getKeyCode() == KeyEvent.VK_LEFT){
           drawing.moveLeft();
           System.out.println("pos: " + drawing.x + "," + drawing.y);
       }

       else if(e.getKeyCode() == KeyEvent.VK_UP){
           drawing.moveUp();
           System.out.println("pos: " + drawing.x + "," + drawing.y);
       }

       else if(e.getKeyCode() == KeyEvent.VK_DOWN){
           drawing.moveDown();
           System.out.println("pos: " + drawing.x + "," + drawing.y);
       }

       else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.spawnEnemy();
			System.out.println("Enemy Spawned!");
		}

       else if(e.getKeyCode() == KeyEvent.VK_SPACE){
           drawing.jump();
           System.out.println("Jump");
       }

       else if(e.getKeyCode() == KeyEvent.VK_A){
           drawing.attack();
           System.out.println("Attack");
       }

       else if(e.getKeyCode() == KeyEvent.VK_X){
           drawing.slide();
           System.out.println("Slide");
       }
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){

	}

	public static void main(String []args){
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(715,445);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(drawing);
		gameFrame.addKeyListener(gameFrame);
	}
}