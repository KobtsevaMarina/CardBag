package goodline.info.cardbag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardsVH extends RecyclerView.ViewHolder {
    public TextView tvNameCard;
    public TextView tvCategory;
    public TextView tvSale;
    public RecyclerView rvImageCard;

    public CardsVH(@NonNull View itemView) {
        super(itemView);
        tvNameCard=itemView.findViewById(R.id.tvNameCard);
        tvCategory=itemView.findViewById(R.id.tvCategory);
        tvSale=itemView.findViewById(R.id.tvSale);
        rvImageCard=itemView.findViewById(R.id.rvCardImage);
    }
}
