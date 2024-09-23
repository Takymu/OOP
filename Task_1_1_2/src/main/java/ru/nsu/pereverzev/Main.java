package ru.nsu.pereverzev;

import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DMNDS
}

class Constants {
    public static final int wscore = 21;
    public static final int decksize = 52;
}

public class Main {
    static int player_score = 0;
    static int diler_score = 0;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать в Блэкджэк!");
        while (true) {
            System.out.printf("Раунд %d\n", player_score + diler_score + 1);
            Player player = new Player();
            Player diler = new Player();
            Casino casino = new Casino();
            System.out.print("Дилер раздал карты\n    Ваши карты: [");
            Card card = casino.getCard("player");
            card.open();
            player.addCardScore(card);
            card = casino.getCard("player");
            card.open();
            player.addCardScore(card);
            System.out.print(player.getCardsList());
            System.out.printf("] => %d\n", player.getScore());
            System.out.print("    Карты дилера: [");
            card = casino.getCard("diler");
            card.open();
            card = diler.addCardScore(card);
            Card dilcard = casino.getCard("diler");
            diler.addCardScore(dilcard);
            System.out.print(diler.getCardsList());
            System.out.print("]\nВаш ход\n-------\nВведите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");
            while (true) {
                while (player.getScore() <= Constants.wscore && console.nextInt() == 1) {
                    card = casino.getCard("player");
                    card.open();
                    card = player.addCardScore(card);
                    System.out.print("Вы открыли карту ");
                    System.out.print(card.getPrintName());
                    System.out.print("\nВаши карты: [");
                    System.out.print(player.getCardsList());
                    System.out.printf("] => %d\nКарты дилера: [", player.getScore());
                    System.out.print(diler.getCardsList());
                    if (player.getScore() <= Constants.wscore) {
                        System.out.print("]\nВаш ход\n-------\nВведите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");
                    } else {
                        System.out.print("]\n");
                    }
                }

                if (player.getScore() > Constants.wscore) {
                    diler_score++;
                    System.out.printf("Вы проиграли раунд! Счет %d:%d ", player_score, diler_score);
                    if (player_score > diler_score) {
                        System.out.print("в вашу пользу.\n");
                    }
                    if (player_score < diler_score) {
                        System.out.print("в пользу дилера.\n");
                    }
                    break;
                }
                System.out.print("Ход дилера\n-------\nДилер открывает закрытую карту ");
                dilcard.open();
                System.out.print(dilcard.getPrintName());
                System.out.print("\nВаши карты: [");
                System.out.print(player.getCardsList());
                System.out.printf("] => %d\nКарты дилера: [", player.getScore());
                System.out.print(diler.getCardsList());
                System.out.printf("] => %d\n", diler.getScore());
                if (diler.isBlackjack()) {
                    System.out.print("У дилера блэкджек!\n");
                    if (!player.isBlackjack()) {
                        System.out.print("У вас - нет!\n");
                        diler_score++;
                        System.out.printf("Вы проиграли раунд! Счет %d:%d ", player_score, diler_score);
                        if (player_score > diler_score) {
                            System.out.print("в вашу пользу.\n");
                        }
                        if (player_score < diler_score) {
                            System.out.print("в пользу дилера.\n");
                        }
                        break;
                    } else {
                        System.out.print("У вас - тоже!\n Ничья. Вы забираете ставку обратно.\n");
                    }
                }

                while (diler.getScore() < 17) {
                    card = casino.getCard("diler");
                    card.open();
                    card = diler.addCardScore(card);
                    System.out.print("Дилер открывает карту ");
                    System.out.print(card.getPrintName());
                    System.out.print("\nВаши карты: [");
                    System.out.print(player.getCardsList());
                    System.out.printf("] => %d\nКарты дилера: [", player.getScore());
                    System.out.print(diler.getCardsList());
                    System.out.printf("] => %d\n", diler.getScore());
                }
                if (diler.getScore() > Constants.wscore) {
                    player_score++;
                    System.out.printf("Вы выиграли раунд! Счет %d:%d ", player_score, diler_score);
                    if (player_score > diler_score) {
                        System.out.print("в вашу пользу.");
                    }
                    if (player_score < diler_score) {
                        System.out.print("в пользу дилера.");
                    }
                    System.out.print("\n");
                    break;
                } else {
                    if (player.getScore() > diler.getScore()) {
                        player_score++;
                        System.out.printf("Вы выиграли раунд! Счет %d:%d ", player_score, diler_score);
                    } else {
                        diler_score++;
                        System.out.printf("Вы проиграли раунд! Счет %d:%d ", player_score, diler_score);
                    }
                    if (player_score > diler_score) {
                        System.out.print("в вашу пользу.\n");
                    }
                    if (player_score < diler_score) {
                        System.out.print("в пользу дилера.\n");
                    }
                }
                break;
            }

        }
    }
}

