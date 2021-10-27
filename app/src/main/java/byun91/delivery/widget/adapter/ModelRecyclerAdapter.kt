package byun91.delivery.widget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import byun91.delivery.model.CellType
import byun91.delivery.model.Model
import byun91.delivery.screen.base.BaseViewModel
import byun91.delivery.util.mapper.ModelViewHolderMapper
import byun91.delivery.util.provider.ResourceProvider
import byun91.delivery.widget.adapter.listener.AdapterListener
import byun91.delivery.widget.adapter.viewholder.ModelViewHolder

class ModelRecyclerAdapter<M : Model, VM : BaseViewModel> (
    private var modelList: List<Model>,
    private val viewModel: VM,
    private val resourcesProvider: ResourceProvider,
    private val adapterListener : AdapterListener
)
    : ListAdapter<Model, ModelViewHolder<M>>(Model.DIFF_CALLBACK){

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int = modelList[position].type.ordinal


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M>
    =  ModelViewHolderMapper.map(parent, CellType.values()[viewType], viewModel, resourcesProvider)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        with(holder) {
            bindData(modelList[position] as M)
            bindViews(modelList[position] as M, adapterListener)
        }
    }

    override fun submitList(list: List<Model>?) {
        list?.let { modelList = it }
        super.submitList(list)
    }
}