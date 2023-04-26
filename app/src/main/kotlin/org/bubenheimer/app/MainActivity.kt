package org.bubenheimer.app

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    var broadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        broadcastReceiver = OrderedBroadcastReceiver(this)

        sendOrderedBroadcast(
                Intent("org.bubenheimer.app.SomeAction"),
                /* receiverPermission = */ null,
//                /* resultReceiver = */ OrderedBroadcastReceiver(),
                /* resultReceiver = */ broadcastReceiver,
                /* scheduler = */ null,
                /* initialCode = */ Activity.RESULT_OK,
                /* initialData = */ null,
                /* initialExtras = */ null
        )
    }
}

class OrderedBroadcastReceiver(
        private val activity: MainActivity
) : BroadcastReceiver() {
    protected fun finalize() {
        println("Finalized")
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        println("Message received")

        activity.broadcastReceiver = null
    }
}
