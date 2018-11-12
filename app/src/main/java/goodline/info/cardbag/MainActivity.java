package goodline.info.cardbag;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private TextView tvNameCard;
    private TextView tvCategory;
    private TextView tvSale;
    private RelativeLayout noCard;
    private RelativeLayout listCard;
    private static final int ADD_CARD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        tvNameCard = findViewById(R.id.tvNameCard);
        tvCategory = findViewById(R.id.tvCategory);
        tvSale = findViewById(R.id.tvSale);

        noCard = (RelativeLayout) findViewById(R.id.rlNoCard);
        listCard = (RelativeLayout) findViewById(R.id.rlCard);

        noCard.setVisibility(View.VISIBLE);
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
                tvNameCard.setText(card.getNameCard());
                tvCategory.setText(card.getCategory());
                tvSale.setText("Скидка "+card.getSale()+"%");}
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

