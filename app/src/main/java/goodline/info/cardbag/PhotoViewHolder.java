package goodline.info.cardbag;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCardFront;
    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivCardFront = itemView.findViewById(R.id.ivImageCardFront);

    }
}
