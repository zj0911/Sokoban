package com.zj;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 公共全局的游戏数据
 */
public class GameData {
    public static final int GAME_FRAME_WIDTH = 600;
    public static final int GAME_FRAME_HEIGHT = 600;
    public  static final   BufferedImage[] gameImages = new BufferedImage[14];
    /**
     * 当前关卡
     */
    public static int currentLevel = 0;
    /**
     * 当前地图
     */
    public static byte[][] currentMap = null;

    public static void initGameImages(){
        try {
            for(int i=0;i<gameImages.length;i++){
                gameImages[i] =  ImageIO.read(new File("images/pic"+i+".JPG"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Point initCurrentMap(){
        currentMap =  GameLevelMap.getMap(currentLevel);
        //行
        int mapRow = currentMap.length;
        //列
        int mapCol =  currentMap[0].length;
        //地图的宽
        int mapWidth =  mapCol*30;
        //地图的高
        int mapHeight =  mapRow*30;
        int rightPlace =  GAME_FRAME_WIDTH - mapWidth;
        int bottomPlace = GAME_FRAME_HEIGHT - mapHeight;
        int startX = rightPlace/2;
        int startY = bottomPlace/2;
        Point positionMap = new Point();
        positionMap.x= startX;
        positionMap.y = startY;
        return positionMap;
    }

    private static byte[][] initialMap;
    private static byte[][] nowMap;  // 当前地图状态

    // 在游戏初始化时调用，用于保存初始地图
    public static void saveInitialMap(byte[][] map) {
        initialMap = map;
        nowMap = copyMap(map); // 初始化当前地图为初始地图的副本
    }

    // 重置地图为初始状态
    public static void resetMap() {
        if (initialMap != null) {
            nowMap = copyMap(initialMap); // 将当前地图重置为初始地图的副本s
        } else {
            // 如果没有保存初始地图，则重新生成一个默认的地图
            nowMap = GameLevelMap.getMap(0);
        }
    }

    // 复制地图
    private static byte[][] copyMap(byte[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        byte[][] copy = new byte[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, cols);
        }
        return copy;
    }
}
