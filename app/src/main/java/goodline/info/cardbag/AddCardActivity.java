package goodline.info.cardbag;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import io.realm.RealmList;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
    private static final int ADD_CATEGORY = 0;
    private final int REQUEST_CODE_PICK_IMAGE_GALLARY = 1;
    private final int REQUEST_CODE_FRONT_PHOTO = 1;
    private final int REQUEST_CODE_BACK_PHOTO = 2;
    ImageView ivPhotoFront, ivPhotoBack;

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
        findViewById(R.id.flFronPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImagGromGallery(REQUEST_CODE_FRONT_PHOTO);
            }
        });

        findViewById(R.id.flBackPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImagGromGallery(REQUEST_CODE_BACK_PHOTO);
            }
        });
        ivPhotoFront = findViewById(R.id.ivPhotoFront);
        ivPhotoBack = findViewById(R.id.ivPhotoBack);
        long currentTime = System.currentTimeMillis();

        card.photoList = new ArrayList<>();
       // card.photoList.add(photoFront);
       // card.photoList.add(photoBack);
    }

    private void showImage(int requestCode, Intent data) {
        try {
            //Получаем URI изображения, преобразуем его в Bitmap
            //объект и отображаем в элементе ImageView нашего интерфейса:
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            switch (requestCode) {
                case REQUEST_CODE_FRONT_PHOTO:
                    ivPhotoFront.setImageBitmap(selectedImage);
                    break;
                case REQUEST_CODE_BACK_PHOTO:
                    ivPhotoBack.setImageBitmap(selectedImage);
                    break;
            }

        } catch (FileNotFoundException e) {
            // Эта ошибка отобразится в случае если не удалось найти изображение
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_CATEGORY:
                if (resultCode == RESULT_OK) {
                    Bundle arguments = data.getExtras();
                    Category category = (Category) arguments.getParcelable(Category.class.getSimpleName());

                }
                break;
            case REQUEST_CODE_BACK_PHOTO:
            case REQUEST_CODE_FRONT_PHOTO:
                showImage(requestCode, data);
                break;


        }
    }

    private void chooseImagGromGallery(int requestCode) {
        // Интент для получения всех приложений которые могут отображать изображения
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        // Вызываем системное диалоговое окно для выбора приложения, которое умеет отображать изображения
        // и возвращать выбранную фотографию
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Выберите изображение");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        // Запускаем приложения и ожидаем результат
        startActivityForResult(chooserIntent, requestCode);
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
