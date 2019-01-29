import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Draw extends JComponent{

	private BufferedImage image;
	private BufferedImage background;
	private URL resource = getClass().getResource("run/run0.png");

    //circle's position
    public int x = 30;
    public int y = 200;

    public int state = 0;

    public Draw(){
		try{
			image = ImageIO.read(resource);
			background = ImageIO.read(getClass().getResource("background.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public void reloadImage(){
        state++;
        //Run Animation
        if(state == 0){
            resource = getClass().getResource("run/run0.png");
        }

        else if(state == 1){
            resource = getClass().getResource("run/run1.png");
        }

         else if(state == 2){
            resource = getClass().getResource("run/run2.png");
        }

         else if(state == 3){
            resource = getClass().getResource("run/run3.png");
        }

         else if(state == 4){
            resource = getClass().getResource("run/run4.png");
        }

         else if(state == 5){
            resource = getClass().getResource("run/run5.png");
            state = 0;
        }
        //

        try{
            image = ImageIO.read(resource);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 17; ctr++){
					try {
						if(ctr==16){
							resource = getClass().getResource("run/run0.png");
						}
						else{
							resource = getClass().getResource("atak/atak"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}

	public void castAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 9; ctr++){
					try {
						if(ctr==8){
							resource = getClass().getResource("run/run0.png");
						}
						else{
							resource = getClass().getResource("cast/cast"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}

		public void jumpAnimation(){
		Thread thread3 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run/run0.png");
						}
						else{
							resource = getClass().getResource("jump/jump"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread3.start();
	}

	public void slideAnimation(){
		Thread thread4 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run/run0.png");
						}
						else{
							resource = getClass().getResource("slide/slide"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread4.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void cast(){
		castAnimation();
	}

	public void jump(){
		jumpAnimation();
		y = y - 20;
	}

	public void slide(){
		slideAnimation();
		x = x + 50;
	}

    public void moveRight(){
        x = x + 5;
        reloadImage();
        repaint();
    }

    public void moveLeft(){
        x = x - 5;
        reloadImage();
        repaint(); 
    }

    public void moveDown(){
        y = y + 5;
        reloadImage();
        repaint();
        
    }

    public void moveUp(){
        y = y - 5;
        reloadImage();
        repaint(); 
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
      	g.drawImage(background, 0, 0, this);
		g.drawImage(image, x, y, this);

    }
}