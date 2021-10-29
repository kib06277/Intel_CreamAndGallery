package com.example.intel_creamandgallery

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor


class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //val intent = Intent("android.intent.action.GET_CONTENT")
            //intent.type = "image/*"
            //startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

            //val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (e:Exception){
            Log.i("EE", "e1 = " + e)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        val uri: Uri? = data!!.data
        Log.d("EE", "image file path is " + getAbsolutePath(this, uri!!))
    }

    @SuppressLint("Range")
    private fun getAbsolutePath(context: Context, uri: Uri): String? {
        try {
            val localContentResolver: ContentResolver = context.getContentResolver()
            val localCursor: Cursor? = localContentResolver.query(uri, null, null, null, null)
            localCursor!!.moveToFirst()

            Log.i("EE", "uri = " + uri)
            Log.i("EE", "uri.authority = " + uri.authority)
            Log.i("EE", "uri.path = " + uri.path)
            Log.i("EE", "uri.host = " + uri.host)

            Log.i("EE", "localCursor = " + localCursor)
            Log.i("EE", "localCursor.count = " + localCursor.count)
            Log.i("EE", "localCursor!!.getColumnName(0) = " + localCursor!!.getColumnName(0))
            Log.i("EE", "localCursor!!.getColumnName(1) = " + localCursor!!.getColumnName(1))
            Log.i("EE", "localCursor!!.getColumnName(2) = " + localCursor!!.getColumnName(2))
            Log.i("EE", "localCursor!!.getColumnName(3) = " + localCursor!!.getColumnName(3))
            Log.i("EE", "localCursor!!.getColumnName(4) = " + localCursor!!.getColumnName(4))
            Log.i("EE", "localCursor!!.getColumnName(5) = " + localCursor!!.getColumnName(5))

            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("document_id")))
            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("mime_type")))
            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("_display_name")))
            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("last_modified")))
            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("flags")))
            Log.i("EE", "return = " + localCursor!!.getString(localCursor!!.getColumnIndex("_size")))

            return localCursor!!.getString(localCursor!!.getColumnIndex("document_id"))
        } catch (e:Exception){
            Log.i("EE", "e2 = " + e)
            return null
        }
    }
}