package jestgame.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jestgame.JestGame;
import jestgame.cards.Card;
import jestgame.cards.Trophies;
import jestgame.player.Player;

public class Vue implements PropertyChangeListener {

    private JFrame window;
    private List<PlayerListener> players;
    private JPanel trophies;

    public Vue(JestGame game) {

        this.window = new JFrame("JEST GAME §§§§");
        this.players = new ArrayList();

        ArrayList<Player> playersList = game.getPlayers();
        Container c = this.window.getContentPane();
        c.setPreferredSize(new Dimension(600, 300));
        String[] b = {BorderLayout.SOUTH, BorderLayout.EAST, BorderLayout.WEST, BorderLayout.NORTH};

        this.players = new ArrayList();

        for (int i = 0; i < playersList.size(); i++) {
            this.players.add(new PlayerListener(playersList.get(i)));            
            this.players.get(i).rotation().setDimension(150, 150);
            this.players.get(i).rotation().angle(Math.PI * i / 2);

            c.add(this.players.get(i).getPanel(), b[i%4]);
        }

        /*for (int i = 0; i < playersList.size(); i++) {
            this.players.add(new PlayerListener(playersList.get(i)));
            this.players.get(i).init(playersList.get(i));
            this.players.get(i).rotation().setDimension(300, 200);
            this.players.get(i).rotation().angle(Math.PI * i / 2);
            playersList.get(i).addEventListener(this.players.get(i));

            c.add(this.players.get(i).getPanel(), b[i%4]);
        }*/


       
        this.trophies = new JPanel();
        game.trophies().addEventListener(this);
        c.add(trophies, BorderLayout.CENTER);

        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.pack();
        this.window.setVisible(true);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Trophies) {
            switch (evt.getPropertyName()) {
                case "update":
                    this.trophies.removeAll();
                    for (Card c : ((Trophies) evt.getSource()).getCards()) {
                        this.trophies.add(new JLabel(c.toString()));
                    }


                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}