package com.flutterjunction.junctionshop.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity("shopping_list")
data class ShoppingList(
    @ColumnInfo("list_id")
    @PrimaryKey
    val id: Int,
    val name: String
)


@Entity("items")
data class Item(
    @ColumnInfo("item_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val qty: String,
    val listId: Int,
    val storeIdFk: Int,
    val date: Date,
    val isChecked: Boolean

)

@Entity("stores")
data class Store(
    @ColumnInfo("store_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listIdFk: Int,
)
data class ItemsWithStoreAndList(

    @Embedded val item: Item,
    @Embedded val shoppingList: ShoppingList,
    @Embedded val store: Store
)