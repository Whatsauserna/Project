//Acknowledgements goes to RyiSnow and Dr. Obrele for helping me code this.

import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class CustomizedPanel extends JPanel implements Runnable //imports thread
{
   final static int playerSize = 50;
   
   public final static int SIZE = 15 * playerSize; //size of the screen
   
   Thread gameThread; //allows the program to run efficiently by doing multiple things at the same time.
   KeyHandler keyH = new KeyHandler();
   
   //Sets FPS
   int FPS = 60;
   
   //creates player
   Player p;
   
   //Set player's default position
   int playerX = SIZE/2;
   int playerY = SIZE/2;
   
   public CustomizedPanel() //Initializes the game screen
   {
      this.setPreferredSize(new Dimension(SIZE,SIZE));
      this.setBackground(Color.green);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
      p = new Player(playerX,playerY);
   }
   
   public void  startGameThread()
   {
      gameThread = new Thread(this);
      gameThread.start(); //automatically calls the run method below
   }
   
   @Override
   public void run() //Is used to create a Thread, starting the thread causes the object's run method to be called in seperately to execute the thread
   {
      double drawInterval = 1000000000/FPS;
      double delta = 0;
      long lastTime = System.nanoTime();
      long timer = 0;
      long currentTime;
      int drawCount = 0;
   
      while(gameThread != null)
      {
         currentTime = System.nanoTime();
         
         delta += (currentTime - lastTime)/drawInterval;
         timer += (currentTime - lastTime);
         lastTime = currentTime;
         
         if(delta > 1)
         {
            //Update : update info such as character positions
            update();
            //draw the screen with the updated information
            repaint();
            delta--;
            drawCount++; //Counts by one whenever it updates and repaints
         }
         
         if(timer >= 1000000000)//Displays FPS
         {
            System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer = 0;
         }
         
      }
   }
   
   public void update() //Updates the screen when the computer detects if the player presses any of the following buttons.
   {
      if(keyH.upPressed == true)
      {
         p.changePosition(3);
         playerX = p.getXCoord();
         playerY = p.getYCoord();
      }
      if(keyH.downPressed == true)
      {
         p.changePosition(4);
         playerX = p.getXCoord();
         playerY = p.getYCoord();
      }
      if(keyH.leftPressed == true)
      {
         p.changePosition(2);
         playerX = p.getXCoord();
         playerY = p.getYCoord();
      }
      if(keyH.rightPressed == true)
      {
         p.changePosition(1);
         playerX = p.getXCoord();
         playerY = p.getYCoord();
      }
      if(keyH.spacePressed == true)
      {
         p.changePosition(5);
         playerX = p.getXCoord();
         playerY = p.getYCoord();
      }
   }
   
   public void paintComponent(Graphics g) //Shows the graphics.
   {
      super.paintComponent(g); 
      
      Graphics2D g2 = (Graphics2D) g;
      
      
      //g2.setColor(Color.white);
      
      //g2.fillRect(playerX,playerY,p.sizex,p.sizey);
      
      p.draw(g,playerX,playerY);//updates player's position & image
      
      g2.dispose();
   }
}