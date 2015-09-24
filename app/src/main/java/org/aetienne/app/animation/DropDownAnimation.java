package org.aetienne.app.animation;

import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Transformation;

/**
 * Created by Adrien on 24/09/2015.
 */
public class DropDownAnimation extends Animation {
    private final int targetHeight;
    private final View view;
    private final boolean down;

    public DropDownAnimation(final View view, int targetHeight, boolean down) {
        this.view = view;
        this.targetHeight = targetHeight;
        final boolean d = this.down = down;

        this.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationEnd(
                    Animation animation) {
            }

            @Override
            public void onAnimationRepeat(
                    Animation animation) {
            }

            @Override
            public void onAnimationStart(
                    Animation animation) {
                if(d){
                    view.setVisibility(View.VISIBLE);
                }
            }

        });
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;

        if (down) {
            newHeight = (int) (targetHeight * interpolatedTime);
        } else {
            newHeight = (int) (targetHeight * (1 - interpolatedTime));
        }
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }


}
