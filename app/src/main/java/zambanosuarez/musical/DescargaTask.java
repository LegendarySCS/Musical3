package zambanosuarez.musical;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DescargaTask extends AsyncTask<String,Void,Boolean> {
    @Override
    protected Boolean doInBackground(String... data) {
        String urlp=(String)data[0];
        String nombre=(String)data[1];
        String ext=".mp3";
        try
        {
            URL url = new URL(urlp);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH ="/sdcard/Music" + "/load";
            Log.v("Archivo", "PATH: " + PATH);
            String nompath=nombre+ext;
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file,nompath);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();

            byte[] buffer = new byte[4096];
            int len1 = 0;

            while ((len1 = is.read(buffer)) != -1)
            {
                fos.write(buffer, 0, len1);
            }

            fos.close();
            is.close();

            Log.e("Tarea", " A new file is downloaded successfully");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
