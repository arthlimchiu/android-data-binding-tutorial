package com.imakeanapp.androiddatabindingsample

import android.databinding.ObservableInt

data class HeroVO(val id: String, val name: String, var matches: ObservableInt, val abilities: String)