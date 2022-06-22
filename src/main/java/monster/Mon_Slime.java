/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import enity.Enity;
import java.util.Random;
import main.GamePanel;

/**
 *
 * @author Nguyen
 */
public class Mon_Slime extends Enity {

    public Mon_Slime(GamePanel gp) {
        super(gp);

        level = 1;
        type = type_monster;
        name = "Green Slime";
        speed = 1;
        attack = 5;
        defense = 0;
        maxLife = 15;
        life = maxLife;
        exp = 4;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("src/main/java/monster/MS/greenslime_down_1");
        up2 = setup("src/main/java/monster/MS/greenslime_down_2");
        down1 = setup("src/main/java/monster/MS/greenslime_down_1");
        down1 = setup("src/main/java/monster/MS/greenslime_down_2");
        left1 = setup("src/main/java/monster/MS/greenslime_down_1");
        left2 = setup("src/main/java/monster/MS/greenslime_down_2");
        right1 = setup("src/main/java/monster/MS/greenslime_down_1");
        right2 = setup("src/main/java/monster/MS/greenslime_down_2");
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
