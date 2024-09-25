package ru.nsu.pereverzev;

import java.util.ArrayList;


/**
 * class that will model player behaviour.
 */
public class Player {
    int blackjack;
    int score;
    int cardcnt;
    ArrayList<Card> pcards = new ArrayList<Card>();

    Player() {
        blackjack = 0;
        score = 0;
        cardcnt = 0;
    }

    /**
     * Method returns string with the card names, suits and prices.
     */

    public String getCardsList() {
        String s = "";
        int isfirst = 1;
        for (Card curc : pcards) {
            if (isfirst == 1) {
                isfirst = 0;
            } else {
                s += (", ");
            }
            s += curc.getPrintName();
        }
        return s;
    }

    /**
     * method adds card to player, score increments by the value of the card price.
     */

    public Card addCardScore(Card card) {
        if (card.getName() == "ace") {
            if (score + card.getPrice() <= Constants.wscore) {
                score += card.getPrice();
            } else {
                score += 1;
                card.setPrice(1);
            }
        } else {
            score += card.getPrice();
        }
        cardcnt++;
        pcards.add(card);
        return card;
    }

    /**
     * method returns boolean that symbolise, has player blackjack or not
     */

    public boolean isBlackjack() {
        if (score == Constants.wscore && cardcnt == 2) {
            blackjack = 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns player's score
     */

    public int getScore() {
        return score;
    }
}
