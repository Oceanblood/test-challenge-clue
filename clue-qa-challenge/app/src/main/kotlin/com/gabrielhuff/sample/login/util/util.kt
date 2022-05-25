/**
 * General purpose utility to be used across the app.
 *
 * **Note**: Split into separate files if this grows
 */

package com.gabrielhuff.sample.login.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import com.gabrielhuff.sample.login.fragment.SimpleDialog
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.io.Reader

// Activities

/**
 * Show a dialog with the given params
 */
fun AppCompatActivity.showSimpleDialog(
        tag: String? = null,
        title: String,
        description: String,
        button: String
) {
    // Create dialog
    val dialog = SimpleDialog().apply {
        arguments = Bundle().apply {
            putString(SimpleDialog.ARG_TITLE, title)
            putString(SimpleDialog.ARG_DESCRIPTION, description)
            putString(SimpleDialog.ARG_BUTTON, button)
        }
    }

    // Show
    dialog.show(supportFragmentManager, tag)
}

/**
 * Start an activity with no extras, finishing if `finish` is true (default behaviour)
 */
inline fun <reified T : Activity> Activity.startActivity(finish: Boolean = true) {
    startActivity(Intent(this, T::class.java))
    if (finish) finish()
}

/**
 * Navigate up to the activity's parent
 */
fun Activity.navigateUp() = navigateUpTo(parentActivityIntent)

/**
 * Dismiss the soft keyboard if shown
 */
fun Activity.dismissKeyboard() {
    currentFocus?.windowToken?.let {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(it, 0)
    }
}

// Context

/**
 * Return true if a network connection is available or false otherwise
 */
fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = manager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

// Views

/**
 * A progress bar's relative progress, ranging from 0 to 1
 */
var ProgressBar.relativeProgress : Float
    get() = progress.toFloat() / max
    set(value) { progress = (value * max).toInt() }

/**
 * A consumer capable of setting the receiver's relative progress whenever invoked
 */
fun ProgressBar.relativeProgress() : Consumer<Float> = Consumer { relativeProgress = it }

// RxJava

/**
 * Subscribe to the receiver using the provided callbacks.
 *
 * We are not using RxKotlin's implementation because we need the subscription callback
 */
inline fun <T> Single<T>.subscribeBy(
        crossinline onSubscribe: (Disposable) -> Unit = { },
        crossinline onSuccess: (T) -> Unit = { },
        crossinline onError: (Throwable) -> Unit = { }
) {
    subscribe(object : SingleObserver<T> {
        override fun onSuccess(t: T) = onSuccess(t)

        override fun onSubscribe(d: Disposable) = onSubscribe(d)

        override fun onError(e: Throwable) = onError(e)
    })
}

/**
 * Subscribe to the receiver using the provided callbacks.
 *
 * We are not using RxKotlin's implementation because we need the subscription callback
 */
inline fun Completable.subscribeBy(
        crossinline onSubscribe: (Disposable) -> Unit = { },
        crossinline onComplete: () -> Unit = { },
        crossinline onError: (Throwable) -> Unit = { }
) {
    subscribe(object : CompletableObserver {
        override fun onComplete() = onComplete()

        override fun onSubscribe(d: Disposable) = onSubscribe(d)

        override fun onError(e: Throwable) = onError(e)
    })
}

// Fuel

/**
 * Return a [Single] that emits the input error generated by the [toError] callback in case the
 * response fails with the input [statusCode].
 *
 * If no such failure occurs, the entire result will be forwarded
 */
inline fun <T : Any> Single<Result<T, FuelError>>.mapError(
        statusCode: Int,
        crossinline toError: () -> Throwable
): Single<Result<T, FuelError>> = doOnSuccess {
        if ((it as? Result.Failure)?.error?.response?.statusCode == statusCode) throw toError()
}

/**
 * Return a [Single] that emits the input error generated by the [toError] callback in case the
 * response fails.
 *
 * If no such failure occurs, the result body will be forwarded (the error will be dropped since we
 * are defining the handling logic here).
 */
inline fun <T : Any> Single<Result<T, FuelError>>.mapAnyError(
        crossinline toError: () -> Throwable
): Single<T> = map { (it as? Result.Success)?.value ?: throw toError() }

/**
 * Add a token authorization header to the request with the format: `Authorization: Bearer <token>`
 */
fun Request.authenticateBearer(token: String): Request = header("Authorization" to "Bearer $token")

inline fun <reified T : Any> Gson.toResponseDeserializerOf() = object : ResponseDeserializable<T> {
    override fun deserialize(reader: Reader): T = fromJson<T>(reader, object : TypeToken<T>() {}.type)
}