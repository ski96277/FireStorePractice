package imransk.ml.firestorepractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolderClass> {

    Context context;
    List<Products> productsList;

    public ProductsAdapter(Context context, List<Products> products) {
        this.context = context;
        this.productsList = products;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_product,parent,false);


        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        Products product=productsList.get(position);

        holder.textViewName.setText(product.getProductName());
        holder.textViewBrand.setText(product.getProductBrand());
        holder.textViewDesc.setText(product.getProductDescription());
        holder.textViewPrice.setText("INR " + product.getProductPrice());
        holder.textViewQty.setText("Available Units: " + product.getProductQuentity());

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewBrand, textViewDesc, textViewPrice, textViewQty;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewBrand = itemView.findViewById(R.id.textview_brand);
            textViewDesc = itemView.findViewById(R.id.textview_desc);
            textViewPrice = itemView.findViewById(R.id.textview_price);
            textViewQty = itemView.findViewById(R.id.textview_quantity);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Products product=productsList.get(getAdapterPosition());

            Intent intent=new Intent(context,UpdateActivity.class);
            intent.putExtra("product", product);
            view.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }


}
