package com.example.guest.soloboggle;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    GridView gridView;
    String[] consonants = new String[] {
            "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    String[] vowels = new String[] { "A", "E", "I", "O", "U", "Y"};
    List<String> mPlayerLetters = BoggleDapter.getPlayerLetters();
    List<String> mPlayerWord = new ArrayList<>();
    List<String> mCompletedWords = new ArrayList<>();
    ArrayList<String> mCompleteArray = new ArrayList<>();
    private TextView mEnteredLetters;
    private ListView mCompletedWordList;

    @Bind(R.id.wordClear) Button mWordClear;
    @Bind(R.id.wordSubmit) Button mWordSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mEnteredLetters = (TextView) findViewById(R.id.enteredLetters);
        mCompletedWordList = (ListView) findViewById(R.id.completedWordList);


        ButterKnife.bind(this);
        gridView = (GridView) findViewById(R.id.lettersGrid);
        gridView.setAdapter(new BoggleDapter(this, consonants, vowels));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                String selectedItem = mPlayerLetters.get(position);
                Log.d("gameActivity", String.format("value : %s", selectedItem));
                mPlayerWord.add(selectedItem);
                if (mPlayerWord.size() < 13) {
                    mEnteredLetters.setText(TextUtils.join("", mPlayerWord));
                } else {
                    Toast.makeText(GameActivity.this, "You have reached the max character " +
                            "count", Toast.LENGTH_LONG).show();
                }

            }
        });
        mWordClear.setOnClickListener(this);
        mWordSubmit.setOnClickListener(this);

        new CountDownTimer(90000, 1) {

            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                long millis = (millisUntilFinished % 60000) % 1000;
                setTitle(String.format("Time left: %d:%d.%d", minutes, seconds,
                        millis));
            }

            public void onFinish() {
                mCompleteArray.addAll(mCompletedWords);
                Intent intent = new Intent(GameActivity.this, resultsActivity.class);
                intent.putStringArrayListExtra("mCompletedWords", mCompleteArray);
            }
        }.start();

    }

    @Override
    public void onClick(View v) {
        if (v == mWordClear) {
            mEnteredLetters.setText("");
            mPlayerWord.clear();
        }

        if (v == mWordSubmit) {
            if (mPlayerWord.size() < 3) {
                Toast.makeText(GameActivity.this, "More Letters required", Toast.LENGTH_LONG).show();
                mEnteredLetters.setText("");
                mPlayerWord.clear();
            } else {
                mCompletedWords.add(TextUtils.join("", mPlayerWord));
                Log.d("mCompletedWords", mCompletedWords.toString());
                mEnteredLetters.setText("");
                mPlayerWord.clear();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout
                        .simple_list_item_2, android.R.id.text1, mCompletedWords) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);

                        ListItemShow.setTextColor(Color.parseColor("#03A9F4"));

                        return view;
                    }
                };
                mCompletedWordList.setAdapter(adapter);

            }
        }

    }

}
