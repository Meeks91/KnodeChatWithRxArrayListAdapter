package com.example.micah.knodechat.chatActivity.view

import android.util.Log
import com.example.micah.knodechat.chatActivity.model.ChatSocketHelper
import com.example.micah.knodechat.rxBus.RxBus
import com.example.micah.knodechat.rxBus.RxBusNotificationType

/**
 * Created by Micah on 19/08/2017.
 */


class ChatActivityPresenter(val mChatActivityDelegate: ChatActivityDelegate, val mChatSocketHelper: ChatSocketHelper) {

    private val TAG = ChatActivityPresenter::class.simpleName

    init {

        initChatSocketConnection()
    }

    //MARK: ---------------------- INITIALISATION

    /* inits the connection to the chat socket using the mChatSocketHelper and RxBus */
    fun initChatSocketConnection(){

        //init the connection to the chat socket
        mChatSocketHelper.initChatConnection()

        //listen for chat messages using rxBus
        RxBus.bus.subscribe {

            //make sure this is a chat message
            if (it.type == RxBusNotificationType.messageReceived)

                    //notify the view we receievd a chat message
                    mChatActivityDelegate.onMessageReceived(it.data as String)
        }
    }

    //MARK: ---------------------- INITIALISATION

    //MARK: ---------------------- SENDING CHAT MESSAGES

    /* is called by the view to send chat messages */
    fun onSendMessage(messageET: String) {

        mChatSocketHelper.sendChatMessage(messageET)
    }

    //MARK: ---------------------- SENDING CHAT MESSAGES
}