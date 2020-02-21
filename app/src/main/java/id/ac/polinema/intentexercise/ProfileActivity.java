package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import aset.data;
import  static id.ac.polinema.intentexercise.RegisterActivity.DATA_KEY;


public class ProfileActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE=1;
    private static final String TAG=RegisterActivity.class.getCanonicalName();
    public static final String IMAGE_KEY = "image";
    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private ImageView photoImage;
    private RegisterActivity reg;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullnameText=findViewById(R.id.label_fullname);
        emailText=findViewById(R.id.label_email);
        homepageText=findViewById(R.id.label_homepage);
        aboutText=findViewById(R.id.label_about);
        photoImage=findViewById(R.id.image_profile);

        Bundle extras=getIntent().getExtras();
        Bitmap bmp=(Bitmap)extras.getParcelable("IMAGE_KEY");
        photoImage.setImageBitmap(bmp);

        findViewById(R.id.button_homepage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitHomepage("http://"+homepageText.getText().toString());
            }
        });

        data data=getIntent().getParcelableExtra(DATA_KEY);
        if(data!=null) {
            fullnameText.setText(data.getFullname());
            emailText.setText(data.getEmail());
            homepageText.setText(data.getHomepage());
            aboutText.setText(data.getAbout());
        }
    }

    public void visitHomepage(String url){
        Uri homepage=Uri.parse(url);
        Intent in=new Intent(Intent.ACTION_VIEW, homepage);
        if(in.resolveActivity(getPackageManager())!=null){
            startActivity(in);
        }
    }
}
