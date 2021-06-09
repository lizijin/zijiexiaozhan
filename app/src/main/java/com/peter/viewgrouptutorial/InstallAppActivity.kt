package com.peter.viewgrouptutorial

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File


class InstallAppActivity : AppCompatActivity() {
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_install_app)

        val result =
            ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            println("jiangbin oncreate  ${packageManager.canRequestPackageInstalls()}")
        }
    }

    fun installMeituan(view: View) {
        val file = File(Environment.getExternalStorageDirectory(), "meituan/meituan.apk")

        val uriFromFile = Uri.fromFile(file)
        println("zijiexiaozhan  uriFromFile $uriFromFile")
        var uriFromFileProvider =
            FileProvider.getUriForFile(this, "com.meituan.install", file)
        println("zijiexiaozhan  uriFromFileProvider $uriFromFileProvider")
        var install = Intent(Intent.ACTION_VIEW)
        install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        install.setDataAndType(uriFromFileProvider, "application/vnd.android.package-archive")
        startActivity(install);
    }

    fun installTouTiao(view: View) {
        val file = File(Environment.getExternalStorageDirectory(), "toutiao/toutiao.apk")
        val uriFromFile = Uri.fromFile(file)
        println("zijiexiaozhan  uriFromFile $uriFromFile")
        var uriFromFileProvider =
            FileProvider.getUriForFile(this, "com.toutiao.install", file)
        println("zijiexiaozhan  uriFromFileProvider $uriFromFileProvider")
        var install = Intent(Intent.ACTION_VIEW)
        install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        install.setDataAndType(uriFromFileProvider, "application/vnd.android.package-archive")
        startActivity(install);
    }
}