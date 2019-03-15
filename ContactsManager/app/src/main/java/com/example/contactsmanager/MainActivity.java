package com.example.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button btn_show_more = findViewById(R.id.show_hide_additional_fields);
        final LinearLayout hiddenLayout = findViewById(R.id.additional_fields_container);
        final Button btn_save = findViewById(R.id.save_button);
        final Button btn_cancel = findViewById(R.id.cancel_button);

        final EditText name = findViewById(R.id.name_edit_text);
        final EditText phone = findViewById(R.id.phone_number_edit_text);
        final EditText email = findViewById(R.id.email_edit_text);
        final EditText address = findViewById(R.id.address_edit_text);
        final EditText job = findViewById(R.id.job_title_edit_text);
        final EditText company = findViewById(R.id.company_edit_text);
        final EditText website = findViewById(R.id.website_edit_text);
        final EditText im = findViewById(R.id.im_edit_text);
        btn_show_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenLayout.getVisibility() == View.VISIBLE){
                    hiddenLayout.setVisibility(View.INVISIBLE);
                    btn_show_more.setText("Show additional fields");
                } else {
                    hiddenLayout.setVisibility(View.VISIBLE);
                    btn_show_more.setText("Show less fields");
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }
                if (phone.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
                }
                if (email.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (address.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
                }
                if (job.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, job.getText().toString());
                }
                if (company.getText().toString() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website.getText().toString() != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                    contactData.add(websiteRow);
                }
                if (im.getText() != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String phoneStr = intent.getStringExtra("com.example.contactsmanager.PHONE_NUMBER_KEY");
            if (phone != null) {
                phone.setText(phoneStr);
            } else {
                Toast.makeText(this, getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
            }
        }
    }


}
