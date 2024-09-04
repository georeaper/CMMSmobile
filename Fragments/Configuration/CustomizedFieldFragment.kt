package com.gkprojects.cmmsandroidapp.Fragments.Configuration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkprojects.cmmsandroidapp.Adapter.CustomizedField
import com.gkprojects.cmmsandroidapp.Adapter.CustomizedFieldsAdapter
import com.gkprojects.cmmsandroidapp.Fragments.TechnicalCases.CaseInsertFragment
import com.gkprojects.cmmsandroidapp.R
import com.gkprojects.cmmsandroidapp.databinding.FragmentCustomizedFieldBinding
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog
import java.io.File


class CustomizedFieldFragment : Fragment() {


    private lateinit var binding: FragmentCustomizedFieldBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomizedFieldBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customizedFieldsWorkOrderReportType.setOnClickListener {
            showFragment(WorkOrderReportTypeListFragment())
        }
        binding.customizedFieldsModel.setOnClickListener {
            showFragment(ModelListFragment())
        }
        binding.customizedFieldsCategory.setOnClickListener {
            showFragment(CategoryAssetListFragment())
        }
        binding.customizedFieldsManufacturer.setOnClickListener {
            showFragment(ManufacturerListFragment())
        }
        binding.customizedFieldsTechnicalCasesUrgency.setOnClickListener {
            showFragment(TicketUrgencyListFragment())
        }


    }

    private  fun showFragment(fragment : Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frameLayout1, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


}
