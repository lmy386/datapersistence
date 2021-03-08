package com.example.datapersistence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContentProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_provider);
        Button make_call = findViewById(R.id.make_call);
        make_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProvider.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ContentProvider.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else
                    call();



            }
        });

        //读取系统联系人
        Button read_contacts = findViewById(R.id.read_contacts);
        read_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentProvider.this,ReadContactsActivity.class);
                startActivity(intent);
            }
        });

        Button cmp = findViewById(R.id.create_my_provider);
        cmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentProvider.this,MyContentProviderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }
                else{
                    Toast.makeText(this, "你拒绝了拨打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:

        }
    }
}