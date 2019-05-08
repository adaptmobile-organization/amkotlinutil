package dk.adaptmobile.amkotlinutil.extensions

import okhttp3.Response
import okhttp3.ResponseBody

private fun Response.cloneResponseBody(): ResponseBody {
    return ResponseBody.create(this.body()!!.contentType(), this.peekBody(Long.MAX_VALUE).string())
}