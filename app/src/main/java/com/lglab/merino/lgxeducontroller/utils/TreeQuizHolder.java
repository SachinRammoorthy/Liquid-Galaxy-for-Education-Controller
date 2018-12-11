package com.lglab.merino.lgxeducontroller.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lglab.merino.lgxeducontroller.R;
import com.lglab.merino.lgxeducontroller.activities.CreateQuestionActivity;
import com.lglab.merino.lgxeducontroller.asynctask.InsertQuizTask;
import com.lglab.merino.lgxeducontroller.asynctask.RemoveQuizTask;
import com.lglab.merino.lgxeducontroller.asynctask.UpdateQuizTask;
import com.lglab.merino.lgxeducontroller.games.quiz.Quiz;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Albert Merino on 02/10/18.
 */
public class TreeQuizHolder extends TreeNode.BaseNodeViewHolder<TreeQuizHolder.IconTreeItem> {
    public static final String TAG = TreeQuizHolder.class.getSimpleName();
    private ImageView arrowView;

    public TreeQuizHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, final IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.treeview_node_game, null, false);
        TextView tvValue = view.findViewById(R.id.node_value);
        tvValue.setText(value.text);


        final ImageView iconView = view.findViewById(R.id.imageIcon);
        iconView.setImageDrawable(context.getResources().getDrawable(value.icon));

        arrowView = view.findViewById(R.id.arrow_icon);
        ImageView addPOIButton = view.findViewById(R.id.btn_addPOI);
        ImageView editButton = view.findViewById(R.id.btn_edit);
        ImageView deleteButton = view.findViewById(R.id.btn_delete);

        switch (value.type) {
            case SUBJECT:
                addPOIButton.setVisibility(View.VISIBLE);
                addPOIButton.setOnClickListener(view1 -> {
                    showToast("Add Game");
                    showAddGameDialog();
                });
                break;
            case GAME:
                addPOIButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);

                addPOIButton.setOnClickListener(view12 -> {
                    showToast("Add Question ");
                    Intent intent = new Intent(context, CreateQuestionActivity.class);
                    intent.putExtra("quiz", value.quiz);
                    intent.putExtra("type", CreateQuestionActivity.UpdateNew.NEW);
                    context.startActivity(intent);
                });
                deleteButton.setOnClickListener(view12 -> {
                    showToast("Deleted Game");
                    new RemoveQuizTask(value.quiz).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    getTreeView().removeNode(node);
                });
                break;
            case QUESTION:
                int id = node.getId() - 1;
                arrowView.setVisibility(View.GONE);
                editButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);

                if (node.getId() % 2 == 0) {
                    view.setBackgroundColor(context.getResources().getColor(R.color.white));
                }

                editButton.setOnClickListener(view12 -> {
                    showToast("Edit question");
                    Intent intent = new Intent(context, CreateQuestionActivity.class);
                    intent.putExtra("quiz", value.quiz);
                    intent.putExtra("index", id);
                    intent.putExtra("type", CreateQuestionActivity.UpdateNew.UPDATE);
                    context.startActivity(intent);
                });
                deleteButton.setOnClickListener(view12 -> {

                    showToast("Deleted Question " + id);
                    value.quiz.questions.remove(id);
                    new UpdateQuizTask(value.quiz).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    getTreeView().removeNode(node);
                });
                break;
        }
        return view;
    }

    private void showAddGameDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Add game");
        alertDialog.setMessage("Game title");

        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(android.R.string.yes,
                (dialog, which) -> {
                    Quiz quiz = new Quiz();
                    quiz.name = input.getText().toString();
                    showToast("Adding game " + quiz.name);
                    new InsertQuizTask(quiz).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                });

        alertDialog.setNegativeButton(android.R.string.cancel, null);

        alertDialog.show();

    }

    private void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.ic_keyboard_arrow_down_black_36dp : R.drawable.ic_keyboard_arrow_right_black_36dp));
    }

    public enum TreeQuizType {SUBJECT, GAME, QUESTION, NONE}

    public static class IconTreeItem {
        public String text;
        public int icon;
        public long id;
        public TreeQuizType type;
        public Quiz quiz;

        public IconTreeItem(int icon, String text, long id, TreeQuizType type) {
            this(icon, text, id, type, null);
        }

        public IconTreeItem(int icon, String text, long id, TreeQuizType type, Quiz quiz) {
            this.icon = icon;
            this.text = text;
            this.type = type;
            this.id = id;
            this.quiz = quiz;
        }
    }


}
