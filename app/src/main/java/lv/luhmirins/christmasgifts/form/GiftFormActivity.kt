package lv.luhmirins.christmasgifts.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.R

class GiftFormActivity : AppCompatActivity() {

    private var currentGift: Gift? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
         TODO connect your layout with some actions:
          1) retrieve id of current gift from `intent`
          2) fetch gift from `MyApp.GIFTS` by observing changes
          3) cache data in `currentGift` for future use
          4) if data is not empty(null) write existing values to inputs
          5) add 'save' button click listener to either update or save
                gift to database and finish activity

          [Cheat 2]
         */
    }

    /*
     TODO write a function that:
      1) takes text from inputs
      2) creates new Gift object
      3) executes `SaveAsync` to save new gift to database

      [Cheat 3]
    */
    private fun saveGift() {
    }

    /*
    TODO write a function that:
     1) takes text from inputs
     2) creates new Gift object with same uid
     3) executes `UpdateAsync` to save gift changes to database

     [Cheat 4]
   */
    private fun updateGift(gift: Gift) {
    }


    /*
     TODO implement delete button
      1) override `onPrepareOptionsMenu(Menu)` method of `AppCompatActivity`
      2) using `menuInflates` inflate `R.menu.menu_form`
      3) don`t forget to add `return true` so that menu is displayed

      [Cheat 5]
    */

    /*
     TODO implement delete button click handling
      1) if `currentGift` is not null execute `DeleteAsync`
      2) finish current activity
      3) don't forget to `return true` to show that we handled the click

      [Cheat 5]
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete) {
        }
        return super.onOptionsItemSelected(item)
    }

}
