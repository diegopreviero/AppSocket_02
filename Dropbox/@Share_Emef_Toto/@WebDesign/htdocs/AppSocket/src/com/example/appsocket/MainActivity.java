//http://www.youtube.com/watch?v=ckWG3JXCCzM
//http://stackoverflow.com/questions/14251694/unfortunately-application-has-stopped-in-android

package com.example.appsocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Socket client;
    private PrintWriter printwriter;
    private EditText etMsg, etIP, etPort;
    private Button button;
    private String messsage;
    int port = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIP = (EditText) findViewById(R.id.editText1);
        etPort = (EditText) findViewById(R.id.editText2);
        etMsg = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                messsage = etMsg.getText().toString();
                etMsg.setText("");
                port = Integer.parseInt(etPort.getText().toString());

                new Thread(new Runnable() 
                {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try 
                        {
                            client = new Socket(etIP.getText().toString(), port);
                            printwriter = new PrintWriter(client.getOutputStream(),true);
                            printwriter.write(messsage);
                            printwriter.flush();
                            printwriter.close();
                            client.close();
                        }

                        catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }       
                    }
                }).start();


            }
        });
    }

}