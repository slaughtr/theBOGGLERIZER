package com.example.guest.soloboggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class GameActivity extends AppCompatActivity {
    GridView gridView;
    String[] consonants = new String[] {
            "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    String[] vowels = new String[] { "A", "E", "I", "O", "U", "Y"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridView = (GridView) findViewById(R.id.lettersGrid);
        gridView.setAdapter(new BoggleDapter(this, consonants, vowels));
}
}
