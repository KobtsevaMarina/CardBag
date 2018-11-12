package goodline.info.cardbag;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories;
    private LayoutInflater inflater;
    private onItemClickListener clickListener;

    public CategoryListAdapter(Context context, List<Category> categories, onItemClickListener clickListener) {
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
    }

    public interface onItemClickListener {
        void onItemClick(Category item);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_category, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        final Category categoryItem = categories.get(position);
        categoryViewHolder.tvCategoryName.setText(categoryItem.getName());
        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(categoryItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
