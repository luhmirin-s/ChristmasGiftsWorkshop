package lv.luhmirins.christmasgifts.form

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_form.*
import lv.luhmirins.christmasgifts.DeleteAsync
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.MyApp
import lv.luhmirins.christmasgifts.R
import lv.luhmirins.christmasgifts.SaveAsync
import lv.luhmirins.christmasgifts.UpdateAsync

class GiftFormActivity : AppCompatActivity() {

    private var currentGift: Gift? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val currentGiftId = intent?.getLongExtra("gift_id", 0) ?: 0
        MyApp.GIFTS.getGift(currentGiftId).observe(this, Observer { nullableGift ->
            currentGift = nullableGift

            nullableGift?.let { notNullGift ->
                input_to_whom.setText(notNullGift.toWhom)
                input_gift.setText(notNullGift.giftName)
                input_notes.setText(notNullGift.notes)
            }
        })

        fab_save.setOnClickListener {
            currentGift
                ?.let { updateGift(it) }
                ?: saveGift()
            finish()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete) {
            currentGift?.let { gift ->
                DeleteAsync().execute(gift)
            }
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateGift(gift: Gift) {
        val newGift = Gift(
            uid = gift.uid,
            toWhom = input_to_whom.text.toString(),
            giftName = input_gift.text.toString(),
            notes = input_notes.text.toString()
        )
        UpdateAsync().execute(newGift)
    }

    private fun saveGift() {
        val newGift = Gift(
            toWhom = input_to_whom.text.toString(),
            giftName = input_gift.text.toString(),
            notes = input_notes.text.toString()
        )
        SaveAsync().execute(newGift)
    }

}
