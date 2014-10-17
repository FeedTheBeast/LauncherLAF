package net.ftb.laf.output;

import net.ftb.ui.utils.UIUtils;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public final class FTBProgressBarUI
extends BasicProgressBarUI{
    public static ComponentUI createUI(JComponent comp){
        return new FTBProgressBarUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setForeground(UIUtils.RED);
        comp.setBackground(UIUtils.GRAY);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Dimension getPreferredSize(JComponent comp){
        return new Dimension(super.getPreferredSize(comp).width, 10);
    }
}