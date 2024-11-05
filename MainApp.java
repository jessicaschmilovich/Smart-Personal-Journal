import java.util.Scanner;

/**
 * MainApp contains the main method to initiate the Smart Personal Journal.
 * It prompts the user for a journal entry, analyzes its sentiment, saves the entry,
 * and logs the mood based on the sentiment analysis.
 */
public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of SentimentAnalyzer to analyze journal entry sentiment
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // Prompt the user for their journal entry
        System.out.println("Welcome to the Smart Personal Journal!");
        System.out.println("Enter your journal entry:");
        String content = scanner.nextLine();

        // Analyze the sentiment of the journal entry
        String sentiment = analyzer.analyzeSentiment(content);

        // Get a random motivational quote
        String motivationalQuote = Quotes.getQuotes();

        // Display the analyzed sentiment to the user
        System.out.println("\nAnalyzed Sentiment: " + sentiment);

        // Display the motivational quote to the user
        System.out.println("Here is your motivational quote: " + motivationalQuote);

        // Create a new JournalEntry object with the content, detected sentiment, and motivational quote
        JournalEntry entry = new JournalEntry(content, sentiment, motivationalQuote);

        // Save the journal entry to a file for record-keeping
        entry.saveEntry();

        // Log the mood (sentiment) to a mood log file for tracking over time
        MoodLog.logMood(sentiment);

        // Confirmation message
        System.out.println("Your journal entry has been saved, and the mood has been logged.");
        System.out.println("Thank you for using the Smart Personal Journal!");

        // Close the scanner to free up resources
        scanner.close();
    }
}