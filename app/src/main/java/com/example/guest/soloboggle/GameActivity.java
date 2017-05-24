package com.example.guest.soloboggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class GameActivity extends AppCompatActivity {
    GridView gridView;
    String[] consonants = new String[] {
            "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    String[] vowels = new String[] { "A", "E", "I", "O", "U", "Y"};
    List<String> mPlayerLetters = BoggleDapter.getPlayerLetters();
    List<String> mPlayerWord = new ArrayList<>();
    private TextView mEnteredLetters;

    @Bind(R.id.grid_item_letter) TextView mGridItemLetters;
//    @Bind(R.id.enteredLetters) TextView mEnteredLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mEnteredLetters = (TextView) findViewById(R.id.enteredLetters);

        gridView = (GridView) findViewById(R.id.lettersGrid);
        gridView.setAdapter(new BoggleDapter(this, consonants, vowels));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                String selectedItem = mPlayerLetters.get(position);
                Log.d("gameActivity", String.format("value : %s", selectedItem));
                mPlayerWord.add(selectedItem);
                mEnteredLetters.setText(TextUtils.join("", mPlayerWord));

            }
        });


    }

}
