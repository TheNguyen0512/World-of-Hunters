/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import enity.Enity;
import main.GamePanel;

/**
 *
 * @author Nguyen
 */
public class OBJ_Sword extends Enity{
    public  OBJ_Sword(GamePanel gp){
        super(gp);
        
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("src/main/java/object/OBJ/sword_normal");
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
