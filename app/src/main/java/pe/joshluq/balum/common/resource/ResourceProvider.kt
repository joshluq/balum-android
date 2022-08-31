package pe.joshluq.balum.common.resource

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

}