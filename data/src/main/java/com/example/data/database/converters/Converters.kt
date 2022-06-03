package com.example.data.database.converters

import androidx.room.TypeConverter
import com.example.data.model.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toPersons(persons:List<Person>):String{
        return Gson().toJson(
            persons,
            object :TypeToken<List<Person>>() {}.type
        )?:"[]"
    }

    @TypeConverter
    fun fromPersonsJson(json:String):List<Person>{
        return Gson().fromJson(
            json,
            object :TypeToken<List<Person>>() {}.type
        )?: emptyList()
    }
}