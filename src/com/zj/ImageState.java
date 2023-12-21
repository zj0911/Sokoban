package com.zj;

import java.util.ArrayList;
import java.util.List;

public class ImageState {
    public static final byte NONE = 0;
    public static final byte WALL = 1;
    public static final byte BOX = 2;
    public static final byte BOX_END = 3;
    public static final byte END = 4;
    public static final byte MAN_DOWN = 5;
    public static final byte MAN_LEFT = 6;
    public static final byte MAN_RIGHT = 7;
    public static final byte MAN_UP = 8;
    public static final byte GRASS = 9;
    public static final byte MAN_DOWN_END = 10;
    public static final byte MAN_LEFT_END = 11;
    public static final byte MAN_RIGHT_END = 12;
    public static final byte MAN_UP_END = 13;

    public static final List<Byte> boxs = new ArrayList<>();

    public static final List<Byte> grassAndEnd = new ArrayList<>();

    public static final List<Byte> manEnds = new ArrayList<>();

    static {
        //静态代码块，只执行一次
        //所有箱子 2,3
        boxs.add(BOX);
        boxs.add(BOX_END);

        //地板和终点 4,9
        grassAndEnd.add(GRASS);
        grassAndEnd.add(END);

        //所有在终点的人物状态
        //10,11,12,13
        manEnds.add(MAN_DOWN_END);
        manEnds.add(MAN_LEFT_END);
        manEnds.add(MAN_RIGHT_END);
        manEnds.add(MAN_UP_END);
    }

    public static boolean oneStepIsBoxs(byte man) {
        return boxs.contains(man);
    }

    public static boolean twoStepIsGrassOrEnd(byte man) {
        return grassAndEnd.contains(man);
    }

    public static boolean oneStepIsGrassOrEnd(byte man) {
        return twoStepIsGrassOrEnd(man);
    }

    public static byte twoSetpIsBoxEndByEnd(byte man) {
        if (man == END) {
            //箱子往前走一步
            return BOX_END;
        } else {
            return BOX;
        }
    }

    public static byte oneSetpIsManEndByBox(byte man, byte manState) {
        //人物下一步是普通箱子
        if (man == BOX) {
            //人物前进，正常显示
            return manState;
        } else {
            //人物角色下一步是在终点的箱子
            //箱子往前，进一步，箱子的原位置是终点
            //人物往前，进一步，人物就变成在终点的人物
            return (byte) (manState + 5);
        }
    }

    public static byte oneStepIsManEndByEnd(byte man, byte manState) {
        if (man == END) {
            return (byte) (manState + 5);
        } else {
            return manState;
        }
    }

    public static byte isGrassOrEnd(byte man) {
        //如果当前人物是在终点的人物
        if (manEnds.contains(man)) {
            //人物前进后，原位置是终点
            return END;
        } else {
            //人物前进后，原位置是地板
            return GRASS;
        }
    }
}
