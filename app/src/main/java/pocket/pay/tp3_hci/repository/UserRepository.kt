package pocket.pay.tp3_hci.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pocket.pay.tp3_hci.model.User
import pocket.pay.tp3_hci.network.UserRemoteDataSource
import pocket.pay.tp3_hci.network.model.NetworkCode
import pocket.pay.tp3_hci.network.model.NetworkRegister

class UserRepository (
    private val remoteDataSource: UserRemoteDataSource
) {
    private val currentUserMutex = Mutex()
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun verify(code: NetworkCode): User? {
        return remoteDataSource.verify(code).asModel()
    }

    suspend fun register(user: NetworkRegister) {
        remoteDataSource.register(user)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun getCurrentUser(refresh: Boolean): User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }
        return currentUserMutex.withLock { this.currentUser }
    }
}