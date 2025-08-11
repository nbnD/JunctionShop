package com.flutterjunction.junctionshop

import android.content.Context
import com.flutterjunction.junctionshop.data.room.ShoppingListDatabase
import com.flutterjunction.junctionshop.ui.repository.Repository

object Graph {

    lateinit var db: ShoppingListDatabase
        private set

    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            storeDao = db.storeDao(),
            itemDao = db.itemDao()
        )
    }

    fun provide(context: Context){
        db=ShoppingListDatabase.getDatabase(context)
    }
}