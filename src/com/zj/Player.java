package com.zj;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class Player {

    private int row;
    private int col;

    public Player() {
        this.initManPosition();
    }

    public void initManPosition() {
        for (int row = 0; row < GameData.currentMap.length; row++) {
            for (int col = 0; col < GameData.currentMap[row].length; col++) {
                if (GameData.currentMap[row][col] == 5 ||
                        GameData.currentMap[row][col] == 6 ||
                        GameData.currentMap[row][col] == 7 ||
                        GameData.currentMap[row][col] == 8 ||
                        GameData.currentMap[row][col] == 10 ||
                        GameData.currentMap[row][col] == 11 ||
                        GameData.currentMap[row][col] == 12 ||
                        GameData.currentMap[row][col] == 13) {
                    this.row = row;
                    this.col = col;
                    break;
                }
            }
        }
    }

    List<StepRecord> stepRecords = new ArrayList<>();

    public void addStepRecord(int row, int col, byte[][] currentMap) {
        StepRecord stepRecord = new StepRecord(row, col, currentMap);
        stepRecords.add(stepRecord);
    }

    public void move(int keyCode) {
        byte[][] currentMap = GameData.currentMap;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                //当前人人物位置
                byte man = currentMap[this.row][this.col];
                //下一步
                byte oneStep = currentMap[this.row - 1][this.col];
                //下两步
                byte twoSetp = currentMap[this.row - 2][this.col];
                //下一步如果是墙，什么都不做
                if (oneStep == ImageState.WALL) {
                    return;
                }
                //下一步，如果是箱子，或者在终点的箱子，
                if (ImageState.oneStepIsBoxs(oneStep)) {
                    //判断箱子，是否可以移动 下两步，如果是地板或终点，可以移动
                    if (ImageState.twoStepIsGrassOrEnd(twoSetp)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下两步，如果是终点，就变成在终点的盒子，否则还是盒子
                        currentMap[this.row - 2][this.col] = ImageState.twoSetpIsBoxEndByEnd(twoSetp);
                        //下一步，如果是盒子，就是当前方向上的人物，否则就是在终点的人物
                        currentMap[this.row - 1][this.col] = ImageState.oneSetpIsManEndByBox(oneStep, ImageState.MAN_UP);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.row--;
                    }
                } else {
                    //下一步，如果是地板或终点，可以移动
                    if (ImageState.oneStepIsGrassOrEnd(oneStep)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下一步，如果是终点，就变成在终点的人物，否则就是当前方向上的人物
                        currentMap[this.row - 1][this.col] = ImageState.oneStepIsManEndByEnd(oneStep, ImageState.MAN_UP);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.row--;
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                //当前人人物位置
                man = currentMap[this.row][this.col];
                //下一步
                oneStep = currentMap[this.row + 1][this.col];
                //下两步
                twoSetp = currentMap[this.row + 2][this.col];
                //下一步如果是墙，什么都不做
                if (oneStep == ImageState.WALL) {
                    return;
                }
                //下一步，如果是箱子，或者在终点的箱子，
                if (ImageState.oneStepIsBoxs(oneStep)) {
                    //判断箱子，是否可以移动 下两步，如果是地板或终点，可以移动
                    if (ImageState.twoStepIsGrassOrEnd(twoSetp)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下两步，如果是终点，就变成在终点的盒子，否则还是盒子
                        currentMap[this.row + 2][this.col] = ImageState.twoSetpIsBoxEndByEnd(twoSetp);
                        //下一步，如果是盒子，就是当前方向上的人物，否则就是在终点的人物
                        currentMap[this.row + 1][this.col] = ImageState.oneSetpIsManEndByBox(oneStep, ImageState.MAN_DOWN);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.row++;
                    }
                } else {
                    //下一步，如果是地板或终点，可以移动
                    if (ImageState.oneStepIsGrassOrEnd(oneStep)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下一步，如果是终点，就变成在终点的人物，否则就是当前方向上的人物
                        currentMap[this.row + 1][this.col] = ImageState.oneStepIsManEndByEnd(oneStep, ImageState.MAN_DOWN);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.row++;
                    }
                }
                break;
            case KeyEvent.VK_LEFT:

                //当前人人物位置
                man = currentMap[this.row][this.col];
                //下一步
                oneStep = currentMap[this.row][this.col - 1];
                //下两步
                twoSetp = currentMap[this.row][this.col - 2];
                //下一步如果是墙，什么都不做
                if (oneStep == ImageState.WALL) {
                    return;
                }
                //下一步，如果是箱子，或者在终点的箱子，
                if (ImageState.oneStepIsBoxs(oneStep)) {
                    //判断箱子，是否可以移动 下两步，如果是地板或终点，可以移动
                    if (ImageState.twoStepIsGrassOrEnd(twoSetp)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下两步，如果是终点，就变成在终点的盒子，否则还是盒子
                        currentMap[this.row][this.col - 2] = ImageState.twoSetpIsBoxEndByEnd(twoSetp);
                        //下一步，如果是盒子，就是当前方向上的人物，否则就是在终点的人物
                        currentMap[this.row][this.col - 1] = ImageState.oneSetpIsManEndByBox(oneStep, ImageState.MAN_LEFT);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.col--;
                    }
                } else {
                    //下一步，如果是地板或终点，可以移动
                    if (ImageState.oneStepIsGrassOrEnd(oneStep)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下一步，如果是终点，就变成在终点的人物，否则就是当前方向上的人物
                        currentMap[this.row][this.col - 1] = ImageState.oneStepIsManEndByEnd(oneStep, ImageState.MAN_LEFT);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.col--;
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                //当前人人物位置
                man = currentMap[this.row][this.col];
                //下一步
                oneStep = currentMap[this.row][this.col + 1];
                //下两步
                twoSetp = currentMap[this.row][this.col + 2];
                //下一步如果是墙，什么都不做
                if (oneStep == ImageState.WALL) {
                    return;
                }
                //下一步，如果是箱子，或者在终点的箱子，
                if (ImageState.oneStepIsBoxs(oneStep)) {
                    //判断箱子，是否可以移动 下两步，如果是地板或终点，可以移动
                    if (ImageState.twoStepIsGrassOrEnd(twoSetp)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下两步，如果是终点，就变成在终点的盒子，否则还是盒子
                        currentMap[this.row][this.col + 2] = ImageState.twoSetpIsBoxEndByEnd(twoSetp);
                        //下一步，如果是盒子，就是当前方向上的人物，否则就是在终点的人物
                        currentMap[this.row][this.col + 1] = ImageState.oneSetpIsManEndByBox(oneStep, ImageState.MAN_RIGHT);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.col++;
                    }
                } else {
                    //下一步，如果是地板或终点，可以移动
                    if (ImageState.oneStepIsGrassOrEnd(oneStep)) {
                        this.addStepRecord(this.row, this.col, currentMap);
                        //下一步，如果是终点，就变成在终点的人物，否则就是当前方向上的人物
                        currentMap[this.row][this.col + 1] = ImageState.oneStepIsManEndByEnd(oneStep, ImageState.MAN_RIGHT);
                        //当前人物位置，如果是在终点的人物，就是终点，否则就是地板
                        currentMap[this.row][this.col] = ImageState.isGrassOrEnd(man);
                        this.col++;
                    }
                }
                break;
        }
    }

    public boolean isIntoNext() {
        byte[][] currentMap = GameData.currentMap;
        for (int row = 0; row < currentMap.length; row++) {
            for (int col = 0; col < currentMap[row].length; col++) {
                if (currentMap[row][col] == 4 ||
                        currentMap[row][col] == 10 ||
                        currentMap[row][col] == 11 ||
                        currentMap[row][col] == 12 ||
                        currentMap[row][col] == 13) {
                    return false;
                }
            }
        }
        return true;
    }

    public void intoNextLevel() {
        GameData.currentLevel++;
        GameData.initCurrentMap();
        this.initManPosition();
        this.stepRecords.clear();
    }
    public void intoLastLevel() {
        GameData.currentLevel--;
        GameData.initCurrentMap();
        this.initManPosition();
        stepRecords.clear(); // 清空步骤记录
    }

    /**
     * 回退一步
     */
    public void undo() {
        if (stepRecords.size() > 0) {
            StepRecord stepRecord = stepRecords.get(stepRecords.size() - 1);
            this.row = stepRecord.getRow();
            this.col = stepRecord.getCol();
            GameData.currentMap = stepRecord.getMap();
            stepRecords.remove(stepRecords.size() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot back！");
        }
    }

    public void resetLevel() {
        if (!stepRecords.isEmpty()) {
            StepRecord initialStep = stepRecords.get(0); // 获取第一个记录
            // 使用初始地图状态来重置地图
            GameData.currentMap = initialStep.getMap();
            // 其他重置操作
            this.initManPosition(); // 重新设置玩家位置
            this.stepRecords.clear(); // 清空步骤记录
        }
    }
}
