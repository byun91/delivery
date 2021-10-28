package byun91.delivery

import android.app.Application
import android.content.Context
import byun91.delivery.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DeliveryApp :Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DeliveryApp)
            modules(appModule) }
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