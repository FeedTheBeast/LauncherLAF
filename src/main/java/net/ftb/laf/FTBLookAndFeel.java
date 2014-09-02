package net.ftb.laf;

import net.ftb.laf.button.FTBButtonUI;
import net.ftb.laf.button.FTBToggleButtonUI;
import net.ftb.laf.ctrl.FTBComboBoxUI;
import net.ftb.ui.utils.UIUtils;

import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class FTBLookAndFeel
extends MetalLookAndFeel{
    @Override
    public void initComponentDefaults(UIDefaults table){
        super.initComponentDefaults(table);

        // Control
        table.put("ComboBox.selectionBackground", UIUtils.GRAY);
        table.put("ComboBox.selectionForeground", Color.black);
        table.put("ComboBox.foreground", Color.black);
        table.put("ComboBox.background", UIUtils.WHITE);
    }

    @Override
    public void initClassDefaults(UIDefaults table){
        super.initClassDefaults(table);

        // Buttons
        table.put("ButtonUI", FTBButtonUI.class.getCanonicalName());
        table.put("ToggleButtonUI", FTBToggleButtonUI.class.getCanonicalName());

        // Control
        table.put("ComboBoxUI", FTBComboBoxUI.class.getCanonicalName());
    }

    @Override
    public String getName(){
        return "FTBLookAndFeel";
    }

    @Override
    public String getID(){
        return FTBLookAndFeel.class.getName();
    }

    @Override
    public String getDescription(){
        return "FTB Look and Feel";
    }

    @Override
    public boolean isNativeLookAndFeel(){
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel(){
        return true;
    }
}