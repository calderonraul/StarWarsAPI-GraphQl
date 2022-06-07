package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converters.Converters
import com.example.data.model.allPeople.Person
import com.example.data.model.personDetail.PersonX


@Database(entities = [Person::class, PersonX::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PersonsDatabase : RoomDatabase() {
    abstract val personsDao: PersonsDao
}