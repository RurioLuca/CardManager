package studyjam.auron.com.spesa.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by luca on 3/22/16.
 */
public class ItemSpaceGrid extends RecyclerView.ItemDecoration {


    private static int LEFT = 0;
    private static int CENTER = 1;
    private static int RIGHT = 2;


    private static String TAG = "ItemSpaceGrid";
    private int space;
    private int row;
    private Rect outRect;
    private int current_pos;
    private int spanIndex;

    public ItemSpaceGrid(int space, int row) {
        this.space = space;
        this.row = row;
    }

    @Override
    public void getItemOffsets(Rect outRects, View view, RecyclerView parent, RecyclerView.State state) {
        this.outRect = outRects;
        current_pos = parent.getChildLayoutPosition(view);
        spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();


        if (spanIndex == 0) {

            setSpace(outRect, space, space, space, space);

        } else {

            setSpace(outRect, space, space, 0, space);

        }


    }


    private void setSpace(Rect outRect, int top, int bottom, int left, int right) {

        int marginRight = right;
        if (current_pos + 1 != row)
            marginRight = right / 2;

        if (current_pos < row) {
            outRect.top = top;
            outRect.right = right;
            outRect.left = left;
            outRect.bottom = bottom;
        } else {
            outRect.top = 0;
            outRect.right = right;
            outRect.left = left;
            outRect.bottom = bottom;
        }


    }

}
