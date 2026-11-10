//Acknowledgements goes to RyiSnow, Dr. Obrele, & BroCode for helping me code this.
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;


public class Player extends Entity
{
   int sizex, sizey; //Size of the image
   public int speed;
   private int xVelocity;
   private int yVelocity;
   private int resize;
   
   public Player(int row, int col)
   {
      sizex = 50;
      sizey = 50;
      speed = 5;
      this.row = row;
      this.col = col;
      setDefaultValues();
      getPlayerImage();
   }
   
   
   public void getPlayerImage()
   {
      try{
         up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/front-face.png"));
         right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/walking/pixil-frame-1.png"));
         right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/walking/pixil-frame-3.png"));
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   public void setDefaultValues()
   {
      row = 500;
      col = 500;
      direction = "down";
      
   }
   
   public void setXDirection(int x)
   {
      xVelocity = x;
   }
   
   public int getXCoord()
   {
      return row;
   }
   
   public void setYDirection(int Y)
   {
      yVelocity = Y;
   }
   
   public int getYCoord()
   {
      return col;
   }
   
   public void checkCollisionsOnScreen() //Prevents the player going offscreen //Pathfinding in the 3rd quarter // Also check collisions ahead of the player instead on the player - Dr. Obrele
   {
      if(row < 0)
      {
         yVelocity = 0;
         row = 0;
      }
      else if(row >= CustomizedPanel.SIZE - sizex)
      {
         yVelocity = 0;
         row =(int) (CustomizedPanel.SIZE - sizex);  
      }
      
      if(col < 0)
      {
         xVelocity = 0;
         col = 0;
      }
      else if(col >= CustomizedPanel.SIZE - sizey - sizey/2)
      {
         xVelocity = 0;
         col =(int) (CustomizedPanel.SIZE - sizey - sizey/2);
      }
   }
   
  /* public void resizeImage(ImageIcon d) //Credits go to Daniel Searson on CodeRanch //Infiniteloop
   {
      Image img = d.getImage();
      
      //BufferedImage bi = new BufferedImage(sizex,sizey,BufferedImage.TYPE_4BYTE_ABGR);
      //Graphics g = bi.createGraphics();
      //g.drawImage(img,row,col,img.getWidth(null)*resize,img.getHeight(null)*resize,null,null);
      
      Image newImage = img.getScaledInstance(sizex*resize,sizey*resize,Image.SCALE_SMOOTH);
      
      placeholder = new ImageIcon(newImage);
      sizex = placeholder.getIconWidth();
      sizey = placeholder.getIconHeight();
   } */
   
   public void move() //changes x and y position
   {
      row = row + xVelocity;
      xVelocity = 0;
      col = col + yVelocity;
      yVelocity = 0;
      checkCollisionsOnScreen();
      
   }
   
   public void changePosition(int m) //Makes player move depending on where it's facing.
   {
      if(m == 1)
      {
         direction = "right";
         setXDirection(speed); //Right
         move();
      }
      if(m == 2)
      {
         direction = "left";
         setXDirection(-speed); //left
         move();
      }
      if(m == 3)
      {
         direction = "down";
         setYDirection(-speed); //down
         move();
      }
      if(m == 4)
      {
         direction = "up";
         setYDirection(speed); //up
         move();
      }
      
      spriteCounter++;
      if(spriteCounter > 15)
      {
         if(spriteNum == 1)
         {
            spriteNum = 2;
         }
         else if(spriteNum == 2)
         {
            spriteNum = 1;
         }
         spriteCounter = 0;
      }
   }
   
   /*public void keyReleased(KeyEvent e) //From the initial player class. The KeyHandler class handles this
   {
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         setXDirection(0);
         move();
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         setXDirection(0);
         move();
      }
      
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         setYDirection(0);
         move();
      }
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         setYDirection(0);
         move();
      }
   }*/
   
   public void draw(Graphics g, int ro, int col)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      //placeholder = new ImageIcon("Assets/Player/front-face.png");
      //resizeImage(placeholder); //DO NOT UNCOMMENT THIS THIS WILL CREATE AN INFINITE LOOP
      BufferedImage img = null;
      
      switch(direction){
      case "up":
         if(spriteNum == 1)
         {
         img = up1;
         }
         if(spriteNum == 2)
         {
         img = up2;
         }
         break;
      case "down":
         if(spriteNum == 1)
         {
         img = down1;
         }
         if(spriteNum == 2)
         {
            img = down2;
         }
         break;
      case "left":
         if(spriteNum == 1)
         {
         img = left1;
         }
         if(spriteNum == 2)
         {
         img = left2;
         }
         break;
      case "right":
         if(spriteNum == 1)
         {
         img = right1;
         }
         if(spriteNum == 2)
         {
         img = right2;
         }
         break;
      }
      
      g2.drawImage(img,row,col,sizex,sizey,null);
      
      row = ro;
      this.col = col;
   }
}