package byun91.deliveryapp

import android.app.Application
import android.content.Context

class DeliveryApp :Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    override fun onTerminate() { // 앱 프로세스 종료 시
        super.onTerminate()
        appContext = null
    }

    companion object{
        var appContext : Context? = null // terminate 에서 null 로 변경해야하기 때문에 nullable
             private set // 내부에서만 사용
    }

}