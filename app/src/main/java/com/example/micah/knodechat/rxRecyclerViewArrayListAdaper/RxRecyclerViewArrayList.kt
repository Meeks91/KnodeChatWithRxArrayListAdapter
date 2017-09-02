package com.example.micah.knodechat.rxRecyclerViewArrayListAdaper

import android.app.Activity
import android.app.Fragment
import android.support.v7.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

/**
 * Created by Micah on 20/08/2017.
 */

class RxRecyclerViewArrayList<T>: ArrayList<T> {

   private val disposeBag = CompositeDisposable()
   private val dataSubject = PublishSubject.create<RxRecyclerViewArrayListDataUpdateHolder<T>>()

    constructor(): super()

    constructor(collection: MutableCollection<T>) : super(collection)

    override fun add(element: T): Boolean {

        //send data change to trigger recyclerView update
        dataSubject.onNext(RxRecyclerViewArrayListDataUpdateHolder(size - 1, true))

        return super.add(element)
    }

    override fun removeAt(index: Int): T {

        //send data change to trigger recyclerView update
        dataSubject.onNext(RxRecyclerViewArrayListDataUpdateHolder(index, false))

        return super.removeAt(index)
    }

    override fun add(index: Int, element: T) {

        //send data change to trigger recyclerView update
        dataSubject.onNext(RxRecyclerViewArrayListDataUpdateHolder(index, true))

        super.add(index, element)
    }

    override fun addAll(elements: Collection<T>): Boolean {

        dataSubject.onNext(RxRecyclerViewArrayListDataUpdateHolder(0, true))

        return super.addAll(elements)
    }

    /**
     *
     * Binds this RxRecyclerViewArrayList to the specified [rv]. Whenever the arrayList's data updates the
     *[onDataChange] callback is called and provides the viewHolder of the type specified the generic [VH].
     * The viewHolder can be updated by the data [element], which which will be a data element from an index of
     * of the RxRecyclerViewArrayList.
     */
    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified VH: RecyclerView.ViewHolder> bind(rv: RecyclerView, layout: Int, layoutConfig: LayoutConfig, crossinline onDataChange: (item: VH, element: T) -> Unit): CompositeDisposable  {

        //set up the recyclerView:
        val rvAdapter = RxArrayListRecyclerAdapter<VH, T>(this, layout, VH::class.constructors.first())

        rv.adapter = rvAdapter

        rv.layoutManager = layoutConfig.generateLayoutManagerUsing(rv.context)

        //send updates to the rvAdapter:
        dataSubject.subscribe { dataUpdateHolder ->

            getActivityFrom(rv).runOnUiThread {

                rvAdapter.notifyDataUpdateUsing(dataUpdateHolder)
            }

        }.addTo(disposeBag)


        //receive onBind calls from the rvAdapter:
        rvAdapter.layoutUpdateSubject.subscribe { update ->

            //send the onBind notification to the onDataChange callback
            onDataChange(update.default, this.get(update.position))

        }.addTo(disposeBag)

        return disposeBag
    }

    /**
     * returns the activity holding the [rv]. If the [rv] is direct in an activity
     * it returns it, else if the [rv] is in a fragment it gets the activity
     * from the fragment and returns it from there
     *
     */
    private fun getActivityFrom(rv: RecyclerView): Activity {

        if (rv.context is Activity)  return rv.context as Activity

        else return (rv.context as Fragment).activity
    }
}

