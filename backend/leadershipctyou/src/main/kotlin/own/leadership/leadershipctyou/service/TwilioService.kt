package own.leadership.leadershipctyou.service

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TwilioService(
    @Value("\${twilio.account.sid}") private val sid: String,
    @Value("\${twilio.auth.token}") private val token: String,
    @Value("\${twilio.phone.number}") private val from: String
) {
    init {
        Twilio.init(sid, token)
    }



    fun sendSms(to: String, message: String) {

        Message.creator(
            com.twilio.type.PhoneNumber(to),
            com.twilio.type.PhoneNumber(from),
            message
        ).create()
    }
}