package net.ftb.laf;

import net.ftb.ui.utils.UIUtils;

import java.awt.Graphics;
import javax.swing.JComponent;

public final class FTBCorner
extends JComponent{
    @Override
    public void paint(Graphics g){
        g.setColor(UIUtils.RED);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}