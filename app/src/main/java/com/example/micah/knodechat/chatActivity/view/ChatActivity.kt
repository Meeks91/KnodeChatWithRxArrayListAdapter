package com.example.micah.knodechat.chatActivity.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.micah.knodechat.ChatMessagesRVViewHolder
import com.example.micah.knodechat.R
import com.example.micah.knodechat.dagger.DaggerInjector
import com.example.micah.knodechat.rxRecyclerViewArrayListAdaper.LayoutConfig
import com.example.micah.knodechat.rxRecyclerViewArrayListAdaper.Orientation
import com.example.micah.knodechat.rxRecyclerViewArrayListAdaper.RowType
import com.example.micah.knodechat.rxRecyclerViewArrayListAdaper.RxRecyclerViewArrayList

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class ChatActivity : AppCompatActivity(), ChatActivityDelegate {

    private val TAG = ChatActivity::class.simpleName
    private val rxArrayList = RxRecyclerViewArrayList<String>()
    @Inject lateinit var chatActivityPresenter: ChatActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerInjector.configureInjectionFor(this).inject(this)

        initMessagesRecyclerView()
    }

    //MARK: -------------- INITIALISATION

    /**
     * inits the messagesRV by binding it to the rxArrayList
     */
    fun initMessagesRecyclerView(){

        rxArrayList.bind<ChatMessagesRVViewHolder>(messagesRV, R.layout.message_item, LayoutConfig(Orientation.vertical, RowType.single, 1)) { vh, message ->

            //set the messageTV text with the given message
            vh.messageTV.text = message
        }
    }

    //MARK: -------------- INITIALISATION

    //MARK: ----------------- INPUT ROUTING

    /**
     * Handles: sending the message via the present and
     * and clearing the messageET after it's been sent
     */
    fun onSendMessageClicked(view: View){

        //send the message stored in the messageET
        chatActivityPresenter.onSendMessage(messageET.text.toString())

        //clear the message editText
        messageET.text.clear()
    }

    //MARK: ----------------- INPUT ROUTING

    //MARK: ----------------- UPDATE UI LISTENERS

    /**
     *
     * called by the presenter when a message is received.
     * It updates the rxArrayList with the given message
     */
    override fun onMessageReceived(message: String) {

        rxArrayList.add(message)
    }
    //MARK: ----------------- UPDATE UI LISTENERS
}