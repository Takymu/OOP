package ru.nsu.pereverzev;



public class Card {
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

    public void setOwner(String newOwner) {
        owner = newOwner;
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

    /**
     * method returns nice name of card in format like "Name Suit X".
     * or says, that card is closed.
     * @return String, name of the card.
     */

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
                        default:
                            s += "[Цена карты вне диапазона]";
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
                default:
                    s += "[Тип карты не удалось определить]";
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
                default:
                    s += " [Масть карты не удалось определить]";
            }
            s = s + " (" + price + ")";
        }
        return s;
    }

    public Suit getSuit() {
        return suit;
    }


}