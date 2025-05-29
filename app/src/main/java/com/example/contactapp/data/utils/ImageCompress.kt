package com.example.contactapp.data.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import java.io.ByteArrayOutputStream


fun ImageCompress(imageData: ByteArray): ByteArray{

    val bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.size)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,50,outputStream)
    return outputStream.toByteArray()
}