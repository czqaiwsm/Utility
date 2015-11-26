package com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.utility.AlertDialogUtils;
import com.utility.R;
import com.utility.waiterLayer.UILoadingDialog;
import com.utility.waiterLayer.WaitLayer;
import com.utility.waiterLayer.WaitUtility;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.displayAlert(MyActivity.this, "alertHeader", "helloMessage", "cancel");
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.displayMyAlertChoice(MyActivity.this, "title", "message", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyActivity.this, "confirm", Toast.LENGTH_SHORT).show();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.displayMyAlertChoice(MyActivity.this, "title", "message", "Positive",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyActivity.this, "confirm", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UILoadingDialog.show(MyActivity.this);
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               WaitUtility.showWaitLayer(MyActivity.this);
            }
        });
    }
}
