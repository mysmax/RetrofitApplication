package mysmax.com.retrofitapplication.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void internalFileStore()
    {
        //Include Write External Storage
        // Create a file in the Internal Storage
        String fileName = "MyFile";
        String content = "hello world";

        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //In order to cache some data, i.e., create cache files rather than storing data in files
        //persistently, we can open a File object representing the cache directory in the internal storage
        //by using the getCacheDir() method. Let’s see some code to create cache files.
    }

    private void internalFileStoreWithCache()
    {
        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        try {
            // file = File.createTempFile("MyCache", null, getCacheDir());
            file = new File(getCacheDir(), "MyCache");
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        If we use getFilesDir() instead of getCacheDir() then it’ll store files in the files
        directory making the code similar in effect to the previous example. Also note the
        commented line that uses File.createTempFile() to create the file.
        */
    }

    private void readInternalFileFromCacheDir()
    {
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(getCacheDir(), "MyCache"); // Pass getFilesDir() and "MyFile" to read file
             // Read
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }

            Log.d("FileStore", buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
    These should be files belonging to your app that’ll get deleted once the user uninstalls the app.
    These files will be accessible to other apps but are such that don’t provide any value to other apps
    or even to the user outside the context of the app like certain media (audio, images, etc.) files downloaded by a game.
    Saving files to the directories appropriate for holding your private files is super easy.
    You can try the previous examples but instead use getExternalFilesDir() to get the appropriate directory path.
     */
    private void externalStorageFile()
    {
        //getExternalStorageDirectory() – This returns the primary (top-level or root) external storage directory.
        //getExternalStoragePublicDirectorty() – This returns a top level public external storage directory for shoving files
        //of a particular type based on the argument passed. So basically the external storage

        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "MyCache");
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLocalFile()
    {
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open("file.txt");

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);
            // Use model
            //GsonParse gsonObj = gson.fromJson(reader, GsonParse.class);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    private boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (String aChildren : children) {
                    deletedAll = deleteFile(new File(file, aChildren)) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }
        return deletedAll;
    }
}
