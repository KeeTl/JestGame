package jestgame.player;
import java.util.List;
import java.util.Scanner;
import jestgame.cards.Card;
import jestgame.cards.Hand;

public class PhysicalPlayer extends Player {

    public PhysicalPlayer(String name) {
        super(name);
    }

    @Override
    public void offer() {
        
        Scanner s = new Scanner(System.in);
    }

    @Override
    protected Player selectPlayer(List<Player> availablePlayers) {
        int pId = -1;
        System.out.println("Pick a player : ");
        int i = 1;
        for (Player p : availablePlayers) {
            System.out.print(i);
            System.out.print(": ");
            System.out.print(p.getname());
            System.out.print(" ");
            System.out.println(p.getHand());
            i++;
        }
        Scanner pInput = new Scanner(System.in);
        String iString = pInput.nextLine();
        try {
            pId = Integer.parseInt(iString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return availablePlayers.get(pId - 1);
    }

    @Override
    protected Card selectCard(Hand h) {
        int cId = -1;
        System.out.println("Pick a card : ");
        System.out.println(h);
        Scanner pInput = new Scanner(System.in);
        String iString = pInput.nextLine();
        try {
            cId = Integer.parseInt(iString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return h.removeCard(cId);
    }
    
}