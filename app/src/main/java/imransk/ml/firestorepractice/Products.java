package imransk.ml.firestorepractice;

import android.text.Editable;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Products implements Serializable {
    String productName;
    String productBrand;
    String productDescription;
    String productPrice;
    String productQuentity;

    @Exclude private String id;

    public Products() {
    }

    public Products(String productName, String productBrand, String productDescription, String productPrice, String productQuentity) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuentity = productQuentity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuentity() {
        return productQuentity;
    }

    public void setProductQuentity(String productQuentity) {
        this.productQuentity = productQuentity;
    }
}
