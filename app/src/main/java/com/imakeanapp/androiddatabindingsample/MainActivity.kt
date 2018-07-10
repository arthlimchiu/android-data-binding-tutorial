package com.imakeanapp.androiddatabindingsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

class MainActivity : AppCompatActivity(), HeroesAdapter.OnHeroClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.heroes_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val repository = HeroesRepository.getInstance(applicationContext)
        val heroes = repository.fetchHeroes()

        val adapter = HeroesAdapter(heroes, this)
        recyclerView.adapter = adapter
    }

    override fun onHeroClick(hero: Hero) {
        val intent = Intent(this, HeroActivity::class.java)
        intent.putExtra(HeroActivity.ARG_HERO_ID, hero.id)
        startActivity(intent)
    }
}
