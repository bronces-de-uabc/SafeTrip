package com.example.erik.safetrip;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactSelect extends AppCompatActivity {
    TextView textView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_select);

        textView0 = (TextView)findViewById(R.id.labelSelect);

        String[] datos = new String[]{ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selectionClause = ContactsContract.Data.MIMETYPE + "=" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sort = ContactsContract.Data.DISPLAY_NAME + "ASC";

        Cursor contactsCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                datos, selectionClause, null, sort);

        textView0.setText("");

        while(contactsCursor.moveToNext()){
            textView0.append("Nombre: " + contactsCursor.getString(0) + " Numero: " + contactsCursor.getString(1) + "\n");
        }

    }

}
