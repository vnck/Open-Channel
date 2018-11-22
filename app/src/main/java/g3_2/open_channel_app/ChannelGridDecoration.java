package g3_2.open_channel_app;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ChannelGridDecoration extends RecyclerView.ItemDecoration {
    private int largePadding;
    private int smallPadding;

    public ChannelGridDecoration(int largePadding, int smallPadding) {
        this.largePadding = largePadding;
        this.smallPadding = smallPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = smallPadding;
        outRect.right = smallPadding;
        outRect.top = largePadding;
        outRect.bottom = largePadding;
    }
}