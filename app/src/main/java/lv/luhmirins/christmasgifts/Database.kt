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


@Database(entities = [Gift::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun giftDao(): GiftDao
}

@Entity(tableName = "gifts")
data class Gift(
    @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "to_whom") var toWhom: String = "",
    @ColumnInfo(name = "gift_name") var giftName: String = "",
    @ColumnInfo(name = "notes") var notes: String = ""
)

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
