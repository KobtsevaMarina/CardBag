package goodline.info.cardbag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_card extends AppCompatActivity {
     EditText etNameCard;
     EditText etCategory;
     EditText etSale;

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
        Card card = new Card();
        card.setNameCard(etNameCard.getText().toString());
        card.setCategory(etCategory.getText().toString());
        card.setSale(etSale.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Card.class.getSimpleName(), card);
        startActivity(intent);
    }
}
