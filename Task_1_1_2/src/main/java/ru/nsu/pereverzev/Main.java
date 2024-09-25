package ru.nsu.pereverzev;

import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DMNDS
}

/**
 * basic game constants, wscore is winning score and decksize is the size of deck
 */

class Constants {
    public static final int wscore = 21;
    public static final int decksize = 52;
}

/**
 * main function call the play function, that do all I/O stuff
 */

public class Main {
    static int player_score = 0;
    static int diler_score = 0;

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        System.out.println("Добро пожаловать в Блэкджэк!");
        while (IntReader.getLastreaded() != 2) {
            System.out.printf("Раунд %d\n", player_score + diler_score + 1);
            Player player = new Player();
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
            Player diler = new Player();
            card = diler.addCardScore(card);
            Card dilcard = casino.getCard("diler");
            diler.addCardScore(dilcard);
            System.out.print(diler.getCardsList());
            System.out.print("""
                    ]
                    Ваш ход
                    -------
                    Введите "1", чтобы взять карту, и "0", чтобы остановиться...
                    """);
            while (player.getScore() <= Constants.wscore && IntReader.read() == 1) {
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
                    System.out.print("""
                            ]
                            Ваш ход
                            -------
                            Введите "1", чтобы взять карту, и "0", чтобы остановиться...
                            """);
                } else {
                    System.out.print("]\n");
                }
            }
            if (IntReader.getLastreaded() == 2) {
                return;
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
                continue;
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
                    continue;
                } else {
                    System.out.print("У вас - тоже!\n Ничья. Вы забираете ставку обратно.\n");
                }
            }
            if (player.isBlackjack()) {
                System.out.print("У вас блэкджек!\n");
                if (!diler.isBlackjack()) {
                    System.out.print("У дилера - нет!\n");
                    diler_score++;
                    System.out.printf("Вы выиграли раунд! Счет %d:%d ", player_score, diler_score);
                    if (player_score > diler_score) {
                        System.out.print("в вашу пользу.\n");
                    }
                    if (player_score < diler_score) {
                        System.out.print("в пользу дилера.\n");
                    }
                    continue;
                } else {
                    System.out.print("У дилера - тоже!\n Ничья. Вы забираете ставку обратно.\n");
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
            } else {
                if (player.getScore() == diler.getScore()) {
                    System.out.printf("Ничья! Счет %d:%d\n", player_score, diler_score);
                    continue;
                } else if (player.getScore() > diler.getScore()) {
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

        }
    }
}





