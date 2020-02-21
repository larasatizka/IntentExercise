package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import aset.data;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG=RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE=1;
    public static final String DATA_KEY="data";
    public static final String IMAGE_KEY="gambar";
    public ImageView avatarImage;
    private TextInputEditText fullnameText;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private TextInputEditText confirmText;
    private TextInputEditText homepageText;
    private TextInputEditText aboutText;
    private Uri imageUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        avatarImage=findViewById(R.id.image_profile);
        fullnameText=findViewById(R.id.text_fullname);
        emailText=findViewById(R.id.text_email);
        passwordText=findViewById(R.id.text_password);
        confirmText=findViewById(R.id.text_confirm_password);
        homepageText=findViewById(R.id.text_homepage);
        aboutText=findViewById(R.id.text_about);

    }

    public void changePhoto(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            return;
        }
        if(requestCode==GALLERY_REQUEST_CODE){
            if(data!=null){
                try{
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }

        }

    }

    public void kirimData(View view) {
        String fullname=fullnameText.getText().toString();
        String email=emailText.getText().toString();
        String password=passwordText.getText().toString();
        String confirm=confirmText.getText().toString();
        String homepage=homepageText.getText().toString();
        String about=aboutText.getText().toString();

        data data=new data(fullname, email, password, confirm, homepage, about);
        Intent intent=new Intent(this, ProfileActivity.class);
        if(fullname.isEmpty()){
            fullnameText.setError("Fill your fullname");
        } else if(email.isEmpty() || isValidEmail(email)==false){
            emailText.setError("Fill your email correctly");
        } else if(password.isEmpty()){
            passwordText.setError("Fill your password");
        } else if(confirm.isEmpty() || !confirm.equals(password)){
            confirmText.setError("Fill your confirm password correctly");
        } else if(homepage.isEmpty() || isValidHomepage(homepage)==false){
            homepageText.setError("Fill your homepage correctly");
        } else if(about.isEmpty()){
            aboutText.setError("Fill your about text");
        } else {
            intent.putExtra(DATA_KEY, data);
            avatarImage.buildDrawingCache();
            Bitmap image=avatarImage.getDrawingCache();
            Bundle extras=new Bundle();
            extras.putParcelable("IMAGE_KEY", image);
            intent.putExtras(extras);
            startActivity(intent);

        }

    }

    public boolean isValidEmail(String email){
        boolean validate;
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if(email.matches(emailPattern)){
            validate=true;
        } else if(email.matches(emailPattern2)){
            validate=true;
        } else {
            validate=false;
        }
        return validate;
    }

    public boolean isValidHomepage(String email){
        boolean validate;
        String emailPattern="[a-zA-Z0-9._-].+[a-z]+";
        String emailPattern2="[a-zA-Z0-9._-].+[a-z]+\\.+[a-z]+";

        if(email.matches(emailPattern)){
            validate=true;
        } else if(email.matches(emailPattern2)){
            validate=true;
        }
        else {
            validate=false;
        }
        return validate;
    }
}