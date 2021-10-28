package byun91.delivery.screen.main.home.restaurant

import android.util.Log
import androidx.core.os.bundleOf
import byun91.delivery.databinding.FragmentListBinding
import byun91.delivery.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment : BaseFragment<RestaurantListViewModel, FragmentListBinding>() {
    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }
    override val viewModel by viewModel<RestaurantListViewModel> {
        (parametersOf(restaurantCategory))
    }

        override fun getViewBinding(): FragmentListBinding
                = FragmentListBinding.inflate(layoutInflater)

        override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner){
            Log.e("restaurantList", it.toString())
        }

        companion object{

        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"

        fun newInstance(restaurantCategory: RestaurantCategory) : RestaurantListFragment{
            return RestaurantListFragment().apply {
                arguments =  bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory
                )
            }
        }
    }

}