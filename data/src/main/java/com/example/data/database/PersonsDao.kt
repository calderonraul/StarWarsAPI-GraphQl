package com.example.starwarsapigraphql.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonsDao {
    @Query("SELECT * FROM persons_table")
    fun getPersonsFromRoom(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPersons(persons:List<Person>)

    @Query("DELETE * FROM persons_table")
    fun deleteTable()

}