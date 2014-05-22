package com.dijoux.marc.todolist;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Cram on 19/05/2014.
 */
public class PriorityFilter implements InputFilter {

    private int min, max;

    public PriorityFilter() {
        this.min = 0;
        this.max = 4;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}