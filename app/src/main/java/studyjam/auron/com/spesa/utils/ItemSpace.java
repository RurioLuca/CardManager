package studyjam.auron.com.spesa.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luca on 4/4/16.
 */
public class ItemSpace extends RecyclerView.ItemDecoration {
    private int space;

    public ItemSpace(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }


    }

}


