package com.example.n2w;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

public class Translator {
    private static final String TAG = "Translator";
    // Application context(MainActivity)
    private Context context;
    // Set of numbers
    private Set<String> keySet;
    // Map of the number and words
    private JSONObject map;
    // This array is used to store the numbers that are split from a digit
    private ArrayList<String> numbers;
    // This array is used to store words corresponding to the number
    private ArrayList<ArrayList<String>> wordSet;

    /**
     * Constructor: load the data file.
     */
    public Translator(Context context) {
        this.context = context;
        loadFile();
    }

    /**
     * Translate digits into a set of words.
     *
     * @param digits input
     */
    public void translate(String digits) {
        // New a space
        numbers = new ArrayList<>();
        wordSet = new ArrayList<>();
        // Remove space and dot
        digits = digits.replaceAll("[\\s.]", "");
        // Split number and translate them
        translateNumber(digits);
    }

    /**
     * Randomly generate a set of words according to numbers.
     *
     * @return a set of words
     */
    public String getResult() {
        StringBuilder str = new StringBuilder(" ");

        for (int i = 0; i < wordSet.size(); i++) {
            str.append(randomlyPick(wordSet.get(i))).append(" ");
        }
        return str.toString();
    }

    /**
     * Split digits into numbers, translate them and store the corresponding words.
     *
     * @param input digits
     */
    private void translateNumber(String input) {
        int length = input.length();
        String subNumber;

        if (length != 0) {
            // Check from the longest substring
            for (int i = length; i > 0; i--) {
                subNumber = input.substring(0, i);

                if (isExist(subNumber)) {
                    // Store split-number
                    numbers.add(subNumber);
                    // Store corresponding words
                    wordSet.add((ArrayList<String>) JSONObject.parseArray(map.get(subNumber).toString(), String.class));
                    // Find next
                    translateNumber(input.substring(i));

                    break;
                }
            }
        }
    }


    /**
     * Randomly pick a word from a set of words.
     * Some numbers can be represented by more than one word.
     *
     * @param array A set of words that can represent a number
     * @return A randomly-picked word
     */
    private String randomlyPick(ArrayList<String> array) {
        int random = (int) (Math.random() * array.size());

        return array.get(random);
    }

    /**
     * Check whether a number can be represented by a word.
     *
     * @param number A sub-string of digits
     * @return True or False
     */
    private boolean isExist(String number) {
        return keySet.contains(number);
    }

    /**
     * Return the set of numbers.
     * Each number is the longest substring of the input digits, which can be represented by at least one word
     *
     * @return A set of numbers
     */
    public ArrayList<String> getNumbers() {
        return numbers;
    }

    /**
     * Return the set of words that can be used to represented numbers
     *
     * @return An array which contains the words
     */
    public ArrayList<ArrayList<String>> getWordSet() {
        return wordSet;
    }

    /**
     * Load the data file.
     */
    private void loadFile() {
        // Access data in the res/raw directory, using context
        InputStream is = context.getResources().openRawResource(R.raw.data);
        StringBuilder data = new StringBuilder();
        String str;

        try {
            // Read data
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while ((str = reader.readLine()) != null) {
                data.append(str);
            }
            // Store the map
            map = (JSONObject) JSON.parse(data.toString());
            // Store the key set
            keySet = map.keySet();
            Log.d(TAG, "loadFile: Success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
