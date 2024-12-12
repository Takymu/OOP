package ru.nsu.pereverzev;

import java.util.ArrayList;

public class List extends Element {
    ArrayList<Element> elements;

    public static class Builder implements ru.nsu.pereverzev.Builder {
        private List list;

        public Builder() {
            list = new List();
        }
        
        public Builder add(String text) {
            list.add(new Text.Builder().setText(text).build());
            return this;
        }

        public Builder add(Element element) {
            list.add(element);
            return this;
        }

        public List build() {
            return new List(list.elements);
        }
    }

    public List(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public List() {
        this(new ArrayList<Element>());
    }

    public void add(Element element) {
        elements.add(element);
    }

    public Element get(int index) {
        return elements.get(index);
    }

    public void remove(int index) {
        elements.remove(index);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < elements.size(); i++) {
            result += String.valueOf(i + 1) + ". " + elements.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        List other = (List) obj;
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals(other.elements.get(i))) {
                return false;
            }
        }
        return true;
    }
}
