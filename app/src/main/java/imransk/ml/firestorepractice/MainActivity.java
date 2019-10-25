package imransk.ml.firestorepractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * name
     */
    private EditText mEdittextName;
    /**
     * brand
     */
    private EditText mEdittextBrand;
    /**
     * description
     */
    private EditText mEdittextDesc;
    /**
     * price
     */
    private EditText mEdittextPrice;
    /**
     * quantity
     */
    private EditText mEdittextQty;
    /**
     * save
     */
    private Button mButtonSave;
    /**
     * View Products
     */
    private TextView mTextviewViewProducts;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEdittextName = (EditText) findViewById(R.id.edittext_name);
        mEdittextBrand = (EditText) findViewById(R.id.edittext_brand);
        mEdittextDesc = (EditText) findViewById(R.id.edittext_desc);
        mEdittextPrice = (EditText) findViewById(R.id.edittext_price);
        mEdittextQty = (EditText) findViewById(R.id.edittext_qty);
        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonSave.setOnClickListener(this);
        mTextviewViewProducts = (TextView) findViewById(R.id.textview_view_products);
        mTextviewViewProducts.setOnClickListener(this);

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button_save:

                saveProduct();


                break;
            case R.id.textview_view_products:

                startActivity(new Intent(this,ProductsActivity.class));

                break;
        }
    }

    private void saveProduct() {

        Products products = new Products(mEdittextName.getText().toString(),
                mEdittextBrand.getText().toString(),
                mEdittextDesc.getText().toString(),
                mEdittextPrice.getText().toString(),
                mEdittextQty.getText().toString());

        firebaseFirestore.collection("Products")
                .add(products)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
