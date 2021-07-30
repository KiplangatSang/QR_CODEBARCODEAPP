package com.example.qr_barcodeapplcation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

const val CAMERA_PERMISSION_REQUEST_CODE = 1
class MainActivity : AppCompatActivity() {
    var name:String=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionRequest()

        val scanCodeFragment = ScanCodeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment,scanCodeFragment)
            addToBackStack(null)
            commit()
        }


        btnGenerateCode.setOnClickListener {
            name = etName.text.toString()

            val generateCodeFragment = GenerateCodeFragment(name)
            if(name.trim().isNotEmpty() && name!= null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, generateCodeFragment)
                addToBackStack(null)
                commit()

            }
            }
                else{
                Toast.makeText(this,"Enter a Name",Toast.LENGTH_LONG).show()
            }

        }


        btnScanCode.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment,scanCodeFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    // asking for users permission
    private fun permissionRequest(){
        val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    fun makeRequest(){
        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CAMERA),
        CAMERA_PERMISSION_REQUEST_CODE
                )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when(requestCode){
        CAMERA_PERMISSION_REQUEST_CODE ->{
            if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera Permission is required",Toast.LENGTH_LONG).show()
            }
        }
    }

    }


}