package fr.heban.ficheheban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class EditIdentityActivity extends AppCompatActivity {

    //Attribut représentant la clef dans l'extra de l'intention de retour
    public final static String EXTRA_DATA_IDENTITY_ARRAY_STR = "fr.heban.ficheheban.editIdentityActivity.EXTRA_DATA_IDENTITY_ARRAY_STR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_identity);

        Intent intent = getIntent();
        String[] arr = intent.getStringArrayExtra(MainActivity.EXTRA_DATA_IDENTITY_ARRAY);


        if(Objects.equals(arr[0], getString(R.string.app_unknow))) {
            ((EditText) findViewById(R.id.editText_lastname)).setHint(arr[0]);
        } else {
            ((EditText) findViewById(R.id.editText_lastname)).setText(arr[0]);
        }

        if(Objects.equals(arr[1], getString(R.string.app_unknow))) {
            ((EditText) findViewById(R.id.editText_firstname)).setHint(arr[1]);
        } else {
            ((EditText) findViewById(R.id.editText_firstname)).setText(arr[1]);
        }

        if(Objects.equals(arr[2], getString(R.string.app_unknow))) {
            ((EditText) findViewById(R.id.editText_tel)).setHint(arr[2]);
        } else {
            ((EditText) findViewById(R.id.editText_tel)).setText(arr[2]);
        }


    }
    // Bouton permettant de fermer l'activité
    public void onBtnCancleClicked(View view) {

        setResult(RESULT_CANCELED);
        this.finish();
    }

    // Bouton permettant de retourner le résultat par intention
    public void onBtnOkClicked(View view) {

        //Récupération des valeurs des champs
        String lastname = ((EditText) findViewById(R.id.editText_lastname)).getText().toString();
        String firstname = ((EditText) findViewById(R.id.editText_firstname)).getText().toString();
        String tel = ((EditText) findViewById(R.id.editText_tel)).getText().toString();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_IDENTITY_ARRAY_STR, new String[]{lastname, firstname, tel});

        setResult(RESULT_OK, intent);
        this.finish();

    }
}