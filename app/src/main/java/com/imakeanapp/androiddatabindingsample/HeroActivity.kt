package com.imakeanapp.androiddatabindingsample

import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.imakeanapp.androiddatabindingsample.databinding.ActivityHeroBinding

class HeroActivity : AppCompatActivity() {

    companion object {
        const val ARG_HERO_ID = "hero_id"
    }

    private lateinit var heroVO: HeroVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHeroBinding = DataBindingUtil.setContentView(this, R.layout.activity_hero)

        val bundle = requireNotNull(intent.extras)
        val heroId = bundle.getString(ARG_HERO_ID, "")

        val repository = HeroesRepository.getInstance(applicationContext)
        repository.getHero(heroId)?.let {
            hero ->
            heroVO = HeroVO(hero.id, hero.name, ObservableInt(hero.matches), hero.abilities)
            binding.heroVO = heroVO
            binding.increaseMatches.setOnClickListener {
                heroVO.matches.set(heroVO.matches.get() + 1)
            }
        }
    }
}
