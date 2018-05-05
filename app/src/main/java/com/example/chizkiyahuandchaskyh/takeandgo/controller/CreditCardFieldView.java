package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;

/**
 * TODO: document your custom view class.
 */
public class CreditCardFieldView extends CustomFormFieldView {

    private ImageButton deleteBtn;
    private ImageButton addBtn;

    public interface CreditCardFieldDelegate {

    }

    public CreditCardFieldView(Context context) {
        super(context);
    }

    public CreditCardFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        deleteBtn = findViewById(R.id.remove_btn);
        addBtn = findViewById(R.id.new_btn);
    }

    @Override
    protected int getLayout() {
        return R.layout.view_credit_card_field;
    }

    protected void onActionButtonClick(View view) {
        if(showNewButton()) {

        }
    }

    private Boolean showNewButton() {
        return getValue().length() > 0;
    }

    private void refreshButtonState() {
        if(showNewButton()) {
            addBtn.setVisibility(VISIBLE);
            deleteBtn.setVisibility(GONE);
        }
        else {
            addBtn.setVisibility(GONE);
            deleteBtn.setVisibility(VISIBLE);
        }
    }
}
