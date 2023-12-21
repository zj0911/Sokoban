package com.zj;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 画布
 */
public class GamePanel extends JPanel {
    private Point positionMap;
    public GamePanel(){
        GameData.initGameImages();
        positionMap = GameData.initCurrentMap();
    }

    /**
     * 画布上的绘制方法
     * Graphics g 画笔
     * @param g
     */
    //重写 paint(Graphics g) 方法
    //alt+ ins
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("宋体",Font.BOLD,30));
        g.drawString("Level: ", 200, 140);
        g.drawString(String.valueOf(GameData.currentLevel+1),320,140);
        for(int row = 0;row<GameData.currentMap.length;row++){
            byte[] rowArray=   GameData.currentMap[row];
            for(int col = 0;col<rowArray.length;col++){
               int index =  GameData.currentMap[row][col];
               BufferedImage image=  GameData.gameImages[index];
               g.drawImage(image,positionMap.x+col*30,positionMap.y+row*30,image.getWidth(),image.getHeight(),null);
            }
        }
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.setColor(Color.BLACK);
        g.drawString("Operation: ↑/↓/←/→",150, 500);
    }

}
