package com.example.co.cuctusweather.Ac

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.co.cuctusweather.HomeActivity
import com.example.co.cuctusweather.ShowToast
import com.example.co.cuctusweather.databinding.ActivityStartBinding
import com.example.co.cuctusweather.databinding.CreateAccountLayoutBinding

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
                    val intent= Intent(this@StartActivity, HomeActivity::class.java)
                    startActivity(intent)
                    ShowToast("welcome !!!!!")
                }

            }
    private lateinit var  binding:ActivityStartBinding
    private lateinit var  perf:SharedPreferences
    @SuppressLint("CommitPrefEdits")
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
        //user password and login
        perf = getSharedPreferences("weather_password", 0)
        val editor: SharedPreferences.Editor = perf.edit()
        if (perf.getString("password","null").toString()!="null"){
            binding.addAccount.visibility=View.GONE
        }
        else {
            binding.addAccount.setOnClickListener {
                val dialogBinding: CreateAccountLayoutBinding =
                    CreateAccountLayoutBinding.inflate(layoutInflater)
                val dialog: Dialog = Dialog(this)
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialogBinding.createAccount.setOnClickListener {
                    if (dialogBinding.inputUsername.text.isEmpty() || dialogBinding.inputPassword.text.isEmpty()) {
                        ShowToast("enter your user name and password")
                    } else {
                        editor.putString("password", dialogBinding.inputPassword.text.toString())
                        editor.apply()
                        binding.addAccount.visibility = View.GONE
                        dialog.dismiss()
                    }
                }
                dialog.show()

            }
        }
        //init password
        binding.signIn.setOnClickListener {
            if (binding.inputPassword.text.isEmpty()) {
                ShowToast("please enter your password")
               binding.signIn.isEnabled=false
           }
           else{
               val prefernce:SharedPreferences=getSharedPreferences("weather_password",0)
               val edite:SharedPreferences.Editor=prefernce.edit()
               val password: String? =prefernce.getString("password","null")
               if (password==binding.inputPassword.text.toString()){
                   val intent= Intent(this@StartActivity, HomeActivity::class.java)
                   startActivity(intent)
                   ShowToast("welcome !!!!!")

               }
               else{
                   ShowToast("password is incorrect")
               }

           }
       }
        //btn_on_finger scan
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
    //every time activity start
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