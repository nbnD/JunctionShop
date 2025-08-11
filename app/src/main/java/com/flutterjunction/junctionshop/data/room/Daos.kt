package com.flutterjunction.junctionshop.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.flutterjunction.junctionshop.data.room.models.Item
import com.flutterjunction.junctionshop.data.room.models.ItemsWithStoreAndList
import com.flutterjunction.junctionshop.data.room.models.ShoppingList
import com.flutterjunction.junctionshop.data.room.models.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM ITEMS")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM ITEMS WHERE item_id=:itemId")
    fun getItem(itemId: Int): Flow<Item>

}


@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(store: Store)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(store: Store)

    @Delete
    suspend fun delete(store: Store)

    @Query("SELECT * FROM stores")
    fun getAllStores(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE store_id=:storeId")
    fun getItem(storeId: Int): Flow<Store>

}

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Query(
        """
        SELECT * FROM ITEMS AS I INNER JOIN shopping_list AS S
        ON I.listId =S.list_id INNER JOIN stores AS ST
         ON I.storeIdFk=ST.store_id
    """
    )
    fun getItemsWithStoreAndList():Flow<List<ItemsWithStoreAndList>>

    @Query(
        """
        SELECT * FROM ITEMS AS I INNER JOIN shopping_list AS S
        ON I.listId =S.list_id INNER JOIN stores AS ST
         ON I.storeIdFk=ST.store_id WHERE S.list_id = :listId
    """
    )
    fun getItemsWithStoreAndListFilteredById(listId:Int):Flow<List<ItemsWithStoreAndList>>
    @Query(
        """
        SELECT * FROM ITEMS AS I INNER JOIN shopping_list AS S
        ON I.listId =S.list_id INNER JOIN stores AS ST
         ON I.storeIdFk=ST.store_id WHERE I.item_id = :itemId
    """
    )
    fun getItemWithStoreAndListFilteredById(itemId:Int):Flow<ItemsWithStoreAndList>

}

