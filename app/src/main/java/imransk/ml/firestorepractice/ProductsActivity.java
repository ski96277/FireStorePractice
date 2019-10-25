package imransk.ml.firestorepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private List<Products> productList;
    private ProgressBar progressBar;

    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        firebaseFirestore=FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        adapter = new ProductsAdapter(getApplicationContext(), productList);

        recyclerView.setAdapter(adapter);


        //get value from FireStore

        firebaseFirestore.collection("Products")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressBar.setVisibility(View.GONE);

                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> documentsList=queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot snapshot: documentsList){
                        Products products=snapshot.toObject(Products.class);
                        products.setId(snapshot.getId());
                        Log.e("TAG", "onSuccess: "+snapshot.getId() );
                        productList.add(products);
                    }

                    adapter.notifyDataSetChanged();

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProductsActivity.this, "Failed To Load Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
