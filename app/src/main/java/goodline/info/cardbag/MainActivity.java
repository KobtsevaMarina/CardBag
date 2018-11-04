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

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    RelativeLayout noCard;
    RelativeLayout listCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        Switch switchCard = (Switch) findViewById(R.id.switchCard);
        noCard = (RelativeLayout) findViewById(R.id.rlNoCard);
        listCard = (RelativeLayout) findViewById(R.id.rlCard);
        noCard.setVisibility(View.VISIBLE);
        if (switchCard != null) {
            switchCard.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            listCard.setVisibility(View.GONE);
            noCard.setVisibility(View.VISIBLE);
        }
        else {
            noCard.setVisibility(View.GONE);
            listCard.setVisibility(View.VISIBLE);
        }
    }

    public void btnAddCardClick(View view) {
        Intent intent = new Intent(this, add_card.class);
        startActivity(intent);

    }
}
