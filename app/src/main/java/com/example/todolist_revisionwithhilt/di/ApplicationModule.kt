package com.example.todolist_revisionwithhilt.di


import android.app.Application
import androidx.room.Room
import com.example.todolist_revisionwithhilt.Repository.RoomRepo
import com.example.todolist_revisionwithhilt.Repository.RoomRepoImpl
import com.example.todolist_revisionwithhilt.RoomDB.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            context = app,
            ToDoDatabase::class.java,
            "RoomDB"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRepository(db: ToDoDatabase): RoomRepo{
        return RoomRepoImpl(db.dao)
    }
}