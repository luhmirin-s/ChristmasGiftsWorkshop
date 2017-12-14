package lv.luhmirins.christmasgifts.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import lv.luhmirins.christmasgifts.Gift
import lv.luhmirins.christmasgifts.R
import kotlin.properties.Delegates.observable

class GiftsListAdapter(
    private val context: Context,
    private val onClick: (Long) -> Unit
) : RecyclerView.Adapter<GiftViewHolder>() {

    var giftList: List<Gift> by observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder = LayoutInflater
        .from(context)
        .inflate(R.layout.list_item, parent, false)
        .let { GiftViewHolder(it) }

    override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
        holder.bind(giftList[position], onClick)
    }

    override fun getItemCount(): Int = giftList.size
}

class GiftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Gift, onClick: (Long) -> Unit) {
        itemView.item_to_whom.text = "To ${item.toWhom}"
        itemView.item_gift.text = item.giftName
        itemView.item_card.setOnClickListener { onClick(item.uid) }
    }
}
