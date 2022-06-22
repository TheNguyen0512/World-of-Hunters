
package object;

import enity.Enity;
import main.GamePanel;

/**
 *
 * @author phúc
 */
public class OBJ_Chest extends Enity{
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("src/main/java/object/OBJ/chest");
    }
};