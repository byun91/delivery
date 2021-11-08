package byun91.delivery.screen.mylocation

import android.content.Context
import android.content.Intent
import androidx.core.view.isGone
import androidx.core.view.isVisible
import byun91.delivery.R
import byun91.delivery.data.entity.MapSearchInfoEntity
import byun91.delivery.databinding.ActivityMyLocationBinding
import byun91.delivery.screen.base.BaseActivity
import byun91.delivery.screen.main.home.HomeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MyLocationActivity :
    BaseActivity<MyLocationViewModel, ActivityMyLocationBinding> (), OnMapReadyCallback{

    override val viewModel by viewModel<MyLocationViewModel>(){
        parametersOf(
            intent.getParcelableExtra<MapSearchInfoEntity>(
                HomeViewModel.MY_LOCATION_KEY
            )
        )
    }

    override fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            finish()
        }
        confirmButton.setOnClickListener {

        }
        setupGoogleMap()
    }

    private fun setupGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun observeData() = viewModel.myLocationStateLiveData.observe(this){
        when(it){
            is MyLocationState.Loading ->{
                handleLoadingState()
            }
            is MyLocationState.Success ->{
                handleSuccessState(it)
            }
            is MyLocationState.Confirm ->{

            }
            else -> Unit
        }
    }

    private fun handleLoadingState() = with(binding) {
        locationLoading.isVisible = true
        locationTitleTextView.text = getString(R.string.loading)
    }

    private fun handleSuccessState(state: MyLocationState.Success) = with(binding) {
        val info = state.mapSearchInfoEntity
        locationLoading.isGone = true
        locationTitleTextView.text = info.fullAddress
    }

    override fun getViewBinding(): ActivityMyLocationBinding
    = ActivityMyLocationBinding.inflate(layoutInflater)


    companion object{
        fun newIntent(context: Context, mapSearchInfoEntity: MapSearchInfoEntity)
        = Intent(context, MyLocationActivity::class.java).apply {
            putExtra(HomeViewModel.MY_LOCATION_KEY, mapSearchInfoEntity)
        }
    }

    private lateinit var map : GoogleMap


    override fun onMapReady(map: GoogleMap) {
        this.map = map
        viewModel.fetchData()
    }
}