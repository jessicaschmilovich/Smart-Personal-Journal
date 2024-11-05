# Smart-Personal-Journal

The Smart Personal Journal is a Java-based application that allows users to create journal entries, analyzes the sentiment of their entries, provides motivational quotes, and logs the user's mood based on sentiment. This application leverages OpenNLP for natural language processing (NLP) to detect sentences and tokenize text for sentiment analysis.

Features:

- Journal Entry Creation: Users can input journal entries, which are saved with a timestamp and analyzed for sentiment.
- Sentiment Analysis: Uses OpenNLP models to tokenize text and determine the sentiment of each entry (Positive, Negative, or Neutral).
- Motivational Quotes: After each entry, the app provides a random motivational quote, which is also stored with the journal entry.
- Mood Logging: Based on the sentiment, the app logs the user's mood in a separate file, enabling users to track their mood over time.

Project Structure:

lib: Contains required OpenNLP JAR files

models: Contains NLP model files used for sentiment analysis
  en-pos-maxent.bin
  en-sent.bin
  en-token.bin

journal_entries.txt: Stores journal entries with date, sentiment, and motivational quote

mood_log.txt: Stores mood log with sentiment analysis results over time

JournalEntry.java: Class representing a journal entry with content, sentiment, and quote

MainApp.java: Contains the main method to initiate the Smart Personal Journal

MoodLog.java: Logs mood (sentiment) results to a separate file

Quotes.java: Generates random motivational quotes

SentimentAnalyzer.java: Analyzes sentiment using OpenNLP
