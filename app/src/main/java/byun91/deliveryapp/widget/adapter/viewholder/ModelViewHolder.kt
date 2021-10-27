package byun91.deliveryapp.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import byun91.deliveryapp.model.Model
import byun91.deliveryapp.screen.base.BaseViewModel
import byun91.deliveryapp.util.provider.ResourceProvider
import byun91.deliveryapp.widget.adapter.listener.AdapterListener

abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel,
    protected val resourceProvider: ResourceProvider
) : RecyclerView.ViewHolder(binding.root){

    abstract fun reset()

    open fun bindData(model: M) {
        reset()
    }

    abstract fun bindViews(model: Model, adapterListener: AdapterListener)


}