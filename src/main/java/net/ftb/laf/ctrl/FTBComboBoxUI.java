package net.ftb.laf.ctrl;

import net.ftb.laf.FTBArrowButton;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public final class FTBComboBoxUI
extends BasicComboBoxUI{
    public static ComponentUI createUI(JComponent comp){
        return new FTBComboBoxUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setForeground(Color.white);
    }

    @Override
    protected JButton createArrowButton() {
        return new FTBArrowButton(BasicArrowButton.SOUTH);
    }
}