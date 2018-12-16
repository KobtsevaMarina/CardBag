package goodline.info.cardbag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import io.realm.RealmList;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.realm.Realm;

public class AddCardActivity extends AppCompatActivity {

    private Card card;
    private List<Integer> photos;
    private EditText etNameCard;
    private EditText etCategory;
    private EditText etSale;
    private static final int ADD_CATEGORY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Дабaвить карту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        etNameCard = findViewById(R.id.etNameCard);
        etCategory = findViewById(R.id.etCategory);
        etSale = findViewById(R.id.etSale);
        card = new Card();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void btnAddCardClick(View view) {
        List<Photo> photoList = Arrays.asList(
                new Photo(R.drawable.card_lenta),
                new Photo(R.drawable.card_lenta_back)
        );

        card.setNameCard(etNameCard.getText().toString());
        Random random = new Random();
        int id = random.nextInt(200000);
        Category category = new Category(id,etCategory.getText().toString());
        card.setCategory(category);
        card.setSale("Скидка "+etSale.getText().toString()+"%");
        card.setPhotoList(photoList);

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(cardMap2Realm(card));
            }
        });
        realm.close();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Card.class.getSimpleName(), card);
        setResult(RESULT_OK,intent);
        startActivity(intent);
        finish();
    }

    private CardRealm cardMap2Realm(Card card) {
        CardRealm cardRealm = new CardRealm();
        cardRealm.setId(card.getId());
        cardRealm.setNameCard(card.getNameCard());
        cardRealm.setSale(card.getSale());
        cardRealm.setCategory(categoreMap2Realm(card.getCategory()));
        cardRealm.setPhotoList(photoMap2Realm(card.getPhotoList()));
        return cardRealm;
    }
    private RealmList<PhotoRealm> photoMap2Realm(List <Photo> photo)
    {
        RealmList <PhotoRealm> photoRealm = new RealmList<>();
        for(Photo photos: photo) {
            PhotoRealm photoRealm1 = new PhotoRealm();
            photoRealm1.setImageID(photos.getImageID());
            photoRealm.add(photoRealm1);
        }
        return photoRealm;
    }

    private CategoryRealm categoreMap2Realm(Category category) {
        CategoryRealm categoryRealm = new CategoryRealm();
        categoryRealm.setId(category.getId());
        categoryRealm.setName(category.getName());
        return categoryRealm;
    }
    public void etCategoryClick(View view) {
            Intent intent = new Intent(this, CategoryListActivity.class);
            startActivityForResult(intent,ADD_CATEGORY);
    }
   /* protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        else try{
            Bundle arguments = data.getExtras();
            Category category = (Category) arguments.getParcelable(Category.class.getSimpleName());
            if (arguments == null||category==null||resultCode!=RESULT_OK) {
                return;
            }
            else {
                etCategory.setText(category.getName());
            }
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
        }
    }*/
}
