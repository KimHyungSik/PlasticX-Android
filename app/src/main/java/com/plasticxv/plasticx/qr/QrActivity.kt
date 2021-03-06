package com.plasticxv.plasticx.qr

import android.view.LayoutInflater
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.plasticxv.plasticx.MyApplication
import com.plasticxv.plasticx.base.BaseActivity
import com.plasticxv.plasticx.databinding.ActivityQrBinding
import javax.inject.Inject

class QrActivity : BaseActivity<ActivityQrBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityQrBinding
            = ActivityQrBinding::inflate

    private lateinit var codeScanner: CodeScanner

    @Inject
    lateinit var viewModel: QrViewModel

    override fun setup() {
        (application as MyApplication).appComponent.inject(this)

        codeScanner = CodeScanner(this, binding.scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            codeScanner.stopPreview()
            runOnUiThread {
                viewModel.borrowTumbler(it.text) {
                    setResult(RESULT_OK)
                    finish()
                }
            }
            codeScanner.startPreview()
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "QR인식 실패",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

}