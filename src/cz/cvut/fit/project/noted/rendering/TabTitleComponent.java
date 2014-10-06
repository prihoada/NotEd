package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Renders the title and close button for a tab.
 * @author Adam Příhoda
 */
public class TabTitleComponent extends JPanel
{

    private final JTabbedPane pane;

    public TabTitleComponent(final JTabbedPane pane,
                             final TabManager tabManager,
                             final LocalizationManager localizationManager)
    {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.pane = pane;
        setOpaque(false);

        JLabel label = new JLabel()
        {
            @Override
            public String getText()
            {
                int i = pane.indexOfTabComponent(TabTitleComponent.this);
                if (i != -1)
                {
                    Tab tab = tabManager.getTabAt(i);
                    return pane.getTitleAt(i) + (!tab.isSaved() ? "*" : "");
                }
                return null;
            }
        };

        this.add(label);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        JButton button = new TabButton(pane, this, localizationManager, tabManager);
        this.add(button);
        this.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

}

class TabButton extends JButton implements ActionListener
{

    private final JTabbedPane pane;
    private final JPanel component;
    private static final int size = 17;

    private final TabManager tabManager;

    public TabButton(JTabbedPane pane,
                     JPanel component,
                     LocalizationManager localizationManager,
                     TabManager tabManager)
    {
        this.tabManager = tabManager;
        this.pane = pane;
        this.component = component;
        this.setPreferredSize(new Dimension(size, size));
        setToolTipText(localizationManager.getString("tab_close_button").getName());
        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
        addMouseListener(buttonMouseListener);
        setRolloverEnabled(true);
        addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int i = pane.indexOfTabComponent(component);
        if (i != -1)
        {
            tabManager.closeTab(i);
        }
    }

    @Override
    public void updateUI()
    {
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        //shift the image for pressed buttons
        if (getModel().isPressed())
        {
            g2.translate(1, 1);
        }
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        if (getModel().isRollover())
        {
            g2.setColor(Color.RED);
        }
        int delta = 6;
        g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
        g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
        g2.dispose();
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter()
    {
        @Override
        public void mouseEntered(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };

}
