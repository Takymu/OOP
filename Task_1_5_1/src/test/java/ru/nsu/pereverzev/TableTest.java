package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for Table functionality.
 */
class TableTest {
    
    /**
     * Tests table builder with random row generation.
     */
    @Test
    void test() {
        Table.Builder tableBuilder = new Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(8)
            .addRow("Index", "Random");
        for (int i = 1; i <= 5; i ++) {
            final var value = (int) (Math.random() * 10);
            if (value > 5) {
                tableBuilder.addRow(i, new
                    Text.Bold(String.valueOf(value)));
            } else {
                tableBuilder.addRow(i, (int) (Math.random() * 10));
            }
        }
        Table table = tableBuilder.build();
        Assertions.assertNotNull(table);
    }
}