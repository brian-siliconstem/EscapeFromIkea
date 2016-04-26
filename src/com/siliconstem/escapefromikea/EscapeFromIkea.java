/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siliconstem.escapefromikea;

import com.siliconstem.tilepanel.TilePanel;
import java.awt.Color;

//import java.awt.Color; 
import asciipanel.AsciiPanel;
import com.siliconstem.tilepanel.Utilities;
import java.awt.Dimension; 
import javax.swing.JFrame; 
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 *
 * @author HP ENVY
 */
public class EscapeFromIkea extends JFrame implements KeyListener {

    TilePanel gameScreen;
    TheShopper shopper=new TheShopper();
    IkeaEmployee ikeaEmployee=new IkeaEmployee();
    
    
    String tileFilename="IkeaTileSet.png";
    String tileNamesFilename="IkeaTileSet.txt";
    
    public EscapeFromIkea(){
        //get the url of the sprite image and names we'll use
        URL spriteURL= EscapeFromIkea.class.getResource(tileFilename);
        URL spriteNamesURL= EscapeFromIkea.class.getResource(tileNamesFilename);
        if(spriteURL==null) {
            System.out.println("spriteURL is NULL");
            System.exit(0);
        }
        if(spriteNamesURL==null) {
            System.out.println("spriteNamesURL is NULL");
            System.exit(0);
        }
        //load the url into a sprite
        BufferedImage glyphs=Utilities.loadBufferedImage(spriteURL);
        //load the names into a stringarray
        String[] glyphNames=Utilities.loadCSVToArray(spriteNamesURL);
        //create the tilepanel with our sprite
        gameScreen=new TilePanel(32,20,glyphs,glyphNames,19,19,16,16);
        
        //set up the individual glyphs
        gameScreen.loadGlyphs(glyphs);
        
        
        
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Escape from Ikea");
        this.setSize(gameScreen.getWidthPixels(),gameScreen.getHeightPixels());
        this.add(gameScreen);
        
        initializeTheGame();
        drawScreen();
    }
    // here we set up the game - this is called once at the beginning of a new game
    public void initializeTheGame(){
        shopper.xLocation=8;
        shopper.yLocation=5;
        shopper.tilePanelImageId=gameScreen.getTileByName("shopper1");
        ikeaEmployee.xLocation=16;
        ikeaEmployee.yLocation=10;
        ikeaEmployee.tilePanelImageId=83;
        
        
       
                
    }
  
    //this is called after every key press to redraw the scree with any changes
    public void drawScreen(){
        //draw the background
        gameScreen.clear(173);
        
        //draw a wall
        for(int i=0;i<20;i++){
            gameScreen.write(9, i, 8);
        }
        
        //draw the shopper
        System.out.println(shopper.toString());
        gameScreen.write(shopper.tilePanelImageId,shopper.xLocation,shopper.yLocation);
        
        //draw the ikea employee
        gameScreen.write(ikeaEmployee.tilePanelImageId,ikeaEmployee.xLocation,ikeaEmployee.yLocation);
        this.repaint();
    }
 
    
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped:"+e.getKeyChar());
        
    }
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased:"+e.getKeyChar());
        //drawScreen();
    }
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed"+e.getKeyChar());
        //int xChange;
        //int yChange
        int oldX=this.shopper.xLocation;
        int oldY=this.shopper.yLocation;
        int newX=this.shopper.xLocation;
        int newY=this.shopper.yLocation;
        if (e.getKeyCode()== KeyEvent.VK_Q){
            System.exit(0);
        }
        else if (e.getKeyCode()== KeyEvent.VK_LEFT){
            newX=this.shopper.xLocation-1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            newX=this.shopper.xLocation+1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){ 
            newY=this.shopper.yLocation-1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            
            newY=this.shopper.yLocation+1;
        }
     
        if(this.gameScreen.getGlyph(newX, newY)==9){
            //we walked into a wall, go back
            this.shopper.xLocation=oldX;
            this.shopper.yLocation=oldY;
        }
        else{
            this.shopper.xLocation=newX;
            this.shopper.yLocation=newY;
        }
        
        
        
        
        drawScreen();
    }
    public static void oldMain(){
        //TilePanel();
     //  EscapeFromIkea escapeGame =new EscapeFromIkea();
    /*    AsciiPanel game=new AsciiPanel(80,32);
        game.loadGlyphs();
        JFrame mainWindow*/
    }
    public static void testArray(){
        int[] myArray;
        myArray=new int[6];
        myArray[2]=5;
        System.out.println(myArray[2]);
    }
   
     public static void testTilePanel(){
        TilePanel tp=new TilePanel(5,5);
        tp.load();
        JFrame window=new JFrame();
        window.add(tp);
        window.setVisible(true);
        window.setSize(tp.getWidthPixels(),tp.getHeightPixels());
        tp.write(1,2,2);
        tp.write(58,0,0);
        tp.write(58,1,0);
        tp.write(58,2,0);
        tp.write(58,3,0);
        EscapeFromIkea theGame=new EscapeFromIkea();
        window.addKeyListener(theGame);
    }
    
    public static void test2(){
        
        
   
    }
    public static void test1(){
        TilePanel tp=new TilePanel(20,20);
        tp.loadGlyphs();
        
        JFrame window=new JFrame();
        window.add(tp);
        window.setVisible(true);
        window.setSize(tp.getWidthPixels(),tp.getHeightPixels());
        
        tp.write(1,0,0);
        tp.write(1,1,0);
        tp.write(1,0,1);
        tp.write(1,1,1);
        tp.write(1,2,2);
       
    }
    public static void testArrays(){
        int[][] my2DArray;
        my2DArray=new int[80][32]; 
        System.out.println(my2DArray);
        System.out.println(my2DArray[1][1]);
    }
    
     public static void main(String[] args) {
       

        EscapeFromIkea escapeGame =new EscapeFromIkea();
     
    /*    AsciiPanel game=new AsciiPanel(80,32);
        game.loadGlyphs();
        JFrame mainWindow=new JFrame();
        mainWindow.setTitle("Test AsciiPanel");
        mainWindow.setVisible(true);
        mainWindow.setSize(game.getCharWidth()*game.getWidthInCharacters(),game.getCharHeight()*game.getHeightInCharacters());
        //mainWindow.add(game);
        game.write("Hello World!",5,7);
        
        TilePanel tp=new TilePanel(32,20);
        tp.loadGlyphs();
        mainWindow.add(tp);
        
        tp.write(1,2,2);
      */ 
    }

   
}
