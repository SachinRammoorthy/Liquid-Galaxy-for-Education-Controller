package com.lglab.merino.lgxeducontroller.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.TextView;

import com.lglab.merino.lgxeducontroller.R;
import com.lglab.merino.lgxeducontroller.games.quiz.QuizManager;
import com.lglab.merino.lgxeducontroller.utils.ResultsAdapter;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = findViewById(R.id.my_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ResultsAdapter adapter = new ResultsAdapter();
        rv.setAdapter(adapter);

        ((TextView) findViewById(R.id.textViewScore)).setText("You have scored " + String.valueOf(QuizManager.getInstance().correctAnsweredQuestionsCount()) + " out of " + String.valueOf(QuizManager.getInstance().getQuiz().questions.size()) + "!");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
            return onSupportNavigateUp();

        return super.onKeyDown(keyCode, event);
    }

}


