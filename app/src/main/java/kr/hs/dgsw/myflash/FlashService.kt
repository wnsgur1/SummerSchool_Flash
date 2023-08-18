package kr.hs.dgsw.myflash

import android.app.Service
import android.content.Intent
import android.os.IBinder

class FlashService : Service() {
    private val torch:Torch by lazy{
        Torch(this)
    }

    private var isRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "on"->{
                torch.flashOn()
                isRunning = true
            }
            "off"->{
                torch.flashOff()
                isRunning = false
            }
            else ->{
                if(isRunning){
                    torch.flashOn()
                }else{
                    torch.flashOff()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}