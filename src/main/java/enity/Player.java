/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield;
import object.OBJ_Sword;

/**
 *
 * @author Nguyen
 */
public final class Player extends Enity {

    //SYSTEM
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    int hasShield = 0;
    public ArrayList<Enity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.gp = gp;
        this.keyHandler = keyHandler;
        screenX = gp.ScreenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.ScreenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";

        //player status
        level = 1;
        maxLife = 20;
        life = maxLife;
        exp = 0;
        strength = 5;
        dexterity = 2;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword(gp);
        currentShield = new OBJ_Shield(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("src/main/java/enity/player/boy_up_1");
        up2 = setup("src/main/java/enity/player/boy_up_2");
        down1 = setup("src/main/java/enity/player/boy_down_1");
        down2 = setup("src/main/java/enity/player/boy_down_2");
        left1 = setup("src/main/java/enity/player/boy_left_1");
        left2 = setup("src/main/java/enity/player/boy_left_2");
        right1 = setup("src/main/java/enity/player/boy_right_1");
        right2 = setup("src/main/java/enity/player/boy_right_2");
    }

    @Override
    public void update() {
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true || keyHandler.shiftPressed) {
            if (keyHandler.shiftPressed == true) {
                speed = gp.playerSpeed + 3;
            } else {
                speed = gp.playerSpeed;
            }

            if (keyHandler.upPressed == true) {
                direction = "up";
            } else if (keyHandler.downPressed == true) {

                direction = "down";
            } else if (keyHandler.leftPressed == true) {

                direction = "left";
            } else if (keyHandler.rightPressed == true) {

                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int npcIndex = gp.cChecker.checkEnity(this, gp.npc);
            interactNPC(npcIndex);

            int objIndex = gp.cChecker.checkObject((this), true);
            pickUpObject(objIndex);

            int monsterIndex = gp.cChecker.checkEnity(this, gp.monster);
            contactMonster(monsterIndex);
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true) {
            invincible = false;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {            
            String objectName = gp.obj[i].name;
            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.obj[i]);
                if(objectName == "Door"){
                    inventory.remove(gp.obj[i]);
                }
            }
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        inventory.remove(gp.obj[i]);
                        System.out.println("Key: " + hasKey);
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        int x = screenX;
        int y = screenY;
        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.ScreenWidth - screenX;
        if (rightOffset > gp.worldHeight - worldX) {
            x = gp.ScreenWidth - (gp.worldWidth - worldX);
        }

        int leftOffset = gp.ScreenHeight - screenY;
        if (leftOffset > gp.worldHeight - worldY) {
            y = gp.ScreenHeight - (gp.worldHeight - worldY);
        }
        g2.drawImage(image, screenX, screenY, null);
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.diaglogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyHandler.enterPressed = false;
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }

    public void restoreLifeAndMan() {
        life = maxLife;
        invincible = false;
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                invincible = true;
                gp.gameState = gp.combatState;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                int damagePlayer = attack - gp.monster[i].defense;
                int damageMS = gp.monster[i].attack - defense;
                if (damagePlayer < 0) {
                    damagePlayer = 0;
                }
                if (damageMS < 0) {
                    damageMS = 0;
                }
                life -= damageMS;
                gp.monster[i].life -= damagePlayer;
                gp.monster[i].invincible = false;

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    exp += gp.monster[i].exp;
                    if (exp > nextLevelExp) {
                        level++;
                        nextLevelExp = nextLevelExp * 2;
                        strength += 2;
                        dexterity++;
                        maxLife += 5;
                        life = maxLife;
                        attack = getAttack();
                        defense = getDefense();
                    }
                }

            }
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Enity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword
                    || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getAttack();
            }
        }
    }
}
