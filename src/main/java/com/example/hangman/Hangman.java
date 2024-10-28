package main.java.com.example.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final int MAX_LIVES = 6;
    private static final String[] WORDS = {"java", "programming", "computer", "hangman", "developer"};
    private String wordToGuess;
    private char[] guessedWord;
    private int lives;
    private List<Character> guessedLetters;

    public Hangman() {
        Random random = new Random();
        wordToGuess = WORDS[random.nextInt(WORDS.length)];
        guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
        lives = MAX_LIVES;
        guessedLetters = new ArrayList<>();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hangman game!");

        while (lives > 0) {
            System.out.println("Word: " + String.valueOf(guessedWord));
            System.out.println("You have " + lives + " lives left.");
            System.out.print("Enter a letter: ");
            char guessedLetter = scanner.nextLine().toLowerCase().charAt(0);

            if (guessedLetters.contains(guessedLetter)) {
                System.out.println("You already tried that letter. Try another.");
                continue;
            }

            guessedLetters.add(guessedLetter);

            if (wordToGuess.indexOf(guessedLetter) >= 0) {
                System.out.println("Correct!");
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guessedLetter) {
                        guessedWord[i] = guessedLetter;
                    }
                }
                if (String.valueOf(guessedWord).equals(wordToGuess)) {
                    System.out.println("Congratulations! You guessed the word: " + wordToGuess);
                    break;
                }
            } else {
                lives--;
                System.out.println("Incorrect. The letter is not in the word.");
                drawHangman();
            }
        }

        if (lives == 0) {
            System.out.println("You lost! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private void drawHangman() {
        switch (lives) {
            case 5 -> System.out.println("""
                    |
                    |
                    |
                    |____
                    """);
            case 4 -> System.out.println("""
                    |
                    |   O
                    |
                    |____
                    """);
            case 3 -> System.out.println("""
                    |
                    |   O
                    |   |
                    |____
                    """);
            case 2 -> System.out.println("""
                    |
                    |   O
                    |  /|
                    |____
                    """);
            case 1 -> System.out.println("""
                    |
                    |   O
                    |  /|\\
                    |____
                    """);
            case 0 -> System.out.println("""
                    |
                    |   O
                    |  /|\\
                    |  / \\
                    |____
                    """);
        }
    }

    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.play();
    }
}
