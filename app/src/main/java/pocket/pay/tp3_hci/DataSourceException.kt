package pocket.pay.tp3_hci

class DataSourceException (
    var code: Int,
    message: String,
) : Exception(message)
