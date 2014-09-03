package net.ftb.laf.container;

import net.ftb.laf.FTBSplitPaneDivider;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public final class FTBSplitPaneUI
extends BasicSplitPaneUI{
    public static ComponentUI createUI(JComponent comp){
        return new FTBSplitPaneUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public BasicSplitPaneDivider createDefaultDivider(){
        return new FTBSplitPaneDivider(this);
    }
}