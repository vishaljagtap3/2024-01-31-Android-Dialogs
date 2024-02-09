package in.bitcode.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DialerFilter;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlert, btnDatePicker, btnTimePicker, btnSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

        btnAlert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("BitCode Tech");
                        builder.setMessage("Do you like android?");
                        builder.setIcon(R.mipmap.ic_launcher);

                        DialogInterface.OnClickListener listener =
                                new AlertButtonsListener();

                        builder.setPositiveButton(
                                "Yes",
                                //listener
                                new PositiveButtonListener()
                        );
                        builder.setNegativeButton(
                                "No",
                                //listener
                                new NegativeButtonListener()
                        );
                        builder.setNeutralButton(
                                "Don't Know",
                                //listener
                                new NeutralButtonListener()
                        );
                        //builder.setCancelable(false);

                        builder.setOnDismissListener(
                                new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        mt("Dialog dismissed");
                                    }
                                }
                        );

                        builder.setOnCancelListener(
                                new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        mt("Dialog cancelled");
                                    }
                                }
                        );

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
        );

        btnDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        new MyOnDateSetListener(),
                                        2024,
                                        1,
                                        9
                                );
                        datePickerDialog.show();
                    }
                }
        );

        btnTimePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog =
                                new TimePickerDialog(
                                        MainActivity.this,
                                        new MyOnTimeSetListener(),
                                        18,
                                        20,
                                        false
                                );
                        timePickerDialog.show();
                    }
                }
        );

        btnSingIn.setOnClickListener(new BtnSignInClickListener());
    }

    private class BtnSignInClickListener implements View.OnClickListener {
        int invalidLoginAttempts = 0;
        @Override
        public void onClick(View v) {

            Dialog loginDialog = new Dialog(MainActivity.this);
            loginDialog.setContentView(R.layout.login_dialog);

            EditText edtUsername = loginDialog.findViewById(R.id.edtUsername);
            EditText edtPassword = loginDialog.findViewById(R.id.edtPassword);
            Button btnLogin = loginDialog.findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (
                                    edtUsername.getText().toString().equals("bitcode") &&
                                            edtPassword.getText().toString().equals("bitcode")
                            ) {
                                mt("Login successful!");
                                loginDialog.dismiss();
                            }
                            else {
                                mt("Login failed!");
                                invalidLoginAttempts++;
                                if(invalidLoginAttempts == 3) {
                                    mt("Account blocked...");
                                    loginDialog.dismiss();
                                }
                            }

                        }
                    }
            );

            loginDialog.show();
        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker dialog, int hourOfDay, int minute) {
            btnTimePicker.setText(hourOfDay + " : " + minute);
        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker dialog, int year, int month, int dayOfMonth) {
            btnDatePicker.setText(dayOfMonth + " - " + (month + 1) + " - " + year);
        }
    }

    private class PositiveButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Positive");
        }
    }

    private class NegativeButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Negative");
        }
    }

    private class NeutralButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Neutral");
        }
    }

    private class AlertButtonsListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Which = " + which);
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mt("You love android!");
                    break;
                case -DialogInterface.BUTTON_NEGATIVE:
                    mt("You love iOS!");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("You are not human!");
                    break;
            }
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        btnAlert = findViewById(R.id.btnAlert);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnSingIn = findViewById(R.id.btnSignIn);
    }
}