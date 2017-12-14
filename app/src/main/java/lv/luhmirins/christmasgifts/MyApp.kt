package lv.luhmirins.christmasgifts

import android.app.Application
import android.arch.persistence.room.Room

/*
 * This is a main class of our app. It is preserved in memory while app is running so it
 * is relatively safe to store out database access object here.
 */
class MyApp : Application() {

    companion object {
        lateinit var GIFTS: GiftDao
    }

    /*
     * This is an application lifecycle callback that is executed single time when application starts.
     */
    override fun onCreate() {
        super.onCreate()

        GIFTS = Room
            .databaseBuilder(this, AppDb::class.java, "gifts-db")
            .build()
            .giftDao()
    }
}
