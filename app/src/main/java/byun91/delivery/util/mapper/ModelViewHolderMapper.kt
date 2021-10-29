package byun91.delivery.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import byun91.delivery.databinding.ViewholderEmptyBinding
import byun91.delivery.databinding.ViewholderRestaurantBinding
import byun91.delivery.model.CellType
import byun91.delivery.model.Model
import byun91.delivery.screen.base.BaseViewModel
import byun91.delivery.util.provider.ResourceProvider
import byun91.delivery.widget.adapter.viewholder.EmptyViewHolder
import byun91.delivery.widget.adapter.viewholder.ModelViewHolder
import byun91.delivery.widget.adapter.viewholder.restaurant.RestaurantViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourceProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.EMPTY_CELL -> {
                EmptyViewHolder(ViewholderEmptyBinding.inflate(inflater,parent,false),
                viewModel,
                resourcesProvider
                )
            }
            CellType.RESTAURANT_CELL ->{
                RestaurantViewHolder(ViewholderRestaurantBinding.inflate(inflater,parent,false),
                viewModel,
                resourcesProvider)
            }
        }
        return viewHolder as ModelViewHolder<M>
    }
}