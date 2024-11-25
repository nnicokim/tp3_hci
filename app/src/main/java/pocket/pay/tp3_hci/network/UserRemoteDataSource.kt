package pocket.pay.tp3_hci.network

import pocket.pay.tp3_hci.SessionManager
import pocket.pay.tp3_hci.network.api.UserApiService
import pocket.pay.tp3_hci.network.model.NetworkCode
import pocket.pay.tp3_hci.network.model.NetworkCredentials
import pocket.pay.tp3_hci.network.model.NetworkRegister
import pocket.pay.tp3_hci.network.model.NetworkUser

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun verify(code: NetworkCode): NetworkUser {
        return handleApiResponse { userApiService.verify(code) }
    }

    suspend fun register(user: NetworkRegister): NetworkUser {
        return handleApiResponse { userApiService.register(user) }
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}