package com.peter.viewgrouptutorial

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class PhotoActivity : AppCompatActivity() {
    val REQUEST_SYSTEM_GALLERY = 1
    val REQUEST_SYSTEM_CROP = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        gotoSystemGallery()
    }

    /**
     * 跳转到系统相册
     */
    fun gotoSystemGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        startActivityForResult(
            intent,
            REQUEST_SYSTEM_GALLERY
        )
    }

    fun gotoSystemCrop(uri: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")

        intent.putExtra("crop", "true");// 进行修剪
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra("outputX", 360)
        intent.putExtra("outputY", 360)
        val tmpFile =
            File(externalCacheDir.toString() + File.separator + "photo" + "_" + System.currentTimeMillis() + ".png")
        var cropUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                this,
                "com.peter.viewgrouptutorial1",
                tmpFile
            )
        } else {
            Uri.fromFile(tmpFile)
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);

        grantPermissionFix(intent, cropUri)
        startActivityForResult(intent, REQUEST_SYSTEM_CROP);
    }

    private fun grantPermission(intent: Intent, uri: Uri?) {
        var flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
        flag = flag or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.addFlags(flag)
        val resInfoList = packageManager
            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            println("xiaozhan $packageName")
            grantUriPermission(packageName, uri, flag)
        }
    }

    private fun grantPermissionFix(intent: Intent, uri: Uri?) {
        var flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
        flag = flag or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.addFlags(flag)
        val resInfoList = packageManager
            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            try {
                grantUriPermission(packageName, uri, flag)
            } catch (e: Exception) {
                continue
            }
            intent.action = null
            intent.component =
                ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
            break
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SYSTEM_GALLERY -> {
                    gotoSystemCrop(data?.data!!)
                    println("xiaozhan 选择了系统图片 ${data?.data}")
                }
                REQUEST_SYSTEM_CROP -> {
                    println("xiaozhan 图片裁剪成功")
                }
            }
        }
    }
}