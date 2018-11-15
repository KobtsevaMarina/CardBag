package goodline.info.cardbag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsVH>{
    private MainActivity mainActivity;
    private List<Card> cards;
    private LayoutInflater inflater;
    private RecyclerView rvImageCard;
    private ImageCardAdapter ivAdapter;
    private Context context;

    public CardsAdapter(Context context, List<Card> cards, RecyclerView recyclerView) {
        this.cards = cards;
        this.inflater = LayoutInflater.from(context);
        mainActivity=(MainActivity) context;
        this.context=context;
        rvImageCard=recyclerView;
    }

    @NonNull
    @Override
    public CardsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_cards, viewGroup, false);
        return new CardsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsVH cardsVH, int position) {
        Card cardItem = cards.get(position);
        cardsVH.tvNameCard.setText(cardItem.getNameCard());
        cardsVH.tvCategory.setText(cardItem.getCategory());
        cardsVH.tvSale.setText(cardItem.getSale());
        rvImageCard.setLayoutManager(new LinearLayoutManager(context));
        ivAdapter = new ImageCardAdapter(context, cards);
        rvImageCard.setAdapter(ivAdapter);
    }
    public void insertItem(Card item) {
        cards.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
