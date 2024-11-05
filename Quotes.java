import java.util.ArrayList;

public class Quotes {
    
    private static int index; // Stores the random index for selecting a quote

    /**
     * Returns a random motivational quote based on the sentiment score.
     * If the sentiment score is neutral, positive, or negative, it selects a quote
     * from the corresponding list of quotes.
     * 
     * @return A motivational quote as a String depending on the sentiment score.
     */
    public static String getQuotes() {
        // Check if sentiment score is neutral (between -2 and 2)
        if(SentimentAnalyzer.getSentimentScore() > -2 && SentimentAnalyzer.getSentimentScore() < 2) {
            // Create a list of neutral quotes
            ArrayList<String> neutralQuotes = new ArrayList<String>();
            neutralQuotes.add("What you do today can improve all your tomorrows. – Ralph Marston");
            neutralQuotes.add("Simplicity is the ultimate sophistication. – Leonardo da Vinci");
            neutralQuotes.add("Knowledge is power. – Francis Bacon");
            neutralQuotes.add("A wise man will make more opportunities than he finds. – Francis Bacon");
            neutralQuotes.add("Actions speak louder than words. – Proverb");
            neutralQuotes.add("Well done is better than well said. – Benjamin Franklin");
            neutralQuotes.add("The only constant in life is change. – Heraclitus");
            neutralQuotes.add("Everything has beauty, but not everyone sees it. – Confucius");
            neutralQuotes.add("Courage is grace under pressure. – Ernest Hemingway");
            neutralQuotes.add("The mind is everything. What you think, you become. – Buddha");

            // Generate a random index and return the quote at that index
            index = (int)(Math.random() * neutralQuotes.size());
            return neutralQuotes.get(index);
        }
        
        // Check if sentiment score is positive (2 or greater)
        else if(SentimentAnalyzer.getSentimentScore() >= 2) {
            // Create a list of positive quotes
            ArrayList<String> positiveQuotes = new ArrayList<String>();
            positiveQuotes.add("In a gentle way, you can shake the world. – Mahatma Gandhi");
            positiveQuotes.add("Too often we underestimate the power of a touch, a smile, a kind word, or a listening ear, all of which have the potential to turn a life around. – Leo Buscaglia");
            positiveQuotes.add("With the new day comes new strength and new thoughts. – Eleanor Roosevelt");
            positiveQuotes.add("Do what you can, with what you have, where you are. – Theodore Roosevelt");
            positiveQuotes.add("Wherever you go, no matter what the weather, always bring your own sunshine. – Anthony J. D’Angelo");
            positiveQuotes.add("Keep your face always toward the sunshine—and shadows will fall behind you. – Walt Whitman");
            positiveQuotes.add("You are never too old to set another goal or to dream a new dream. – C.S. Lewis");
            positiveQuotes.add("Success is not final, failure is not fatal: It is the courage to continue that counts. – Winston Churchill");
            positiveQuotes.add("Believe you can and you're halfway there. – Theodore Roosevelt");
            positiveQuotes.add("Happiness is not something ready made. It comes from your own actions. – Dalai Lama");

            // Generate a random index and return the quote at that index
            index = (int)(Math.random() * positiveQuotes.size());
            return positiveQuotes.get(index);
        }
        
        // If sentiment score is negative (less than -2)
        else {
            // Create a list of negative quotes
            ArrayList<String> negativeQuotes = new ArrayList<String>();
            negativeQuotes.add("Hard times may have held you down, but they will not last forever. Keep pushing forward. – Unknown");
            negativeQuotes.add("You have within you right now, everything you need to deal with whatever the world can throw at you. – Brian Tracy");
            negativeQuotes.add("Stars can’t shine without darkness. – D.H. Sidebottom");
            negativeQuotes.add("When everything feels like an uphill struggle, just think of the view from the top. – Unknown");
            negativeQuotes.add("No storm, not even the one in your life, can last forever. – Iyanla Vanzant");
            negativeQuotes.add("You don’t have to control your thoughts. You just have to stop letting them control you. – Dan Millman");
            negativeQuotes.add("The darkest nights produce the brightest stars. – John Green");
            negativeQuotes.add("It’s okay to be a glowstick: Sometimes we have to break before we shine. – Jadah Sellner");
            negativeQuotes.add("You are allowed to be both a masterpiece and a work in progress simultaneously. – Sophia Bush");
            negativeQuotes.add("You’ve survived 100% of your worst days. You’re stronger than you think. – Unknown");

            // Generate a random index and return the quote at that index
            index = (int)(Math.random() * negativeQuotes.size());
            return negativeQuotes.get(index);
        }
    }
}