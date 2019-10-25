package imransk.ml.firestorepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextBrand;
    private EditText editTextDesc;
    private EditText editTextPrice;
    private EditText editTextQty;
    private Products product;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextName = findViewById(R.id.edittext_name);
        editTextBrand = findViewById(R.id.edittext_brand);
        editTextDesc = findViewById(R.id.edittext_desc);
        editTextPrice = findViewById(R.id.edittext_price);
        editTextQty = findViewById(R.id.edittext_qty);
        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
        firebaseFirestore=FirebaseFirestore.getInstance();

         product= (Products) getIntent().getSerializableExtra("product");

        editTextName.setText(product.getProductName());
        editTextBrand.setText(product.getProductBrand());
        editTextDesc.setText(product.getProductDescription());
        editTextPrice.setText(product.getProductPrice());
        editTextQty.setText(product.getProductQuentity());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                updateProduct();
                break;
            case R.id.button_delete:
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Delete");
                builder.setMessage("Do you Want to delete it ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteProduct();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;
        }
    }

    private void deleteProduct() {

        firebaseFirestore.collection("Products")
                .document(product.getId())
                .delete()
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ProductsActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }
        });

    }

    private void updateProduct() {

        Products p=new Products(
                editTextName.getText().toString(),
                editTextBrand.getText().toString(),
                editTextDesc.getText().toString(),
                editTextPrice.getText().toString(),
                editTextQty.getText().toString()
        );
        firebaseFirestore.collection("Products")
                .document(product.getId())
                .set(p)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
