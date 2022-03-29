package com.test;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class JButtonDemo
{
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("Java按钮组件示例");    //创建Frame窗口
        frame.setSize(400, 200);
        JPanel jp=new JPanel();    //创建JPanel对象
        JButton btn1=new JButton("我是普通按钮");    //创建JButton对象
        JButton btn2=new JButton("我是带背景颜色按钮");
        JButton btn3=new JButton("我是不可用按钮");
        JButton btn4=new JButton("我是底部对齐按钮");
        jp.add(btn1);
        btn2.setBackground(Color.YELLOW);    //设置按钮背景色
        jp.add(btn2);
        btn3.setEnabled(false);    //设置按钮不可用
        jp.add(btn3);
        Dimension preferredSize=new Dimension(160, 60);    //设置尺寸
        btn4.setPreferredSize(preferredSize);    //设置按钮大小
        btn4.setVerticalAlignment(SwingConstants.BOTTOM);    //设置按钮垂直对齐方式
        jp.add(btn4);
        frame.add(jp);
        frame.setBounds(300, 200, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



