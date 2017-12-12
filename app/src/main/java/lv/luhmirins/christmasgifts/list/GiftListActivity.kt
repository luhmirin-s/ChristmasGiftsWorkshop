package lv.luhmirins.christmasgifts.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import lv.luhmirins.christmasgifts.R
import lv.luhmirins.christmasgifts.form.GiftFormActivity

class GiftListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val adapter = GiftsListAdapter(this) { giftId ->
            openFormActivity(giftId)
        }

        list_recycler.layoutManager = LinearLayoutManager(this)
        list_recycler.adapter = adapter

        ViewModelProviders.of(this).get(GiftListViewModel::class.java)
            .getAllPresents()
            .observe(this, Observer { gifts ->
                gifts?.let { adapter.giftList = gifts }
            })

        list_add.setOnClickListener {
            openFormActivity(null)
        }
    }

    private fun openFormActivity(giftId: Long?) {
        val intent = Intent(this, GiftFormActivity::class.java)
        giftId?.let { intent.putExtra("gift_id", it) }
        startActivity(intent)
    }
}
