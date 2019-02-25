package com.riningan.dota2heroes

import androidx.test.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.nio.charset.Charset


class RequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest) = MockResponse().apply {
        when {
            request.path.contains("/heroStats") -> setResponseCode(200).setBody(readAsset("heroStats.json"))
            else -> setResponseCode(404)
        }
    }


    private fun readAsset(name: String): String? {
        return try {
            val inputStream = InstrumentationRegistry.getContext().assets.open(name)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}