import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Draw extends JComponent{

	private BufferedImage image;
	private BufferedImage background;
	private BufferedImage charP;
	public URL resource = getClass().getResource("char/idle0.png");


	public int x = 300;
	public int y = 300;
	public int height = 0;
	public int width = 0;

	// animation states
	public int state = 0;

	// randomizer
	public Random randomizer;

	// enemy
	public int enemyCount;
	Monster[] monsters = new Monster[10];

	//player stats
	public int life = 100;
	public int mana = 100;
	public int lvl = 1;
	public int atk = 3;
	public int exp = 1;
	public int gold = 0;
	public boolean idle = true;
	public boolean alive = true;

	public Draw(){
		randomizer = new Random();
		spawnEnemy();
		
		try{
			image = ImageIO.read(resource);
			background = ImageIO.read(getClass().getResource("background.png"));
			charP = ImageIO.read(getClass().getResource("CharPic.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();

		startGame();
	}

	public void startGame(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < monsters.length; c++){
							if(monsters[c]!=null){
								monsters[c].moveTo(x,y);
								repaint();
							}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(enemyCount < 10){
			monsters[enemyCount] = new Monster(randomizer.nextInt(600), randomizer.nextInt(300), this);
			enemyCount++;
		}
	}

    public void reloadImage(){
        state++;
        if(state == 0){
            resource = getClass().getResource("char/run0.png");
        }

        else if(state == 1){
            resource = getClass().getResource("char/run1.png");
        }

         else if(state == 2){
            resource = getClass().getResource("char/run2.png");
        }

         else if(state == 3){
            resource = getClass().getResource("char/run3.png");
        }

         else if(state == 4){
            resource = getClass().getResource("char/run4.png");
        }

         else if(state == 5){
            resource = getClass().getResource("char/run5.png");
            state = 0;
        }
    	
        try{
            image = ImageIO.read(resource);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void idleAnimation(){
    	Thread thread0 = new Thread(new Runnable(){
    		public void run(){
    			while(idle){
    				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("char/idle0.png");
						}
						else{
							resource = getClass().getResource("char/idle"+ctr+".png");
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
    		}
    	});
    	thread0.start();
    }

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("char/idle0.png");
						}
						else{
							resource = getClass().getResource("char/atak"+ctr+".png");
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
					for(int x=0; x<monsters.length; x++){
						if(monsters[x]!=null){
							if(monsters[x].contact){
								monsters[x].life = monsters[x].life - atk;
						}
					}
				}
				}
			}
		});
		thread1.start();
	}

	public void jumpAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							y = y + 40;
							resource = getClass().getResource("char/idle0.png");
						}
						else{
							y = y - 10;
							resource = getClass().getResource("char/jump"+ctr+".png");
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

	public void slideAnimation(){
		Thread thread3 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("char/idle0.png");
						}
						else{
							x = x + 10;
							resource = getClass().getResource("char/slide"+ctr+".png");
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
		thread3.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void jump(){
		jumpAnimation();
	}

	public void slide(){
		slideAnimation();
	}

    public void moveRight(){
        x = x + 5;
        reloadImage();
        repaint();
        checkCollision();
    }

    public void moveLeft(){
        x = x - 5;
        reloadImage();
        repaint(); 
        checkCollision();
    }

    public void moveDown(){
        y = y + 5;
        reloadImage();
        repaint();
        checkCollision();
    }

    public void moveUp(){
        y = y - 5;
        reloadImage();
        repaint();
        checkCollision(); 
    }

	public void checkCollision(){
		int xChecker = x + width;
		int yChecker = y;

		for(int x=0; x<monsters.length; x++){
			boolean collideX = false;
			boolean collideY = false;

			if(monsters[x]!=null){
				monsters[x].contact = false;

				if(yChecker > monsters[x].yPos){
					if(yChecker-monsters[x].yPos < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}
				else{
					if(monsters[x].yPos - (yChecker+height) < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}

				if(xChecker > monsters[x].xPos){
					if((xChecker-width)-monsters[x].xPos < monsters[x].width){
						collideX = true;
						System.out.println("collideX");
					}
				}
				else{
					if(monsters[x].xPos-xChecker < monsters[x].width){
						collideX = true;
						System.out.println("collideX");
					}
				}
			}

			if(collideX && collideY){
				System.out.println("collision!");
				monsters[x].contact = true;
			}
		}
	}

    public void paintComponent(Graphics g){
        super.paintComponent(g);

      	g.drawImage(background, 0, 0, this);
		g.drawImage(image, x, y, this);
		
		//HUD
		g.drawImage(charP, 5, 5, this);
		//Life
		g.setColor(Color.RED);
		g.fillRect(60, 15, 200, 20);

		g.setColor(Color.GREEN);
		g.fillRect(60, 15, life * 2, 20);

		//Exp
		g.setColor(Color.GRAY);
		g.fillRect(60, 40, 100, 10);

		g.setColor(Color.YELLOW);
		g.fillRect(60, 40, exp * 2, 10);


		for(int c = 0; c < monsters.length; c++){		
			if(monsters[c]!=null){
				g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 5);
			}	
		}
	}

	//WIP
	public void reward(){
		exp = monsters[x].exp + exp;
	}

	public void checkDeath(){
		for(int c = 0; c < monsters.length; c++){
			if(monsters[c]!=null){
				if(!monsters[c].alive){
					monsters[c] = null;
				}
			}			
		}
	}
}