package byun91.delivery.screen.main.my

import byun91.delivery.databinding.FragmentHomeBinding
import byun91.delivery.databinding.FragmentMyBinding
import byun91.delivery.screen.base.BaseFragment
import byun91.delivery.screen.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding
    = FragmentMyBinding.inflate(layoutInflater)

    override fun observeData() {
    }


    companion object{
        fun newInstance() = MyFragment()
        const val TAG = "MyFragment"
    }
}