package com.gkprojects.cmmsandroidapp.DataClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "Maintenances")
data class Maintenances(
    @PrimaryKey var MaintenanceID: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "RemoteID") var RemoteID: Int?,
    @ColumnInfo(name = "Name") var Name: String?,
    @ColumnInfo(name = "Description") var Description: String?,
    @ColumnInfo(name = "LastModified") var LastModified: String?,
    @ColumnInfo(name = "DateCreated") var DateCreated: String?,
    @ColumnInfo(name = "Version") var Version: String?
)

