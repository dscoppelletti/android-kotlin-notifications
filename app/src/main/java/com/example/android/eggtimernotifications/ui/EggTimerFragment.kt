/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.databinding.FragmentEggTimerBinding

class EggTimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentEggTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_egg_timer, container, false
        )

        val viewModel = ViewModelProvider(this).get(
                EggTimerViewModel::class.java)

        binding.eggTimerViewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        // TODO STEP 1.7 - Call create channel
        createChannel(
                getString(R.string.egg_notification_channel_id),
                getString(R.string.egg_notification_channel_name)
        )
        // TODO END STEP 1.7

        return binding.root
    }

    /* DOC STEP 2.4 - Change importance
    Once the channel is created, you, the developer, cannot change the
    importance of the channel. This is because the channel is now exposed to the
    user and they have full control over the importance of the channel.
    DOC END STEP 2.4 */

    private fun createChannel(channelId: String, channelName: String) {
        // TODO STEP 1.6 - Create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    // TODO STEP 2.4 - Change importance
                    // NotificationManager.IMPORTANCE_LOW
                    NotificationManager.IMPORTANCE_HIGH
                    // TODO END STEP 2.4
            )

                    /* DOC STEP 2.6
                    - OnePlus A3003, Android 9
                    Badges appear provided they are not disabled.
                    In [App Info|App notifications] "Allow notification dot" is
                    on anyway.

                    - Genymotion 2.14.0, Google Pixel 2, Android 8.0.0
                    Badges do not appear anyway.
                    In [App Info|App notifications] "Allow notification dot" is
                    on anyway.
                    DOC END STEP 2.6 */

            // TODO STEP 2.6 - Disable badges for this channel
                    .apply {
                        setShowBadge(false)
                    }
            // TODO END STEP 2.6

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time for breakfast"

            val notificationManager = requireActivity().getSystemService(
                    NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(notificationChannel)
        }
        // TODO END STEP 1.6
    }

    companion object {
        fun newInstance() = EggTimerFragment()
    }
}

