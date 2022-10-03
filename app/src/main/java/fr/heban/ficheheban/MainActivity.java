package fr.heban.ficheheban;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_IDENTITY_ARRAY = "fr.heban.ficheheban.mainActivity.EXTRA_DATA_IDENTITY_ARRAY";
    public static final String EXTRA_DATA_ADDRESS_ARRAY = "fr.heban.ficheheban.mainActivity.EXTRA_DATA_ADDRESS_STR_ARRAY";

    private ActivityResultLauncher<Intent> resultEditIdentity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent res = result.getData();
                        String[] arr = res.getStringArrayExtra(EditIdentityActivity.EXTRA_DATA_IDENTITY_ARRAY_STR);
                        ((TextView) findViewById(R.id.textView_lastname_data)).setText(arr[0]);
                        ((TextView) findViewById(R.id.textView_firstname_data)).setText(arr[1]);
                        ((TextView) findViewById(R.id.textView_tel_data)).setText(arr[2]);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> resultEditAddress = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
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
    }

    public void onBtnEditIdentityClicked(View view) {
        //getValue
        String lastname = ((TextView) findViewById(R.id.textView_lastname_data)).getText().toString();
        String firstname = ((TextView) findViewById(R.id.textView_firstname_data)).getText().toString();
        String tel = ((TextView) findViewById(R.id.textView_tel_data)).getText().toString();

        Intent intent = new Intent(this, EditIdentityActivity.class);
        String[] data = {lastname, firstname, tel};
        intent.putExtra(EXTRA_DATA_IDENTITY_ARRAY, data);

        resultEditIdentity.launch(intent);
    }

    public void onBtnEditAddressClicked(View view) {
        //getValue
        String num = ((TextView) findViewById(R.id.textView_num_data)).getText().toString();
        String street = ((TextView) findViewById(R.id.textView_street_data)).getText().toString();
        String zipCode = ((TextView) findViewById(R.id.textView_zipCode_data)).getText().toString();
        String city = ((TextView) findViewById(R.id.textView_city_data)).getText().toString();

        Intent intent = new Intent(this, EditAddressActivity.class);
        String[] data = {num, street, zipCode, city};
        intent.putExtra(EXTRA_DATA_ADDRESS_ARRAY, data);

        resultEditAddress.launch(intent);
    }
}