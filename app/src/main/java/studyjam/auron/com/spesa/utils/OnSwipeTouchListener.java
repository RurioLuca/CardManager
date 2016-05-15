package studyjam.auron.com.spesa.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by luca on 4/26/16.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {


    private final GestureDetector gestureDetector;
    private Swiped swiped;


    public OnSwipeTouchListener(Context ctx, Swiped swiped) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        this.swiped = swiped;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    public interface Swiped {
        void onSwipeRight();

        void onSwipeLeft();

        void onSwipeTop();

        void onSwipeBottom();
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            swiped.onSwipeRight();
                        } else {
                            swiped.onSwipeLeft();
                        }
                    }
                    result = true;
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        swiped.onSwipeBottom();
                    } else {
                        swiped.onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
}
