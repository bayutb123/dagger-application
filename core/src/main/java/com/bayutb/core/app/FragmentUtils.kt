package com.bayutb.core.app

import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry

fun Fragment.getParentNavBackStackEntry(): NavBackStackEntry {
    val parentId = requireActivity().navController().currentDestination!!.parent!!.id
    return requireActivity().navController().getBackStackEntry(parentId)
}