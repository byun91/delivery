package byun91.deliveryapp.widget.adapter.viewholder

import byun91.deliveryapp.databinding.ViewholderEmptyBinding
import byun91.deliveryapp.model.Model
import byun91.deliveryapp.screen.base.BaseViewModel
import byun91.deliveryapp.util.provider.ResourceProvider
import byun91.deliveryapp.widget.adapter.listener.AdapterListener

 class EmptyViewHolder(
    private val binding : ViewholderEmptyBinding,
    viewModel: BaseViewModel,
    resourceProvider: ResourceProvider
) : ModelViewHolder<Model>(binding, viewModel, resourceProvider){

    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener) = Unit

}