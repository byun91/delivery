package byun91.delivery.screen.mylocation

import android.content.Context
import android.content.Intent
import byun91.delivery.data.entity.MapSearchInfoEntity
import byun91.delivery.databinding.ActivityMyLocationBinding
import byun91.delivery.screen.base.BaseActivity
import byun91.delivery.screen.main.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MyLocationActivity :
    BaseActivity<MyLocationViewModel, ActivityMyLocationBinding> (){

    override val viewModel by viewModel<MyLocationViewModel>(){
        parametersOf(
            intent.getParcelableExtra<MapSearchInfoEntity>(
                HomeViewModel.MY_LOCATION_KEY
            )
        )
    }

    override fun observeData() {
    }

    override fun getViewBinding(): ActivityMyLocationBinding
    = ActivityMyLocationBinding.inflate(layoutInflater)

    companion object{
        fun newIntent(context: Context, mapSearchInfoEntity: MapSearchInfoEntity)
        = Intent(context, MyLocationActivity::class.java).apply {
            putExtra(HomeViewModel.MY_LOCATION_KEY, mapSearchInfoEntity)
        }
    }
}