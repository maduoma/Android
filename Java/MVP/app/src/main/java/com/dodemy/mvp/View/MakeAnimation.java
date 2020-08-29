package com.dodemy.mvp.View;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MakeAnimation {
    public static void makeAnimationOnView(int resourceId, Techniques techniques, int duration, int repeat, View view) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(repeat)
                .playOn(view.findViewById(resourceId));

    }

}
