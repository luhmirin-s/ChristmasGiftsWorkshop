# Cheats

### Cheat 1: form activity layout
``` xml
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="lv.luhmirins.christmasgifts.form.GiftFormActivity">

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab_save"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        android:layout_marginBottom="16dp"
        android:layout_marginEnd="16dp"
        android:tint="@color/white_text"
        app:backgroundTint="@color/color_accent"
        app:fabSize="normal"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:srcCompat="@drawable/icon_save"/>

    <android.support.design.widget.TextInputLayout
        android:id="@+id/layout_to_whom"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:hint="To:"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <android.support.design.widget.TextInputEditText
            android:id="@+id/input_to_whom"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:singleLine="true"/>
    </android.support.design.widget.TextInputLayout>

    <android.support.design.widget.TextInputLayout
        android:id="@+id/layout_gift"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:hint="Gift"
        app:layout_constraintEnd_toEndOf="@+id/layout_to_whom"
        app:layout_constraintStart_toStartOf="@+id/layout_to_whom"
        app:layout_constraintTop_toBottomOf="@+id/layout_to_whom">

        <android.support.design.widget.TextInputEditText
            android:id="@+id/input_gift"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </android.support.design.widget.TextInputLayout>

    <android.support.design.widget.TextInputLayout
        android:id="@+id/layout_notes"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:hint="Notes"
        app:layout_constraintEnd_toEndOf="@+id/layout_gift"
        app:layout_constraintStart_toStartOf="@+id/layout_gift"
        app:layout_constraintTop_toBottomOf="@+id/layout_gift">

        <android.support.design.widget.TextInputEditText
            android:id="@+id/input_notes"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="top"
            android:lines="5"
            android:singleLine="false"/>
    </android.support.design.widget.TextInputLayout>

</android.support.constraint.ConstraintLayout>
```


### Cheat 2: form layout biding to actions
``` kotlin
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
```


### Cheat 3: function to save gift
``` kotlin
private fun saveGift() {
    val newGift = Gift(
        toWhom = input_to_whom.text.toString(),
        giftName = input_gift.text.toString(),
        notes = input_notes.text.toString()
    )
    SaveAsync().execute(newGift)
}
```


### Cheat 4: function to update gift
``` kotlin
private fun updateGift(gift: Gift) {
    val newGift = Gift(
        uid = gift.uid,
        toWhom = input_to_whom.text.toString(),
        giftName = input_gift.text.toString(),
        notes = input_notes.text.toString()
    )
    UpdateAsync().execute(newGift)
}
```


### Cheat 5: delete button in menu
``` kotlin
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
```


### Cheat 6: opening form activity
``` kotlin
private fun openFormActivity(giftId: Long?) {
    val intent = Intent(this, GiftFormActivity::class.java)
    giftId?.let { intent.putExtra("gift_id", it) }
    startActivity(intent)
}
```