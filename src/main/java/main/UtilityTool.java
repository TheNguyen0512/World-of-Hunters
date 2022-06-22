/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nguyen
 */
//Hỗ trợ Lấy Ảnh
public class UtilityTool {
    public  BufferedImage scaleImage(BufferedImage original, int wid, int heig){
        BufferedImage scaledImage = new BufferedImage(wid,heig,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, wid, heig, null);
        
        return  scaledImage;
    }
}
