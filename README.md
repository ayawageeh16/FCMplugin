# FCMplugin

push notification plugin for android appsthat registers a device for Firebase (FCM) and store it by communicating with an API endpoint (provided)


<strong> *** Installation </strong>

<u> Step 1. Add the JitPack repository to your build file </u>

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
<u> Step 2. Add the dependency </u>

dependencies {
	        implementation 'com.github.ayawageeh16:FCMplugin:1.0.0'
	}
  
 <strong> *** Requirements </strong>
 
 Register MyFirebaseService in your manifest file:
 
            android:name=".service.MyFirebaseService"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
 
 
 <strong> *** Methods </strong>
 
 To get current token : FCMpluginInitializer.initialize(this);

 
 
 
