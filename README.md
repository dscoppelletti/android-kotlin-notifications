EggTimer - Final Code 
============================================================================

Solution code for Advanced Android with Kotlin Codelab 

Content: http://codelabs.developers.google.com/codelabs/advanced-android-kotlin-training-notifications

Introduction
------------

EggTimer is a timer app for cooking eggs.
You can start and stop the timer, choose different cooking intervals.

In this codelab, working from this starter app, you:

* Add notitications to the eggtimer app.
* Use channels and importance for the app notifications. 
* Customize and style the notifications.

Pre-requisites
--------------

You should be familiar with:

* Services, AlarmManager, Broadcast Receivers.

Lab path
--------

### 3. Getting started

1. [AlarmReceiver.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/receiver/AlarmReceiver.kt)
    
    `AlarmReceiver` is triggered by the `AlarmManager` to send the notification
    when the user-defined timer is up.

1. [SnoozeReceiver.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/receiver/SnoozeReceiver.kt)

    `SnoozeReceiver` handles the user click to snooze the notification.

1. [EggTimerFragment.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerFragment.kt)

    `EggTimerFragment` is part of the UI portion of the app.

1. [EggTimerViewModel.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerViewModel.kt)

    `EggTimerViewModel` is responsible for starting and canceling the timer and
    for other lifecycle-related app tasks.

1. [BindingUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/BindingUtils.kt)

    Binding adapters to enable data binding between the app UI and the
    ViewModel.

1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)

    Extension methods on the `NotificationManager`.

### 4. Add Notifications to your app

Using notifications is a great way to get the attention of your users to your
app. Whether your app is not running or running in the foreground, a
notification will show a popup window on top of the screen and may include sound 
or vibration. To create a notification, you need to use a notification builder
and provide a title text, a content text, and an icon. Once the builder has all
the necessary fields, NotificationManager, which is a system service, helps you
to display this content as a notification. NotificationManager is responsible
for sending a notification, updating its contents, and canceling the
notification.

#### 4.1. Create a basic Notification

1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 1.1 - Extension function to send messages
    * Step 1.2 - Get an instance of `NotificationCompat.Builder`
    * Step 1.3 - Set title, text and icon to builder
    * Step 1.4 - Call notify
1. [EggTimerViewModel.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerViewModel.kt)
    * Step 1.5 - Get an instance of `NotificationManager` and call
        `sendNotification`

#### 4.2. Notification Channels

Starting with API level 26, all notifications must be assigned to a channel. If
you tap and hold the app launcher icon, select app info, and tap notifications,
you will see a list of notification channels associated with the app. Right now
the list is empty because your app has not created any channels.

Channels represent a _type_ of notification — for example, your egg timer can
send a notification when the egg is cooked, and also use another channel to send
daily notifications to remind you to have eggs with your breakfast. All
notifications in a channel are grouped together, and users can configure
notification settings for a whole channel. This allows users to personalize
their notification settings based on the kind of notification they are
interested in. For example, your users can disable the breakfast notifications,
but still choose to see the notifications from the timer.

Developers set the initial settings, importance and behavior, to be applied to
all notifications in a channel. After you set the initial settings, users can
override these settings.

1. [EggTimerViewModel.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerViewModel.kt)
    * Step 1.6 - Create a channel
    * Step 1.7 - Call create channel

#### 4.3. Add notifications to your app

1. [AlarmReceiver.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/receiver/AlarmReceiver.kt)
    * Step 1.9 - Add call to `sendNotification`
    * Step 1.10 - Remove toast
1. [EggTimerViewModel.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerViewModel.kt)
    * Step 1.10 - Remove the notification code 

#### 4.4. Add a content intent

Showing the notification and informing the user is great, but when a user clicks
a notification, they expect to go back to the corresponding app.

An Intent is a messaging object you can use to request an action from another
app component. Intents can be used for starting an activity, a service, or
delivering a broadcast. In this case, you use this intent to tell the system to
open `MainActivity` when the user taps the notification. Since your app consists
of only a single view, you do not have many options here. However, in a larger
app, the notification should create a seamless experience by bringing the user
to a screen which makes sense for when they interact with the notification.

