package pocket.pay.tp3_hci

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun loadAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    fun removeAuthToken() {
        val editor = preferences.edit()
        editor.remove(AUTH_TOKEN)
        editor.apply()
    }

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val PREFERENCES_NAME = "pocket.pay.tp3_hci"
        const val AUTH_TOKEN = "auth_token"
    }
}