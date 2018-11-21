package goodline.info.cardbag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardVH> {

    private int[] cards;
    private LayoutInflater inflater;

    public ImageCardAdapter(Context context, int[] cardList) {
        this.cards = cardList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageCardVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_card, viewGroup, false);
        return new ImageCardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCardVH imageCardVH, int position) {
        imageCardVH.imageFront.setImageResource(cards[position]);
    }

    @Override
    public int getItemCount() {
        return cards.length;
    }
}
