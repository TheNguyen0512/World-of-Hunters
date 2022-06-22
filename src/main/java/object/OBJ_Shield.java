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
public class OBJ_Shield extends Enity{
    public  OBJ_Shield(GamePanel gp){
        super(gp);
        
        
        type = type_shield;
        name = "Normal Shield";
        down1 = setup("src/main/java/object/OBJ/shield_wood");
        defenseValue = 1;
        description = "[" + name + "]\nMake by wood.";
    }
}
