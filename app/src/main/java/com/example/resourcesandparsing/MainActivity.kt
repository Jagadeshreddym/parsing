package com.example.resourcesandparsing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.resourcesandparsing.ui.theme.ResourcesAndParsingTheme
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResourcesAndParsingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(getString(R.string.button_name))

                }
            }

            readSampleJSON()

            readArrayOfJsonObject()

            readJsonObjectWithArray()
        }
    }

    private fun readSampleJSON(): Unit {
        val file_name = "sample.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }

        val jsonObject = JSONObject(data)

        val name = jsonObject.getString("name")
        val age = jsonObject.getInt("age")
        val height = jsonObject.getDouble("height")

        Log.i("readSampleJSON", "name : $name || age : $age || height: $height")
    }


    private fun readArrayOfJsonObject(): Unit {

        val bufferReader = application.assets.open("android_version.json").bufferedReader()
        val json_string = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(json_string);

        for (i in 0..jsonArray.length() - 1) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

            val name = jsonObject.getString("name")
            val version = jsonObject.getString("version")

            Log.i("readArrayOfJsonObject", "name: $name || version : $version  \n")
        }
    }

    private fun readJsonObjectWithArray():Unit{
        try {
            val bufferReader = application.assets.open("user_list.json").bufferedReader()
            val json_string = bufferReader.use {
                it.readText()
            }
            val obj = JSONObject(json_string)
            val userArray = obj.getJSONArray("users")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                val name = userDetail.getString("name")
                val email = userDetail.getString("email")
                val contact = userDetail.getJSONObject("contact")
                val phoneNumber = contact.getString("mobile")

                Log.i("readSampleJSON", "name : $name || email : $email || phoneNumber: $phoneNumber")

            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier

    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ResourcesAndParsingTheme {
        Greeting("Android")
    }
}


