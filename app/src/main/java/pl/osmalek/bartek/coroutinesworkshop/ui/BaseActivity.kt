package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelChildren

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onDestroy() {
        coroutineContext.cancelChildren()
        super.onDestroy()
    }
}
