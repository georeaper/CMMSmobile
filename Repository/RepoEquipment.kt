package com.gkprojects.cmmsandroidapp.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.gkprojects.cmmsandroidapp.CMMSDatabase
import com.gkprojects.cmmsandroidapp.DataClasses.CustomerSelect
import com.gkprojects.cmmsandroidapp.DataClasses.EquipmentListInCases
import com.gkprojects.cmmsandroidapp.DataClasses.EquipmentSelectCustomerName

import com.gkprojects.cmmsandroidapp.DataClasses.Equipments
import com.gkprojects.cmmsandroidapp.DataClasses.Tickets

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RepoEquipment {
    companion object{
        var userDatabase:CMMSDatabase?=null

        private fun intialiseDB(context:Context): CMMSDatabase?
        {
            return CMMSDatabase.getInstance(context)!!
        }

        fun getEquipmentsDataByCustomerID(context: Context,id: String):LiveData<List<Equipments>>{
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().getAllDataEquipmentsByCustomerID(id)
        }


        fun insert(context: Context,equipment: Equipments)
        {
            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
            equipment.DateCreated = dateFormat.format(currentDateTime)
            equipment.LastModified=dateFormat.format(currentDateTime)
            userDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.EquipmentsDAO().addEquipments(equipment)
            }
        }


        fun getAllEquipmentData(context: Context): LiveData<List<Equipments>>
        {
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().getAllEquipments()
        }
        fun updateEquipmentData(context: Context,equipments: Equipments){
            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())

            equipments.LastModified=dateFormat.format(currentDateTime)
            if (equipments.Model==""){
                equipments.Model=null
            }
            if (equipments.EquipmentCategory==""){
                equipments.EquipmentCategory=null
            }
            if (equipments.Manufacturer==""){
                equipments.Manufacturer=null
                }

            userDatabase = intialiseDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.EquipmentsDAO().updateEquipments(equipments)
            }

        }
        fun getCustomerID(context: Context):LiveData<List<CustomerSelect>>{
            userDatabase = intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().getCustomerID()
        }
        fun getCustomerNameDashboard (context: Context):LiveData<List<EquipmentSelectCustomerName>>{
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().getCustomerName()
        }

        suspend fun delete(context: Context,equipments: Equipments){
            userDatabase= intialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch{
                userDatabase!!.EquipmentsDAO().delete(equipments)
            }


        }
        fun getRecordbyId(context: Context, id :String):LiveData<Equipments>{
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().SelectRecordById(id)
        }
        fun getEquipmentByCustomer ( context: Context, id : String):LiveData<List<EquipmentListInCases>>{
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().selectEquipmentByCustomerID(id)
        }
        fun getTicketsByEquipmentId(context: Context,id: String):LiveData<List<Tickets>>{
            userDatabase= intialiseDB(context)
            return userDatabase!!.EquipmentsDAO().getTicketsByEquipmentId(id)
        }


    }

}