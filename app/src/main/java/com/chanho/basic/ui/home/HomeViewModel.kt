package com.chanho.basic.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.chanho.basic.repository.Repository

class HomeViewModel
@ViewModelInject constructor(private val repository: Repository): ViewModel()