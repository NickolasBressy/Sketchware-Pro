package com.besome.sketch.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besome.sketch.tools.QuizBoard;
import com.sketchware.remod.R;

import mod.hey.studios.util.Helper;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BuildingDialog extends BottomSheetDialog {

    private final TextView tvProgress;
    private final QuizBoard quizBoard;
    private boolean cancelOnBackPressed;

    public BuildingDialog(Context context) {
        super(context, R.style.progress);
        setContentView(R.layout.build_progress_msg_box);

        LinearLayout quizLayout = findViewById(R.id.layout_quiz);
        quizBoard = new QuizBoard(context);
        quizLayout.addView(quizBoard, 0);
        setTitle(Helper.getResString(R.string.common_message_progress));
        tvProgress = findViewById(R.id.tv_progress);
        tvProgress.setText(Helper.getResString(R.string.common_message_loading));
        
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    public void setProgress(String text) {
        tvProgress.setText(text);
    }

    public void setIsCancelableOnBackPressed(boolean cancelable) {
        cancelOnBackPressed = cancelable;
    }

    public boolean isCancelableOnBackPressed() {
        return cancelOnBackPressed;
    }

    @Override
    public void dismiss() {
        quizBoard.a();
        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        if (!cancelOnBackPressed) {
            super.onBackPressed();
        }
    }
}
