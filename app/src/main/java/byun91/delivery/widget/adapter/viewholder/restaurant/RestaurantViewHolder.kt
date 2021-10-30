package byun91.delivery.widget.adapter.viewholder.restaurant

import byun91.delivery.R
import byun91.delivery.databinding.ViewholderRestaurantBinding
import byun91.delivery.extensions.load
import byun91.delivery.model.restaurant.RestaurantModel
import byun91.delivery.screen.base.BaseViewModel
import byun91.delivery.util.provider.ResourceProvider
import byun91.delivery.widget.adapter.listener.AdapterListener
import byun91.delivery.widget.adapter.listener.restaurant.RestaurantListListener
import byun91.delivery.widget.adapter.viewholder.ModelViewHolder

class RestaurantViewHolder(
     private val binding : ViewholderRestaurantBinding,
     viewModel: BaseViewModel,
     resourceProvider: ResourceProvider
) : ModelViewHolder<RestaurantModel>(binding, viewModel, resourceProvider){

    override fun reset() = with(binding) {
    }

    override fun bindData(model: RestaurantModel) {
        super.bindData(model)
        with(binding) {
            restaurantImage.load(model.restaurantImageUrl, 24f)
            restaurantTitleText.text = model.restaurantTitle
            gradeText.text = resourceProvider.getString(R.string.grade_format, model.grade)
            reviewCountText.text = resourceProvider.getString(R.string.review_count, model.reviewCount)
            val (minTime, maxTime) = model.deliveryTimeRange
            deliveryTimeText.text = resourceProvider.getString(R.string.delivery_time, minTime, maxTime)

            val (minTip, maxTip) = model.deliveryTipRange
            deliveryTipText.text = resourceProvider.getString(R.string.delivery_tip, minTip, maxTip)
        }
    }

    override fun bindViews(model: RestaurantModel, adapterListener: AdapterListener) = with(binding) {
        if(adapterListener is RestaurantListListener){
            root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

}

