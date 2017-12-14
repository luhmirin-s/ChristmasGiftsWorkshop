package lv.luhmirins.christmasgifts

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Update
import android.os.AsyncTask


/*
 * This is the main SQLite database access class. In this app we use Room framework for database.
 * More info: https://developer.android.com/topic/libraries/architecture/room.html
 */
@Database(entities = [Gift::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun giftDao(): GiftDao

}

/*
 * This class describes "gifts" table in our database.
 */
@Entity(tableName = "gifts")
data class Gift(
    @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "to_whom") var toWhom: String = "",
    @ColumnInfo(name = "gift_name") var giftName: String = "",
    @ColumnInfo(name = "notes") var notes: String = ""
)

/*
 * This interface describes what operation we can do with "gifts" database.
 * Query annotations contain proper SQL queries.
 * Insert, Update and Delete annotations are also just shortcuts for corresponding SQL queries.
 */
@Dao
interface GiftDao {
    @Query("SELECT * FROM gifts")
    fun getAllGifts(): LiveData<List<Gift>>

    @Query("SELECT * FROM gifts WHERE uid = :giftId")
    fun getGift(giftId: Long): LiveData<Gift>

    @Insert
    fun saveGift(gift: Gift)

    @Update
    fun updateGift(gift: Gift)

    @Delete
    fun deleteGift(gift: Gift)
}

/*
 * Since database operations can take a long time to complete those operations
 * MUST be do in background so it does not freeze applications UI.
 * For this reason we are using very simple AsyncTasks.
 *
 * https://developer.android.com/reference/android/os/AsyncTask.html
 */

class SaveAsync : AsyncTask<Gift, Unit, Unit>() {
    override fun doInBackground(vararg params: Gift) {
        MyApp.GIFTS.saveGift(params[0])
    }
}

class UpdateAsync : AsyncTask<Gift, Unit, Unit>() {
    override fun doInBackground(vararg params: Gift) {
        MyApp.GIFTS.updateGift(params[0])
    }
}

class DeleteAsync : AsyncTask<Gift, Unit, Unit>() {
    override fun doInBackground(vararg params: Gift) {
        MyApp.GIFTS.deleteGift(params[0])
    }
}
