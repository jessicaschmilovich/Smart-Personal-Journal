import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * SentimentAnalyzer loads OpenNLP models and provides methods to analyze
 * the sentiment of a given text, tokenize text, and detect sentences.
 */

public class SentimentAnalyzer {

    private TokenizerME tokenizer; // Tokenizer model for breaking text into tokens (words)
    private SentenceDetectorME sentenceDetector; // Sentence detector model for splitting text into sentences
    private static int sentimentScore = 0; // Initialize sentiment score

    /**
     * Constructor initializes the Tokenizer and SentenceDetector models.
     * Model files must be in the "models" folder.
     */
    public SentimentAnalyzer() {
        try {
            // Load tokenization model
            InputStream tokenModelIn = new FileInputStream("models/en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            tokenizer = new TokenizerME(tokenModel);
            tokenModelIn.close();

            // Load sentence detection model (optional, for future use)
            InputStream sentModelIn = new FileInputStream("models/en-sent.bin");
            SentenceModel sentModel = new SentenceModel(sentModelIn);
            sentenceDetector = new SentenceDetectorME(sentModel);
            sentModelIn.close();

        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for any file not found or model initialization errors
        }
    }

    /**
     * Analyzes the sentiment of the given text by checking for positive and
     * negative words and scoring their occurrences.
     * 
     * @param text The journal entry text to analyze.
     * @return A String representing the sentiment ("Positive", "Negative", or "Neutral").
     */
    public String analyzeSentiment(String text) {
        // Define lists of positive and negative words
        List<String> positiveWords = Arrays.asList(
            "happy", "great", "joy", "love", "excellent", "fantastic", "wonderful", "positive", "amazing", "ecstatic", "loved", "excited",
            "blissful", "cheerful", "radiant", "optimistic", "uplifted", "delighted", "content", "grateful", "appreciative", "inspired", 
            "hopeful", "elated", "passionate", "brilliant", "exhilarated", "energetic", "enthusiastic", "gleeful", "graceful", "heartwarming",
            "incredible", "jubilant", "lighthearted", "motivated", "vibrant", "empowered", "thrilled", "resilient", "serene", "peaceful", 
            "playful", "spirited", "soothing", "victorious", "sunny", "thankful", "refreshing", "enriched", "fulfilled", "joyful", 
            "rewarded", "encouraged", "free", "accomplished", "harmonious", "breezy", "dazzling", "rejuvenated", "sparkling", "radiating", 
            "uplifting", "effervescent", "soaring", "mindful", "centered", "faithful", "hopeful", "secure", "cheering", "glowing", 
            "flourishing", "perfect", "unique", "magical", "vivacious", "trusting", "powerful", "loving", "brave", "worthy", "healing", 
            "refreshing", "unbreakable", "kind", "radiant", "mind-blowing", "spectacular", "playful", "transforming", "peaceful", 
            "fantabulous", "phenomenal", "sublime", "resplendent", "heartfelt", "gracious", "superb", "marvelous", "uplifted", 
            "splendid", "tremendous", "resilient", "positive", "awesome", "appreciated", "radiant", "affirmed", "luminous", "grand", 
            "glorious", "cherished", "memorable", "valuable", "significant", "shining", "glowing", "encouraging", "flourishing", 
            "worthy", "prosperous", "winning", "calm", "tranquil", "unwavering", "steadfast", "brilliant", "spectacular", "bold", 
            "adventurous", "amazing", "appreciative", "worthy", "unique", "resilient", "cheerful", "energetic", "wonderful", "meaningful", 
            "secure", "gracious", "fabulous", "mindful", "radiating", "thoughtful", "refreshing", "renewed", "exhilarated", "peaceful", 
            "fulfilled", "generous", "exalted", "intuitive", "unstoppable", "genuine", "trustworthy", "reliable", "unbelievable", 
            "committed", "relaxed", "peaceful", "joyous", "invigorated", "content", "magnificent", "heartened", "elevated", "grateful", 
            "glorious", "fantastic", "peaceful", "thriving", "bright", "harmonious", "appreciative", "blessed", "secure", "abundant", 
            "safe", "radiant", "courageous", "creative", "respected", "successful", "loved", "balanced", "unmatched", "unbeatable", 
            "stellar", "bubbly", "enlightened", "light", "blooming", "nurturing", "pleasant", "splendid", "open-hearted", "truthful", 
            "connected", "dynamic", "evolving", "shining", "strengthened", "elevated", "worthy", "fulfilling", "radiating", "majestic", 
            "breathtaking", "sparkling", "magnetizing", "cherished", "memorable", "thriving", "inspiring", "powerful", "empowered", 
            "transformed", "calm", "light-hearted", "peace-filled", "glowing", "flourishing", "playful", "sincere", "enthused", 
            "satisfied", "lovable", "courageous", "fulfilled", "prosperous", "trusting", "free-spirited", "cheerful", "adored", 
            "heartening", "blossoming", "resilient", "purposeful", "joyous", "supportive", "incredible", "mind-blowing", "kind", 
            "bountiful", "luminous", "gracious", "uplifting", "carefree", "happy-go-lucky", "centered", "compassionate", "open", 
            "daring", "jovial", "joyous", "glistening", "blissful", "free", "celebrated", "positive", "wonderful", "hopeful", 
            "uplifted", "grace-filled", "cheering", "magnetic", "delightful", "jubilant", "thrilled", "proud", "smile"
        );        
        List<String> negativeWords = Arrays.asList(
            "sad", "bad", "hate", "terrible", "awful", "horrible", "negative", "painful", "angry", "upset",
            "depressed", "miserable", "lonely", "anxious", "fearful", "worried", "hopeless", "defeated", 
            "guilty", "ashamed", "insecure", "frustrated", "overwhelmed", "irritated", "unhappy", "disappointed",
            "discouraged", "distressed", "helpless", "worthless", "grieving", "disgusted", "rejected", "melancholy",
            "resentful", "embarrassed", "exhausted", "confused", "betrayed", "abandoned", "pessimistic", "numb",
            "angst", "dread", "tense", "apathetic", "broken", "doubtful", "envious", "jealous", "worried", 
            "heartbroken", "fearful", "tormented", "gloomy", "desperate", "traumatized", "shattered", "fearful", 
            "paranoid", "vindictive", "regretful", "forsaken", "inhibited", "stressed", "tense", "unworthy", 
            "disheartened", "offended", "agitated", "apprehensive", "troubled", "misunderstood", "alienated", 
            "criticized", "defeated", "ignored", "belittled", "harassed", "humiliated", "intimidated", "lonely", 
            "isolated", "dismal", "inferior", "inadequate", "lost", "misjudged", "annoyed", "apathetic", 
            "bitter", "contemptuous", "crushed", "doubtful", "devastated", "disturbed", "drained", "fearful", 
            "helpless", "hurt", "insulted", "jealous", "neglected", "resentful", "rejected", "sorrowful", 
            "stranded", "stuck", "tearful", "unfulfilled", "unloved", "unsure", "untrusting", "withdrawn", 
            "bitter", "blue", "cold", "desolate", "dejected", "desolate", "destroyed", "dissatisfied", 
            "forgotten", "heavy-hearted", "hurtful", "inferior", "injured", "lonely", "lost", "melancholic", 
            "pained", "powerless", "regretful", "suffering", "tense", "unappreciated", "unwanted", "upset", 
            "vindictive", "worthless", "worried", "abandoned", "blaming", "discouraged", "frustrated", 
            "intolerant", "jealous", "moody", "pained", "stressed", "terrified", "troubled", "unappreciated", 
            "unworthy", "useless", "vulnerable", "worried", "exasperated", "aggravated", "aggrieved", "betrayed", 
            "broken-hearted", "distrustful", "dishonored", "guilty", "heavy-hearted", "hurt", "ignored", 
            "insecure", "insulted", "irrelevant", "lonely", "misunderstood", "needy", "panicked", "pathetic", 
            "put-down", "resentful", "shattered", "unimportant", "vindictive", "withdrawn", "blamed", 
            "inhibited", "stigmatized", "stressed", "tense", "tired", "unappreciated", "unimportant", "unworthy", 
            "vulnerable", "weak", "worthless", "worn-out", "die", " ...", "not"
        );

        // Tokenize the text to evaluate each word
        String[] tokens = tokenizeText(text);

        // Loop through each token and adjust sentiment score
        for (String token : tokens) {
            if (positiveWords.contains(token.toLowerCase())) {
                sentimentScore += 2; // Positive words add to the score
            } else if (negativeWords.contains(token.toLowerCase())) {
                sentimentScore -= 2; // Negative words subtract from the score
            }
        }

        // Determine overall sentiment based on score thresholds
        if (sentimentScore >= 2) {
            return "Positive";
        } else if (sentimentScore <= -2) {
            return "Negative";
        }
        return "Neutral";
    }

    public static int getSentimentScore()
    {
        return sentimentScore;
    }

    /**
     * Tokenizes text into individual words or tokens using OpenNLP's tokenizer model.
     * 
     * @param text The text to tokenize.
     * @return An array of String tokens (words).
     */
    public String[] tokenizeText(String text) {
        return tokenizer.tokenize(text);
    }

    /**
     * Detects sentences in the text using OpenNLP's sentence detector model.
     * 
     * @param text The text to split into sentences.
     * @return An array of sentences in the text.
     */
    public String[] detectSentences(String text) {
        return sentenceDetector.sentDetect(text);
    }
}