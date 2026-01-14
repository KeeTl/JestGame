package jestgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jestgame.cards.*;
import jestgame.player.Player;

public class PlayerListener implements PropertyChangeListener {
    private JPanel panel;
    private Rotation rotation;
    private LinkedList<JLabel> cards;
    private JPanel pCards;
    private JLabel pname;
    private JLabel jest;

    public PlayerListener(Player p) {
        this.rotation = new Rotation();
        this.panel = new JPanel();
        this.cards = new LinkedList();
        this.pCards = new JPanel();
        this.pname = new JLabel();
        this.jest = new JLabel();

        this.pCards.setBackground(Color.DARK_GRAY);
        this.panel.add(this.pCards, BorderLayout.CENTER);
        this.panel.add(this.pname, BorderLayout.SOUTH);
        this.panel.add(this.jest, BorderLayout.SOUTH);

        p.addEventListener(this);
    }

    public JPanel getPanel() {
        return this.panel;
    }

   /*
    public void init(Player p) {        
        p.addEventListener(this);
        this.pname.setText(p.getname());
        this.panel.setPreferredSize(this.rotation().dim);
    }
    */

    public Rotation rotation() {
        return this.rotation;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Player) {
            System.out.println(".()" + evt.getPropertyName());
            switch (evt.getPropertyName()) {
                case "hand":
                    this.pCards.removeAll();
                    for (Card c : ((Hand) evt.getNewValue()).getCards()) {
                        System.out.println("Card" + c.toString());
                        JLabel j = new JLabel();
                        j.setForeground(Color.white);
                        if (c.isFaceup()) {
                            j.setText(c.toString());
                        }
                        else {
                            j.setText("Hidden");
                        }
                        this.pCards.add(j);
                    }
                    break;
                case "name":
                    this.pname.setText((String) evt.getNewValue());
                    break;
                case "jest":
                    this.jest.setText(Integer.toString(((Jest) evt.getNewValue()).getCards().size()) + "cards");
                    break;
                default:
                    throw new AssertionError();
            }
        }

        else if (evt.getSource() instanceof Rotation) {
            switch (evt.getPropertyName()) {
                case "update":
                    this.panel.revalidate();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

}
