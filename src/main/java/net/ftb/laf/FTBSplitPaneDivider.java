package net.ftb.laf;

import net.ftb.ui.utils.UIUtils;

import java.awt.Graphics;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public final class FTBSplitPaneDivider
extends BasicSplitPaneDivider{
    public FTBSplitPaneDivider(BasicSplitPaneUI ui){
        super(ui);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(UIUtils.RED);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}