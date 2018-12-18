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

import io.realm.Realm;
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

        cardList = new ArrayList<>();
        rvCardList.setLayoutManager(new LinearLayoutManager(this));
        cardList = map2DataList(getCardList());

        if (cardList == null || cardList.isEmpty()){
            isCard(false);
        }

        adapter = new CardsAdapter(this, cardList);
        rvCardList.setAdapter(adapter);
    }
    private RealmResults<CardRealm> getCardList() {
        try{
        RealmResults<CardRealm> result = getDefaultInstance().where(CardRealm.class).findAll();
            return  result;
        }
        catch (Exception ex){
            String message = ex.getMessage();
        }
        return  null;
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
        startActivity(intent);
        finish();
    }
    private List<Card> map2DataList(List<CardRealm> realmList) {
        List<Card> cards = new ArrayList<>();
        for (CardRealm cardRealm : realmList) {
            Card card = new Card (
                    cardRealm.getId(),
                    cardRealm.getNameCard(),
                    categoryMap2Data(cardRealm.getCategory()),
                    cardRealm.getSale(),
            (ArrayList) photoMap2Data(cardRealm.getPhotoList())
            );
            cards.add(card);
        }
        return cards;
    }

    private List<Photo> photoMap2Data(List<PhotoRealm> realmList) {
        List<Photo> photos = new ArrayList<>();
        for (PhotoRealm photoRealm : realmList) {
            Photo photo = new Photo(
                    photoRealm.getImageID()
            );
            photos.add(photo);
        }
        return photos;
    }
    private Category categoryMap2Data(CategoryRealm categoryRealm) {
        Category category = new Category();
        category.setId(categoryRealm.getId());
        category.setName(categoryRealm.getName());
        return category;
    }
}

