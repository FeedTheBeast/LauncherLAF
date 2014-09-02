package net.ftb.ui.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.Map;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;

public final class UIUtils{
    public static final Color RED = new Color(224, 75, 66);
    public static final Color GRAY = new Color(221, 221, 221);
    public static final Color WHITE = new Color(254, 254, 254);
    public static final Color DARK_GRAY = new Color(104, 104, 104);

    private static Map<Key, Object> hints;
    private static Map<Key, Object> oldHints;

    public static void setBackground(Component c, Color bg) {
        setBackground0(c.getAccessibleContext(), bg);
    }

    private static void setBackground0(AccessibleContext ac, Color bg) {
        ac.getAccessibleComponent().setBackground(bg);
        int n = ac.getAccessibleChildrenCount();
        for (int i=0; i<n; i++) {
            setBackground0(ac.getAccessibleChild(i).getAccessibleContext(), bg);
        }
    }

    public static void setForeground(Component c, Color fg){
        setForeground0(c.getAccessibleContext(), fg);
    }

    private static void setForeground0(AccessibleContext ac, Color fg){
        ac.getAccessibleComponent().setForeground(fg);
        for(int i = 0; i < ac.getAccessibleChildrenCount(); i++){
            setForeground0(ac.getAccessibleChild(i).getAccessibleContext(), fg);
        }
    }

    public static void antialiasOn(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(hints == null){
            hints = new HashMap<Key, Object>();
            hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            oldHints = new HashMap<Key, Object>();
            oldHints.put(RenderingHints.KEY_FRACTIONALMETRICS, g2.getRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS));
            oldHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING));
            oldHints.put(RenderingHints.KEY_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING));
        }

        g2.addRenderingHints(hints);
    }

    public static AlphaComposite alpha(float f){
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
    }

    public static void antialiasOff(Graphics g){
        ((Graphics2D) g).addRenderingHints(oldHints);
    }

    public static Image load(String s){
        try{
            return ImageIO.read(System.class.getResourceAsStream("/assets/" + s + ".png"));
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String wrap(String text, int len){
        StringBuilder builder = new StringBuilder(text);
        int i = 0;
        while(i + len < builder.length() && (i = builder.lastIndexOf(" ", i + len)) != -1){
            builder.replace(i, i + 1, "\n");
        }
        return builder.toString();
    }
}