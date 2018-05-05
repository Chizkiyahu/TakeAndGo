package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.chizkiyahuandchaskyh.takeandgo.R;

/**
 * TODO: document your custom view class.
 */
public class CustomFormFieldView extends RelativeLayout {

    public interface CustomFormFieldValidation {
        public Pair<Boolean,String> validateValue(String value);
    }

    private String value;
    private String label;
    private String placeholder;
    private int inputType;
    private Boolean editable;

    private EditText textView;
    private TextInputLayout layout;
    private View rootView;

    private CustomFormFieldValidation validator;

    public CustomFormFieldView(Context context) {
        super(context);
        init(context);
    }

    public CustomFormFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    protected void init(Context context) {
        rootView = inflate(context, getLayout(), this);

        textView = rootView.findViewById(R.id.textView);
        layout = rootView.findViewById(R.id.layout);

        if(textView != null) {
            textView.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        textView.setHint(placeholder);
                        layout.setErrorEnabled(false);
                        layout.setError(null);
                    } else {
                        if (validator != null) {
                            Pair<Boolean, String> res = validator.validateValue(getValue());
                            if (!res.first) {
                                layout.setErrorEnabled(true);
                                layout.setError(res.second);
                            }
                        }
                        textView.setHint("");
                    }
                }
            });
        }
    }

    public String getValue() {
        value = textView.getText().toString();
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        textView.setText(value);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        layout.setHint(label);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
        textView.setInputType(inputType);
    }

    public CustomFormFieldValidation getValidator() {
        return validator;
    }

    public void setValidator(CustomFormFieldValidation validator) {
        this.validator = validator;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
        textView.setEnabled(editable);
    }

    protected int getLayout() {
        return R.layout.view_custom_form_field;
    }
}