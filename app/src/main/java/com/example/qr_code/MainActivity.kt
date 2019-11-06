package com.example.qr_code

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
// i access internet to work my aapp
// implement following dependency for scan QR code or BAR code both
// implementation 'com.journeyapps:zxing-android-embedded:3.6.0'
// Remember first imlement dependency and then work otherwise it may failed our gradle
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_scan.setOnClickListener {
           val scanner = IntentIntegrator(this)
            // line 22 will be used to scan only qr code not bar code
           // scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            // line 24 remove our beep sound when it will scanned
           // scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

       // if code is scanabble or not
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            // if  qr is null then
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                }
                // if qr scanned then what happend
                else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
