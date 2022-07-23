package com.afinaufal.androiddasar.afinaufalsubmission_1.mydi

import android.content.Context
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyDataLocalAppSource
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.myroom.MyRoomDB
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.MyAppRepository
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.remotedata.RemoteMyApps
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.AppExecutor
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.JsonRead

object MyInjection {
    fun provideToMyAppRepository(context: Context):MyAppRepository{
        val databaseApp = MyRoomDB.getInstance(context)

        val myRemoteApp = RemoteMyApps.getInstance(JsonRead(context))
        val localStorage = MyDataLocalAppSource.getInstance(databaseApp.myAppEntertaimentDao())
        val appExecutor = AppExecutor()

        return MyAppRepository.getInstance(myRemoteApp, localStorage, appExecutor)
    }
}