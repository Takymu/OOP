package ru.nsu.pereverzev;

import java.util.ArrayList;

public class Table extends Element{
    private ArrayList<Element> cells1;
    private ArrayList<Element> cells2;

    Alignment al1;
    Alignment al2;

    enum Alignment {
        ALIGN_LEFT,
        ALIGN_CENTER,
        ALIGN_RIGHT
    }
    
    public static final Alignment ALIGN_LEFT = Alignment.ALIGN_LEFT;
    public static final Alignment ALIGN_CENTER = Alignment.ALIGN_CENTER;
    public static final Alignment ALIGN_RIGHT = Alignment.ALIGN_RIGHT;

    Table(ArrayList<Element> cells1, ArrayList<Element> cells2) {
        this.cells1 = cells1;
        this.cells2 = cells2;
    }

    public static class Builder implements ru.nsu.pereverzev.Builder {
        private ArrayList<Element> elements1;
        private ArrayList<Element> elements2;
        Alignment al1;
        Alignment al2;
        int rowlimit;
        Builder() {
            elements1 = new ArrayList<Element>();
            elements2 = new ArrayList<Element>();
            rowlimit = 0;
            al1 = ALIGN_LEFT;
            al2 = ALIGN_LEFT;
        }

        private Text convertToText(Object obj) {
            if (obj instanceof Text) {
                return (Text) obj;
            } else if (obj instanceof String) {
                return Text.of((String) obj);
            } else {
                return Text.of(String.valueOf(obj));
            }
        }

        public Builder addRow(Object e1, Object e2) {
            elements1.add(convertToText(e1));
            elements2.add(convertToText(e2));
            rowlimit--;
            if(rowlimit == -1) {
                throw new IllegalStateException("Row limit exceeded");   
            }
            return this;
        }
        public Builder withAlignments(Alignment al1, Alignment al2) {
            this.al1 = al1;
            this.al2 = al2;
            return this;
        }
        public Builder withRowLimit(int n) {
            elements1 = new ArrayList<Element>(n);
            elements2 = new ArrayList<Element>(n);
            rowlimit = n;
            return this;
        }

        @Override
        public Table build() {
            return new Table(elements1, elements2);
        }
    }
    
    private String align(Element e, Alignment al, int maxlen) {
        String s = "";
        if (al == Alignment.ALIGN_CENTER) {
            s = " ".repeat((maxlen - e.toString().length()) / 2) + e.toString() 
                + " ".repeat((maxlen - e.toString().length()) / 2 + 1);
        } else if (al == Alignment.ALIGN_RIGHT) {
            s = e.toString() + " ".repeat(maxlen - e.toString().length());
        } else {
            s = " ".repeat(maxlen - e.toString().length()) + e.toString();
        }
        return s;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int maxlen_col1 = 0;
        int maxlen_col2 = 0;
        for (int i = 0; i < cells1.size(); ++i) {
            if (cells1.get(i).toString().length() > maxlen_col1) {
                maxlen_col1 = cells1.get(i).toString().length();
            }
            if (cells2.get(i).toString().length() > maxlen_col2) {
                maxlen_col2 = cells2.get(i).toString().length();
            }
        }
        
        result.append("| ").append(align(cells1.get(0), al1, maxlen_col1));
        result.append(" | ").append(align(cells2.get(0), al2, maxlen_col2));
        result.append(" |\n");

        result.append("| ").append("-".repeat(maxlen_col1));
        result.append(" | ").append("-".repeat(maxlen_col2));
        result.append(" |\n");
        
        for (int i = 1; i < cells1.size(); ++i) {
            result.append("| ").append(align(cells1.get(i), al1, maxlen_col1));
            result.append(" | ").append(align(cells2.get(i), al2, maxlen_col2));
            result.append(" |\n");
        }
        return result.toString();
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
        Table other = (Table) obj;
        if (!cells1.equals(other.cells1)) {
            return false;
        }
        if (!cells2.equals(other.cells2)) {
            return false;
        }
        return true;
    }
    
}
