package byun91.delivery.screen.main.home

import byun91.delivery.databinding.FragmentHomeBinding
import byun91.delivery.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding
    = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object{
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }
}