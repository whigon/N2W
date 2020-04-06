package com.example.n2w;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

public class Translator {
    private static final String TAG = "Debug";
    // Application context(MainActivity)
    private Context context;
    // Set of numbers
    private Set<String> keySet;
    // Map of the number and words
    private JSONObject map;
    // This array is used to store the numbers that are split from a digit
    private ArrayList<String> numbers;
    // This array is used to store words corresponding to the number
    private ArrayList<JSONArray> words;

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
     * @return a set of words
     */
    public String translate(String digits) {
        // New a space
        numbers = new ArrayList<>();
        words = new ArrayList<>();

        digits = digits.replaceAll("\\s", "");
        splitNumber(digits);

        return getResult();
    }

    /**
     * Randomly generate a set of words according to numbers.
     *
     * @return a set of words
     */
    public String getResult() {
        StringBuilder str = new StringBuilder(" ");

        for (int i = 0; i < words.size(); i++) {
            str.append(randomlyPick(words.get(i))).append(" ");
        }
        return str.toString();
    }

    /**
     * Split digits into numbers and store the corresponding words.
     *
     * @param input digits
     */
    private void splitNumber(String input) {
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
                    words.add((JSONArray) (map.get(subNumber)));
                    // Find next
                    splitNumber(input.substring(i));

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
    public String randomlyPick(JSONArray array) {
        int random = (int) (Math.random() * array.size());

        return array.getString(random);
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
     * @return A set of JsonArray which contains the words
     */
    public ArrayList<JSONArray> getWords() {
        return words;
    }

    /**
     * Load the data file.
     */
    private void loadFile() {
        // Access data in the res/raw directory, using context
        InputStream is = context.getResources().openRawResource(R.raw.data);
        String data = "";
        String str;

        try {
            // Read data
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while ((str = reader.readLine()) != null) {
                data += str;
            }

            // Store the map
            map = (JSONObject) JSON.parse(data);
            // Store the key set
            keySet = map.keySet();
            Log.d(TAG, "loadFile: Success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
