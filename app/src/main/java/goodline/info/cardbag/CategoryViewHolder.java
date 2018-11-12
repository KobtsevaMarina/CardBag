package goodline.info.cardbag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCategoryName;


    public CategoryViewHolder(@NonNull View itemView)  {
        super(itemView);
        tvCategoryName = itemView.findViewById(R.id.tvCategoryName);

    }
}
