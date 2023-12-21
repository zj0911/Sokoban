package com.zj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


/**
 * 画框
 */
public class GameFrame extends JFrame implements ActionListener {

    GamePanel gamePanel = new GamePanel();
    Player player = new Player();
    // 添加按钮
    JButton nextLevelButton, prevLevelButton, undoButton, restartButton;

    public GameFrame() {
        super.setTitle("Sokoban");
        super.setSize(GameData.GAME_FRAME_WIDTH, GameData.GAME_FRAME_HEIGHT);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.add(gamePanel);
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 处理游戏地图的键盘事件
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // 回退一步
                    player.undo();
                    repaint();
                }
                player.move(e.getKeyCode());
                repaint();
                if (player.isIntoNext()) {
                    if((GameData.currentLevel+1)>=GameLevelMap.getCount()){
                        JOptionPane.showMessageDialog(null,"You Win！");
                        return;
                    }
                    String msg = "Congratulation! You pass level: " + (GameData.currentLevel + 1)
                            + "\nLet's go to next level?";
                    int choice = JOptionPane.showConfirmDialog(null, msg, "Pass Level", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        //进入下一关
                        player.intoNextLevel();
                        repaint();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });
//        super.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    // 回退一步
//                    player.undo();
//                    repaint();
//                }
//                player.move(e.getKeyCode());
//                repaint();
//                if (player.isIntoNext()) {
//                    if((GameData.currentLevel+1)>=GameLevelMap.getCount()){
//                        JOptionPane.showMessageDialog(null,"You Win！");
//                        return;
//                    }
//                    String msg = "Congratulation! You pass level: " + (GameData.currentLevel + 1)
//                            + "\nLet's go to next level?";
//                    int choice = JOptionPane.showConfirmDialog(null, msg, "Pass Level", JOptionPane.YES_NO_OPTION);
//                    if (choice == 0) {
//                        //进入下一关
//                        player.intoNextLevel();
//                        repaint();
//                    } else {
//                        System.exit(0);
//                    }
//                }
//            }
//        });

        // 创建按钮
        nextLevelButton = new JButton("Next Level");
        prevLevelButton = new JButton("Pre Level");
        undoButton = new JButton("Undo");
        restartButton = new JButton("Restart");
        // 设置按钮位置和大小
        nextLevelButton.setBounds(20, 20, 120, 30);
        prevLevelButton.setBounds(160, 20, 120, 30);
        undoButton.setBounds(300, 20, 120, 30);
        restartButton.setBounds(440, 20, 120, 30);
        // 为按钮添加事件监听器
        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GameData.currentLevel+2<=GameLevelMap.getCount()){
                    // 处理进入下一关逻辑
                    String msg = "Let's go to level: " + (GameData.currentLevel + 2) + "?";
                    int choice = JOptionPane.showConfirmDialog(null, msg, "Next Level", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        //进入下一关
                        player.intoNextLevel();
                        repaint();
                    } else {
                        repaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Have no next level！");
                }
            }
        });

        prevLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameData.currentLevel>=1){
                    String msg = "Let's go to level: " + (GameData.currentLevel) + "?";
                    int choice = JOptionPane.showConfirmDialog(null, msg, "Last Level", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        //进入下一关
//                        player.resetLevel();
                        player.intoLastLevel();
                        repaint();
                    } else {
                        repaint();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Cannot back！");
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理悔棋逻辑
                String msg = "Are you sure go back to last step?";
                int choice = JOptionPane.showConfirmDialog(null, msg, "Undo", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    player.undo();
                } else {
                    repaint();
                }
                repaint();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理重新开始逻辑
                String msg = "Are you sure go back to last step?";
                int choice = JOptionPane.showConfirmDialog(null, msg, "Restart", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    player.resetLevel();
                    repaint();
                }else {
//                    System.exit(0);
                    repaint();
                }
            }
        });
        // 为按钮添加焦点监听器，将焦点设置到游戏面板
        nextLevelButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });
        prevLevelButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });
        undoButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });
        restartButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gamePanel.requestFocusInWindow();
            }
        });
        JPanel buttonPanel = new JPanel(); // 创建一个新的面板用于存放按钮
        buttonPanel.setLayout(new FlowLayout()); // 使用FlowLayout布局管理器
        // 将按钮添加到按钮面板中
        buttonPanel.add(nextLevelButton);
        buttonPanel.add(prevLevelButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(restartButton);
        // 将按钮面板放在窗口的顶部
        super.add(buttonPanel, BorderLayout.NORTH);
        // 设置游戏面板占据窗口剩余空间
        gamePanel.setPreferredSize(new Dimension(GameData.GAME_FRAME_WIDTH, GameData.GAME_FRAME_HEIGHT - buttonPanel.getHeight()));
        super.add(gamePanel, BorderLayout.CENTER);

//        Sound sound = new Sound();
//        sound.loadSound();
        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Sound sound = new Sound();
                sound.loadSound();
            }
        });
        soundThread.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
