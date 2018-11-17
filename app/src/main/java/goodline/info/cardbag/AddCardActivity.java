package goodline.info.cardbag;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void btnAddCardClick(View view) {
        int[] imageId= new int[2];
        imageId[0]=R.drawable.card_lenta;
        imageId[1]=R.drawable.card_lenta_back;
        Card card = new Card();
        card.setNameCard(etNameCard.getText().toString());
        card.setCategory(etCategory.getText().toString());
        card.setSale("Скидка "+etSale.getText().toString()+"%");
        card.setImageId(imageId);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Card.class.getSimpleName(), card);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void etCategoryClick(View view) {
            Intent intent = new Intent(this, CategoryListActivity.class);
            startActivityForResult(intent,ADD_CATEGORY);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
    }
}
