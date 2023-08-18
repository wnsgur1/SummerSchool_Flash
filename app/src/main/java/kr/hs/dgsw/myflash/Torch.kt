package kr.hs.dgsw.myflash

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context: Context) {
    private var cameraId: String? = null
    private val camerManager =
        context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    init {
        cameraId = getCamerId()
    }

    private fun getCamerId():String?{
        val cameraIds = camerManager.cameraIdList
        for(id in cameraIds){
            val info = camerManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)
            if (flashAvailable != null && flashAvailable &&
                lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }

    fun flashOn(){
        cameraId?.let {
            camerManager.setTorchMode(it, true)
        }
    }
    fun flashOff(){
        cameraId?.let {
            camerManager.setTorchMode(it, false)
        }
    }
}