package org.flyve.example_java;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.flyve.inventory.InventoryTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "inventory.example";
    private InventoryTask inventoryTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                },
                1);

        Button btnRun = findViewById(R.id.btnRun);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventoryTask = new InventoryTask(MainActivity.this, "example-app-java", true);
                inventoryTask.getXML(new InventoryTask.OnTaskCompleted() {
                    @Override
                    public void onTaskSuccess(String data) {
                        Log.d(TAG, data);
                        //inventoryTask.shareInventory( 2);

                        try {
                            String base64 = data;
                            getSyncWebData("http://10.0.0.6:8000/1e6dwka1", base64, null);
                        } catch (Exception ex) {
                            Log.e(TAG, ex.getMessage());
                        }
                        Toast.makeText(MainActivity.this, "Inventory Success, check the log", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onTaskError(Throwable error) {
                        Log.e(TAG, error.getMessage());
                        Toast.makeText(MainActivity.this, "Inventory fail, check the log", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                }
            }
        }
    }

    public static String base64encode(String text) {
        String rtext = "";
        if(text == null) { return ""; }
        try {
            byte[] data = text.getBytes("UTF-8");
            rtext = Base64.encodeToString(data, Base64.NO_WRAP | Base64.URL_SAFE);
            rtext = rtext.replaceAll("-", "+");
            rtext = rtext.replaceAll(" ", "+");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
        }

        return rtext;
    }

    public static String getSyncWebData(final String url, final String data, final Map<String, String> header) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL dataURL = new URL(url);
            Log.d(TAG, "URL: " + url);
            HttpURLConnection conn = (HttpURLConnection)dataURL.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(500000);

//            for (Map.Entry<String, String> entry : header.entrySet()) {
//                conn.setRequestProperty(entry.getKey(), entry.getValue());
//            }

            // Send post request
            conn.setDoOutput(true);

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(data);
            os.flush();
            os.close();

            if(conn.getResponseCode() >= 400) {
                InputStream is = conn.getErrorStream();
                return inputStreamToString(is);
            }

            InputStream is = conn.getInputStream();
            return inputStreamToString(is);

        }
        catch (final Exception ex) {
            String error = ex.getMessage();
            Log.e(TAG, error);
            return error;
        }
    }

    private static String inputStreamToString(final InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

}
