package eu.tutorials.loginandsignupwithfirebase

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class ChatPageActivity : AppCompatActivity() {

    private lateinit var rvChat: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var ivMic: ImageView
    private lateinit var ivCamera: ImageView
    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
    }

    private lateinit var mediaRecorder: MediaRecorder
    private var isRecording = false
    private val outputFilePath: String
        get() {
            val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
            return "${externalFilesDir?.absolutePath}/audio_recording.3gp"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_page)

        rvChat = findViewById(R.id.rvChat)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        ivMic = findViewById(R.id.ivMic)
        ivCamera = findViewById(R.id.camera)

        // Initialize RecyclerView and Adapter
        rvChat.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(chatMessages)
        rvChat.adapter = chatAdapter

        // Handle send button click
        btnSend.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                sendMessage(message)
                etMessage.text.clear()
            }
        }

        // Handle mic click
        ivMic.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                if (isRecording) {
                    stopAudioRecording()
                } else {
                    startAudioRecording()
                }
            } else {
                checkAndRequestPermissions()
            }
        }

        // Handle camera click
        ivCamera.setOnClickListener {
            openCamera()
        }
    }

    private fun sendMessage(message: String) {
        chatMessages.add(ChatMessage(message, System.currentTimeMillis(), true))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        rvChat.scrollToPosition(chatMessages.size - 1)
        Log.d("ChatPageActivity", "Message sent: $message") // Debug log
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            // Handle the image
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun startAudioRecording() {
        if (isRecording) return

        try {
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(outputFilePath)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                prepare()
                start()
                isRecording = true
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting audio recording", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopAudioRecording() {
        if (!isRecording) return

        try {
            mediaRecorder.apply {
                stop()
                release()
            }
            isRecording = false
            Toast.makeText(this, "Audio recording saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error stopping audio recording", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO
        )
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), REQUEST_CODE_PERMISSIONS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(this, "Permission denied for recording audio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRecording) {
            stopAudioRecording()
        }
    }
}