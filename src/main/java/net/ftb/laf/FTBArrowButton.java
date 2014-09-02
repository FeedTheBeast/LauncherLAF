package net.ftb.laf;

import net.ftb.ui.utils.UIUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

public final class FTBArrowButton
extends JButton{
    private final int dir;

    public FTBArrowButton(int dir){
        this.dir = dir;
        this.setMinimumSize(new Dimension(16, 16));
        this.setPreferredSize(new Dimension(16, 16));
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(this.getModel().isPressed()){
            g2.setColor(UIUtils.RED.brighter());
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        } else{
            g2.setColor(UIUtils.RED);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }


        g2.setColor(Color.black);
        int size;
        size = Math.min((this.getHeight() - 4) / 3, (this.getWidth() - 4) / 3);
        size = Math.max(size, 2);
        this.paintTriangle(g, (this.getWidth() - size) / 2, (this.getHeight() - size) / 2, size, this.dir, true);
    }

    public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
        int mid, i, j;

        j = 0;
        size = Math.max(size, 2);
        mid = (size / 2) - 1;

        g.translate(x, y);

        switch(direction)       {
            case NORTH:
                for(i = 0; i < size; i++)      {
                    g.drawLine(mid-i, i, mid+i, i);
                }
                if(!isEnabled)  {
                    g.drawLine(mid-i+2, i, mid+i, i);
                }
                break;
            case SOUTH:
                if(!isEnabled)  {
                    g.translate(1, 1);
                    for(i = size-1; i >= 0; i--)   {
                        g.drawLine(mid-i, j, mid+i, j);
                        j++;
                    }
                    g.translate(-1, -1);
                }

                j = 0;
                for(i = size-1; i >= 0; i--)   {
                    g.drawLine(mid-i, j, mid+i, j);
                    j++;
                }
                break;
            case WEST:
                for(i = 0; i < size; i++)      {
                    g.drawLine(i, mid-i, i, mid+i);
                }
                if(!isEnabled)  {
                    g.drawLine(i, mid-i+2, i, mid+i);
                }
                break;
            case EAST:
                if(!isEnabled)  {
                    g.translate(1, 1);
                    for(i = size-1; i >= 0; i--)   {
                        g.drawLine(j, mid-i, j, mid+i);
                        j++;
                    }
                    g.translate(-1, -1);
                }

                j = 0;
                for(i = size-1; i >= 0; i--)   {
                    g.drawLine(j, mid-i, j, mid+i);
                    j++;
                }
                break;
        }
        g.translate(-x, -y);
    }
}