class Card {
    Suit suit;
    int price;
    String name;
    String owner;
    int closed;

    Card(int pr, Suit st, String nm) {
        suit = st;
        price = pr;
        name = nm;
        closed = 1;
        owner = "casino"; // in casino
    }

    public void open() {
        closed = 0;
    }

    public void setOwner(String new_owner) {
        owner = new_owner;
    }

    public String getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int pr) {
        price = pr;
    }

    public String getName() {
        return name;
    }

    public String getPrintName() {
        String s = "";
        if (closed == 1) {
            s += "<Закрытая карта>";
            return s;
        } else {
            switch (name) {
                case "default":
                    switch (price) {
                        case 2:
                            s += "Двойка";
                            break;
                        case 3:
                            s += "Тройка";
                            break;
                        case 4:
                            s += "Четверка";
                            break;
                        case 5:
                            s += "Пятерка";
                            break;
                        case 6:
                            s += "Шестерка";
                            break;
                        case 7:
                            s += "Семерка";
                            break;
                        case 8:
                            s += "Восьмерка";
                            break;
                        case 9:
                            s += "Девятка";
                            break;
                        case 10:
                            s += "Десятка";
                            break;
                    }
                    break;
                case "jack":
                    s += "Валет";
                    break;
                case "queen":
                    s += "Дама";
                    break;
                case "king":
                    s += "Король";
                    break;
                case "ace":
                    s += "Туз";
                    break;
            }
            switch (suit) {
                case SPADES:
                    s += " Пики";
                    break;
                case HEARTS:
                    s += " Черви";
                    break;
                case CLUBS:
                    s += " Трефы";
                    break;
                case DMNDS:
                    s += " Бубны";
                    break;
            }
            s = s + " (" + price + ")";
        }
        return s;
    }

    public Suit getSuit() {
        return suit;
    }


}

class Casino {
    Card[] bank;

    Casino() {
        bank = new Card[Constants.decksize];
        int size = Constants.decksize / 4; // count of cards in deck
        supportInitialize(Suit.CLUBS, 0);
        supportInitialize(Suit.SPADES, size);
        supportInitialize(Suit.CLUBS, size * 2);
        supportInitialize(Suit.CLUBS, size * 3);
    }


    private void supportInitialize(Suit suit, int start) {
        int curPrice = 2;
        for (int i = start; i < start + 9; i++) {
            bank[i] = new Card(curPrice, suit, "default");
            curPrice++;
        }
        bank[start + 9] = new Card(10, suit, "jack");
        bank[start + 10] = new Card(10, suit, "queen");
        bank[start + 11] = new Card(10, suit, "king");
        bank[start + 12] = new Card(11, suit, "ace");
    }

    public Card getCard(String new_owner) {
        int casin_card_count = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_card_count++;
            }
        }
        int[] casin_ids = new int[casin_card_count];
        int casin_ids_i = 0;
        for (int i = 0; i < Constants.decksize; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_ids[casin_ids_i] = i;
                casin_ids_i++;
            }
        }
        long id = Math.round(Math.random() * (double) casin_card_count);
        bank[(int) casin_ids[(int) id]].setOwner(new_owner);
        return bank[(int) casin_ids[(int) id]];
    }
}

class Player {
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