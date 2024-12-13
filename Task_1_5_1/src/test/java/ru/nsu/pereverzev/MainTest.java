package ru.nsu.pereverzev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for various text-related functionality in the project.
 * This class contains unit tests for Text, Builder, and related components.
 */
public class MainTest {

    @Test
    public void testTextBuilder() {
        Text.Builder builder = new Text.Builder();
        Text text = builder.setText("Hello").build();
        Assertions.assertEquals("Hello", text.toString().trim());

        Text customText = builder.setText("World").build();
        Assertions.assertEquals("World", customText.toString().trim());
    }

    @Test
    public void testBoldText() {
        Text.Bold boldText = new Text.Bold("Important");
        Assertions.assertEquals("**Important**", boldText.toString().trim());
    }

    @Test
    public void testHeading() {
        Heading h1 = new Heading.Builder().setLevel(1).setText("Title").build();
        Assertions.assertEquals("# Title", h1.toString().trim());

        Heading h2 = new Heading.Builder().setLevel(2).setText("Subtitle").build();
        Assertions.assertEquals("## Subtitle", h2.toString().trim());
    }

    @Test
    public void testLink() {
        Link link = new Link.Builder()
            .setText("Codeium")
            .setUrl("https://codeium.com")
            .build();
        Assertions.assertEquals("[Codeium](https://codeium.com)", link.toString().trim());
    }

    @Test
    public void testImage() {
        Image image = new Image.Builder()
            .setAlt("Logo")
            .setUrl("logo.png")
            .build();
        Assertions.assertEquals("![Logo](logo.png)", image.toString().trim());
    }

    @Test
    public void testQuote() {
        Quote quote = new Quote.Builder()
            .setText("Innovation is key")
            .build();
        Assertions.assertEquals("> Innovation is key", quote.toString().trim());
    }

    @Test
    public void testList() {
        List orderedList = new List.Builder()
            .add("First")
            .add("Second")
            .build();
        Assertions.assertTrue(orderedList.toString().contains("1. First"));
        Assertions.assertTrue(orderedList.toString().contains("2. Second"));
    }

    @Test
    public void testAdvancedList() {
        List mixedList = new List.Builder()
            .add("Text item")
            .add(new Text.Bold("Bold item"))
            .add(new Image.Builder().setUrl("image.jpg").setAlt("Sample").build())
            .build();
        
        Assertions.assertTrue(mixedList.toString().contains("1. Text item"));
        Assertions.assertTrue(mixedList.toString().contains("2. **Bold item**"));
        Assertions.assertTrue(mixedList.toString().contains("3. ![Sample](image.jpg)"));
    }

    @Test
    public void testAdvancedQuote() {
        Quote authoredQuote = new Quote.Builder()
            .setText("asdfasfasdfasdfas as dfa sd asfd")
            .build();
        
        Assertions.assertEquals("> asdfasfasdfasdfas as dfa sd asfd", 
            authoredQuote.toString().trim());
    }

    @Test
    public void testAdvancedLink() {
        Link linkWithTitle = new Link.Builder()
            .setText("asdf")
            .setUrl("https://agdfgadgad.com")
            .build();
        
        Assertions.assertEquals("[asdf](https://agdfgadgad.com)", linkWithTitle.toString().trim());
    }

    @Test
    public void testAdvancedText() {
        Text.Bold boldText = new Text.Bold("Strong");
        Text.Italic italicText = new Text.Italic("Emphasis");
        Text.Crossed crossedText = new Text.Crossed("Deleted");
        Text.Code codeText = new Text.Code("System.out.println()");

        Assertions.assertEquals("**Strong**", boldText.toString().trim());
        Assertions.assertEquals("*Emphasis*", italicText.toString().trim());
        Assertions.assertEquals("~~Deleted~~", crossedText.toString().trim());
        Assertions.assertEquals("`System.out.println()`", codeText.toString().trim());
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
        Assertions.assertNotNull(table);
        Assertions.assertTrue(table.toString().contains("Name"));
        Assertions.assertTrue(table.toString().contains("Value"));
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
        
        Assertions.assertEquals(list1, list1);
        
        Assertions.assertEquals(list1, list2);
        Assertions.assertEquals(list2, list1);
        
        Assertions.assertNotEquals(list1, differentList);
        Assertions.assertNotEquals(list1, null);
        Assertions.assertNotEquals(list1, new Object());
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
        
        Assertions.assertEquals(link1, link1);
        
        Assertions.assertEquals(link1, link2);
        Assertions.assertEquals(link2, link1);
        
        Assertions.assertNotEquals(link1, differentLink);
        Assertions.assertNotEquals(link1, null);
        Assertions.assertNotEquals(link1, new Object());
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
        
        Assertions.assertEquals(image1, image1);
        
        Assertions.assertEquals(image1, image2);
        Assertions.assertEquals(image2, image1);
        
        Assertions.assertNotEquals(image1, differentImage);
        Assertions.assertNotEquals(image1, null);
        Assertions.assertNotEquals(image1, new Object());
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
        
        Assertions.assertEquals(heading1, heading1);
        
        Assertions.assertEquals(heading1, heading2);
        Assertions.assertEquals(heading2, heading1);
        
        Assertions.assertNotEquals(heading1, differentHeading);
        Assertions.assertNotEquals(heading1, null);
        Assertions.assertNotEquals(heading1, new Object());
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
        
        Assertions.assertEquals(quote1, quote1);
        
        Assertions.assertEquals(quote1, quote2);
        Assertions.assertEquals(quote2, quote1);
        
        Assertions.assertNotEquals(quote1, differentQuote);
        Assertions.assertNotEquals(quote1, null);
        Assertions.assertNotEquals(quote1, new Object());
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
        
        Assertions.assertEquals(table1, table1);
        
        Assertions.assertEquals(table1, table2);
        Assertions.assertEquals(table2, table1);
        
        Assertions.assertNotEquals(table1, differentTable);
        Assertions.assertNotEquals(table1, null);
        Assertions.assertNotEquals(table1, new Object());
    }
}