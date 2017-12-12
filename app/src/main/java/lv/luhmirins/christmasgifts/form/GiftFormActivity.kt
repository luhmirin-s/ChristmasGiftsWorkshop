package lv.luhmirins.christmasgifts.form

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_form.*
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.R

class GiftFormActivity : AppCompatActivity() {

    private lateinit var viewModel: FormViewModel
    private var currentGift: Gift? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(FormViewModel::class.java)

        val currentGiftId = intent?.getLongExtra("gift_id", 0) ?: 0

        viewModel.getGift(currentGiftId).observe(this, Observer<Gift> { gift ->
            currentGift = gift

            currentGift?.let { notNullGift ->
                input_to_whom.setText(notNullGift.toWhom)
                input_present.setText(notNullGift.giftName)
                input_notes.setText(notNullGift.notes)
            }

            fab_save.setOnClickListener {
                currentGift
                    ?.let { notNullGift -> updatePresent(notNullGift, viewModel) }
                    ?: savePresent(viewModel)

                finish()
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete) {
            currentGift?.let { viewModel.deleteGift(it) }
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updatePresent(gift: Gift, viewModel: FormViewModel) {
        val newGift = Gift(
            uid = gift.uid,
            toWhom = input_to_whom.text.toString(),
            giftName = input_present.text.toString(),
            notes = input_notes.text.toString()
        )
        viewModel.updateGift(newGift)
    }

    private fun savePresent(viewModel: FormViewModel) {
        val newGift = Gift(
            toWhom = input_to_whom.text.toString(),
            giftName = input_present.text.toString(),
            notes = input_notes.text.toString()
        )
        viewModel.saveGift(newGift)
    }

}
