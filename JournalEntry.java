import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * JournalEntry represents a single journal entry, including the content,
 * date, sentiment, and a motivational quote. It provides functionality to save
 * the entry to a file.
 */
public class JournalEntry {
    private LocalDate date; // Date of the journal entry
    private String content; // Content of the journal entry
    private String sentiment; // Sentiment analysis result
    private String motivationalQuote; // Motivational quote to uplift the user

    /**
     * Constructs a JournalEntry with specified content, sentiment, and a motivational quote.
     * 
     * @param content The content of the journal entry.
     * @param sentiment The analyzed sentiment of the entry.
     * @param motivationalQuote The motivational quote to inspire the user.
     */
    public JournalEntry(String content, String sentiment, String motivationalQuote) {
        this.date = LocalDate.now(); // Sets the entry date to today
        this.content = content; // Sets the entry content
        this.sentiment = sentiment; // Sets the sentiment analysis result
        this.motivationalQuote = motivationalQuote; // Sets the motivational quote
    }

    /**
     * Saves the journal entry to a file named "journal_entries.txt".
     * Each entry includes the date, content, sentiment, and motivational quote.
     */
    public void saveEntry() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("journal_entries.txt", true))) {
            // Write entry details to file in the specified format
            writer.write("Date: " + date + "\n");
            writer.write("Content: " + content + "\n");
            writer.write("Sentiment: " + sentiment + "\n");
            writer.write("Quote: \"" + motivationalQuote + "\"\n\n"); // Motivational quote with formatting
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for any file writing errors
        }
    }
}