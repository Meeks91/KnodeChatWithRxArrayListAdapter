package com.example.micah.knodechat.dagger

import com.example.micah.knodechat.chatActivity.view.ChatActivity
import com.example.micah.knodechat.dagger.modules.ChatActivityModule
import dagger.Component

/**
 * Created by Micah on 26/08/2017.
 */

@Component (modules = arrayOf(ChatActivityModule::class))
interface AppComonent {

    fun inject(chatActivity: ChatActivity)
}