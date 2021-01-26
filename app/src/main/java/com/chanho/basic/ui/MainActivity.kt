package com.chanho.basic.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var binding :ActivityMainBinding
    private val adapter by lazy {
        MainAdapter(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.recyclerview.adapter = adapter
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                performAction()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    this@MainActivity,
                    "Permission Denied\n$deniedPermissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .check()
        onObserve()
    }

    private fun onObserve(){
        viewModel.storeInfo.observe(this) {
            adapter.deleteItems()
            adapter.setItems(it.stores)
        }
    }

    @SuppressLint("MissingPermission")
    private fun performAction() {
        fusedLocationClient?.let {
            it.lastLocation
            .addOnFailureListener(this,
                OnFailureListener { e: Exception ->
                    Log.e(
                        TAG,
                        "performAction: ",
                        e.cause
                    )
                }
            )
            .addOnSuccessListener(
                this,
                OnSuccessListener<Location> { location: Location? ->
                    Log.d(TAG, "performAction: $location")
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        Log.d(
                            TAG,
                            "getLatitude: " + location.latitude
                        )
                        Log.d(
                            TAG,
                            "getLongitude: " + location.longitude
                        )
                        location.latitude = 37.188078
                        location.longitude = 127.043002
                        viewModel.location = location
                        viewModel.fetchStoreInfo()
                    }
                }
            )
        }
    }

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }


}