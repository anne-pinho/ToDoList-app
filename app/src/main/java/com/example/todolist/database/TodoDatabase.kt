package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.models.Todo
import com.example.todolist.utils.DATABASE_NAME


@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTodoDao():TodoDao

    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}