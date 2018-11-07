package goodline.info.cardbag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    RelativeLayout noCard;
    RelativeLayout listCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        noCard = (RelativeLayout) findViewById(R.id.rlNoCard);
        listCard = (RelativeLayout) findViewById(R.id.rlCard);

        noCard.setVisibility(View.VISIBLE);
        try{
        Bundle arguments = getIntent().getExtras();
        Card card=(Card) arguments.getParcelable(Card.class.getSimpleName());

        if (arguments == null||card==null) {
            isCard(false);
        }
        else {
            isCard(true);
            final TextView tvNameCard = findViewById(R.id.tvNameCard);
            final TextView tvCategory = findViewById(R.id.tvCategory);
            final TextView tvSale = findViewById(R.id.tvSale);

            tvNameCard.setText(card.getNameCard());
            tvCategory.setText(card.getCategory());
            tvSale.setText("Скидка "+card.getSale()+"%");}
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
        }
    }

    public void isCard(boolean isCard) {
        if(!isCard){
            listCard.setVisibility(View.GONE);
            noCard.setVisibility(View.VISIBLE);
        }
        else {
            noCard.setVisibility(View.GONE);
            listCard.setVisibility(View.VISIBLE);
        }
    }

    public void btnAddCardClick(View view) {
        Intent intent = new Intent(this, AddCard.class);
        startActivity(intent);

    }
}

