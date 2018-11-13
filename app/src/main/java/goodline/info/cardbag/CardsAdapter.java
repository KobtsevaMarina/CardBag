package goodline.info.cardbag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsVH>{
    private List<Card> cards;
    private LayoutInflater inflater;

    public CardsAdapter(Context context, List<Card> cards) {
        this.cards = cards;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CardsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_cards, viewGroup, false);
        return new CardsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsVH cardsVH, int position) {
        final Card cardItem = cards.get(position);
        cardsVH.tvNameCard.setText(cardItem.getNameCard());
        cardsVH.tvCategory.setText(cardItem.getCategory());
        cardsVH.tvSale.setText(cardItem.getSale());
    }
    public void insertItem(Card item) {
        // Добавить экземпляр класса ChatItem в коллекцию объектов сразу после
        //первого элемента.
        cards.add(item);
        // Обновить адаптер. Вызывав этот метод, в списке recyclerView будет
        ///отрисовано добавление нового элемента
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
