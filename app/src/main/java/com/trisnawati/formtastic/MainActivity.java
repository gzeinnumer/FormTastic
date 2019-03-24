package com.trisnawati.formtastic;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.athkalia.emphasis.EmphasisTextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nama_depan)
    EditText namaDepan;
    @BindView(R.id.nama_blkg)
    EditText namaBlkg;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.sp_jk)
    Spinner spJk;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cpassword)
    EditText cpassword;
    @BindView(R.id.check_agreement)
    CheckBox checkAgreement;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tv_cb)
    EmphasisTextView tvCb;


    public String stNamaDp;
    public String stNamaBlkg;
    public String stEmail;
    public String stJk;
    public String stPassword;
    public String stCPassword;

    ProgressDialog progressDialog;

    Boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvCb.setText("Saya Telah Membaca, Memahami Dan Menyetujui Kebijakan Privasi dan Syarat dan Ketentuan.");
        tvCb.setTextToHighlight("Kebijakan Privasi dan Syarat dan Ketentuan");
        tvCb.setTextHighlightColor(R.color.colorAccent);
        tvCb.setCaseInsensitive(true);
        tvCb.highlight();

        String[] list = {"Laki-laki","Perempuan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, list);
        spJk.setAdapter(adapter);


    }

    @OnClick({R.id.password, R.id.cpassword, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.password:
                break;
            case R.id.cpassword:
                break;
            case R.id.btn_save:
                stNamaDp=namaDepan.getText().toString().trim();
                stNamaBlkg=namaBlkg.getText().toString().trim();
                stEmail=email.getText().toString().trim();
                stJk=spJk.getSelectedItem().toString();
                stPassword= password.getText().toString();
                stCPassword = cpassword.getText().toString();

                //pass tidak valid

                if (stNamaDp.equals("")){
                    namaDepan.setError(getString(R.string.isEmpty));
                } else if (stNamaBlkg.equals("")){
                    namaBlkg.setError(getString(R.string.isEmpty));
                } else if (stEmail.equals("")){
                    email.setError(getString(R.string.isEmpty));
                } else if (stPassword.equals("") && stCPassword.equals("") && stPassword.equals(cpassword)){
                    password.setError(getString(R.string.isEmpty));
                }  else if (!checkAgreement.isChecked()){
                    Toast.makeText(this, "Centang", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Periksa");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setProgress(0);
                    progressDialog.show();
                    
                    final int total=100;
                    Thread timer = new Thread(){
                        @Override
                        public void run() {
                            int jumTime=0;
                            while (jumTime<total){
                                try{
                                    sleep(200);
                                    jumTime +=5;
                                    progressDialog.setProgress(jumTime);
                                    if (jumTime == total){
                                        progressDialog.dismiss();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    timer.start();
                }
                break;
        }
    }
}


















