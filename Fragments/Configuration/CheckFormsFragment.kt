package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkprojects.cmmsandroidapp.Adapter.AdapterCheckFormsFields
import com.gkprojects.cmmsandroidapp.Adapter.CheckFormsAdapter
import com.gkprojects.cmmsandroidapp.DataClasses.CheckForms
import com.gkprojects.cmmsandroidapp.DataClasses.Maintenances
import com.gkprojects.cmmsandroidapp.Models.CheckFormVM
import com.gkprojects.cmmsandroidapp.Models.MaintenancesVM
import com.gkprojects.cmmsandroidapp.R
import com.gkprojects.cmmsandroidapp.databinding.FragmentCheckFormsBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class CheckFormsFragment : Fragment() {
    private lateinit var binding : FragmentCheckFormsBinding
    private lateinit var maintenancesVM : MaintenancesVM
    private lateinit var checkFormsVM : CheckFormVM
    private lateinit var recyclerViewCheckForms: RecyclerView
    private lateinit var adapterCheckForms: CheckFormsAdapter
    private lateinit var adapterCheckFormField : AdapterCheckFormsFields
    private lateinit var searchViewCheckForms: SearchView
    private var tempCheckFormsList = ArrayList<Maintenances>()
    private var checkFormsFields = ArrayList<CheckForms>()
    private var maintenanceID :String? =null
    companion object {
        private const val READ_REQUEST_CODE: Int = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCheckFormsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkFormsVM = ViewModelProvider(this)[CheckFormVM::class.java]

        searchViewCheckForms=binding.checkFormSearchView
        recyclerViewCheckForms=binding.checkFormRecyclerView
        adapterCheckForms=CheckFormsAdapter(tempCheckFormsList)

        adapterCheckFormField= AdapterCheckFormsFields(checkFormsFields)

        recyclerViewCheckForms.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this.context)
            adapter= adapterCheckForms
        }
        adapterCheckForms.setOnClickListener(object :CheckFormsAdapter.OnClickListener{
            override fun onClick(position: Int, model: Maintenances) {
                showDialog(model.MaintenanceID )
               // Toast.makeText(requireContext(),"test ${model.MaintenanceID}",Toast.LENGTH_SHORT).show()
            }

        })



        maintenancesVM= ViewModelProvider(this)[MaintenancesVM::class.java]
        maintenancesVM.getAllMaintenances(requireContext()).observe(viewLifecycleOwner,Observer{
            Log.d("checkforms","$it")
            tempCheckFormsList.clear()
            tempCheckFormsList= it as ArrayList<Maintenances>
            Log.d("checkforms","$tempCheckFormsList")
            adapterCheckForms.setData(tempCheckFormsList)
        })
        val checkFormBtn =binding.checkFormFloatButton
        checkFormBtn.setOnClickListener{
            showAddMaintenanceDialog()
        }



    }

    private fun showAddMaintenanceDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_maintenance, null)
        val nameEditText = dialogView.findViewById<TextInputEditText>(R.id.nameEditText)
        val descriptionEditText = dialogView.findViewById<TextInputEditText>(R.id.descriptionEditText)

        AlertDialog.Builder(context)
            .setTitle("Add Maintenance")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString()
                val description = descriptionEditText.text.toString()

                val maintenance = Maintenances(
                    UUID.randomUUID().toString(),
                    null,
                    name,
                    description,
                    null,
                    null,
                    null
                )

                maintenancesVM.insert(requireContext(),maintenance)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                val myDataList = mutableListOf<CheckForms>()
                requireContext().contentResolver.openInputStream(uri)?.bufferedReader()?.forEachLine { line ->
                    val elements = line.split(";")
                    if(elements.size==3){
                        myDataList.add(CheckForms(UUID.randomUUID().toString(),null,maintenanceID,elements[0], elements[1],elements[2],null,null ,null))
                        Log.d("OpenFiletxt","$myDataList")
                    }
                    else{
                        Log.d("errorLines","${elements.size}")
                    }

                }
                Log.d("testOpenFile","$checkFormsFields")
                for(i in myDataList.indices){
                    checkFormsFields.add(myDataList[i])
                    GlobalScope.launch(Dispatchers.IO) { checkFormsVM.insert(requireContext(),myDataList[i])  }
                }
                adapterCheckFormField.setData(checkFormsFields)
                Log.d("testOpenFile2","$checkFormsFields")
            }
        }
    }

    private fun showDialog(maintenanceId : String ) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_checkform_fields, null)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.dialogCheckFormsFieldsDescription)
        val expectedValuesEditText = dialogView.findViewById<EditText>(R.id.dialogCheckFormsFieldsExpectedValues)
        val valueTypeEditText = dialogView.findViewById<EditText>(R.id.dialogCheckFormsFieldsTypeValues)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.dialogCheckFormsFieldsRecyclerView)
        val importBtn =dialogView.findViewById<Button>(R.id.importButton)
        importBtn.setOnClickListener {
            maintenanceID =maintenanceId
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/plain"
            }
            startActivityForResult(intent, READ_REQUEST_CODE)

        }

        val btn=dialogView.findViewById<Button>(R.id.dialogCheckFormsFieldsbtn)
        btn.setOnClickListener {
            val temp= CheckForms(UUID.randomUUID().toString(),null,maintenanceId,descriptionEditText.text.toString(),expectedValuesEditText.text.toString(),valueTypeEditText.text.toString(),null,null,null)
            GlobalScope.launch(Dispatchers.IO) { checkFormsVM.insert(requireContext(),temp)  }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this.context)
            adapter= adapterCheckFormField
        }
        checkFormsVM.getCheckFormFields(requireContext(),maintenanceId).observe(viewLifecycleOwner,Observer{
            checkFormsFields=it as ArrayList<CheckForms>
            Log.d("CheckFormsFields","$checkFormsFields $it")
            adapterCheckFormField.setData(checkFormsFields)
        })
        adapterCheckFormField.listener = object :AdapterCheckFormsFields.OnItemClickListener{
            override fun onDeleteClick(checkForms: CheckForms) {
                GlobalScope.launch(Dispatchers.IO) { checkFormsVM.delete(requireContext(), checkForms) }
                adapterCheckFormField.notifyDataSetChanged()
            }
        }


        // Set up your RecyclerView here

        AlertDialog.Builder(context)
            .setTitle("Your Dialog Title")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                // Handle the positive button click here

                maintenanceID=null
                // Do something with the input values
            }
            .setNegativeButton("Cancel"){_,_ ->
                maintenanceID=null

            }



            .show()
    }


}