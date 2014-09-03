package net.ftb.laf.ctrl;

import net.ftb.laf.FTBArrowButton;
import net.ftb.ui.utils.UIUtils;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public final class FTBScrollBarUI
extends BasicScrollBarUI{
    public static ComponentUI createUI(JComponent comp){
        return new FTBScrollBarUI();
    }

    @Override
    public JButton createDecreaseButton(int ori){
        return new FTBArrowButton(ori);
    }

    @Override
    public JButton createIncreaseButton(int ori){
        return new FTBArrowButton(ori);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent comp, Rectangle rect){
        g.setColor(UIUtils.RED);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public void paintTrack(Graphics g, JComponent comp, Rectangle rect){
        g.setColor(UIUtils.GRAY);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
}
