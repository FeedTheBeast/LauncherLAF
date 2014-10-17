package net.ftb.laf.container;

import net.ftb.laf.FTBCorner;
import net.ftb.ui.utils.UIUtils;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public final class FTBScrollPaneUI
extends BasicScrollPaneUI{
    public static ComponentUI createUI(JComponent comp){
        return new FTBScrollPaneUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setBorder(BorderFactory.createEmptyBorder());
        this.scrollpane.getViewport().setBackground(UIUtils.WHITE);
        this.scrollpane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new FTBCorner());
        this.scrollpane.setCorner(JScrollPane.LOWER_LEFT_CORNER, new FTBCorner());
        this.scrollpane.setCorner(JScrollPane.UPPER_LEFT_CORNER, new FTBCorner());
        this.scrollpane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new FTBCorner());
    }
}