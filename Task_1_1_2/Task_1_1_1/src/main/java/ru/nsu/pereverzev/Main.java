package ru.nsu.pereverzev;

import java.lang.String;
import java.util.Scanner;


enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DMNDS
}


public class Main {
    static int player_score = 0;
    static int diler_score = 0;
    static int round_num = 1;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать в Блэкджэк!");
        while(true) {
            System.out.printf("Раунд %d\n", round_num);
            Player player = new Player();
            Player diler = new Player();
            Casino casino = new Casino();
            System.out.printf("Дилер раздал карты\n    Ваши карты: [");
            Card card = casino.getCard("player");
            card.open();
            player.addCardScore(card);
            card.printName(player.getScore());
            card = casino.getCard("player");
            card.open();
            player.addCardScore(card);
            casino.printOwnerCards("player", player.getScore());
            System.out.printf("] => %d\n", player.getScore());
            System.out.print("    Карты дилера: [");
            card = casino.getCard("diler");
            card.open();
            diler.addCardScore(card);
            card = casino.getCard("diler");
            diler.addCardScore(card);
            casino.printOwnerCards("diler", diler.getScore());
            System.out.print("]\n  Ваш ход\n -------\n Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться .");
            if (console.nextInt() == 1) {

            }
            break;


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
        new_owner = owner;
    }
    public String getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public String getName() { return name; }

    public void printName(int player_score) {
        if (closed == 1) {
            System.out.print("<Закрытая карта> ");
        } else {
            switch (name) {
                case "default":
                    switch (price) {
                        case 2:
                            System.out.print("Двойка");
                            break;
                        case 3:
                            System.out.print("Тройка");
                            break;
                        case 4:
                            System.out.print("Четверка");
                            break;
                        case 5:
                            System.out.print("Пятерка");
                            break;
                        case 6:
                            System.out.print("Шестерка");
                            break;
                        case 7:
                            System.out.print("Семерка");
                            break;
                        case 8:
                            System.out.print("Восьмерка");
                            break;
                        case 9:
                            System.out.print("Девятка");
                            break;
                        case 10:
                            System.out.print("Десятка");
                            break;
                    }
                    break;
                case "jack":
                    System.out.print("Валет");
                    break;
                case "queen":
                    System.out.print("Дама");
                    break;
                case "king":
                    System.out.print("Король");
                    break;
                case "ace":
                    System.out.print("Туз");
                    break;
            }
            switch (suit) {
                case SPADES:
                    System.out.print(" Пики");
                    break;
                case HEARTS:
                    System.out.print(" Черви");
                    break;
                case CLUBS:
                    System.out.print(" Трефы");
                    break;
                case DMNDS:
                    System.out.print(" Бубны");
                    break;
            }
            if (name == "Ace" && player_score > 21) {
                System.out.print(" (1)");
            } else {
                System.out.printf(" (%d)", price);
            }
        }
    }

    public Suit getSuit() {
        return suit;
    }


}

class Casino {
    Card[] bank;

    Casino() {
        bank = new Card[52];
        int size = 13;
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

    public void printOwnerCards(String ownername, int ownerscore) {
        int isfirst = 1;
        for (int i = 0; i < 52; i++) {
            if (bank[i].getOwner() == ownername) {
                if (isfirst == 1) {
                    isfirst = 0;
                } else {
                    System.out.print(", ");
                }
                bank[i].printName(ownerscore);
            }
        }
    }

    public Card getCard(String new_owner) {
        int casin_card_count = 0;
        for (int i = 0; i < 52; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_card_count++;
            }
        }
        int[] casin_ids = new int[casin_card_count];
        int casin_ids_i = 0;
        for (int i = 0; i < 52; i++) {
            if (bank[i].getOwner() == "casino") {
                casin_ids[casin_ids_i] = i;
                casin_ids_i++;
            }
        }
        long id = Math.round(Math.random() * (double) casin_card_count);
        // System.out.println(id);
        bank[(int) id].setOwner(new_owner);
        return bank[(int) id];
    }
}

class Player {
    int blackjack;
    int score;
    int cardcnt;
    Player() {
        blackjack = 0;
        score = 0;
        cardcnt = 0;
    }

    public void addCardScore(Card card) {
        if (card.getName() == "ace") {
            if (score + card.getPrice() <= 21) {
                score += card.getPrice();
            } else {
                score += 1;
            }
        } else {
            score += card.getPrice();
        }
        cardcnt++;
    }
    public int isBlackjack() {
        if (score == 21 && cardcnt == 2) {
            blackjack = 1;
            return 1;
        } else {
            return 0;
        }
    }
    public int getScore() {
        return score;
    }
}