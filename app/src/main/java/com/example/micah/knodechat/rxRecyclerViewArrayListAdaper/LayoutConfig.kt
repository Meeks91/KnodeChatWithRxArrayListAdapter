package com.example.micah.knodechat.rxRecyclerViewArrayListAdaper

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
* Created by Micah on 27/08/2017.
*/

/**
 * Creates the type of layoutManager that should be assigned to the
 * recyclerView bound to the RxRecyclerViewArrayList.
 * The [orientation], [isStaggered] and [spanCount] are all used to calculate
 * the type of LayoutManager the LayoutConfig can make
 */
class LayoutConfig(val orientation: Orientation, val rowType: RowType, val spanCount: Int?) {

    init {

        //throw an error if the user has used a grid or staggered grid and not provided a spanCount
        if ((rowType == RowType.grid || rowType == RowType.staggered) && spanCount == null){

            throw Error("Layout config cannot be null if the row type is: ${rowType::name}")
        }
    }

    /**
     * Generates and returns a layoutManager for the bounded recyclerView. The type of layoutManager is
     * deciphered through the [layoutConfig]
     */
     fun generateLayoutManagerUsing(context: Context): RecyclerView.LayoutManager {

        //create the android version of orientation from our Orientation object
        val layoutOrientation = if (orientation == Orientation.horiontal) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL

                //create the appropriate type of LayoutManager depending on the rowType
                when(rowType) {

                    RowType.single -> return LinearLayoutManager(context, layoutOrientation, false)
                    RowType.grid -> return GridLayoutManager(context, spanCount!!, layoutOrientation, false)
                    RowType.staggered -> return StaggeredGridLayoutManager(spanCount!!, layoutOrientation)
             }
         }
    }


