package goodline.info.cardbag;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private TextView tvNameCard;
    private TextView tvCategory;
    private TextView tvSale;
    private RelativeLayout noCard;
    private RecyclerView rvCardList;
    private CardsAdapter adapter;
    private List<Card> cardList;
    private static final int ADD_CARD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        tvNameCard = findViewById(R.id.tvNameCard);
        tvCategory = findViewById(R.id.tvCategory);
        tvSale = findViewById(R.id.tvSale);

        noCard = (RelativeLayout) findViewById(R.id.rlNoCard);
        rvCardList = (RecyclerView) findViewById(R.id.rvCardList);

        noCard.setVisibility(View.VISIBLE);
        cardList = new ArrayList<>();
        rvCardList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardsAdapter(this, cardList);
        // устанавливаем для списка адаптер
        rvCardList.setAdapter(adapter);
    }

    public void isCard(boolean isCard) {
        if(!isCard){
            rvCardList.setVisibility(View.GONE);
            noCard.setVisibility(View.VISIBLE);
        }
        else {
            noCard.setVisibility(View.GONE);
            rvCardList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        else try{
            Bundle arguments = data.getExtras();
            Card card=(Card) arguments.getParcelable(Card.class.getSimpleName());

            if (arguments == null||card==null||resultCode!=RESULT_OK) {
                return;
            }
            else {
                isCard(true);
                try {
                    adapter.insertItem(card);
                }
                catch (Exception ex){
                    Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
                }}
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
        }
    }
    public void btnAddCardClick(View view) {
        Intent intent = new Intent(this, AddCardActivity.class);
        startActivityForResult(intent,ADD_CARD);

    }
}

