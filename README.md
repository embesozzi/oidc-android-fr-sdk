<p align="center">
  <h1 align="center">OIDC Android App with ForgeRock SDK</h1>
  <p align="center">
    <a href="#requirements">Requirements</a>
    ·
    <a href="#getting-started">Getting Started</a>
    ·
    <a href="#demostration">Demostration</a>
  </p>
  <hr/>
</p>


The Android Demo App implements OAuth 2.0 flow with the ForgeRock SDK in mode Centralize UI. It means that the app uses AppAuth for Android as a client SDK for communicating with ForgeRock AM instance. App Auth follows the best practices set out in [RFC 8252 - OAuth 2.0 for Native Apps](https://tools.ietf.org/html/rfc8252), including using [Custom Tabs](https://developer.chrome.com/multidevice/android/customtabs) for authorization requests.

## Requirements

* ForgeRock Identity Platform: Access Management (AM) 6.5.2+
* Android API level 21+: Android 5.0 (Lollipop), 6.0 (Marshmallow), 7.0 (Nougat), 8.0 (Oreo), 9.0 (Pie), 10.0, 11.0

## Getting Started

To try out Android demo perform these steps:

1. Setup an Access Management (AM) instance, as described in the **[Documentation](https://sdks.forgerock.com/android/01_prepare-am/)**.
2. Open the Android SDK project in [Android Studio](https://developer.android.com/studio).
3. Open `/app/src/main/res/values/strings.xml` and edit the values to match your AM instance.
4. On the **Run** menu, click **Run 'app'**.

## Demostration

1. App home:
<p align="center">
  <img src="https://github.com/embesozzi/oidc-android-fr-sdk/blob/main/screenshot-home.png" width="320" height="540" align="center" />
</p>

2. Custom Tab authentication process:
<p align="center">
  <img src="https://github.com/embesozzi/oidc-android-fr-sdk/blob/main/screenshot-idp-login.png" wwidth="320" height="540" align="center" />
</p>

3. User information:
<p align="center">
  <img src="https://github.com/embesozzi/oidc-android-fr-sdk/blob/main/screenshot-user.png" width="320" height="540" align="center" />
</p>

