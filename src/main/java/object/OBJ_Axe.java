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
public class OBJ_Axe extends Enity{
    public OBJ_Axe(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = "Key";
        down1 = setup("src/main/java/object/OBJ/axe");
        attackValue = 2;
        description = "[" + name + "]\nAn Axe.";
    }    
}
