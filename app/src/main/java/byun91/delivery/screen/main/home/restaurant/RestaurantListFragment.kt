package byun91.delivery.screen.main.home.restaurant

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import byun91.delivery.databinding.FragmentListBinding
import byun91.delivery.model.restaurant.RestaurantModel
import byun91.delivery.screen.base.BaseFragment
import byun91.delivery.util.provider.ResourceProvider
import byun91.delivery.widget.adapter.ModelRecyclerAdapter
import byun91.delivery.widget.adapter.listener.restaurant.RestaurantListListener
import org.koin.android.ext.android.inject
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
        adapter.submitList(it)
    }

    private val resourceProvider by inject<ResourceProvider>()

    override fun initViews() = with(binding) {
        recyclerVIew.adapter = adapter
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(listOf(),viewModel,resourceProvider,
            adapterListener = object : RestaurantListListener{
                override fun onClickItem(model: RestaurantModel) {
                    Toast.makeText(requireContext(),"$model", Toast.LENGTH_LONG).show()
                }

            })
    }





    companion object{

        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"

        fun newInstance(restaurantCategory: RestaurantCategory) : RestaurantListFragment
        = RestaurantListFragment().apply {
                arguments =  bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory
                )
            }
    }

}