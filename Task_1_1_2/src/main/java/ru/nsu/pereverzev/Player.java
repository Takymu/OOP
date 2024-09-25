package ru.nsu.pereverzev;

import java.util.ArrayList;

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

    public boolean isBlackjack() {
        if (score == Constants.wscore && cardcnt == 2) {
            blackjack = 1;
            return true;
        } else {
            return false;
        }
    }

    public int getScore() {
        return score;
    }
}
