
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nguyen
 */
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String massage = "";
    int messageCount = 0;
    public boolean gameFinished = false;
    public String currentDialog = "";
    public String infoDialog = "";
    public String name = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int combatScreenState = 0;
    int subState = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMaessage(String text) {
        massage = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //Title Screen
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
//Play screen
        if (gp.gameState == gp.playState) {
        }
        //Pause screen
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //DiaLogues Screen
        if (gp.gameState == gp.diaglogueState) {
            drawDialogScreen();
        }
        //Option Screen
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        //Combat Screen
        if (gp.gameState == gp.combatState) {
            drawCombatScreen();
        }
        //Character
        if (gp.gameState == gp.characterState) {
            drawChacraterScreen();
            drawInventory();
        }
        //GameOver
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    //Vẽ giao diện GameOver
    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "GAME OVER";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenteredTex(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredTex(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
        text = "QUIT";
        x = getXforCenteredTex(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    //Vẽ giao diện Dừng Màn Hình Ấn Nút P
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredTex(text);
        int y = gp.ScreenHeight / 2;
        g2.drawString(text, x, y);
    }

    //Đưa Chữ Ra Giữa Màn Hình
    public int getXforCenteredTex(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.ScreenWidth / 2 - length / 2;
        return x;
    }

    //Vẽ giao diện Tương Tác Với NPC nhấn Enter
    public void drawDialogScreen() {
        //Window 
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int wid = gp.ScreenWidth - (gp.tileSize * 4);
        int heig = gp.tileSize * 4;

        drawSubWindow(x, y, wid, heig);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialog, x, y);

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    //Vẽ Ổ Cửa sổ phụ hỗ trợ cho các Màn hình trên
    public void drawSubWindow(int x, int y, int wid, int heig) {

        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, wid, heig, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, wid - 10, heig - 10, 25, 25);

    }

    //Vẽ giao diện bắt đầu vào game
    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            try {
                Image img = ImageIO.read(new File("src/main/java/main/BackGround.png"));

                g2.drawImage(img, 0, 0, gp.ScreenWidth, gp.ScreenHeight, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            g2.setColor(new Color(40, 100, 140));
//            g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
            //Title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "World of Hunters";
            int x = getXforCenteredTex(text);
            int y = gp.tileSize * 3;

            //shawdow
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 5);

            //main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXforCenteredTex(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "LOAD GAME";
            x = getXforCenteredTex(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "QUIT";
            x = getXforCenteredTex(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

            String text = "Select your class";
            int x = getXforCenteredTex(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredTex(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
                name = text;
            }

            text = "Magic";
            x = getXforCenteredTex(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
                name = text;
            }

            text = "Assasin";
            x = getXforCenteredTex(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
                name = text;
            }

            text = "Back";
            x = getXforCenteredTex(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

    }

    //Vẽ giao diện cài đặt
    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //Sub Window
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                options_top(subState, subState);
                break;
            case 1:
                options_control(frameX, frameY);
                break;
            case 2:
                options_EndGame(frameX, frameY);
                break;
        }
        gp.keyHandler.enterPressed = false;
    }

    //giao diện Lựa chọn trong option
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "Options";
        textX = getXforCenteredTex(text);
        textY = frameY + gp.tileSize * 2;
        g2.drawString(text, textX, textY);

        //Full screen on/off
        textX = frameX + gp.tileSize * 7;
        textY += gp.tileSize * 2;
        g2.drawString("How To Play", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 1;
                commandNum = 0;
            }
        }
        //END game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        //Back
        textY += gp.tileSize * 4;
        g2.drawString("Back", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
    }

    //Lựa chọn hướng dẫn chơi trong option
    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "How To Play";
        textX = getXforCenteredTex(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pasue", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        //Back
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
            }
        }
    }

    //Lựa chọn thoát game trong option
    public void options_EndGame(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialog = "Quit the game and \nreturn the menu";
        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //YES
        String text = "Yes";
        textX = getXforCenteredTex(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                titleScreenState = 0;
                gp.gameState = gp.titleState;
            }
        }

        //No
        text = "No";
        textX = getXforCenteredTex(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    //Giao diện đánh nhau turn-base
    public void drawCombatScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);

        int x = gp.tileSize;
        int y = gp.tileSize;
        int wid = gp.ScreenWidth - (gp.tileSize * 10);
        int heig = gp.tileSize * 2;
        String value;
        drawSubWindow(x, y, wid, heig);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        int monsterIndex = gp.cChecker.checkEnity(gp.player, gp.monster);
        if (monsterIndex != 999) {
            g2.drawString(gp.monster[monsterIndex].name + "         Lv." + gp.monster[monsterIndex].level, x, y);
            g2.drawImage(gp.monster[monsterIndex].down1, x * 7, y, null);
            y += 40;
            int currentHP = gp.monster[monsterIndex].life;
            if (currentHP < 0) {
                currentHP = 0;
            }
            value = String.valueOf("HP         " + currentHP + "/" + gp.monster[monsterIndex].maxLife);
            x = getXforAlignToRightText(value, x);
            g2.drawString(value, x + 180, y);
        }

        x = (int) (gp.tileSize * 8.5);
        y = gp.tileSize * 4;
        wid = gp.ScreenWidth - (gp.tileSize * 10);
        heig = (int) (gp.tileSize * 2.5);

        drawSubWindow(x, y, wid, heig);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(infoDialog, x, y);

        g2.drawString(name + "         Lv." + gp.player.level, x, y);
        y += 40;
        int currentHP = gp.player.life;
        if (currentHP < 0) {
            currentHP = 0;
        }
        value = String.valueOf("HP         " + currentHP + "/" + gp.player.maxLife);
        x = getXforAlignToRightText(value, x);
        g2.drawString(value, x + 180, y);
        int tailX = (x + wid) - 30;
        y += 40;
        value = String.valueOf("Exp        " + gp.player.exp + "/" + gp.player.nextLevelExp);
        x = getXforAlignToRightText(value, tailX);
        g2.drawString(value, x - 28, y);

        switch (subState) {
            case 0:
                menucombat(subState, subState);
                break;
            case 1:
                combat_attack(subState, subState);
                break;
            case 2:

                break;
            case 3:
                combat_run(subState, subState);
                break;
        }
        gp.keyHandler.enterPressed = false;
    }

    //giao diện lựa chọn trong menu combat
    public void menucombat(int x, int y) {
        x = gp.tileSize * 2;
        y = (int) (gp.tileSize * 7.5);
        int wid = gp.ScreenWidth - (gp.tileSize * 4);
        int heig = gp.tileSize * 4;
        drawSubWindow(x, y, wid, heig);

        int textX;
        int textY = y;
        int tailX = (x + wid) - 250;
        int monsterIndex = gp.cChecker.checkEnity(gp.player, gp.monster);

        if (gp.player.life <= 0) {
            gp.gameState = gp.gameOverState;
        }
        if (monsterIndex != 999) {
            if (gp.monster[monsterIndex].life <= 0) {
                textX = x + gp.tileSize;
                textY += (int) (gp.tileSize * 1.5);
                String text = " has been defeated\nYou receive ";
                for (String line : text.split("\n")) {
                    g2.drawString(gp.monster[monsterIndex].name + line + gp.monster[monsterIndex].exp + " EXP", textX, textY);
                    textY += 40;
                }
                gp.monster[monsterIndex].dying = true;
                if (gp.keyHandler.enterPressed == true) {
                    gp.gameState = gp.playState;
                }

            } else {
                textX = x + gp.tileSize;
                textY += (int) (gp.tileSize * 1.5);
                g2.drawString("Attack", textX, textY);
                if (commandNum == 0) {
                    g2.drawString(">", textX - gp.tileSize + 20, textY);
                    if (gp.keyHandler.enterPressed == true) {
                        subState = 1;
                        commandNum = 0;
                    }
                }
                textX = getXforAlignToRightText("", tailX);
                g2.drawString("Items", textX, textY);
                if (commandNum == 1) {
                    g2.drawString(">", textX - gp.tileSize + 20, textY);
                }
                textX = x + gp.tileSize;
                textY += (int) (gp.tileSize * 1.5);
                g2.drawString("Run", textX, textY);
                if (commandNum == 2) {
                    g2.drawString(">", textX - gp.tileSize + 20, textY);
                    if (gp.keyHandler.enterPressed == true) {
                        subState = 3;
                        commandNum = 0;
                    }
                }
            }
        }
    }

    //Lựa chọn kiểu đánh trong menu combat
    public void combat_attack(int x, int y) {
        int textX = gp.tileSize * 2;
        int textY = (int) (gp.tileSize * 7.5);
        int wid = gp.ScreenWidth - (gp.tileSize * 4);
        int heig = gp.tileSize * 4;
        int tailX = (textX + wid) - 250;
        drawSubWindow(textX, textY, wid, heig);
        int monsterIndex = gp.cChecker.checkEnity(gp.player, gp.monster);
        if (monsterIndex != 999) {
            textX = gp.tileSize * 3;
            textY += (int) (gp.tileSize * 1.5);
            g2.drawString("Tackle", textX, textY);
            if (commandNum == 0) {
                g2.drawString(">", textX - gp.tileSize + 20, textY);
                if (gp.keyHandler.enterPressed == true) {
                    subState = 0;
                    gp.player.damageMonster(monsterIndex);
                    commandNum = 0;
                }
            }
            textX = getXforAlignToRightText("", tailX);
            g2.drawString("Defense", textX, textY);
            if (commandNum == 1) {
                g2.drawString(">", textX - gp.tileSize + 20, textY);
                if (gp.keyHandler.enterPressed == true) {
                    gp.player.damageMonster(monsterIndex);
                    subState = 0;
                    commandNum = 0;
                }
            }
        }

    }

    //Lựa chọn bỏ chốn khỏi quái
    public void combat_run(int x, int y) {
        int textX = gp.tileSize * 2;
        int textY = (int) (gp.tileSize * 7.5);
        int wid = gp.ScreenWidth - (gp.tileSize * 4);
        int heig = gp.tileSize * 4;
        drawSubWindow(textX, textY, wid, heig);

        textY += (int) (gp.tileSize * 1.25);
        currentDialog = "Do you want run??";
        textX = getXforCenteredTex(currentDialog);
        g2.drawString(currentDialog, textX, textY);
        textY += (int) (gp.tileSize * 1);
        //YES
        String text = "Yes";
        textX = getXforCenteredTex(text);
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize + 20, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.playState;
            }
        }
        textY += (int) (gp.tileSize);
        //No
        text = "No";
        textX = getXforCenteredTex(text);
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - gp.tileSize + 20, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 2;
            }
        }
    }

    //Giao diện trạng thái nhân vật
    public void drawChacraterScreen() {
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWid = gp.tileSize * 5;
        final int frameHei = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWid, frameHei);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 50;
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //value
        int tailX = (frameX + frameWid) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp + "/" + gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY, null);
        textY += lineHeight;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY, null);
        textY += lineHeight;
    }

    //Đưa chữ về phía bên phải
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

    //Giao diện túi đồ của nhân vật
    public void drawInventory() {
        //frame
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWid = gp.tileSize * 6;
        int frameHei = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWid, frameHei);

        //slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //draw player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            //equip cursor
            if (gp.player.inventory.get(i) == gp.player.currentWeapon
                    || gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(Color.yellow);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWid = gp.tileSize;
        int cursorHei = gp.tileSize;

        //draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWid, cursorHei, 10, 10);

        //Description frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHei;
        int dFrameWib = frameWid;
        int dFrameHei = gp.tileSize * 3;
        drawSubWindow(dFrameX, dFrameY, dFrameWib, dFrameHei);
        //draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    //Lấy đồ ở vị trí nào đó trong slot
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
}
