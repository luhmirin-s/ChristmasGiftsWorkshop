package lv.luhmirins.christmasgifts.form

import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.MyApp

class FormViewModel : ViewModel() {

    fun getGift(presentId: Long) = MyApp.GIFTS.getGift(presentId)

    fun saveGift(gift: Gift) {
        SaveAsync().execute(gift)
    }

    fun updateGift(gift: Gift) {
        UpdateAsync().execute(gift)
    }

    fun deleteGift(gift: Gift) {
        DeleteAsync().execute(gift)
    }
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
