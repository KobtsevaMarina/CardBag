package goodline.info.cardbag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ImageCardVH extends RecyclerView.ViewHolder {
    public ImageView imageFront;

    public ImageCardVH(@NonNull View itemView) {
        super(itemView);
        this.imageFront = itemView.findViewById(R.id.ivImageCardFront);
    }
}
