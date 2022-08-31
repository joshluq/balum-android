package pe.joshluq.balum.common.resource

import android.content.Context
import androidx.annotation.StringRes
import java.lang.ref.WeakReference
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val weekContext: WeakReference<Context>,
) : ResourceProvider {

    override fun getString(@StringRes resId: Int) =
        weekContext.get()?.getString(resId) ?: ""

}