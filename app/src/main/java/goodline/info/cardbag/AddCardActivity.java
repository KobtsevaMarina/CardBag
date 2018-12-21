package goodline.info.cardbag;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.RealmList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.realm.Realm;

public class AddCardActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 3;
    private Card card;
    private List<Photo> photoList;
    private EditText etNameCard;
    private EditText etCategory;
    private EditText etSale;
    private static final int ADD_CATEGORY = 0;
    private final int REQUEST_CODE_PICK_IMAGE_GALLARY = 1;
    private final int REQUEST_CODE_FRONT_PHOTO = 1;
    private final int REQUEST_CODE_BACK_PHOTO = 2;
    ImageView ivPhotoFront, ivPhotoBack;
    private File currentImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Дабaвить карту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initDefaultCard();

        etNameCard = findViewById(R.id.etNameCard);
        etCategory = findViewById(R.id.etCategory);
        etSale = findViewById(R.id.etSale);
        card = new Card();
        findViewById(R.id.flFronPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog(REQUEST_CODE_FRONT_PHOTO);
            }
        });

        findViewById(R.id.flBackPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog(REQUEST_CODE_BACK_PHOTO);
            }
        });
        ivPhotoFront = findViewById(R.id.ivPhotoFront);
        ivPhotoBack = findViewById(R.id.ivPhotoBack);
        long currentTime = System.currentTimeMillis();

    }

    @Nullable
    private File createImageFile() {
        // Генерируем имя файла
        String filename = System.currentTimeMillis() + ".jpg";

        // Получаем приватную директорию на карте памяти для хранения изображений
        // Выглядит она примерно так:
        // /sdcard/Android/data/info.goodline.department.learnandroid./files/Pictures
        // Директория будет создана автоматически, если ещё не существует
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Создаём файл
        File image = new File(storageDir, filename);
        try {
            if (image.createNewFile()) {
                return image;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private void takePhoto(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this,
                    "На вашем устройстве недоступна камера",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }


        // Создаём файл для изображения
        currentImageFile = createImageFile();

        if (currentImageFile != null) {
            // Если файл создался — получаем его URI
            Uri imageUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".fileprovider",
                    currentImageFile
            );

            // Передаём URI в камеру
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, requestCode);
        }
    }
    private void showImageSelectionDialog(int requestCode) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setItems(R.array.attachment_variants, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            chooseImagGromGallery(requestCode);
                        } else if (which == 1) {
                            takePhoto(requestCode);
                        }
                    }
                })
                .create();

        if (!isFinishing()) {
            alertDialog.show();
        }
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
                    if(data == null||data.getExtras()==null)
                        return;
                    Bundle arguments = data.getExtras();
                    Category category = (Category) arguments.getParcelable(Category.class.getSimpleName());
                    if (category==null) {
                        return;
                    }
                    card.setCategory(category);
                        etCategory.setText(category.getName());

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

    private void copyPhoto(String fileName, Bitmap img) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            img.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Bitmap getBitmap(ImageView iv) {
        iv.invalidate();
        BitmapDrawable btmpDrawable = (BitmapDrawable) iv.getDrawable();
        return btmpDrawable.getBitmap();
    }
    private void initDefaultCard() {
        card = new Card();
        long currentTime =  System.currentTimeMillis();
        Photo front = new Photo(currentTime+1);
        Photo back = new Photo(currentTime+2);
        photoList= new ArrayList<>();
        photoList.add(front);
        photoList.add(back);
        card.setPhotoList(photoList);
    }
    public void btnAddCardClick(View view) {


        card.setNameCard(etNameCard.getText().toString());
        Random random = new Random();
        int id = random.nextInt(200000);
        card.setId(id);
        card.setSale("Скидка "+etSale.getText().toString()+"%");
        card.setPhotoList(photoList);

        String folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/";

        copyPhoto(

                folder + card.getPhotoList().get(0).getImageID()+ ".jpg",
                getBitmap(ivPhotoFront)
        );
        copyPhoto(
                folder + card.getPhotoList().get(1).getImageID()+".jpg",
                getBitmap(ivPhotoBack)
        );

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
}
