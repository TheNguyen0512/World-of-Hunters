/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import enity.Enity;
import enity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author Nguyen
 */
public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; //16x16 tile
    final int scale = 4;

    // Screen setting
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = tileSize * maxScreenCol;
    public final int ScreenHeight = tileSize * maxScreenRow;

    public int nowRow = 0, nowCol = 0;

    //World setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //For Full Screen
    int ScreenWidth2 = ScreenWidth;
    int ScreenHeight2 = ScreenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gamThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter as = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public Enity npc[] = new Enity[10];
    public Enity obj[] = new Enity[10];
    public Enity monster[] = new Enity[20];
    ArrayList<Enity> enityList = new ArrayList<>();
    public UI ui = new UI(this);

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int diaglogueState = 3;
    public final int combatState = 4;
    public final int optionState = 5;
    public final int characterState = 6;
     public final int gameOverState = 7;

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    public int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        as.setObject();
        as.setNPC();
        as.setMonster();
        gameState = titleState;
    }
    
    public void retry(){
        player.setDefaultPositions();
        player.restoreLifeAndMan();
        as.setMonster();
        as.setNPC();
    }
    
    public void restart(){
        player.setDefaultPositions();
        player.restoreLifeAndMan();
        player.setDefaultValues();
        as.setObject();
        as.setNPC();
        as.setMonster();
    }

    public void startGameThread() {
        gamThread = new Thread(this);
        gamThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int countdraw = 0;

        while (gamThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                countdraw++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + countdraw);
                timer = 0;
                countdraw = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i] = null;
                    }
                }

            }
        }
//        if (gameState == pauseState) {
//
//        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //title screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            //tile
            tileManager.draw(g2);
            enityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    enityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    enityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    enityList.add(monster[i]);
                }
            }
            //draw entities
            for (int i = 0; i < enityList.size(); i++) {
                enityList.get(i).draw(g2, this);
            }
            //empty entity list
            for (int i = 0; i < enityList.size(); i++) {
                enityList.remove(i);
            }

            //UI
            ui.draw(g2);
            //player
            player.draw(g2);
        }
        g2.dispose();
    }
}
