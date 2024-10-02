package com.bayutb.core.app

import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController

fun Fragment.getParentNavBackStackEntry(): NavBackStackEntry {
    val parentId = findNavController().currentDestination!!.parent!!.id
    return findNavController().getBackStackEntry(parentId)
}