package com.example.micah.knodechat.rxRecyclerViewArrayListAdaper

/**
 * This model is used to tell the RxArrayListRecyclerAdapter how it should
 * update itself.
 *
 * The [indexOfUpdate] is the index of where the data is being added
 * into the RxRecyclerViewArrayList. It used so it can update itself
 * at the correct index.
 *
 * The [isUpdateAddingData] specifies if we're adding or removing data
 * in the RxArrayListRecyclerAdapter.
 */
class RxRecyclerViewArrayListDataUpdateHolder<T>(val indexOfUpdate: Int, val isUpdateAddingData: Boolean)