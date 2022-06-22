/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enity;

import java.util.Random;
import main.GamePanel;

/**
 *
 * @author Nguyen
 */
public final class NPC_oldman extends Enity {

    public NPC_oldman(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogues();
    }

    public void getImage() {
        up1 = setup("src/main/java/enity/npc/oldman_up_1");
        up2 = setup("src/main/java/enity/npc/oldman_up_2");
        down1 = setup("src/main/java/enity/npc/oldman_down_1");
        down2 = setup("src/main/java/enity/npc/oldman_down_2");
        left1 = setup("src/main/java/enity/npc/oldman_left_1");
        left2 = setup("src/main/java/enity/npc/oldman_left_2");
        right1 = setup("src/main/java/enity/npc/oldman_right_1");
        right2 = setup("src/main/java/enity/npc/oldman_right_2");
    }

    public void setDialogues() {
        this.dialogues[0] = "Hello. Traveller";
    }

    @Override
    public void speak() {
        super.speak();
    }

    @Override
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 20) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i < 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
