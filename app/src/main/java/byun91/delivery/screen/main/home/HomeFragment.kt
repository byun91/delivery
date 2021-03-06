package byun91.delivery.screen.main.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import byun91.delivery.R
import byun91.delivery.data.entity.LocationLatLngEntity
import byun91.delivery.data.entity.MapSearchInfoEntity
import byun91.delivery.databinding.FragmentHomeBinding
import byun91.delivery.screen.base.BaseFragment
import byun91.delivery.screen.main.home.restaurant.RestaurantCategory
import byun91.delivery.screen.main.home.restaurant.RestaurantListFragment
import byun91.delivery.screen.mylocation.MyLocationActivity
import byun91.delivery.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    private lateinit var locationManager : LocationManager

    override fun getViewBinding(): FragmentHomeBinding
    = FragmentHomeBinding.inflate(layoutInflater)

    private lateinit var myLocationListener: LocationListener
    private val changeLocationLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableExtra<MapSearchInfoEntity>(HomeViewModel.MY_LOCATION_KEY)
                    ?.let { myLocationInfo ->
                        viewModel.loadReverseGeoInformation(myLocationInfo.locationLatLng)
                }
            }
        }


    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions ->
            val responsePermissions = permissions.entries.filter {
                (it.key == Manifest.permission.ACCESS_FINE_LOCATION && it.value) ||
                        (it.key == Manifest.permission.ACCESS_COARSE_LOCATION && it.value)
            }
            if (responsePermissions.filter { it.value }.size == locationPermissions.size) {
                setMyLocationListener()
            } else {
                with(binding.locationTitleTextView) {
                    text = getString(R.string.please_request_location_permission)
                    setOnClickListener {
                        getMyLocation()
                    }
                }
                Toast.makeText(requireContext(), getString(R.string.can_not_assigned_permission), Toast.LENGTH_SHORT).show()
            }
        }

    override fun observeData() = viewModel.homeStateLiveData.observe(viewLifecycleOwner){
        when (it) {
            is HomeState.Uninitialized -> {
                getMyLocation()
            }
            is HomeState.Loading -> {
                binding.locationLoading.isVisible = true
                binding.locationTitleTextView.text = getString(R.string.loading)
            }
            is HomeState.Success -> {
                binding.locationLoading.isGone = true
                binding.locationTitleTextView.text = it.mapSearchInfo.fullAddress
                initViewPager(it.mapSearchInfo.locationLatLng)
            }

            is HomeState.Error -> {
                Toast.makeText(requireContext(),it.messageId,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnable) {
            locationPermissionLauncher.launch(locationPermissions)
        }
    }

    override fun initViews()  = with(binding){
        locationTitleTextView.setOnClickListener {
            viewModel.getMapSearchInfo()?.let { mapInfo ->
                changeLocationLauncher.launch(
                    MyLocationActivity.newIntent(
                        requireContext(), mapInfo
                    )
                )
            }
        }
    }

    private fun initViewPager(locationLatLng: LocationLatLngEntity) = with(binding) {
        val restaurantCategories = RestaurantCategory.values()
        if (::viewPagerAdapter.isInitialized.not()) { // :: ???????????? -> ????????? ?????? ????????? ??????
            val restaurantListFragmentList = restaurantCategories.map{
                RestaurantListFragment.newInstance(it)
            }
            viewPagerAdapter = RestaurantListFragmentPagerAdapter(
                this@HomeFragment, restaurantListFragmentList)
            viewPager.adapter = viewPagerAdapter
        }
        viewPager.offscreenPageLimit = restaurantCategories.size //?????? ???????????? ?????? ????????? ???????????? ??? ?????? ???????????????
        TabLayoutMediator(tabLayout, viewPager){ tab, pos ->
            tab.setText(restaurantCategories[pos].categoryNameId)
        }.attach()
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime = 1500L
        val minDistance = 100f
        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }
        with(locationManager){
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    companion object{

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) // permission group

        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }


    private fun removeLocationListener(){
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }


    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            viewModel.loadReverseGeoInformation(
                LocationLatLngEntity(
                    location.latitude,
                    location.longitude
                )
            )
            removeLocationListener()
        }
    }
}