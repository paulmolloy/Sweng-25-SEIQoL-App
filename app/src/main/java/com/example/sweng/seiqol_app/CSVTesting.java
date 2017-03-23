package com.example.sweng.seiqol_app;

        import android.Manifest;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.media.MediaScannerConnection;
        import android.net.Uri;
        import android.os.Environment;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.view.GestureDetectorCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.GestureDetector;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.opencsv.CSVWriter;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;


public class CSVTesting extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
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
        setContentView(R.layout.activity_csvtesting);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, HelpScreen.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        String[] myStrgs = new String[]{message};
        //passing the message typed by the user to the next activity
        intent.putExtra(EXTRA_MESSAGE, message);
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
                os.write(message.getBytes());
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


        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:molloyp1@tcd.ie"));
        //mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"molloyp1@tcd.ie"});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
        mailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
        File root = Environment.getExternalStorageDirectory();
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "MyApp", message+".csv");
        if (!file.exists() || !file.canRead()) {
            Toast.makeText(this, "Attachment Error", Toast.LENGTH_SHORT).show();
            finish();
            //return;
        }
        Uri uri = Uri.parse("file://" + file);
        mailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(mailIntent, "Send email..."));
        startActivity(intent);
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



}