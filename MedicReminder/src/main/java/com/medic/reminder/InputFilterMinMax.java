package com.medic.reminder;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter{

    private int min, max;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) throws NumberFormatException {

        int input = Integer.parseInt(dest.toString() + source.toString());

        if (isInRange(min, max, input)) {
            return null;
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

}
