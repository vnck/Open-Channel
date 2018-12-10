package g3_2.open_channel_app.chatbot

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import g3_2.open_channel_app.R
import kotlinx.android.synthetic.main.chatbot_main.*
import java.util.*


class MainChatbotActivity : AppCompatActivity() {

    companion object {
        private const val ACCESS_TOKEN = "46fd96a8281a4eed868fdb04cb50fde9"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbot_main)

//        val button: Button = findViewById(R.id.button2)
//        button.setOnClickListener {
//            val intent = Intent(this, LangChange::class.java).apply {
//                putExtra(EXTRA_MESSAGE, message)
//            }
//            startActivity(intent)
//        }


        FuelManager.instance.baseHeaders = mapOf(
                "Authorization" to "Bearer $ACCESS_TOKEN"
        )

        FuelManager.instance.basePath = "https://api.dialogflow.com/v1"

        FuelManager.instance.baseParams = listOf(
                "v" to "20170712",                  // latest protocol
                "sessionId" to UUID.randomUUID(),   // random Id
                "lang" to "en"                      // English language
        )

        val human = ChatUser(
                1,
                "You",
                BitmapFactory.decodeResource(resources,
                        R.drawable.ic_account_circle)
        )

        val agent = ChatUser(
                2,
                "Open Channel Agent",
                BitmapFactory.decodeResource(resources,
                        R.drawable.ic_account_circle)
        )

        my_chat_view.setOnClickSendButtonListener(View.OnClickListener {
                my_chat_view.send(Message.Builder()
                        .setUser(human)
                        .setRight(true)
                        .setText(my_chat_view.inputText)
                        .build()
                )

                my_chat_view.setInputTextHint("Type here")
                Fuel.get("/query",
                        listOf("query" to my_chat_view.inputText))
                        .responseJson { _, _, result ->
                            val reply = result.get().obj()
                                    .getJSONObject("result")
                                    .getJSONObject("fulfillment")
                                    .getString("speech")

                            my_chat_view.send(Message.Builder()
                                    .setUser(agent)
                                    .setText(reply)
                                    .build()
                            )
                            val intent: String? = result.get().obj()
                                    .getJSONObject("result")
                                    .optJSONObject("metadata")
                                    .optString("intentName")

//                if(intent!! == "WEIGHT") {
//                    val unitWeightName = result.get().obj()
//                            .getJSONObject("result")
//                            .getJSONObject("parameters")
//                            .getString("unit-weight-name")
//                    Log.d(TAG, "Line90")
//
//                    val unitWeight = result.get().obj()
//                            .getJSONObject("result")
//                            .getJSONObject("parameters")
//                            .getJSONObject("unit-weight")
//                            .getDouble("amount")
//
//                    val rresult = if(unitWeightName == "lb") {
//                        unitWeight * 2.20462
//                    } else {
//                        unitWeight / 2.20462
//                    }
//
//                    my_chat_view.send(Message.Builder()
//                            .setUser(agent)
//                            .setText("That's ${"%.2f".format(rresult)} $unitWeightName")
//                            .build()
//                    )
//                }
                        }

            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
