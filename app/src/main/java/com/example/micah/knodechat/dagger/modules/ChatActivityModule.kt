package com.example.micah.knodechat.dagger.modules

import android.content.Context
import com.example.micah.knodechat.chatActivity.model.ChatSocketHelper
import com.example.micah.knodechat.chatActivity.view.ChatActivity
import com.example.micah.knodechat.chatActivity.view.ChatActivityDelegate
import com.example.micah.knodechat.chatActivity.view.ChatActivityPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Micah on 26/08/2017.
 */

@Module (includes = arrayOf(SocketIOModule::class))

class ChatActivityModule(val mContext: Context){

    @Provides fun provideContext(): Context {

        return mContext
    }

    @Provides fun chatActivityPresenter(chatActivityDelegate: ChatActivityDelegate, chatSocketHelper: ChatSocketHelper): ChatActivityPresenter{

        return ChatActivityPresenter(chatActivityDelegate, chatSocketHelper)
    }

    @Provides fun ChatActivityViewDelegate(): ChatActivityDelegate{

        return mContext as ChatActivityDelegate
    }
}