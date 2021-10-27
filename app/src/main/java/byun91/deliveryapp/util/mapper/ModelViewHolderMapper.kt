package byun91.deliveryapp.util.mapper

import android.content.res.loader.ResourcesProvider
import android.view.LayoutInflater
import android.view.ViewGroup
import byun91.deliveryapp.databinding.ViewholderEmptyBinding
import byun91.deliveryapp.model.CellType
import byun91.deliveryapp.model.Model
import byun91.deliveryapp.screen.base.BaseViewModel
import byun91.deliveryapp.util.provider.ResourceProvider
import byun91.deliveryapp.widget.adapter.viewholder.EmptyViewHolder
import byun91.deliveryapp.widget.adapter.viewholder.ModelViewHolder

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
        }
        return viewHolder as ModelViewHolder<M>
    }
}