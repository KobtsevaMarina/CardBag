package goodline.info.cardbag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardVH> {

    private List<Card> cards;
    private LayoutInflater inflater;

    public ImageCardAdapter(Context context, List<Card> cardList) {
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
        Card cardItem = cards.get(position);
        imageCardVH.imageFront.setImageResource(cardItem.getImageId()[0]);
        imageCardVH.imageBack.setImageResource(cardItem.getImageId()[1]);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
