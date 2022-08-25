package com.example.prisewatch

import android.app.Application
import com.example.prisewatch.db.room.repo.ItemRepo

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ItemRepo.initialize(this)
    }
}