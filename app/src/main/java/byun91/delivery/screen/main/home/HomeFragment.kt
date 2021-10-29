package byun91.delivery.screen.main.home

import byun91.delivery.databinding.FragmentHomeBinding
import byun91.delivery.screen.base.BaseFragment
import byun91.delivery.screen.main.home.restaurant.RestaurantCategory
import byun91.delivery.screen.main.home.restaurant.RestaurantListFragment
import byun91.delivery.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    override fun getViewBinding(): FragmentHomeBinding
    = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun initViews() {
        super.initViews()
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        val restaurantCategories = RestaurantCategory.values()
        if (::viewPagerAdapter.isInitialized.not()) { // :: 더블콜론 -> 변수에 대한 속성을 참조
            val restaurantListFragmentList = restaurantCategories.map{
                RestaurantListFragment.newInstance(it)
            }
            viewPagerAdapter = RestaurantListFragmentPagerAdapter(
                this@HomeFragment, restaurantListFragmentList)
            viewPager.adapter = viewPagerAdapter
        }
        viewPager.offscreenPageLimit = restaurantCategories.size //매번 페이지가 바뀔 때마다 생성하는 게 아닌 재사용하도
        TabLayoutMediator(tabLayout, viewPager){ tab, pos ->
            tab.setText(restaurantCategories[pos].categoryNameId)
        }.attach()
    }

    companion object{
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }
}