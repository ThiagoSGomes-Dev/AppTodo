package com.app.apptodo.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE table_task ADD COLUMN description TEXT"
        )
        db.execSQL(
            "ALTER TABLE table_task ADD COLUMN is_completed INTEGER NOT NULL DEFAULT 0"
        )
        db.execSQL(
            "ALTER TABLE table_task ADD COLUMN is_favorite INTEGER NOT NULL DEFAULT 0"
        )
    }
}