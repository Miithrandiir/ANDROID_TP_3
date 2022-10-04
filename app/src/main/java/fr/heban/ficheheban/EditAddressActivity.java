package fr.heban.ficheheban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class EditAddressActivity extends AppCompatActivity {

    //Attribut représentant la clef dans l'extra de l'intention de retour
    public final static String EXTRA_DATA_ARRAY_STR = "fr.heban.ficheheban.editAddressActivity.EXTRA_DATA_ARRAY_STR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        Intent intent = getIntent();
        String[] arr = intent.getStringArrayExtra(MainActivity.EXTRA_DATA_ADDRESS_ARRAY);

        EditText et = findViewById(R.id.editText_address_num_data);
        if (Objects.equals(arr[0], getString(R.string.app_unknow))) {
            et.setHint(arr[0]);
        } else {
            et.setText(arr[0]);
        }

        et = findViewById(R.id.editText_address_street_data);
        if (Objects.equals(arr[1], getString(R.string.app_unknow))) {
            et.setHint(arr[1]);
        } else {
            et.setText(arr[1]);
        }

        et = findViewById(R.id.editText_address_zip_code_data);
        if (Objects.equals(arr[2], getString(R.string.app_unknow))) {
            et.setHint(arr[2]);
        } else {
            et.setText(arr[2]);
        }

        et = findViewById(R.id.editText_address_city_data);
        if (Objects.equals(arr[3], getString(R.string.app_unknow))) {
            et.setHint(arr[3]);
        } else {
            et.setText(arr[3]);
        }

    }

    // Bouton permettant de fermer l'activité
    public void onBtnCancleClicked(View view) {

        setResult(RESULT_CANCELED);
        this.finish();
    }

    // Bouton permettant de retourner le résultat par intention
    public void onBtnOkClicked(View view) {

        String num = ((EditText) findViewById(R.id.editText_address_num_data)).getText().toString();
        String street = ((EditText) findViewById(R.id.editText_address_street_data)).getText().toString();
        String zipCode = ((EditText) findViewById(R.id.editText_address_zip_code_data)).getText().toString();
        String city = ((EditText) findViewById(R.id.editText_address_city_data)).getText().toString();

        Intent res = new Intent();
        res.putExtra(EXTRA_DATA_ARRAY_STR, new String[]{num, street, zipCode, city});

        setResult(RESULT_OK, res);
        this.finish();

    }
}