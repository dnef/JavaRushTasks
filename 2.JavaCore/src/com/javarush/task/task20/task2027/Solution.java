package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'a', 'g', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same");
        for (Word word : words)
            System.out.println(word);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    public static ArrayList<Pair> findFirstLetters(int[][] crossword, char letter) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                if (crossword[i][j] == letter)
                    pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }
    public static ArrayList<Object> findSecondLetters(Pair position, char letter, int[][] crossword) {
        ArrayList<Object> result = new ArrayList<>();
        // на одной вертикали
        try {
            if (letter == crossword[position.x + 1][position.y]) {
                result.add(new Pair(position.x + 1, position.y));
                result.add("vertical down");
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (letter == crossword[position.x - 1][position.y]) {
                result.add(new Pair(position.x - 1, position.y));
                result.add("vertical up");
            }
        } catch (IndexOutOfBoundsException e) {}
        // на одной горизонтали
        try {
            if (letter == crossword[position.x][position.y + 1]) {
                result.add(new Pair(position.x, position.y + 1));
                result.add("horizontal right");
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (letter == crossword[position.x][position.y - 1]) {
                result.add(new Pair(position.x, position.y - 1));
                result.add("horizontal left");
            }
        } catch (IndexOutOfBoundsException e) {}
        // на одной диагонали
        try {
            if (letter == crossword[position.x + 1][position.y + 1]) {
                result.add(new Pair(position.x + 1, position.y + 1));
                result.add("diagonal right down");
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (letter == crossword[position.x - 1][position.y - 1]) {
                result.add(new Pair(position.x - 1, position.y - 1));
                result.add("diagonal left up");
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (letter == crossword[position.x + 1][position.y - 1]) {
                result.add(new Pair(position.x + 1, position.y - 1));
                result.add("diagonal left down");
            }
        } catch (IndexOutOfBoundsException e) {}
        try {
            if (letter == crossword[position.x - 1][position.y + 1]) {
                result.add(new Pair(position.x - 1, position.y + 1));
                result.add("diagonal right up");
            }
        } catch (IndexOutOfBoundsException e) {}
        return result;
    }
    public static Pair findOtherLetters(int[][] crossword, char[] otherLetters, Pair secondLetterPosition, int kX, int kY) {
        Pair endOfWord = new Pair(-1, -1);
        int x = secondLetterPosition.x;
        int y = secondLetterPosition.y;
        for (int i = 0; i < otherLetters.length; i++) {
            x += kX;
            y += kY;
            if (x >= crossword.length || x < 0 || y < 0 || y >= crossword[x].length) {
                x = -1;
                y = -1;
                break;
            }
            if (otherLetters[i] != crossword[x][y]) {
                x = -1;
                y = -1;
                break;
            }
        }
        endOfWord.x = x;
        endOfWord.y = y;
        return endOfWord;
    }
    public static Pair wordIsHere(int[][] crossword, char[] otherLetters, Pair secondLetterPosition, String direction) {
        Pair endOfWord = new Pair(-1, -1);
        switch (direction) {
            case "vertical down" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, 1, 0);
                break;
            }
            case "vertical up" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, -1, 0);
                break;
            }
            case "horizontal right" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, 0, 1);
                break;
            }
            case "horizontal left" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, 0, -1);
                break;
            }
            case "diagonal right down" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, 1, 1);
                break;
            }
            case "diagonal left up" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, -1, -1);
                break;
            }
            case "diagonal left down" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, 1, -1);
                break;
            }
            case "diagonal right up" : {
                endOfWord = findOtherLetters(crossword, otherLetters, secondLetterPosition, -1, 1);
                break;
            }
        }
        return endOfWord;
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> wordsList = new ArrayList<>();
        for (String word : words) {
            word = word.toLowerCase().trim();
            if (word.isEmpty()) {
                continue;
            }
            char[] letters = word.toCharArray();
            ArrayList<Pair> firstLetterPositions = findFirstLetters(crossword, letters[0]);
            // если длина слова равна 1 {добавил цикл, чтобы вывести все}
            if (word.length() == 1) {
                for (Pair letter : firstLetterPositions) {
                    Pair firstLetterPosition = letter;
                    Word wordFounded = new Word(word);
                    wordFounded.setStartPoint(firstLetterPosition.y, firstLetterPosition.x);
                    wordFounded.setEndPoint(firstLetterPosition.y, firstLetterPosition.x);
                    wordsList.add(wordFounded);
                }
                continue;
            }
            for (Pair firstLetterPosition : firstLetterPositions) {
                ArrayList<Object> secondLetters = findSecondLetters(firstLetterPosition, letters[1], crossword);
                if (secondLetters.size() != 0) {
                    for (int i = 0; i < secondLetters.size(); i = i + 2) {
                        String direction = (String) secondLetters.get(i + 1);
                        Pair secondLetterPosition = (Pair) secondLetters.get(i);
                        // если длина слова равна 2 {чтобы вернуть вывод только одного, поменять continue на break}
                        if (word.length() == 2) {
                            Word wordFounded = new Word(word);
                            wordFounded.setStartPoint(firstLetterPosition.y, firstLetterPosition.x);
                            wordFounded.setEndPoint(secondLetterPosition.y, secondLetterPosition.x);
                            wordsList.add(wordFounded);
                            continue;
                        }
                        // ищем дальше {чтобы вернуть вывод только одного, поменять continue на break}
                        char[] otherLetters = Arrays.copyOfRange(letters, 2, letters.length);
                        Pair lastLetterPosition = wordIsHere(crossword, otherLetters, secondLetterPosition, direction);
                        if (lastLetterPosition.x != -1) {
                            Word wordFounded = new Word(word);
                            wordFounded.setStartPoint(firstLetterPosition.y, firstLetterPosition.x);
                            wordFounded.setEndPoint(lastLetterPosition.y, lastLetterPosition.x);
                            wordsList.add(wordFounded);
                            continue;
                        }
                    }
                }
            }
        }
        return wordsList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
