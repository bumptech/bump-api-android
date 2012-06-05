Bump API 3.0 for Android
============================

This is an all-new version of the Bump API for Android.

The corresponding iOS API can be found here: https://github.com/bumptech/bump-api-ios/.

Highlights
==========

* Significantly faster and more reliable: this API uses Bump's third-generation platform. The previous Android API used our 1.x platform.
* multiple concurrent sessions: once two users have matched, they can continue to send data to each other and other users
* at the moment breaks compatibility with earlier versions of the API... consider using a different API key just to be safe.

Release Notes
=============

Beta 4
------
* Bugfixes

Beta 3
------
* disableBumping now works
* constants provided for Intent extras  

Beta 2
------
* The Bump service stops itself when completely unbound.
* README's `android update project` example corrected with additional required parameter.

Getting Started
===============

A complete, working example project is contained in `bump-test`. 

1. Agree to the API license agreement and get your API key: http://bu.mp/apiagree

1. Run `android update` on both projects: `android update project -p bump-api-library/; android update project -p bump-test/;`

1. Bump logos and other assets are available here: http://bu.mp/bumpAPIinstructions . Please be aware of the trademark guidelines, to which you must agree before receiving an API key.

Reference the Bump library
-------------------------------------------
In your shell, run `android update project -t android-15 -l path_to/bump-api-library -p path_to_your_project/`


AndroidManifest.xml
-------------------

Add the Bump service to your `<application>`:

```xml
<service android:name="com.bump.api.BumpAPI">
    <intent-filter>
        <action android:name="com.bump.api.IBumpAPI" />
    </intent-filter>
</service>
```

Require the following permissions, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.VIBRATE" />   
```

Import
------
```java
import com.bump.api.IBumpAPI;
import com.bump.api.BumpAPIIntents;
```

Bind to the Bump service
------------------

Create and bind your `ServiceConnection` object:

```java
private IBumpAPI api;

private final ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName className, IBinder binder) {
        api = IBumpAPI.Stub.asInterface(binder);
        try {
            api.configure("your_api_key", "Bump User");
        } catch (RemoteException e) {}
    }
};

bindService(new Intent(IBumpAPI.class.getName()),
            connection, 
            Context.BIND_AUTO_CREATE);

```

Set up your BroadcastReceiver
-----------------

Listen for and filter on Bump intents:

```java
/* NOTE: A complete list of Intents sent, along with their Extras, is contained in bump-api-library/src/com/bump/api/IBumpAPI.aidl */

private final BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        try {
            if (action.equals(BumpAPIIntents.DATA_RECEIVED)) {
                Log.i("Bump Test", "Received data from: " + api.userIDForChannelID(intent.getLongExtra("channelID", 0)));
                Log.i("Bump Test", "Data: " + new String(intent.getByteArrayExtra("data")));
            } else if (action.equals(BumpAPIIntents.MATCHED)) {
                api.confirm(intent.getLongExtra("proposedChannelID", 0), true);
            } else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) {
                api.send(intent.getLongExtra("channelID", 0), "Hello, world!".getBytes());
            } else if (action.equals(BumpAPIIntents.CONNECTED)) {
                api.enableBumping();
            }
        } catch (RemoteException e) {}
    }
};

IntentFilter filter = new IntentFilter();
filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
filter.addAction(BumpAPIIntents.DATA_RECEIVED);
filter.addAction(BumpAPIIntents.NOT_MATCHED);
filter.addAction(BumpAPIIntents.MATCHED);
filter.addAction(BumpAPIIntents.CONNECTED);
registerReceiver(receiver, filter);

```

That's it!
----------

Your app is now bumpable!


Notes
=====

1. Use of this library is subject to both our SDK License agreement, http://bu.mp/licagr_internaluse, and Trademark Guidelines: http://bu.mp/apitrademark
1. This is a beta, please submit any and all comments and questions to tg@bu.mp
