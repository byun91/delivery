package byun91.delivery.widget.adapter.viewholder

import byun91.delivery.databinding.ViewholderEmptyBinding
import byun91.delivery.model.Model
import byun91.delivery.screen.base.BaseViewModel
import byun91.delivery.util.provider.ResourceProvider
import byun91.delivery.widget.adapter.listener.AdapterListener

 class EmptyViewHolder(
     private val binding : ViewholderEmptyBinding,
     viewModel: BaseViewModel,
     resourceProvider: ResourceProvider
) : ModelViewHolder<Model>(binding, viewModel, resourceProvider){

    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener) = Unit

}