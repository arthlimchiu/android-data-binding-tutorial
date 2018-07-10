package com.imakeanapp.androiddatabindingsample

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class HeroesRepository(val heroes: List<Hero>) {

    companion object {
        private val TAG = HeroesRepository::class.java.simpleName

        @Volatile private var INSTANCE: HeroesRepository? = null

        private var heroes: List<Hero> = mutableListOf()

        fun getInstance(context: Context): HeroesRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildRepository(context).also { INSTANCE = it }
        }

        private fun buildRepository(context: Context): HeroesRepository {
            val type = object : TypeToken<List<Hero>>() {}.type
            var jsonReader: JsonReader? = null

            try {
                val inputStream = context.assets.open(HEROES_FILENAME)
                jsonReader = JsonReader(inputStream.reader())
                heroes = Gson().fromJson(jsonReader, type)
            } catch (e: Exception) {
                Log.e(TAG, "Error reading json file", e)
            } finally {
                jsonReader?.close()
            }

            return HeroesRepository(heroes)
        }
    }

    fun fetchHeroes(): List<Hero> {
        return heroes
    }

    fun getHero(heroId: String): Hero? {
        val heroes = fetchHeroes()
        for (hero in heroes) {
            if (hero.id.contentEquals(heroId)) {
                return hero
            }
        }

        return null
    }
}