package com.example.co.cuctusweather

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.co.cuctusweather.databinding.ActivityStartBinding
import java.util.*
import kotlin.concurrent.schedule

class StartActivity : AppCompatActivity() {
    private var cancelationsignal:CancellationSignal?=null
    private val autenticationcallback:BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object :BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    ShowToast("error $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    val intent= Intent(this@StartActivity,HomeActivity::class.java)
                    startActivity(intent)
                    ShowToast("welcome !!!!!")
                }

            }
    private lateinit var  binding:ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CheckedBiometricsupport()
        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("fingetprint")
            .setTitle("your security is needed")
            .setDescription("this app is use the finger print")
            .setNegativeButton(
                "cancel",
                this.mainExecutor,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    ShowToast("canel")
                }).build()
        biometricPrompt.authenticate(getcancelationsignal(),mainExecutor,autenticationcallback)
        //btn_on_finger
        binding.btnUserFinger.setOnClickListener {
            val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("fingetprint")
                .setTitle("your security is needed")
                .setDescription("this app is use the finger print")
                .setNegativeButton(
                    "cancel",
                    this.mainExecutor,
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        ShowToast("canel")
                    }).build()
            biometricPrompt.authenticate(getcancelationsignal(),mainExecutor,autenticationcallback)
        }

    }
    //Support_finger
    private fun CheckedBiometricsupport() : Boolean {
        val keygardmanager: KeyguardManager =getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keygardmanager.isDeviceSecure){
            ShowToast("your device dose not have finger print")
            return false
        }
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)
            != PackageManager.PERMISSION_GRANTED){
            ShowToast("finger print is not enable")
            return false
        }
        return if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        } else true
    }
    private fun getcancelationsignal(): CancellationSignal {
        cancelationsignal= CancellationSignal()
        cancelationsignal?.setOnCancelListener {
            ShowToast("your fingerprint is canceld by user")
        }
        return cancelationsignal as CancellationSignal
    }

    override fun onStart() {
        super.onStart()
        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("fingetprint")
            .setTitle("your security is needed")
            .setDescription("this app is use the finger print")
            .setNegativeButton(
                "cancel",
                this.mainExecutor,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    ShowToast("canel")
                }).build()
        biometricPrompt.authenticate(getcancelationsignal(),mainExecutor,autenticationcallback)

    }
}