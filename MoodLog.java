import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * MoodLog logs the mood (sentiment) of each journal entry to a file for
 * tracking trends over time.
 */
public class MoodLog {

    /**
     * Logs the mood (sentiment) to a file named "mood_log.txt" with the current date.
     * 
     * @param sentiment The sentiment of the journal entry.
     */
    public static void logMood(String sentiment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("mood_log.txt", true))) {
            writer.write("Date: " + LocalDate.now() + " - Sentiment: " + sentiment + "\n");
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for any file writing errors
        }
    }
}