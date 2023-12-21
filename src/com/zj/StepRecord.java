package com.zj;

public class StepRecord {
    private int row;
    private int col;
    //二维数组，还是一维数组都引用传递
    private byte[][] map;

    public StepRecord(int row, int col, byte[][] currentMap) {
        this.row = row;
        this.col = col;
        //赋值的是引用
        this.map = copyMap(currentMap);
    }
    public byte[][] copyMap(byte[][] currentMap){
        int rows = currentMap.length;
        int cols = currentMap[0].length;
        byte[][] copiedMap = new byte[rows][cols];
        for(int i=0;i < rows; i++){
            for(int j=0; j < cols; j++){
                copiedMap[i][j] = currentMap[i][j];
            }
        }
        return  copiedMap;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public byte[][] getMap() {
        return map;
    }

    public void setMap(byte[][] map) {
        this.map = map;
    }
}
