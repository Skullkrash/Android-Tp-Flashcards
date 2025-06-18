package com.example.tp_flashcard.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [FlashCard::class, FlashCardCategory::class], version = 1)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDao

    companion object {
        @Volatile
        private var INSTANCE: FlashCardDatabase? = null

        fun getDatabase(context: Context): FlashCardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashCardDatabase::class.java,
                    "flashcard_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).prepopulate()
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun prepopulate() {
        val dao = flashCardDao()
        // Insert categories
        val categories = listOf(
            FlashCardCategory(1, "History"),
            FlashCardCategory(2, "Geography"),
            FlashCardCategory(3, "Science"),
            FlashCardCategory(4, "Literature")
        )
        categories.forEach { cat ->
            daoInsertCategory(cat)
        }
        // Insert flashcards
        val flashcards = listOf(
            FlashCard(0, 1, "Who was the first president of the United States?", "George Washington"),
            FlashCard(0, 1, "What year did World War II end?", "1945"),
            FlashCard(0, 2, "What is the capital of France?", "Paris"),
            FlashCard(0, 2, "What is the largest desert in the world?", "Sahara"),
            FlashCard(0, 3, "What is the chemical symbol for water?", "H2O"),
            FlashCard(0, 3, "What is the powerhouse of the cell?", "Mitochondria"),
            FlashCard(0, 4, "Who wrote 'To Kill a Mockingbird'?", "Harper Lee"),
            FlashCard(0, 4, "What is the main theme of '1984'?", "Totalitarianism")
        )
        flashcards.forEach { card ->
            daoInsertFlashCard(card)
        }
    }

    private suspend fun daoInsertCategory(category: FlashCardCategory) {
        this.openHelper.writableDatabase.execSQL(
            "INSERT INTO categories (id, name) VALUES (?, ?)",
            arrayOf(category.id, category.name)
        )
    }

    private suspend fun daoInsertFlashCard(card: FlashCard) {
        this.openHelper.writableDatabase.execSQL(
            "INSERT INTO flashcards (categoryId, question, answer) VALUES (?, ?, ?)",
            arrayOf(card.categoryId, card.question, card.answer)
        )
    }
}

