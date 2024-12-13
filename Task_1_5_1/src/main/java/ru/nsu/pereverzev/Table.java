package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * Represents a table element in a document.
 */
public class Table extends Element {
    /**
     * Enumeration of possible text alignments.
     */
    enum Alignment {
        ALIGN_LEFT,
        ALIGN_CENTER,
        ALIGN_RIGHT
    }

    public static final Alignment ALIGN_LEFT = Alignment.ALIGN_LEFT;
    public static final Alignment ALIGN_CENTER = Alignment.ALIGN_CENTER;
    public static final Alignment ALIGN_RIGHT = Alignment.ALIGN_RIGHT;

    private ArrayList<Element> cells1;
    private ArrayList<Element> cells2;
    Alignment alignment1;
    Alignment alignment2;

    /**
     * Constructs a table with cells.
     */
    Table(ArrayList<Element> cells1, ArrayList<Element> cells2) {
        this.cells1 = cells1;
        this.cells2 = cells2;
    }

    /**
     * Builder for creating Table instances.
     */
    public static class Builder implements ru.nsu.pereverzev.Builder {
        private ArrayList<Element> elements1;
        private ArrayList<Element> elements2;
        Alignment alignment1;
        Alignment alignment2;
        int rowLimit;

        /**
         * Initializes a new Table builder.
         */
        public Builder() {
            elements1 = new ArrayList<>();
            elements2 = new ArrayList<>();
            alignment1 = ALIGN_LEFT;
            alignment2 = ALIGN_LEFT;
            rowLimit = Integer.MAX_VALUE;
        }

        /**
         * Converts an object to a Text element.
         */
        private Text convertToText(Object obj) {
            if (obj instanceof Text) {
                return (Text) obj;
            } else if (obj instanceof Element) {
                return new Text.Builder().setText(((Element) obj).toString()).build();
            } else {
                return new Text.Builder().setText(String.valueOf(obj)).build();
            }
        }

        /**
         * Adds a row to the table.
         */
        public Builder addRow(Object e1, Object e2) {
            elements1.add(convertToText(e1));
            elements2.add(convertToText(e2));
            rowLimit--;
            if (rowLimit == -1) {
                throw new IllegalStateException("Row limit exceeded");   
            }
            return this;
        }

        /**
         * Sets column alignments.
         */
        public Builder withAlignments(Alignment alignment1, Alignment alignment2) {
            this.alignment1 = alignment1;
            this.alignment2 = alignment2;
            return this;
        }

        /**
         * Sets the maximum number of rows.
         */
        public Builder withRowLimit(int n) {
            elements1 = new ArrayList<>(n);
            elements2 = new ArrayList<>(n);
            rowLimit = n;
            return this;
        }

        /**
         * Builds the Table instance.
         */
        @Override
        public Table build() {
            return new Table(elements1, elements2);
        }
    }
    
    /**
     * Aligns the given element according to the specified alignment.
     */
    private String align(Element e, Alignment alignment, int maxLength) {
        String s = "";
        if (alignment == Alignment.ALIGN_CENTER) {
            int padding = (maxLength - e.toString().length()) / 2;
            s = " ".repeat(padding) + e.toString() + " ".repeat(maxLength - e.toString().length() - padding);
        } else if (alignment == Alignment.ALIGN_RIGHT) {
            s = " ".repeat(maxLength - e.toString().length()) + e.toString();
        } else {
            s = e.toString() + " ".repeat(maxLength - e.toString().length());
        }
        return s;
    }

    /**
     * Returns the table as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int maxLengthCol1 = cells1.stream()
            .mapToInt(e -> e.toString().length())
            .max()
            .orElse(0);
        int maxLengthCol2 = cells2.stream()
            .mapToInt(e -> e.toString().length())
            .max()
            .orElse(0);
        
        result.append("| ")
            .append(align(cells1.get(0), alignment1, maxLengthCol1))
            .append(" | ")
            .append(align(cells2.get(0), alignment2, maxLengthCol2))
            .append(" |\n");

        result.append("| ")
            .append("-".repeat(maxLengthCol1))
            .append(" | ")
            .append("-".repeat(maxLengthCol2))
            .append(" |\n");
        
        for (int i = 1; i < cells1.size(); ++i) {
            result.append("| ")
                .append(align(cells1.get(i), alignment1, maxLengthCol1))
                .append(" | ")
                .append(align(cells2.get(i), alignment2, maxLengthCol2))
                .append(" |\n");
        }
        return result.toString();
    }

    /**
     * Checks equality with another object.
     */
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
        if (cells1 == null) {
            if (other.cells1 != null) {
                return false;
            }
        } else if (!cells1.equals(other.cells1)) {
            return false;
        }
        if (cells2 == null) {
            if (other.cells2 != null) {
                return false;
            }
        } else if (!cells2.equals(other.cells2)) {
            return false;
        }
        return true;
    }
}
