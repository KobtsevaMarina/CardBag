package goodline.info.cardbag;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

import static io.realm.Realm.getDefaultInstance;

public class MainActivity extends AppCompatActivity  {
    private RelativeLayout noCard;
    private RecyclerView rvCardList;
    private CardsAdapter adapter;
    private List<Card> cardList;
    private static final int ADD_CARD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        noCard = (RelativeLayout) findViewById(R.id.rlNoCard);
        rvCardList = (RecyclerView) findViewById(R.id.rvCardList);

        noCard.setVisibility(View.VISIBLE);
        rvCardList.setLayoutManager(new LinearLayoutManager(this));

        loadCardList();
        rvCardList.setAdapter(adapter);
    }
    private void loadCardList(){
        RealmResults<CardRealm> result = getDefaultInstance().where(CardRealm.class).findAll();
        if (result.isEmpty()) {
            // Карточек нет
            showCardList(false);
            return;
        }
        cardList = new ArrayList<>();
        cardList = CardMapper.map2DataList(result);
        adapter = new CardsAdapter(this, cardList);
        showCardList(true);
    }
    private RealmResults<CardRealm> getCardList() {
        return getDefaultInstance().where(CardRealm.class).findAll();
    }
    public void showCardList(boolean enableList) {
        rvCardList.setVisibility(enableList ? View.VISIBLE : View.GONE);
        noCard.setVisibility(enableList? View.GONE: View.VISIBLE);
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         try{
            if(data == null||data.getExtras()==null||resultCode!=RESULT_OK)
                return;

            Bundle arguments = data.getExtras();
            Card card=(Card) arguments.getParcelable(Card.class.getSimpleName());

            if (arguments == null||card==null)
                return;

            else {
                showCardList(true);
                try {
                    adapter.insertItem(card);
                }
                catch (Exception exception){
                    Toast.makeText(this,"Не удалось загрузить данные",Toast.LENGTH_LONG);
                    Log.e("Error: onActivityResult", exception.getMessage());
                    exception.printStackTrace();
                }}
        }
        catch (Exception exception){
            Toast.makeText(this,"Не удалось загрузить данные",Toast.LENGTH_LONG);
            Log.e("Error: onActivityResult", exception.getMessage());
            exception.printStackTrace();
        }
    }
    public void btnAddCardClick(View view) {
        Intent intent = new Intent(this, AddCardActivity.class);
        startActivityForResult(intent, ADD_CARD);
    }



}

