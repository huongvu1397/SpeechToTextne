package com.example.speechtotextne

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.AudioManager
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.R.string.cancel
import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), RecognitionListener {
    private var speech: SpeechRecognizer? = null
    private var A = "VoiceRecognitionActivity"
    private var intentSpeech: Intent? = null
    private var langSetting = "en-US"
    private var C = false
    private var T = false
    private var r: CountDownTimer? = null
    private var Z = false
    private var M: AudioManager? = null
    private var N = 0
    private var K: Toast? = null
    private var Y = ""
    private var t: EditText? = null
    private var u: EditText? = null
    var o = true



    fun MainActivity() {
        this.T = true
        this.r = object : CountDownTimer(4000, 1000) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }

    }

    companion object {
        const val lang = "en-US"
    }


    override fun onReadyForSpeech(params: Bundle?) {
        this.r?.start()
        Log.e(this.A, "onReadyForSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {
    }

    override fun onBufferReceived(bArr: ByteArray?) {
        //this.I = false;
        var str = this.A
        var stringBuilder = StringBuilder()
        stringBuilder.append("onBufferReceived: ")
        stringBuilder.append(bArr)
        Log.i(str, stringBuilder.toString())
    }

    @SuppressLint("ShowToast")
    override fun onPartialResults(bundle: Bundle?) {
        val stringArrayList = bundle?.getStringArrayList("results_recognition")
        val stringArrayList2 = bundle?.getStringArrayList("android.speech.extra.UNSTABLE_TEXT")
        var str = if (stringArrayList != null) stringArrayList[0] as String else "... "
        var str2 = if (stringArrayList2 != null) stringArrayList2[0] as String else ".."
        var stringBuilder = StringBuilder()
        stringBuilder.append(str)
        stringBuilder.append(str2)
        str2 = stringBuilder.toString()
        if (this.K == null) {
            this.K = Toast.makeText(this, str2, Toast.LENGTH_SHORT)
        }
        this.K?.setGravity(17, 0, 0)
        this.K?.setText(str2)
        this.K?.show()
        str = this.A
        val stringBuilder2 = StringBuilder()
        stringBuilder2.append("onPartialResults ")
        stringBuilder2.append(str2)
        Log.i(str, stringBuilder2.toString())
        this.Y = str2
        this.Z = java.lang.Boolean.valueOf(true)
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.i(this.A, "onEvent")
    }

    override fun onBeginningOfSpeech() {
        Log.i("HVV1312", "onBeginningOfSpeech")
        this.r?.cancel()
    }

    override fun onEndOfSpeech() {
        this.r?.start()
        Log.i(this.A, "onEndOfSpeech")

    }

    override fun onError(error: Int) {
        var c = c(error)
        var str = this.A
        var stringBuilder = StringBuilder()
        stringBuilder.append("FAILED ")
        stringBuilder.append(c)
        Log.d(str, stringBuilder.toString())

    }

    override fun onResults(results: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.r = object : CountDownTimer(4000, 1000) {
            override fun onFinish() {
                C()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }
        this.t = findViewById(R.id.txt)
        this.t?.setTextIsSelectable(true)

        this.t?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(charSequence:CharSequence, i:Int, i2:Int, i3:Int) {}
            override fun onTextChanged(charSequence:CharSequence, i:Int, i2:Int, i3:Int) {}
            override fun afterTextChanged(editable: Editable) {
                this@MainActivity.y()
            }
        })

        if (!o)
        {
            //p()
        }
        else
        {
            //this.S = defaultSharedPreferences.getString("LastFile", "noLastFile")
            //this.x = this.S
            //this.v.setText(this.x)
//            stringBuilder = StringBuilder()
//            stringBuilder.append(this.E)
//            stringBuilder.append("/")
//            stringBuilder.append(this.S)
//            val file = File(stringBuilder.toString())
//            stringBuilder = StringBuilder()
//            try
//            {
//                val bufferedReader = BufferedReader(FileReader(file))
//                while (true)
//                {
//                    readLine = bufferedReader.readLine()
//                    if (readLine == null)
//                    {
//                        break
//                    }
//                    stringBuilder.append(readLine)
//                    stringBuilder.append(10)
//                }
//                bufferedReader.close()
//            }
//            catch (unused: IOException) {
//                this.t?.setText(stringBuilder.toString())
//                this.t?.setSelection(this.t.getText().length)
//                this.B = this.t.getText().toString()
//                this.L = true
//            }
        }

        this.M = getSystemService(Context.AUDIO_SERVICE) as (AudioManager)
        this.N = M?.getStreamVolume(AudioManager.STREAM_MUSIC)!!
        //var abcd = Settings.Secure.getString(applicationContext.contentResolver, "voice_recognition_service")
        speech = SpeechRecognizer.createSpeechRecognizer(this)
        speech?.setRecognitionListener(this)
        intentSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentSpeech?.putExtra(RecognizerIntent.EXTRA_LANGUAGE, langSetting)
        intentSpeech?.putExtra("calling_package", packageName)
        intentSpeech?.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intentSpeech?.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        intentSpeech?.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        speak.setOnClickListener {
            if (C) {
                C = false
                if (speech != null) {
                    speech?.stopListening()
                    speech?.destroy()
                }
                r?.cancel()
                M?.setStreamVolume(3, N, 0)
                //z
                speak.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#757575"))
                return@setOnClickListener
            }
            C = true
            speech?.setRecognitionListener(this)
            speech?.startListening(this.intentSpeech)
            speak.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#afafaf"))

            M?.setStreamVolume(3, 0, 0)
            M?.getStreamVolume(3)
        }
    }

    fun c(i: Int): String {
        val str: String
        when (i) {
            1 -> return "Network timeout"
            2 -> return "Network error"
            3 -> return "Audio recording error"
            4 -> {
                str = "error from server"
                this.r?.cancel()
                (findViewById<FloatingActionButton>(R.id.speak)).backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#757575"))
                Toast.makeText(
                    applicationContext,
                    "SERVER ERROR. Please check your internet connection",
                    Toast.LENGTH_LONG
                ).show()
                return str
            }
            5 -> {
                str = "Client side error"
                C()
                return str
            }
            6 -> return "No speech input"
            7 -> {
                str = "No match"
                C()
                return str
            }
            8 -> return "RecognitionService busy"
            9 -> return "Insufficient permissions"
            else -> return "Didn't understand, please try again."
        }
    }

    private fun C() {
        if (this.speech != null) {
            this.speech?.destroy()
        }
        this.speech?.setRecognitionListener(this)
        this.speech?.startListening(this.intentSpeech)
    }

    fun y() {
        val trim = this.t?.text.toString().trim()
        if (trim.isEmpty())
        {
           // this.w.setText("0")
        }
        else
        {
            //this.w.setText((trim.split(("\\s+").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray().size).toString())
        }
    }

//    fun p() {
//        val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss")
//        val date = Date()
//        val stringBuilder = StringBuilder()
//        stringBuilder.append("Speech_")
//        stringBuilder.append(simpleDateFormat.format(date))
//        stringBuilder.append(".txt")
//        this.x = stringBuilder.toString()
//        this.v.setText(this.x)
//        this.t.setText("")
//        this.B = ""
//        this.L = false
//        n()
//    }
}
