package cubex.mahesh.listviewdynamic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
        ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int status = ContextCompat.checkSelfPermission(
      this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(status== PackageManager.PERMISSION_GRANTED)
        {
            readFiles();
        }else{
            ActivityCompat.requestPermissions(this,
 new String[ ]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);
        }
    } // onCreate
    public void readFiles( )
    {
        lview = findViewById(R.id.lview);
        String path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/";
        File f = new File(path);
        if(!f.exists()){
            path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/";
            f=new File(path);
        }
        String[ ] files =  f.list();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_single_choice,
                        files);
        lview.setAdapter(adapter);
    } // readFiles

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.
                PERMISSION_GRANTED){
            readFiles();
        }else{
            Toast.makeText(this,
     "Can't access storage info..",
                    Toast.LENGTH_SHORT).show();
        }
    }
} // MainActivity
