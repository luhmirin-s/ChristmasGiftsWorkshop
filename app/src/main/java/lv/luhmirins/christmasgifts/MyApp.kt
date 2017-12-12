package lv.luhmirins.christmasgifts

import android.app.Application
import android.arch.persistence.room.Room

class MyApp : Application() {

    companion object {
        lateinit var GIFTS: GiftDao
    }

    override fun onCreate() {
        super.onCreate()

        GIFTS = Room
            .databaseBuilder(this, AppDb::class.java, "gifts-db")
            .build()
            .giftDao()
    }
}
