package com.bayutb.core.app

import android.content.Context
import androidx.navigation.NavController

fun NavController.addGraphFromXML(context: Context, destName: String) {
    val navId = context.resources.getIdentifier("nav_graph", "navigation", context.packageName)
    val desGraphResult = this.navInflater.inflate(navId)
    this.graph.addDestination(desGraphResult)
}