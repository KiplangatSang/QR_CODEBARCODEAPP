package com.example.qr_barcodeapplcation

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.budiyev.android.codescanner.BarcodeUtils.createBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatReader
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.Encoder.encode
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_generate_code.*
import java.lang.Exception


class GenerateCodeFragment(name:String) : Fragment(R.layout.fragment_generate_code) {

    val content = name


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        generateQRCode()

        btnbarCode.setOnClickListener {
            generateBarCode()
        }
        btnqrCode.setOnClickListener{
            generateQRCode()
        }

    }

    private fun generateQRCode(){
        try {

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            ivCode.setImageBitmap(bitmap)


        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }

    }

    private fun generateBarCode() {
        try{
        val writer = MultiFormatWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, 400, 200)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        ivCode.setImageBitmap(bitmap)
    }catch (e:Exception){
        Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
    }

    }

}