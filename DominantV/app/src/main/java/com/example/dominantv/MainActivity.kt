package com.example.dominantv

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dominantv.R
import java.io.IOException


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val camera = findViewById<Button>(R.id.button1)
        val gallery = findViewById<Button>(R.id.button2)
        val processImage = findViewById<Button>(R.id.button3)


        camera.setOnClickListener({
            if(checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent,200)
            }
            else{
                requestPermissions(arrayOf(Manifest.permission.CAMERA),100)
            }
        })

        gallery.setOnClickListener(View.OnClickListener {
            val cameraIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(cameraIntent,5)
        })









    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var ImageView = findViewById<ImageView>(R.id.imageView2)
        if(resultCode== RESULT_OK){
            if(requestCode == 200){
                var image = data?.extras!!["data"] as Bitmap?
                val dimension = Math.min(image!!.width, image.height)
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
                ImageView.setImageBitmap(image)
                image = Bitmap.createScaledBitmap(image,200,200,false)
//                classifyImage(image)

            }
            else{
                val data : Uri? = data?.data
                var image: Bitmap? = null
                try{
                    image = MediaStore.Images.Media.getBitmap(this.contentResolver, data)
                } catch (e: IOException){
                    e.printStackTrace()
                }
                ImageView.setImageBitmap(image)
                image = image?.let { Bitmap.createScaledBitmap(it, 200, 200, false) }
                if (image != null) {
//                    classifyImage(image)
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

}