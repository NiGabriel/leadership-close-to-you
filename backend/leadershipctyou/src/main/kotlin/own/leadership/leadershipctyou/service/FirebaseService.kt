package own.leadership.leadershipctyou.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import org.springframework.stereotype.Service

@Service
class FirebaseService {

    fun sendPushNotification(fcmToken: String, title: String, message: String) {
        val msg = Message.builder()
            .putData("title", title)
            .putData("body", message)
            .setToken(fcmToken)
            .build()

        val response = FirebaseMessaging.getInstance().send(msg)
        println("Push sent. Response ID: $response")
    }
}
