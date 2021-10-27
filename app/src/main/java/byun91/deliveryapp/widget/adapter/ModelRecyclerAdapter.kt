package byun91.deliveryapp.widget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import byun91.deliveryapp.model.CellType
import byun91.deliveryapp.model.Model
import byun91.deliveryapp.screen.base.BaseViewModel
import byun91.deliveryapp.util.mapper.ModelViewHolderMapper
import byun91.deliveryapp.util.provider.ResourceProvider
import byun91.deliveryapp.widget.adapter.listener.AdapterListener
import byun91.deliveryapp.widget.adapter.viewholder.ModelViewHolder

class ModelRecyclerAdapter<M : Model, VM : BaseViewModel> (
    private var modelList: List<Model>,
    private val viewModel: VM,
    private val resourcesProvider: ResourceProvider,
    private val adapterListener : AdapterListener)
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