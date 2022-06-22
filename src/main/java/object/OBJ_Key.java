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
public class OBJ_Key extends Enity{
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        
        name = "Key";
        down1 = setup("src/main/java/object/OBJ/key");
        description = "[" + name + "]\nIt open a door.";
    }    
}
