package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testTextBuilder() {
        Text.Builder builder = new Text.Builder();
        Text text = builder.setText("Hello").build();
        assertEquals("Hello", text.toString().trim());

        Text customText = builder.setText("World").build();
        assertEquals("World", customText.toString().trim());
    }

    @Test
    public void testBoldText() {
        Text.Bold boldText = new Text.Bold("Important");
        assertEquals("**Important**", boldText.toString().trim());
    }

    @Test
    public void testHeading() {
        Heading h1 = new Heading.Builder().setLevel(1).setText("Title").build();
        assertEquals("# Title", h1.toString().trim());

        Heading h2 = new Heading.Builder().setLevel(2).setText("Subtitle").build();
        assertEquals("## Subtitle", h2.toString().trim());
    }

    @Test
    public void testLink() {
        Link link = new Link.Builder()
            .setText("Codeium")
            .setUrl("https://codeium.com")
            .build();
        assertEquals("[Codeium](https://codeium.com)", link.toString().trim());
    }

    @Test
    public void testImage() {
        Image image = new Image.Builder()
            .setAlt("Logo")
            .setUrl("logo.png")
            .build();
        assertEquals("![Logo](logo.png)", image.toString().trim());
    }

    @Test
    public void testQuote() {
        Quote quote = new Quote.Builder()
            .setText("Innovation is key")
            .build();
        assertEquals("> Innovation is key", quote.toString().trim());
    }

    @Test
    public void testList() {
        List orderedList = new List.Builder()
            .add("First")
            .add("Second")
            .build();
        assertTrue(orderedList.toString().contains("1. First"));
        assertTrue(orderedList.toString().contains("2. Second"));
    }

    @Test
    public void testAdvancedList() {
        List mixedList = new List.Builder()
            .add("Text item")
            .add(new Text.Bold("Bold item"))
            .add(new Image.Builder().setUrl("image.jpg").setAlt("Sample").build())
            .build();
        
        assertTrue(mixedList.toString().contains("1. Text item"));
        assertTrue(mixedList.toString().contains("2. **Bold item**"));
        assertTrue(mixedList.toString().contains("3. ![Sample](image.jpg)"));
    }

    @Test
    public void testAdvancedQuote() {
        Quote authoredQuote = new Quote.Builder()
            .setText("asdfasfasdfasdfas as dfa sd asfd")
            .build();
        
        assertEquals("> asdfasfasdfasdfas as dfa sd asfd", 
            authoredQuote.toString().trim());
    }

    @Test
    public void testAdvancedLink() {
        Link linkWithTitle = new Link.Builder()
            .setText("asdf")
            .setUrl("https://agdfgadgad.com")
            .build();
        
        assertEquals("[asdf](https://agdfgadgad.com)", linkWithTitle.toString().trim());
    }

    @Test
    public void testAdvancedText() {
        Text.Bold boldText = new Text.Bold("Strong");
        Text.Italic italicText = new Text.Italic("Emphasis");
        Text.Crossed crossedText = new Text.Crossed("Deleted");
        Text.Code codeText = new Text.Code("System.out.println()");

        assertEquals("**Strong**", boldText.toString().trim());
        assertEquals("*Emphasis*", italicText.toString().trim());
        assertEquals("~~Deleted~~", crossedText.toString().trim());
        assertEquals("`System.out.println()`", codeText.toString().trim());
    }

    @Test
    public void testTableBuilder() {
        Table.Builder tableBuilder = new Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(3)
            .addRow("Name", "Value");

        tableBuilder.addRow(42, "John");
        tableBuilder.addRow("Alice", new Text.Bold("100"));

        Table table = tableBuilder.build();
        assertNotNull(table);
        assertTrue(table.toString().contains("Name"));
        assertTrue(table.toString().contains("Value"));
    }

    @Test
    public void testListEquals() {
        List list1 = new List.Builder()
            .add("First")
            .add("Second")
            .build();
        
        List list2 = new List.Builder()
            .add("First")
            .add("Second")
            .build();
        
        List differentList = new List.Builder()
            .add("First")
            .add("Third")
            .build();
        
        assertEquals(list1, list1);
        
        assertEquals(list1, list2);
        assertEquals(list2, list1);
        
        assertNotEquals(list1, differentList);
        assertNotEquals(list1, null);
        assertNotEquals(list1, new Object());
    }

    @Test
    public void testLinkEquals() {
        Link link1 = new Link.Builder()
            .setText("gfgf")
            .setUrl("https://gfgf.com")
            .build();
        
        Link link2 = new Link.Builder()
            .setText("gfgf")
            .setUrl("https://gfgf.com")
            .build();
        
        Link differentLink = new Link.Builder()
            .setText("Different")
            .setUrl("https://example.com")
            .build();
        
        assertEquals(link1, link1);
        
        assertEquals(link1, link2);
        assertEquals(link2, link1);
        
        assertNotEquals(link1, differentLink);
        assertNotEquals(link1, null);
        assertNotEquals(link1, new Object());
    }

    @Test
    public void testImageEquals() {
        Image image1 = new Image.Builder()
            .setAlt("Logo")
            .setUrl("logo.png")
            .build();
        
        Image image2 = new Image.Builder()
            .setAlt("Logo")
            .setUrl("logo.png")
            .build();
        
        Image differentImage = new Image.Builder()
            .setAlt("Different")
            .setUrl("other.png")
            .build();
        
        assertEquals(image1, image1);
        
        assertEquals(image1, image2);
        assertEquals(image2, image1);
        
        assertNotEquals(image1, differentImage);
        assertNotEquals(image1, null);
        assertNotEquals(image1, new Object());
    }

    @Test
    public void testHeadingEquals() {
        Heading heading1 = new Heading.Builder()
            .setText("Title")
            .setLevel(1)
            .build();
        
        Heading heading2 = new Heading.Builder()
            .setText("Title")
            .setLevel(1)
            .build();
        
        Heading differentHeading = new Heading.Builder()
            .setText("Different")
            .setLevel(2)
            .build();
        
        assertEquals(heading1, heading1);
        
        assertEquals(heading1, heading2);
        assertEquals(heading2, heading1);
        
        assertNotEquals(heading1, differentHeading);
        assertNotEquals(heading1, null);
        assertNotEquals(heading1, new Object());
    }

    @Test
    public void testQuoteEquals() {
        Quote quote1 = new Quote.Builder()
            .setText("key")
            .build();
        
        Quote quote2 = new Quote.Builder()
            .setText("key")
            .build();
        
        Quote differentQuote = new Quote.Builder()
            .setText("quote")
            .build();
        
        assertEquals(quote1, quote1);
        
        assertEquals(quote1, quote2);
        assertEquals(quote2, quote1);
        
        assertNotEquals(quote1, differentQuote);
        assertNotEquals(quote1, null);
        assertNotEquals(quote1, new Object());
    }

    @Test
    public void testTableEquals() {
        Table.Builder tableBuilder1 = new Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(2)
            .addRow("Name", "Value");
        tableBuilder1.addRow(42, "John");
        Table table1 = tableBuilder1.build();
        
        Table.Builder tableBuilder2 = new Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(2)
            .addRow("Name", "Value");
        tableBuilder2.addRow(42, "John");
        Table table2 = tableBuilder2.build();
        
        Table differentTable = new Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(2)
            .addRow("Different", "Table")
            .addRow(99, "Alice")
            .build();
        
        assertEquals(table1, table1);
        
        assertEquals(table1, table2);
        assertEquals(table2, table1);
        
        assertNotEquals(table1, differentTable);
        assertNotEquals(table1, null);
        assertNotEquals(table1, new Object());
    }
}