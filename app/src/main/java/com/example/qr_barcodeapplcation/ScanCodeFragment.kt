package com.example.qr_barcodeapplcation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.fragment_scan_code.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class ScanCodeFragment : Fragment(R.layout.fragment_scan_code) {
   private lateinit var codeScanner:CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scanCode()
    }

    private fun scanCode(){
        codeScanner = context?.let { CodeScanner(it,codescanner_view) }!!

        codeScanner.apply {
            camera =CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
             activity?.runOnUiThread{
                 val text = it.text
                 tv_itemsview.text = text.toString()
             }
                
                errorCallback = ErrorCallback {
                       activity?.runOnUiThread {
                           Log.e("main", "Cannot Initialize Camera: ${it.message}")
                       }
                }

            }

            codescanner_view.setOnClickListener{
                codeScanner.startPreview()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }


}