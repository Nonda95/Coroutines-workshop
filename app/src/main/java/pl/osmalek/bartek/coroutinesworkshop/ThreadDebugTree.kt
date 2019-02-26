package pl.osmalek.bartek.coroutinesworkshop

import timber.log.Timber

class ThreadDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val threadTag = "${tag.orEmpty()} [${Thread.currentThread().name}]"
        super.log(priority, threadTag, message, t)
    }
}
