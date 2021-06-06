package com.example.playground;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ADD_GROUND extends AppCompatActivity {
    EditText phoneNo, personeName, groudName, email, location,price;
    Button save;
    ImageView imageView;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    FirebaseDatabase database;
    private DatabaseReference myRef=FirebaseDatabase.getInstance().getReference().child("new ground");
    StorageReference storageReference= FirebaseStorage.getInstance().getReference();
    private Uri imageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d__g_r_o_u_n_d);
        final ActionBar actionBar = getSupportActionBar();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        personeName=findViewById(R.id.personeName);
        phoneNo=findViewById(R.id.phoneNo);
        groudName=findViewById(R.id.groundName);
        email=findViewById(R.id.email);
        location=findViewById(R.id.location);
        price=findViewById(R.id.price);
        save=findViewById(R.id.save);
        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosepic();
            }
        });
        // FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// Write a message to the database
        // database = FirebaseDatabase.getInstance();
        //myRef = database.getReference();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String txt="ggggggggggggggggggggggggggggggggggggggggggggggg";

                String txt_personName=personeName.getText().toString();
                String txt_phoneNo=phoneNo.getText().toString();
                String txt_location=location.getText().toString();
                String txt_groundName=groudName.getText().toString();
                String txt_price=price.getText().toString();
                String txt_email=email.getText().toString();
                if (txt_email.isEmpty() || txt_groundName.isEmpty() || txt_location.isEmpty() ||txt_personName.isEmpty() ||
                        txt_phoneNo.isEmpty() || txt_price.isEmpty())
                {
                    Toast.makeText(ADD_GROUND.this, "Some Detials Missed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ADD_GROUND.this, "Your Detials Add Sucessfully", Toast.LENGTH_SHORT).show();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                    StorageReference fileref=storageReference.child(System.currentTimeMillis() + "." + getFileExtention(imageuri));
                    fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               HashMap<String,Object> map=new HashMap<>();

                               map.put("personName",txt_personName);
                               map.put("groundName",txt_groundName);
                               map.put("phoneNumber",txt_phoneNo);
                               map.put("location",txt_location);
                               map.put("price",txt_price);
                               map.put("email",txt_email);
                               String image=uri.toString();
                               map.put("image",image);
                               myRef.push().setValue(map);

                           }
                       })     ;
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


                    Intent ii= new Intent(ADD_GROUND.this,MainActivity.class);
                    startActivity(ii);






                   // FirebaseDatabase.getInstance().getReference().child("new ground").push().setValue(map);

                }}
        });





        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }




    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    private void choosepic() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,101);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==101 && resultCode==RESULT_OK && data!=null)){
            imageuri=data.getData();
            imageView.setImageURI(imageuri);
        }
    }
    private String getFileExtention(Uri mUri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}
