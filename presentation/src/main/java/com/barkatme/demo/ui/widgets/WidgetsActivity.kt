package com.barkatme.demo.ui.widgets

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.ActivityWidgetsBinding
import com.barkatme.demo.ui.base.BaseActivity


@Suppress("DEPRECATION")
class WidgetsActivity : BaseActivity() {

    companion object {
        const val APPWIDGET_HOST_ID = 1025
        const val REQUEST_PICK_APPWIDGET = 1026
        const val REQUEST_CREATE_APPWIDGET = 1027
    }

    lateinit var binding: ActivityWidgetsBinding
    private val mAppWidgetManager by lazy { AppWidgetManager.getInstance(applicationContext) }
    private val mAppWidgetHost by lazy { AppWidgetHost(this, APPWIDGET_HOST_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_widgets)
        test()
    }

    private fun selectWidget() {
        val appWidgetId = mAppWidgetHost.allocateAppWidgetId()
        val pickIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_PICK)
        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        pickIntent.addEmptyData()
        startActivityForResult(pickIntent, REQUEST_PICK_APPWIDGET)
    }

    private fun Intent.addEmptyData() {
        val customInfo = ArrayList<Parcelable>()
        putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_INFO, customInfo)
        val customExtras = ArrayList<Parcelable>()
        putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_EXTRAS, customExtras)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_APPWIDGET) {
                configureWidget(data)
            } else if (requestCode == REQUEST_CREATE_APPWIDGET) {
                if (data != null) {
                    createWidget(data)
                }
            }
        } else if (resultCode == RESULT_CANCELED && data != null) {
            val appWidgetId = data.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1)
            if (appWidgetId != -1) {
                mAppWidgetHost.deleteAppWidgetId(appWidgetId)
            }
        }
    }

    private fun configureWidget(data: Intent?) {
        val extras = data!!.extras
        val appWidgetId = extras!!.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1)
        val appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId)
        if (appWidgetInfo.configure != null) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE)
            intent.component = appWidgetInfo.configure
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            startActivityForResult(intent, REQUEST_CREATE_APPWIDGET)
        } else {
            createWidget(data)
        }
    }

    private fun createWidget(data: Intent) {
        val extras = data.extras
        val appWidgetId = extras!!.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1)
        val appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId)
        val hostView = mAppWidgetHost.createView(this, appWidgetId, appWidgetInfo)
        hostView.setAppWidget(appWidgetId, appWidgetInfo)
        binding.mainContent.addView(hostView)
    }

    private fun test() {
        for (i in 1..10) {
            try {
                val appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(i)
                val hostView = mAppWidgetHost.createView(this, i, appWidgetInfo)
                hostView.setAppWidget(i, appWidgetInfo)
                binding.mainContent.addView(hostView)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.buttonAddWidget.setOnClickListener { selectWidget() }
        mAppWidgetHost.startListening()

        val infos = mAppWidgetManager.installedProviders

        for (i in infos.indices) {
            val provider = infos[i].provider
//            val ids = mAppWidgetManager.getAppWidgetIds(provider)
            Log.d("TAG", provider.packageName + "; " + provider.className)
//            if (ids.isNotEmpty()) {
//                Log.d("TAG", "Found non-zero-length provider!")
//                id = ids[0]
//                info = infos[i]
//                break
//            }.
        }
    }

    override fun onStop() {
        super.onStop()
        mAppWidgetHost.stopListening()
    }

    fun removeWidget(hostView: AppWidgetHostView) {
        mAppWidgetHost.deleteAppWidgetId(hostView.appWidgetId)
        binding.mainContent.removeView(hostView)
    }
}