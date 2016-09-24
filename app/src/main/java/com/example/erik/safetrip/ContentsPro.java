package com.example.erik.safetrip;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onegravity.contactpicker.contact.Contact;
import com.onegravity.contactpicker.contact.ContactDescription;
import com.onegravity.contactpicker.contact.ContactSortOrder;
import com.onegravity.contactpicker.core.ContactPickerActivity;
import com.onegravity.contactpicker.group.Group;
import com.onegravity.contactpicker.picture.ContactPictureType;

import java.util.List;


public class ContentsPro extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CONTACT = 0;
    private Button btn;
    private TextView textView;
    private boolean mDarkTheme;
    private static final String EXTRA_DARK_THEME = "EXTRA_DARK_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_pro);

        btn = (Button)findViewById(R.id.buttonC);
        textView = (TextView)findViewById(R.id.texto);

        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonC:
                //ObtenerDatos();
                Intent intent = new Intent(this, ContactPickerActivity.class)
                        .putExtra(ContactPickerActivity.EXTRA_THEME, mDarkTheme ? R.style.AppTheme : R.style.ContactPicker_Theme_Dark)
                        .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE, ContactPictureType.ROUND.name())
                        .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION, ContactDescription.ADDRESS.name())
                        .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                        .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER, ContactSortOrder.AUTOMATIC.name());
                startActivityForResult(intent, REQUEST_CONTACT);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK &&
                data != null && data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA)) {

            // we got a result from the contact picker

            // process contacts
            List<Contact> contacts = (List<Contact>) data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA);
            for (Contact contact : contacts) {
                // process the contacts...

            }

            // process groups
            List<Group> groups = (List<Group>) data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA);
            for (Group group : groups) {
                // process the groups...

            }
        }
    }
//    public void ObtenerDatos(){
//
//        String[] projeccion = new String[] { /*ContactsContract.Data._ID,*/ ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER/*, ContactsContract.CommonDataKinds.Phone.TYPE*/ };
//        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
//                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
//                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
//        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";
//
//        Cursor c = getContentResolver().query(
//                ContactsContract.Data.CONTENT_URI,
//                projeccion,
//                selectionClause,
//                null,
//                sortOrder);
//
//        textView.setText("");
//
//
//        while(c.moveToNext()){
//            textView.append(/*"Identificador: " + c.getString(0) + */" Nombre: " + c.getString(0) + " Número: " + c.getString(1)/*+  " Tipo: " + c.getString(3)*/+"\n");
//        }
//        c.close();
//
//        /*contactsCursor = getContentResolver().query(
//                ContactsContract.Contacts.CONTENT_URI,   // URI de contenido para los contactos
//                projection,                        // Columnas a seleccionar
//                selectionClause                    // Condición del WHERE
//                selectionArgs,                     // Valores de la condición
//                sortOrder);                        // ORDER BY columna [ASC|DESC]*/
//
//    }
}
