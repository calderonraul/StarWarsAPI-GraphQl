package com.example.data.database.converters

import androidx.room.TypeConverter
import com.example.data.model.Homeworld
import com.example.data.model.Person
import com.example.data.model.Species
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

    @TypeConverter
    fun toSpecies(species: Species?):String{
        return Gson().toJson(
            species,
            object:TypeToken<Species>() {} .type
        )?:""
    }

    @TypeConverter
    fun fromSpecies(json: String?):Species?{
        return Gson().fromJson(
            json,
            object : TypeToken<Species?>() {} .type
        )
    }
    @TypeConverter
    fun toHomeWorld(homeworld: Homeworld?):String?{
        return Gson().toJson(
            homeworld,
            object : TypeToken<Homeworld?>() {} .type
        )
    }
    @TypeConverter
    fun fromHomeworld(json: String):Homeworld{
        return Gson().fromJson(
            json,
            object : TypeToken<Homeworld>() {} .type
        )
    }
}