1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 1.11 - Create `Intent`
    * Step 1.12 - Create `PendingIntent`
    * Step 1.13 - Set content intent

#### 4.5. Cancel the notification

You have a functional egg timer with notifications, but there is a small issue.
If you set the timer, get a notification, and set the timer again, the previous
notification stays on the status bar while the new timer is running. This can
confuse your user if the app is in the background, and may result in undercooked
eggs.

To fix this, you need to clear the previous notification when you start a new
timer.

1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 1.14 - Cancel all notifications
1. [EggTimerViewModel.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerViewModel.kt)
    * Step 1.15 - Call cancel notification

### 5. Customizing Notifications

#### 5.1. Style your notification

Styling your notification according to your needs and the notification content
will make your notifications stand out and look more like an extension of your
application. The notification framework comes with several built-in styles to
help, and you can always create your own.

`NotificationCompat` offers built-in styles for:

* `BigTextStyle`, which can display a large block of text, such as showing the
    contents of an email when expanded.
* `BigPictureStyle`, which shows large-format notifications that include a large
    image attachment.
* `InboxStyle`, which shows a conversation style text content.
* `MediaStyle`, which shows controls for media playback.
* `MessagingStyle`, which shows large-format notifications that include multiple
    messages between any number of people.

1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 2.0 - Add style
    * Step 2.1 - Add style to builder

#### 5.2. Notification actions

Notification actions are another customization you can add to your
notifications. Your notifications currently redirect to your app when users
click on them. In addition to this default notification action, you can add
action buttons that complete an app-related task from the notification.

A notification can offer up to three action buttons that allow the user to
respond quickly, such as snooze a reminder or reply to a text message. These
action buttons should not duplicate the action performed when the user taps the
notification.

To add an action button, pass a `PendingIntent` to the `addAction()` function on
the builder. This is similar to setting up the notification's default tap action
by calling `setContentIntent()`, except instead of launching an activity, you
can do a variety of other things, for example, start a `BroadcastReceiver` that
performs a job in the background so the action does not interrupt the app that's
already open.

1. [SnoozeReceiver.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/receiver/SnoozeReceiver.kt)
    * Step 2.2 - Cancel all notifications
1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 2.2 - Add snooze action
    * Step 2.3 - Add snooze action

#### 5.3. Notification importance

Importance determines how much the notification should interrupt the user
visually and audibly. Notifications with higher importance will be more
interruptive to users.

You must specify the importance level in the `NotificationChannel` constructor. 
The importance level you assign to a channel applies to all notification
messages that you post to it.

| Importance (Android 8.0 and higher) | Priority (Android 7.1 and lower) | User-visible importance level |
| -- | -- | -- |
| `IMPORTANCE_HIGH` | `PRIORITY_HIGH`/`PRIORITY_MAX` | Makes a sound and appears as a heads-up notification (pops up at the top of the screen) |
| `IMPORTANCE_DEFAULT` | `PRIORITY_DEFAULT` | Make a sound |
| `IMPORTANCE_LOW` | `PRIORITY_LOW` | No sound |
| `IMPORTANCE_MIN` | `PRIORITY_MIN` |  No sound and does not appear in the status bar |

1. [EggTimerFragment.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerFragment.kt)
    * Step 2.4 - Change importance
1. [NotificationUtils.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/util/NotificationUtils.kt)
    * Step 2.5 - Set priority

#### 5.4. Notification badges

Notification badges are small dots that appear on the launcher icon of the
associated app when the app has an active notification. Users can long-press on
the app icon to reveal the notifications.

These dots, called _badges_, appear by default, and there's nothing your app
needs to do. However, there might be situations where badges don't make sense
for your notifications, so you can disable them on a per-channel basis.

1. [EggTimerFragment.kt](http://github.com/dscoppelletti/android-kotlin-notifications/blob/attend/app/src/main/java/com/example/android/eggtimernotifications/ui/EggTimerFragment.kt)
    * Step 2.6 - Disable badges for this channel

References
----------

* [Intents and Intent Filters](http://developer.android.com/guide/components/intents-filters)
* [Notification design guide](http://material.io/design/platform-guidance/android-notifications.html#settings)
