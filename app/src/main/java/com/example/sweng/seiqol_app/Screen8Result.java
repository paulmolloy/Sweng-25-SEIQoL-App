package com.example.sweng.seiqol_app;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Screen8Result extends AppCompatActivity  {
    public final int GIGA_BYTE = 1073741824;
    public final int MEGA_BYTE = 1048576;
    public final int BYTE = 1024;
    long time;
    ArrayList<String> data = new ArrayList<>();
    String dataString;
    Intent i;
    Intent j;
    TextView dataDisplayTv;
    private GestureDetectorCompat mDetector;
    private static final String DEBUG_TAG = "Gestures";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen8_result);
        getStorage(findViewById(R.id.activity_rect_testing));
        dataDisplayTv = (TextView) findViewById(R.id.data_display_tv);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }


        dataString = Arrays.toString(data.toArray());
        dataString = dataString.substring(1,dataString.length()-1);
        dataDisplayTv.setText(dataString);
        sendMessage();

        Button next = (Button) findViewById(R.id.email_b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = data.get(0);
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:molloyp1@tcd.ie"));
                //mailIntent.setType("text/plain");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"molloyp1@tcd.ie"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
                File root = Environment.getExternalStorageDirectory();
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "MyApp", message+".csv");
                if (!file.exists() || !file.canRead()) {
                    Toast.makeText(Screen8Result.this, "Attachment Error", Toast.LENGTH_SHORT).show();
                    finish();
                    //return;
                }
                Uri uri = Uri.parse("file://" + file);
                mailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(mailIntent, "Send email..."));

            }
        });

        Button back = (Button) findViewById(R.id.back_7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                j = new Intent(Screen8Result.this, PieTesting.class);

                startActivity(j);
            }
        });



    }

    public void sendMessage(){
        Intent intent = new Intent(this, HelpScreen.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = data.get(0);
        String[] myStrgs = new String[]{message};
        //passing the message typed by the user to the next activity
        verifyStoragePermissions(this);// checking to see if we have permission to write, if not it will ask for permission
        if(isExternalStorageWritable()==true) {
            Log.w("Its good.", "There's storage.");


            //File myPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS);
            File csbFile = new File(Environment.getExternalStorageDirectory() + File.separator + "MyApp", message+".csv");
            csbFile.getParentFile().mkdirs(); //creates the directory for the file
            try {
                csbFile.createNewFile();// creates the file itself
                FileOutputStream os = new FileOutputStream(csbFile);
                FileReader fr = new FileReader(csbFile);
                FileWriter fw = new FileWriter(csbFile);
                //fw.write(message);
                os.write(dataString.getBytes());
                //os.write(fr.read());
                os.close();
                MediaScannerConnection.scanFile(this,
                        new String[]{csbFile.toString()}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("ExternalStorage", "Scanned " + path + ":");
                                Log.i("ExternalStorage", "-> uri=" + uri);
                            }
                        });
            /*CSVWriter writer = new CSVWriter(new FileWriter(csbFile));
            writer.writeNext(myStrgs);
            writer.close();*/
            } catch (IOException e) {
            /*Insert bullshit error message here*/
                Log.w("ExternalStorage", "Error writing " + csbFile, e);
            }




        }else{
            Log.w("ExternalStorage", "no external storage available.");
        }



    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        // if the media is mounted i.e. if external storage is writable and readable
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            // We don't have permission so prompt the user to grant permission
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }




    public void displayDialogBox(View view, long memo, String storageSI){
        Log.w("Inside", "it's" + memo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String dMessage = "Your internal memory is now less than " + memo + storageSI + ". Recommend to export as an e-mail to ensure data is not lost.";
        builder.setMessage(dMessage);
        builder.setTitle("Storage warning");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getStorage(View view){
        long mem = Environment.getExternalStorageDirectory().getFreeSpace();
        String storageSI = "";
        if(mem>=GIGA_BYTE){
            mem = mem/GIGA_BYTE;
            storageSI = "GB";
        }else if(mem>=MEGA_BYTE){
            mem = mem/MEGA_BYTE;
            storageSI = "MB";
        }else{
            mem = mem/BYTE;
            storageSI = "KB";
        }
        Log.w("Free space", "equals " + mem + storageSI);
        if(storageSI.equals("MB") && mem<20)
        {
            Log.w("Free space", "is" + mem);
            displayDialogBox(view, mem, storageSI);
        }
    }
}