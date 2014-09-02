package net.ftb.ui;

import net.ftb.ui.utils.UIUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class LightBarScrollPane
extends JComponent{
    private static final int THUMB_BORDER_SIZE = 2;
    private static final int THUMB_SIZE = 4;
    private static final Color THUMB_COLOR = new Color(136, 136, 136);

    private final JScrollPane scrollPane;
    private final JScrollBar verticalScrollBar;
    private final JScrollBar horizontalScrollBar;

    public LightBarScrollPane(JComponent component){
        this.setOpaque(false);
        scrollPane = new JScrollPane(component);
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setVisible(false);
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new MyScrollBarUI());

        horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setVisible(false);
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setUI(new MyScrollBarUI());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayer(verticalScrollBar, JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(horizontalScrollBar, JLayeredPane.PALETTE_LAYER);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLayout(new ScrollPaneLayout(){
            @Override
            public void layoutContainer(Container parent){
                viewport.setBounds(0, 0, getWidth(), getHeight());
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        displayScrollBarsIfNecessary(viewport);
                    }
                });
            }
        });

        layeredPane.add(horizontalScrollBar);
        layeredPane.add(verticalScrollBar);
        layeredPane.add(scrollPane);

        setLayout(new BorderLayout(){
            @Override
            public void layoutContainer(Container target){
                super.layoutContainer(target);
                int width = getWidth();
                int height = getHeight();
                scrollPane.setBounds(0, 0, width, height);

                int scrollBarSize = 12;
                int cornerOffset = verticalScrollBar.isVisible() &&
                        horizontalScrollBar.isVisible() ? scrollBarSize : 0;
                if(verticalScrollBar.isVisible()){
                    verticalScrollBar.setBounds(width - scrollBarSize, 0,
                            scrollBarSize, height - cornerOffset);
                }
                if(horizontalScrollBar.isVisible()){
                    horizontalScrollBar.setBounds(0, height - scrollBarSize,
                            width - cornerOffset, scrollBarSize);
                }
            }
        });
        add(layeredPane, BorderLayout.CENTER);
    }

    private void displayScrollBarsIfNecessary(JViewport viewPort){
        displayVerticalScrollBarIfNecessary(viewPort);
        displayHorizontalScrollBarIfNecessary(viewPort);
    }

    private void displayVerticalScrollBarIfNecessary(JViewport viewPort){
        Rectangle viewRect = viewPort.getViewRect();
        Dimension viewSize = viewPort.getViewSize();
        boolean shouldDisplayVerticalScrollBar =
                viewSize.getHeight() > viewRect.getHeight();
        verticalScrollBar.setVisible(shouldDisplayVerticalScrollBar);
    }

    private void displayHorizontalScrollBarIfNecessary(JViewport viewPort){
        Rectangle viewRect = viewPort.getViewRect();
        Dimension viewSize = viewPort.getViewSize();
        boolean shouldDisplayHorizontalScrollBar =
                viewSize.getWidth() > viewRect.getWidth();
        horizontalScrollBar.setVisible(shouldDisplayHorizontalScrollBar);
    }

    private static class MyScrollBarButton
    extends JButton{

        private MyScrollBarButton(){
            setOpaque(false);
            setFocusable(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private static class MyScrollBarUI
    extends BasicScrollBarUI{
        @Override
        protected JButton createDecreaseButton(int orientation){
            return new MyScrollBarButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation){
            return new MyScrollBarButton();
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
            Graphics2D g2 = (Graphics2D) g;

            int orientation = scrollbar.getOrientation();
            int arc = THUMB_SIZE;
            int x = thumbBounds.x + THUMB_BORDER_SIZE;
            int y = thumbBounds.y + THUMB_BORDER_SIZE;

            int width = orientation == JScrollBar.VERTICAL ? THUMB_SIZE : thumbBounds.width - (THUMB_BORDER_SIZE * 2);
            width = Math.max(width, THUMB_SIZE);
            int height = orientation == JScrollBar.VERTICAL ? thumbBounds.height - (THUMB_BORDER_SIZE * 2) : THUMB_SIZE;
            height = Math.max(height, THUMB_SIZE);

            UIUtils.antialiasOn(g2);
            g2.setColor(THUMB_COLOR);
            g2.fillRect(x, y, width, height);
            g2.dispose();
            UIUtils.antialiasOff(g2);
        }

        @Override protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds){}

    }
}