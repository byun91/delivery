package byun91.delivery.util.provider

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class DefaultResourceProvider(
    private val context: Context
) : ResourceProvider {
    override fun getString(@StringRes resId: Int): String  = context.getString(resId)

    override fun getString(@StringRes resId: Int, vararg formArgs: Any) : String
    = context.getString(resId, *formArgs) // *스타프로젝션 (vararg 파라미터에 가진 리스트나, 콜렉션의 요소를 넘긴다) ->스프레드연산자

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getColorStateList(@ColorRes resId: Int): ColorStateList
    = context.getColorStateList(resId)
}