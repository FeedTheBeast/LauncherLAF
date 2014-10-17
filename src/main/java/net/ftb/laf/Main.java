package net.ftb.laf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public final class Main
extends JFrame{
    static{
        try{
            UIManager.setLookAndFeel(FTBLookAndFeel.class.getName());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private Main(){
        this.getContentPane().setBackground(Color.black);
        this.setMinimumSize(new Dimension(830, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BorderLayout());
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JPanel(), new JPanel());
        splitter.setLeftComponent(new JScrollPane(new JTextArea()));
        splitter.setRightComponent(new JPanel(new FlowLayout(FlowLayout.CENTER)){
            JProgressBar bar = new JProgressBar(0, 100);

            {
                this.bar.setValue(50);
                this.add(this.bar);
            }
        });
        this.getContentPane().add(splitter, BorderLayout.CENTER);
    }

    public static void main(String... args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Main().setVisible(true);
            }
        });
    }

    private static enum Side{
        TOP, LEFT, BOTTOM, RIGHT
    }
}