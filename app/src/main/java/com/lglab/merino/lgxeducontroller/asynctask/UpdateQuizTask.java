package com.lglab.merino.lgxeducontroller.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.lglab.merino.lgxeducontroller.games.quiz.Quiz;
import com.lglab.merino.lgxeducontroller.legacy.data.POIsProvider;

public class UpdateQuizTask extends AsyncTask<Void, Integer, Void> {
    public static final String TAG = UpdateQuizTask.class.getSimpleName();
    private Quiz quiz;

    public UpdateQuizTask(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.i(TAG, "doInBackground: " + quiz.pack().toString());
            POIsProvider.updateQuizById((int) quiz.id, quiz.pack().toString());
        } catch (Exception e) {
            cancel(true);
        }
        return null;
    }
}
