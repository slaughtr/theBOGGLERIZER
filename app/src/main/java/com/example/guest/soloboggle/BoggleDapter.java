package com.example.guest.soloboggle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BoggleDapter extends BaseAdapter {
    private Context mContext;
    private String[] mConsonants;
    private String[] mVowels;
    private static List<String> mPlayerLetters = new ArrayList<>();

    public BoggleDapter (Context context, String[] consonants, String[] vowels) {
        this.mContext = context;
        this.mConsonants = consonants;
        this.mVowels = vowels;
    }

    @Override
    public int getCount() {
        getRandomPlayerLetters();
        return 12;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void getRandomPlayerLetters() {
        while (mPlayerLetters.size() < 9) {
            Random ran = new Random();
            int consonantRand = ran.nextInt(21);
            mPlayerLetters.add(mConsonants[consonantRand]);
        }

        while (mPlayerLetters.size() < 13) {
            Random ran = new Random();
            int vowelRand = ran.nextInt(6);
            mPlayerLetters.add(mVowels[vowelRand]);
        }
    }

    static List<String> getPlayerLetters() {
        return mPlayerLetters;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.letter_grid, null);


            // pull views
            TextView letterView = (TextView) gridView
                    .findViewById(R.id.grid_item_letter);

            // set values into views
            letterView.setText(mPlayerLetters.get(position));  // using dummy data for now
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
