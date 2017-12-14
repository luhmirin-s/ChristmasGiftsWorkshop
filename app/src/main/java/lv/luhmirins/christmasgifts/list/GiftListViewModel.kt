package lv.luhmirins.christmasgifts.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.MyApp

/*
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */
class GiftListViewModel : ViewModel() {

    private var data: LiveData<List<Gift>>? = null

    fun getAllGifts(): LiveData<List<Gift>> {
        if (data == null) {
            data = MyApp.GIFTS.getAllGifts()
        }
        return data!!
    }

    override fun onCleared() {
        data = null
    }
}
