# Android Stori Technical Test

On this project I developed a banking application with three main features that were user registration, user login and bank information. To achieve this, I used the following technologies:

- Kotlin
- Kotlin Coroutines
- Kotlin Test
- Jetpack Compose
- Koin Injection Framework
- MockK
- Coil
- Firebase

## 🔖 Features

On this section I document the features I worked on showing the path where the user sets everything correct or the system works as expected and the unhappy path where something went wrong.

### 🎯 Sign in

Happy Path | Unhappy Path
--- | --- 
<video src="https://github.com/user-attachments/assets/482cc2a7-5b95-4113-9cba-ab3ebc8527e1"> | <video src="https://github.com/user-attachments/assets/4cc8b28b-6c35-4559-a32c-fc31377cf754">


### 🎯 Sign up

Happy Path | Unhappy Path
--- | --- 
<video src="https://github.com/user-attachments/assets/e006aabd-f1db-4ab4-9268-5aa7faa0fdb0"> | <video src="https://github.com/user-attachments/assets/1c17a1be-d329-4b16-9329-e52d445d754c">

### 🎯 Home

Happy Path | Unhappy Path
--- | --- 
<video src="https://github.com/user-attachments/assets/68345f9c-2f60-4e1b-b9c6-d3daa1e7b214"> | <img src="https://github.com/user-attachments/assets/b35f555a-f617-422f-af3f-95cb71078bc9"  width="300">

In order to verify that the photo was loaded correctly to Firebase, I put a dialog that will display the saved image whenever we click on the email text on the app bar:

[Screen_recording_20241015_214448.webm](https://github.com/user-attachments/assets/db47c11d-ac3b-45c9-8b64-91af7633ad36)

## ✅ Testing

To test the sign in flow, we can use the following account:

```kt
email = miguel.alpizar@financy.mx
password = Password123!
```

We can verify the tests with either of these two methods:

- With the command `./gradlew test`

![image](https://github.com/user-attachments/assets/92911d43-be61-4ee3-a9eb-4bb15b0334ca)

- With the Android Studio run command `gradle test`

![image](https://github.com/user-attachments/assets/e9b44ccc-13b5-4e78-b75e-d6031897620f)
