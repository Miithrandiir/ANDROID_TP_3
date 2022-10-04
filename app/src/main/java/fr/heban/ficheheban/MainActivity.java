package fr.heban.ficheheban;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_IDENTITY_ARRAY = "fr.heban.ficheheban.mainActivity.EXTRA_DATA_IDENTITY_ARRAY";
    public static final String EXTRA_DATA_ADDRESS_ARRAY = "fr.heban.ficheheban.mainActivity.EXTRA_DATA_ADDRESS_STR_ARRAY";

    public final static String BUNDLE_SAVE = "fr.heban.ficheheban.MainActivity.BUNDLE_SAVE";

    /*
        Enregistrement d'une intention pour l'édition de l'identité
     */
    private ActivityResultLauncher<Intent> resultEditIdentity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // On vérifie si le retour est bon et s'il y a des données
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent res = result.getData();
                        String[] arr = res.getStringArrayExtra(EditIdentityActivity.EXTRA_DATA_IDENTITY_ARRAY_STR);
                        //Insertion des données dans les champs correspondants
                        ((TextView) findViewById(R.id.textView_lastname_data)).setText(arr[0]);
                        ((TextView) findViewById(R.id.textView_firstname_data)).setText(arr[1]);
                        ((TextView) findViewById(R.id.textView_tel_data)).setText(arr[2]);
                    }
                }
            }
    );

    /*
        Enregistrement d'une intention pour l'édition de l'adresse
     */
    private ActivityResultLauncher<Intent> resultEditAddress = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // On vérifie si le retour est bon et s'il y a des données
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent res = result.getData();
                        String[] arr = res.getStringArrayExtra(EditAddressActivity.EXTRA_DATA_ARRAY_STR);

                        ((TextView) findViewById(R.id.textView_num_data)).setText(arr[0]);
                        ((TextView) findViewById(R.id.textView_street_data)).setText(arr[1]);
                        ((TextView) findViewById(R.id.textView_zipCode_data)).setText(arr[2]);
                        ((TextView) findViewById(R.id.textView_city_data)).setText(arr[3]);

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On regarde si une sauvegarde d'un état d'une instance existe (Pour éviter d'oublier les données
        // quand on passe du portrait au paysage)
        if(savedInstanceState != null) {
            //On récupère les données du tableau
            String[] arr = savedInstanceState.getStringArray(BUNDLE_SAVE);
            //On vérifie que l'on a bien le nombre de champs nécessaires
            if(arr != null && arr.length == 7) {
                ((TextView) findViewById(R.id.textView_lastname_data)).setText(arr[0]);
                ((TextView) findViewById(R.id.textView_firstname_data)).setText(arr[1]);
                ((TextView) findViewById(R.id.textView_tel_data)).setText(arr[2]);
                ((TextView) findViewById(R.id.textView_num_data)).setText(arr[3]);
                ((TextView) findViewById(R.id.textView_street_data)).setText(arr[4]);
                ((TextView) findViewById(R.id.textView_zipCode_data)).setText(arr[5]);
                ((TextView) findViewById(R.id.textView_city_data)).setText(arr[6]);
            }

        }

    }

    // Méthode qui permet de lancer l'activité d'édition de l'identité
    public void onBtnEditIdentityClicked(View view) {
        //getValue
        String lastname = ((TextView) findViewById(R.id.textView_lastname_data)).getText().toString();
        String firstname = ((TextView) findViewById(R.id.textView_firstname_data)).getText().toString();
        String tel = ((TextView) findViewById(R.id.textView_tel_data)).getText().toString();

        Intent intent = new Intent(this, EditIdentityActivity.class);
        //Sauvegarde des données dans un tableau de string
        String[] data = {lastname, firstname, tel};
        intent.putExtra(EXTRA_DATA_IDENTITY_ARRAY, data);

        resultEditIdentity.launch(intent);
    }

    // Méthode qui permet de lancer l'activité d'édition de l'adresse
    public void onBtnEditAddressClicked(View view) {
        //getValue
        String num = ((TextView) findViewById(R.id.textView_num_data)).getText().toString();
        String street = ((TextView) findViewById(R.id.textView_street_data)).getText().toString();
        String zipCode = ((TextView) findViewById(R.id.textView_zipCode_data)).getText().toString();
        String city = ((TextView) findViewById(R.id.textView_city_data)).getText().toString();

        Intent intent = new Intent(this, EditAddressActivity.class);
        //Sauvegarde des données dans un tableau de string
        String[] data = {num, street, zipCode, city};
        intent.putExtra(EXTRA_DATA_ADDRESS_ARRAY, data);

        resultEditAddress.launch(intent);
    }

    //Sauvegarde de l'instance courrante quand l'utilisateur souhaite changé l'orientation de
    //son smartphone
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String num = ((TextView) findViewById(R.id.textView_num_data)).getText().toString();
        String street = ((TextView) findViewById(R.id.textView_street_data)).getText().toString();
        String zipCode = ((TextView) findViewById(R.id.textView_zipCode_data)).getText().toString();
        String city = ((TextView) findViewById(R.id.textView_city_data)).getText().toString();
        String lastname = ((TextView) findViewById(R.id.textView_lastname_data)).getText().toString();
        String firstname = ((TextView) findViewById(R.id.textView_firstname_data)).getText().toString();
        String tel = ((TextView) findViewById(R.id.textView_tel_data)).getText().toString();
        //Enregistrement de tout dans un tableau, plus simple à gérer, puis moins de variable pour
        //les clefs
        outState.putStringArray(BUNDLE_SAVE, new String[]{lastname,firstname,tel,num,street,zipCode,city});
        super.onSaveInstanceState(outState);
    }

}